package com.tom.code_generator.template.interceptor;

import com.tom.code_generator.config.GlobalConfig;
import com.tom.code_generator.table.TableInfo;
import com.tom.code_generator.template.TemplateInfo;
import com.tom.code_generator.template.TemplateRenderConfig;
import com.tom.code_generator.utils.NameUtils;


/**
 * @author tom
 * @description 完善渲染配置拦截器   在渲染前需要完善必要的参数(包名 文件名)
 * @date 2021/1/8 22:30
 */
public class ImproveRenderConfigInterceptor implements TemplateRenderInterceptor {

    @Override
    public boolean preRender(TemplateRenderConfig config) {
        GlobalConfig globalConfig = config.getGlobal();

        //获取当前渲染的表与模板  添加package fileName
        TableInfo tableInfo = config.getTable();
        TemplateInfo templateConfig = config.getTemplate();
        String templateId = templateConfig.getId();

        //获取文件名 包名
        String packageInfo = templateConfig.getCompletePackage(globalConfig.getParentPackage());
        String fileName = templateConfig.getRealFileName(tableInfo.getClassName());
        config.addPackage(templateId, packageInfo);
        config.addFileName(templateId, fileName);

        //可能会需要处理名字
        config.addParam("NameUtils", NameUtils.class);
        return true;
    }
}
