package pers.tom.generator.mvc;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import pers.tom.generator.basic.BatchCodeGenerator;
import pers.tom.generator.basic.JavaFileWritePathGetter;
import pers.tom.generator.basic.renderdata.RenderData;
import pers.tom.generator.basic.template.Template;
import pers.tom.generator.mvc.config.MvcNamingStyleConfig;
import pers.tom.generator.mvc.config.MvcPackageConfig;
import pers.tom.generator.mvc.config.MvcTemplateConfig;
import pers.tom.generator.mvc.renderdata.*;
import pers.tom.generator.mvc.table.TableInfo;
import pers.tom.generator.mvc.table.TableInfoFactory;

import java.util.Collection;


/**
 * @author tom
 * @description
 * @date 2021/3/14 11:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MvcCodeGenerator extends BatchCodeGenerator {

    /**写入路径获取器*/
    private FileWritePathGetter writePathGetter;

    /**命名风格配置*/
    private MvcNamingStyleConfig namingStyleConfig;

    /**包配置*/
    private MvcPackageConfig packageConfig;

    /**mvc模板配置*/
    private MvcTemplateConfig templateConfig;

    /**table info工厂*/
    private TableInfoFactory tableInfoFactory;

    /**
     * 生成逻辑
     */
    public void generate(){

        this.preGenerate();

        //获取需要生成的表 进行生成
        Collection<TableInfo> tableInfos = tableInfoFactory.get();
        if(!CollectionUtils.isEmpty(tableInfos)){
            for(TableInfo table : tableInfos){
                this.doGenerateSingleTable(table);
            }
        }
    }

    /**
     * 生成前置操作
     */
    protected void preGenerate(){
        //检查非null属性
        Assert.notNull(templateConfig, "templateConfig不能为null");
        Assert.notNull(tableInfoFactory, "tableInfoFactory不能为null");

        //检查可以为null属性 并设置默认值
        if(namingStyleConfig == null){
            namingStyleConfig = MvcNamingStyleConfig.getDefaultConfig();
        }
        if(packageConfig == null){
            packageConfig = MvcPackageConfig.getDefaultConfig();
        }
        if(writePathGetter == null){
            writePathGetter = new JavaFileWritePathGetter(System.getProperty("user.dir"));
        }
    }

    /**
     * 生成单个表相关的数据
     * @param table table
     */
    protected void doGenerateSingleTable(TableInfo table){

        //生成entity
        EntityRenderData entity = new EntityRenderData(packageConfig.getEntityPackage(), namingStyleConfig.getEntityName(table.getClassName()), table);
        this.doGenerate(templateConfig.getEntityTemplate(), entity);

        //生成mapper
        MapperRenderData mapper = new MapperRenderData(packageConfig.getMapperPackage(), namingStyleConfig.getMapperName(table.getClassName()), entity);
        this.doGenerate(templateConfig.getMapperTemplate(), mapper);

        //生成service
        ServiceRenderData service = new ServiceRenderData(packageConfig.getServicePackage(), namingStyleConfig.getServiceName(table.getClassName()), mapper);
        this.doGenerate(templateConfig.getServiceTemplate(), service);

        //生成serviceImpl
        ServiceImplRenderData serviceImpl = new ServiceImplRenderData(packageConfig.getServiceImplPackage(), namingStyleConfig.getServiceImplName(table.getClassName()), service);
        this.doGenerate(templateConfig.getServiceImplTemplate(), serviceImpl);

        //生成controller
        ControllerRenderData controller = new ControllerRenderData(packageConfig.getControllerPackage(), namingStyleConfig.getControllerName(table.getClassName()), service);
        this.doGenerate(templateConfig.getControllerTemplate(), controller);
    }


    private void doGenerate(Template template, RenderData renderData){
        if(template != null && renderData != null){
            super.generate(template, renderData, writePathGetter.getWritePath(renderData));
        }
    }

}
