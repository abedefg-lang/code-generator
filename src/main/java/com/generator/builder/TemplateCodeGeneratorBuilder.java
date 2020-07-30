package com.generator.builder;

import com.generator.CodeGenerator;
import com.generator.TemplateCodeGenerator;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.tablesource.TableSource;
import com.tablesource.TableSourceImpl;
import com.tablesource.converter.NameConverter;
import com.tablesource.info.TableInfo;
import com.template.BasicTemplate;
import com.template.TemplateConfig;
import com.utils.TypeMappingUtil;
import com.utils.reflect.ReflectUtils;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class TemplateCodeGeneratorBuilder implements CodeGeneratorBuilder{

    /**存放基础的模板*/
    private static Map<String, TemplateConfig> basicTemplateMap;

    /**codeGenerator*/
    private TemplateCodeGenerator codeGenerator;

    /**context标签*/
    private Element context;

    static {
        //初始化map
        BasicTemplate[] basicTemplates = BasicTemplate.values();
        basicTemplateMap = new HashMap<>(basicTemplates.length);
        for(BasicTemplate bt : basicTemplates){
            basicTemplateMap.put(bt.getConfig().getName(), bt.getConfig());
        }
    }

    public TemplateCodeGeneratorBuilder(String classPath){
        try {
            //解析传递进来的文件路径
            SAXReader reader = new SAXReader();
            //获取url
            URL url = Thread.currentThread().getContextClassLoader().getResource(classPath);
            Objects.requireNonNull(url, "找不到文件: " + classPath);
            context = reader.read(url).getRootElement().element("context");
            //创建实例对象
            codeGenerator = new TemplateCodeGenerator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建基本的信息
     */
    private void builderBasicConfig() throws Exception {
        //注入属性
        ReflectUtils.simpleInject(codeGenerator, parseAttribute(context));
    }

    /**
     * 解析typeMapping标签进行注册
     */
    private void registerTypeMapping() throws ClassNotFoundException {
        Element typeMappings = context.element("typeMappings");
        if(typeMappings != null){
            //获取子标签
            List<Element> typeMappingList = typeMappings.elements("typeMapping");
            Map<String, String> map;
            for(Element element : typeMappingList){
                map = parseAttribute(element);
                TypeMappingUtil.register(map.get("dbType"), Class.forName(map.get("javaType")));
            }
        }
    }

    /**
     * 解析tables标签  创建tableInfo
     */
    private void builderTableInfos() throws Exception {
        Element tables = context.element("tables");
        //解析属性
        Map<String, String> map = parseAttribute(tables);
        //通过配置创建转换器
        NameConverter converter = !map.containsKey("nameConverter") ? NameConverter.NOTHING_CONVERTER : (NameConverter) Class.forName(map.get("nameConverter")).newInstance();
        //获取dataSource标签
        TableSource tableSource = new TableSourceImpl(buildDataSource(tables.element("dataSource")));
        //全表生成  优先于 指定生成
        boolean allTables = "true".equals(map.get("allTables"));
        String[] tableNames = map.containsKey("tableNames") ? map.get("tableNames").split(",") : new String[0];
        List<TableInfo> tableInfos = allTables ? tableSource.getAll(converter) : tableSource.getTableInfos(converter, tableNames);
        codeGenerator.setTableInfos(tableInfos);
    }

    /**
     * 构建dataSource
     */
    private DataSource buildDataSource(Element dataSource){
        Objects.requireNonNull(dataSource, "必须配置dataSource");
        //获取属性的内容
        Map<String, String> attributeMap = parseAttribute(dataSource);
        //创建DataSource 默认使用MysqlDataSource
        MysqlDataSource source = new MysqlDataSource();
        source.setUrl(attributeMap.get("url"));
        source.setUser(attributeMap.get("user"));
        source.setPassword(attributeMap.get("password"));
        return source;
    }


    /**
     * 解析并创建templates
     */
    private void builderTemplates() throws Exception {
        //获取templates标签
        Element templates = context.element("templates");
        if(templates != null){
            //获取所有template标签
            List<Element> list = templates.elements("template");
            //循环解析
            Map<String, String> attributeMap;
            TemplateConfig config;
            for(Element template : list){
                //解析参数
                attributeMap = this.parseAttribute(template);
                //通过名字判断是否是基础模板  如果不是创建一个新的模板配置类
                config = basicTemplateMap.containsKey(attributeMap.get("name")) ? basicTemplateMap.get(attributeMap.get("name")) : new TemplateConfig();
                //注入属性
                ReflectUtils.simpleInject(config, attributeMap);
                //解析property标签并且进行设置
                config.setPropertyMap(parseProperties(template));
                codeGenerator.putTemplateConfig(config);
            }
        }
    }

    @Override
    public CodeGenerator build() {
        try{
            registerTypeMapping();
            builderBasicConfig();
            builderTableInfos();
            builderTemplates();
        }catch (Exception e){
            e.printStackTrace();
        }
        return codeGenerator;
    }

    /**
     * 解析一个标签的属性 封装成一个Map
     */
    private Map<String, String> parseAttribute(Element element){
        List<Attribute> list = element.attributes();
        Map<String, String> map = new HashMap<>(list.size());
        for(Attribute attribute : list){
            map.put(attribute.getName(), attribute.getValue());
        }
        return map;
    }

    /**
     * 解析一个标签下的所有property标签
     */
    private Map<String, String> parseProperties(Element parent){
        //获取所有property标签
        List<Element> properties = parent.elements("property");
        Map<String, String> map = new HashMap<>(properties.size());
        for(Element property : properties){
            //获取name属性  value属性
            map.put(property.attributeValue("name"), property.attributeValue("value"));
        }
        return map;
    }
}
