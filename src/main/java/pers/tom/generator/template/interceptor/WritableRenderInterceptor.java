package pers.tom.generator.template.interceptor;

import pers.tom.generator.config.GlobalConfig;
import pers.tom.generator.template.TemplateInfo;
import pers.tom.generator.template.TemplateRenderContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author tom
 * @description 可写入的拦截器  在渲染之后实现写入文件
 * @date 2021/1/8 22:36
 */
public class WritableRenderInterceptor implements TemplateRenderInterceptor {

    /**
     * 渲染前可以通过包名 文件名拼接出写入路径
     * 判断是否能够写入文件(当文件不存在 或者 可以覆盖文件时)
     * @param config 渲染配置
     */
    @Override
    public boolean preRender(TemplateRenderContext config) {
        GlobalConfig global = config.getGlobal();
        File writeFile = new File(this.getOutputPath(config));

        //当文件不存在 或者 可以覆盖文件时才进行渲染
        return !writeFile.exists() || global.isOverwriteFile();
    }

    @Override
    public void postRender(Object content, TemplateRenderContext config) {

        //获取输出路径
        File writeFile = new File(this.getOutputPath(config));

        //如果父文件夹不存在进行创建
        if(!writeFile.getParentFile().exists()){
            writeFile.getParentFile().mkdirs();
        }

        //写入文件
        try(FileWriter writer = new FileWriter(writeFile);
            BufferedWriter bw = new BufferedWriter(writer)){
            bw.write(content.toString());
            bw.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取数据路径
     * @param param 参数
     * @return 返回路径
     */
    private String getOutputPath(TemplateRenderContext param){

        //获取包名 文件名
        GlobalConfig global = param.getGlobal();
        TemplateInfo template = param.getTemplate();
        String templateId = template.getId();
        String packageInfo = param.getPackage(templateId);
        String fileName = param.getFileName(templateId);

        //拼接输出路径
        String separator = File.separator;
        return global.getOutputRootPath()+separator+packageInfo.replace(".", separator)+separator+fileName+"."+template.getTargetFileSuffix();
    }
}
