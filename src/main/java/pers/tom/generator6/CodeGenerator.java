package pers.tom.generator6;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import pers.tom.generator6.config.AuthorConfig;
import pers.tom.generator6.template.Template;

import java.util.*;

/**
 * @author tom
 * @description 代码生成器
 * @date 2021/6/12 23:31
 */
@Data
@Accessors(chain = true)
public class CodeGenerator {

    /**模板map key : templateName , value : template*/
    private final Map<String, Template> templateMap;

    /**作者相关配置*/
    private AuthorConfig authorConfig;

    /**生成结果处理器*/
    private GenerateResultHandler resultHandler;

    public CodeGenerator(){
        this.templateMap = new LinkedHashMap<>();
    }

    /**
     * 添加模板 如果模板名存在会进行覆盖
     * @param template template
     */
    public CodeGenerator addTemplate(@NonNull Template template){

        Assert.notNull(template, "template 不能为null");
        templateMap.put(template.getName(), template);
        return this;
    }

    /**
     * 判断是否存在指定名称的模板
     * @param templateName template name
     * @return boolean
     */
    public boolean hasTemplate(String templateName){
        return templateMap.containsKey(templateName);
    }

    /**
     * 获取指定名称的模板
     * @param templateName template name
     * @return template
     */
    public Template getTemplate(String templateName){

        return templateMap.get(templateName);
    }

    /**
     * 删除指定名称的模板
     * @param templateName template name
     * @return 被删除的模板
     */
    public Template removeTemplate(String templateName){

        return templateMap.remove(templateName);
    }

    /**
     * 获取所有的template
     * @return templates
     */
    public List<Template> getTemplates(){

        return Collections.unmodifiableList(new ArrayList<>(templateMap.values()));
    }

    /**
     * 获取map格式的template
     * @return template map
     */
    public Map<String, Template> getTemplateMap(){

        return Collections.unmodifiableMap(templateMap);
    }

    /**
     * 核心生成逻辑
     * @param param 生成参数
     */
    public void generate(Map<String, Object> param){

        // 属性检测
        Assert.notNull(resultHandler, "resultHandler 不能为null");

        // 开始生成
        System.out.println("=========== 准备生成 ============");
        List<Template> templates = getTemplates();
        for(Template template : templates){
            System.out.println("=========== 开始生成 "+template.getName()+" 模板 ==========");
            // 生成
            String result = template.render(param);
            // 处理结果
            resultHandler.handle(template, param, result);
        }
        System.out.println("=========== 生成结束 ============");
    }


    /**
     * 生成结果处理器
     */
    public interface GenerateResultHandler{

        /**
         * 处理生成结果
         * @param template template
         * @param param param
         * @param result 生成结果
         */
        void handle(Template template, Map<String, Object> param, String result);
    }

}
