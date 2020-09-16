package com.generator.builder;

import com.alibaba.druid.pool.DruidDataSource;
import com.generator.config.GlobalConfig;
import com.generator.config.ModelConfig;
import com.generator.TemplateCodeGenerator;
import com.tablesource.TableSource;
import com.tablesource.dialect.AbstractDialect;
import com.tablesource.dialect.Dialect;
import com.tablesource.dialect.MySqlDialect;
import com.tablesource.nameconverter.NameConverter;
import com.template.TemplateConfig;
import com.utils.TypeMappingUtil;
import com.utils.file.FileUtils;
import com.utils.reflect.ReflectUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 解析xml文件中的配置来builder TemplateCodeGenerator
 * 并且传递的xml路径是classPath形式的
 */
@SuppressWarnings("unchecked")
public class XmlTemplateCodeGeneratorBuilder extends TemplateCodeGeneratorBuilder {

    /**xml中的跟标签*/
    protected Element context;


    public XmlTemplateCodeGeneratorBuilder(File generatorXml){
        try{
            SAXReader reader = new SAXReader();
            context = reader.read(generatorXml).getRootElement().element("context");
            codeGenerator = new TemplateCodeGenerator();
        }catch (DocumentException d){
            d.printStackTrace();
        }
    }

    public XmlTemplateCodeGeneratorBuilder(String path){
        this(FileUtils.createFile(path));
    }


    @Override
    protected void buildGlobal() throws Exception {
        GlobalConfig global = new GlobalConfig();
        //进行注入
        ReflectUtils.simpleInject(global, parseAttribute(context));
        codeGenerator.setGlobal(global);
    }

    @Override
    protected void buildTableInfos() throws Exception {
        Element tables = context.element("tables");
        if(tables != null) {
            //创建TableSource
            TableSource tableSource = new TableSource();
            //设置属性
            ReflectUtils.simpleInject(tableSource, parseAttribute(tables));
            tableSource.setNameConverter(buildNameConverter(tables.element("nameConverter")));
            tableSource.setDialect(buildDialect(tables.element("dialect")));
            //获取tableInfos
            codeGenerator.setTableInfos(tableSource.getTableInfos());
        }
    }

    /**
     * 构建dataSource
     * @param dialect dialect标签
     * @return 返回dataSource对象
     */
    private Dialect buildDialect(Element dialect) throws Exception {
        //获取dataSource标签  创建
        Element dataSource = dialect.element("dataSource");
        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName(dataSource.attributeValue("driver"));
        source.setUrl(dataSource.attributeValue("url"));
        source.setUsername(dataSource.attributeValue("username"));
        source.setPassword(dataSource.attributeValue("password"));

        String dialectClass = dialect.attributeValue("class");
        //如果class为null 创建mysql
        AbstractDialect ad = StringUtils.isEmpty(dialectClass) ? new MySqlDialect(source) : (AbstractDialect) Class.forName(dialectClass).getConstructor(DataSource.class).newInstance(source);
        //注入属性
        ReflectUtils.simpleInject(ad, parseProperties(dialect));
        return ad;
    }

    /**
     * 构建nameConverter
     * @param nameConverter nameConverter标签
     * @return 返回nameConverter对象
     */
    private NameConverter buildNameConverter(Element nameConverter) throws Exception {
        if(nameConverter != null){
            //获取类型
            String className = nameConverter.attributeValue("class");
            NameConverter converter = (NameConverter) Class.forName(className).newInstance();
            //对属性进行注入
            ReflectUtils.simpleInject(converter, parseProperties(nameConverter));
            return converter;
        }
        //如果为null 返回nothing
        return NameConverter.NONE;
    }

    @Override
    protected void buildModel() throws Exception {
        Element element = context.element("model");
        ModelConfig model = new ModelConfig();
        //当element不为null的时候进行注入
        if(element != null){
            ReflectUtils.simpleInject(model, parseAttribute(element));
        }
        codeGenerator.setModel(model);
    }

    @Override
    protected void buildTemplates() throws Exception {
        Element templates = context.element("templates");
        if(templates != null) {
            //获取所有template标签
            List<Element> list = templates.elements("template");

            Map<String, String> attributeMap;
            TemplateConfig config;
            for(Element template : list){
                //解析属性配置
                attributeMap = parseAttribute(template);
                //通过配置的name 到工厂获取config
                config = TemplateConfig.SimpleFactory.get(attributeMap.get("name"));
                //注入属性
                ReflectUtils.simpleInject(config, attributeMap);
                config.setPropertyMap(parseProperties(template));
                codeGenerator.putTemplateConfig(config);
            }
        }
    }

    @Override
    protected void parseTypeMappings() throws Exception {
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
     * 解析一个标签中的属性  并且将属性的名字和值 以Map的形式返回
     * @param element 需要解析的元素
     * @return Map
     */
    protected Map<String, String> parseAttribute(Element element){
        Objects.requireNonNull(element, "element 不能为null");
        List<Attribute> list = element.attributes();
        Map<String, String> map = new HashMap<>();
        for(Attribute attribute : list){
            map.put(attribute.getName(), attribute.getValue());
        }
        return map;
    }

    /**
     * 解析一个标签下的所有property标签
     */
    protected Map<String, String> parseProperties(Element parent){
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
