package pers.tom.generator6;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import pers.tom.generator6.config.AuthorConfig;
import pers.tom.generator6.exception.CodeGeneratorException;
import pers.tom.generator6.interceptor.RenderInterceptor;
import pers.tom.generator6.template.Template;

import java.util.*;

/**
 * @author tom
 * @description 代码生成器
 * @date 2021/6/12 23:31
 */
public class CodeGenerator {

    /**模板*/
    private final Map<String, Template> templateMap;

    /**分组模板*/
    private final Map<String, List<Template>> groupTemplateMap;

    /**作者相关配置*/
    private AuthorConfig authorConfig;

    /**渲染拦截器*/
    private List<RenderInterceptor> interceptors;

    public CodeGenerator(){
        this.templateMap = new HashMap<>(8);
        this.groupTemplateMap = new HashMap<>(4);
        this.init();
    }

    /**
     * 初始化方法
     * 交给子类实现
     */
    protected void init(){

    }

    /**
     * 指定模板进行生成
     * @param param 渲染参数
     * @param resultHandler 渲染结果处理器
     * @param templateNames 需要使用的模板
     */
    public void generate(Map<String, Object> param, GenerateResultHandler resultHandler, String... templateNames){

        List<Template> templates = new ArrayList<>(templateNames.length);
        for(String name : templateNames){
            templates.add(this.obtainTemplate(name));
        }
        this.doBatchGenerate(param, resultHandler, templates);
    }

    /**
     * 指定分组进行生成
     * @param param 渲染参数
     * @param resultHandler 渲染结果处理器
     * @param groupName 需要使用的分组
     */
    public void generateByGroup(Map<String, Object> param, GenerateResultHandler resultHandler, String groupName){

        this.doBatchGenerate(param, resultHandler, groupTemplateMap.get(groupName));
    }

    /**
     * 在每次渲染的时候 会向渲染数据中添加内部的数据
     * @param param 渲染参数
     * @return 添加内部值之后的param
     */
    public Map<String, Object> addInternalRenderData(Map<String, Object> param){

        return param;
    }

    /**
     * 添加模板
     * @param template 模板
     * @param groupNames 分组名称 可以为空
     */
    public CodeGenerator addTemplate(@NonNull Template template,
                                     String... groupNames){
        //参数检测
        Assert.notNull(template, "template 不能为null");

        String templateName = template.getName();
        if(templateMap.containsKey(templateName)){
            throw new CodeGeneratorException("该模板已存在 templateName : " + templateName);
        }
        //添加到map
        templateMap.put(templateName, template);

        //添加到groupMap
        for(String groupName : groupNames){
            List<Template> templates = groupTemplateMap.computeIfAbsent(groupName, k -> new ArrayList<>());
            templates.add(template);
        }

        return this;
    }

    /**
     * 获取指定名称的模板
     * @param name name
     * @return template
     */
    public Template getTemplate(String name){
        return templateMap.get(name);
    }

    /**
     * 获取指定名称的模板 如果没有找到模板抛出异常
     * @param name name
     * @return template
     */
    public Template obtainTemplate(String name){
        Template template = this.getTemplate(name);
        if(template == null){
            throw new CodeGeneratorException("找不到该模板 templateName : " + name);
        }
        return template;
    }

    /**
     * 获取所有模板
     * @return template map
     */
    public Map<String, Template> getTemplateMap(){
        return Collections.unmodifiableMap(this.templateMap);
    }

    /**
     * 获取指定分组下的全部模板
     * @param groupName 分组名称
     * @return templates
     */
    public List<Template> getGroupingTemplates(String groupName){
        List<Template> templates = groupTemplateMap.get(groupName);
        return templates == null ? Collections.emptyList() : Collections.unmodifiableList(templates);
    }

    /**
     * 获取所有分组模板
     * @return group template map
     */
    public Map<String, List<Template>> getGroupingTemplateMap(){
        return Collections.unmodifiableMap(groupTemplateMap);
    }

    /**
     * 设置作者配置
     * @param authorConfig author config
     */
    public CodeGenerator setAuthorConfig(AuthorConfig authorConfig){
        this.authorConfig = authorConfig;
        return this;
    }

    /**
     * 获取作者配置
     * @return author config
     */
    public AuthorConfig getAuthorConfig() {
        return authorConfig;
    }

    /**
     * 添加拦截器
     * @param interceptor interceptor
     */
    public CodeGenerator addInterceptor(@NonNull RenderInterceptor interceptor){

        //参数检测
        Assert.notNull(interceptor, "interceptor 不能为null");

        if(interceptors == null){
            interceptors = new ArrayList<>();
        }
        interceptors.add(interceptor);

        return this;
    }

    /**
     * 获取所有拦截器
     * @return interceptors
     */
    public List<RenderInterceptor> getInterceptors(){
        return CollectionUtils.isEmpty(interceptors) ? Collections.emptyList() : Collections.unmodifiableList(this.interceptors);
    }

    /**
     * 批量生成
     * @param templates 需要生成的模板
     * @param param 渲染参数
     * @param resultHandler 生成结果处理器
     */
    private void doBatchGenerate(Map<String, Object> param, GenerateResultHandler resultHandler, List<Template> templates){

        Assert.notNull(resultHandler, "resultHandler不能为null");

        System.out.println("==========准备生成==========");
        if(!CollectionUtils.isEmpty(templates)){
            for(Template template : templates){
                //前置
                if(applyPreRender(template, param)){
                    System.out.println("==========开始生成 "+template.getName()+" 模板==========");
                    //渲染
                    Object result = template.render(param);
                    //后置
                    applyPostRender(template, param, result);
                    //处理结果
                    resultHandler.handle(template, param, result);
                }
            }
        }
        System.out.println("==========生成结束==========");
    }

    /**
     * 执行前置渲染
     * @param template 当前渲染的模板
     * @param param 渲染参数
     * @return 返回是否执行后续渲染
     */
    private boolean applyPreRender(Template template, Map<String, Object> param){
        if(!CollectionUtils.isEmpty(interceptors)){
            for(RenderInterceptor interceptor : interceptors){
                if(!interceptor.preRender(template, param)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 执行后置渲染
     * @param template 当前已成功渲染的模板
     * @param param 渲染参数
     * @param result 渲染结果
     */
    private void applyPostRender(Template template, Map<String, Object> param, Object result){
        if(!CollectionUtils.isEmpty(interceptors)){
            for(RenderInterceptor interceptor : interceptors){
                interceptor.postRender(template, param, result);
            }
        }
    }

}
