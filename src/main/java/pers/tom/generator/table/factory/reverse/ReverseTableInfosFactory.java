package pers.tom.generator.table.factory.reverse;

import cn.hutool.core.lang.ClassScanner;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;
import pers.tom.generator.table.TableInfo;
import pers.tom.generator.table.factory.TableInfosFactory;
import pers.tom.generator.table.factory.reverse.parser.EntityClassParser;
import pers.tom.generator.template.TemplateRenderConfig;
import pers.tom.generator.template.interceptor.TemplateRenderInterceptor;

import java.util.*;

/**
 * @author tom
 * @description 逆向的tableInfos工厂  只有实体类还没有表 需要通过解析实体类获取tableInfo
 *              由于entity已经存在了 只需要直接从mapper开始生成
 *              但是会需要entity的包名与文件名  需要在渲染前添加进entity的包名
 * @date 2021/1/7 13:37
 */
@Data
@Accessors(chain = true)
public class ReverseTableInfosFactory implements TableInfosFactory, TemplateRenderInterceptor {

    /**需要生成的entityClass*/
    private Set<Class<?>> entityClasses;

    /**将tableInfo中的className与Class关联*/
    private Map<String, Class<?>> entityClassMap;

    /**实体类解析器*/
    private EntityClassParser entityParser;

    /**需要知道其它模板使用获取entity信息用的id*/
    private String entityTemplateId;


    public ReverseTableInfosFactory(){

        //初始化默认entityTemplateId
        this.entityTemplateId = "entity";
        this.entityClasses = new HashSet<>();
    }

    @Override
    public List<TableInfo> getTableInfos() {

        Objects.requireNonNull(entityParser, "entityParser不能为null");
        if(!CollectionUtils.isEmpty(entityClasses)){

            this.entityClassMap = new HashMap<>(entityClasses.size());

            //开始解析
            TableInfo table;
            List<TableInfo> tableInfos = new ArrayList<>();
            for(Class<?> entityClass : entityClasses){
                table = entityParser.parse(entityClass);
                if(table != null){

                    //table中的className与当前class关联
                    entityClassMap.put(table.getClassName(), entityClass);
                    tableInfos.add(table);
                }
            }
            return tableInfos;
        }
        return Collections.emptyList();
    }

    /**
     * 添加entityClasses
     * @param entityClasses entityClasses
     */
    public ReverseTableInfosFactory addEntityClasses(Class<?>... entityClasses){
        this.entityClasses.addAll(Arrays.asList(entityClasses));
        return this;
    }

    /**
     * 扫描指定包下的全部class
     * @param basePackages basePackages
     */
    public ReverseTableInfosFactory scan(String... basePackages){

        if(basePackages != null){
            for(String basePackage : basePackages){
                this.entityClasses.addAll(ClassScanner.scanPackage(basePackage));
            }
        }
        return this;
    }


    @Override
    public boolean preRender(TemplateRenderConfig config) {

        //通过当前渲染的表获取出对应的class
        TableInfo table = config.getTable();
        Class<?> entityClass = entityClassMap.get(table.getClassName());

        //如果该entityClass不存在不进行生成
        if(entityClass != null){
            config.addPackage(entityTemplateId, entityClass.getPackage().getName());
            config.addFileName(entityTemplateId, entityClass.getSimpleName());
            return true;
        }
        return false;
    }
}
