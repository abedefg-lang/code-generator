package pers.tom.generator.template.interceptor;

import pers.tom.generator.config.GlobalConfig;
import pers.tom.generator.table.TableInfo;
import pers.tom.generator.template.TemplateInfo;
import pers.tom.generator.template.TemplateRenderConfig;
import pers.tom.generator.utils.NameUtils;

import java.time.LocalDate;


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

        config.addParam("NameUtils", NameUtils.class);
        config.addParam("date", LocalDate.now().toString());
        return true;
    }

}
