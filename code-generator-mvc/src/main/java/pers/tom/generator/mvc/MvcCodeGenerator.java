package pers.tom.generator.mvc;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;
import pers.tom.generator.basic.BatchCodeGenerator;
import pers.tom.generator.basic.template.FileTemplate;
import pers.tom.generator.mvc.config.MvcNamingStyleConfig;
import pers.tom.generator.mvc.config.MvcPackageConfig;
import pers.tom.generator.mvc.config.MvcTemplateConfig;

import pers.tom.generator.mvc.table.TableInfoFactory;


/**
 * @author tom
 * @description
 * @date 2021/3/14 11:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MvcCodeGenerator extends BatchCodeGenerator {

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

        //生成entity

    }


    /**
     * 在执行生成逻辑前检查参数
     */
    private void preGenerate(){

        Assert.notNull(templateConfig, "templateConfig不能为null");
        Assert.notNull(tableInfoFactory, "tableInfoFactory不能为null");

        if(namingStyleConfig == null){
            namingStyleConfig = MvcNamingStyleConfig.getDefaultConfig();
        }
        if(packageConfig == null){
            packageConfig = MvcPackageConfig.getDefaultConfig();
        }

    }


//    private Collection<EntityRenderData> buildEntities(){
//
//        Collection<TableInfo> tableInfos = tableInfoFactory.get();
//        List<EntityRenderData> entities = new ArrayList<>(tableInfos.size());
//        EntityRenderData entity;
//        for(TableInfo table : tableInfos){
//            entity = new EntityRenderData(packageConfig.getEntityPackage(), namingStyleConfig.getEntityName(table.getClassName()));
//            entity.setTable(table);
//            entities.add(entity);
//        }
//        return entities;
//    }
//
//    private Collection<MapperRenderData> buildMappers(Collection<EntityRenderData> entities){
//
//        List<MapperRenderData> mappers = new ArrayList<>(entities.size());
//        MapperRenderData mapper;
//        for(EntityRenderData entity : entities){
//            mapper = new MapperRenderData(packageConfig.getMapperPackage(), namingStyleConfig.getMapperName(entity.getTable().getClassName()));
//            mapper.setEntity(entity);
//            mappers.add(mapper);
//        }
//        return mappers;
//    }

//    private Collection<ServiceRenderData> buildServices(Collection<MapperRenderData> mappers){
//
//    }
//
//    private Collection<ControllerRenderData> buildControllers(Collection<ServiceRenderData> services){
//
//    }

    private void doGenerate(){

        //生成entity
        FileTemplate entityTemplate = templateConfig.getEntityTemplate();
        if(entityTemplate != null){

        }

    }


}
