package com.generator.builder;

import com.generator.TemplateCodeGenerator;
import com.template.BasicTemplate;
import com.template.TemplateConfig;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.tablesource.TableSource;
import com.tablesource.TableSourceImpl;
import com.tablesource.info.TableInfo;
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


/**
 * 构建templateCodeGenerator
 */
@SuppressWarnings("unchecked")
public class TemplateCodeGeneratorBuilder implements CodeGeneratorBuilder {

    /**存放基础的模板*/
    private static final Map<String, TemplateConfig> basicTemplateMap;

    /**需要构建的生成器*/
    private TemplateCodeGenerator codeGenerator;

    /**根标签*/
    private Element root;

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
            root = reader.read(url).getRootElement();
            //创建实例对象
            codeGenerator = new TemplateCodeGenerator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TemplateCodeGenerator build() {
        try{
            buildBasicConfig();
            buildModelConfig();
            buildTemplates();
        }catch (Exception e){
            e.printStackTrace();
        }
        return codeGenerator;
    }

    /**
     * 构建基本的配置   输入路径,父级包,是否覆盖文件,表信息
     * */
    private void buildBasicConfig() throws Exception {
        Element tables = root.element("tables");
        //获取属性
        Map<String, String> map = this.parseAttribute(tables);
        //注入属性配置
        ReflectUtils.simpleInject(codeGenerator, map);
        //获取tableInfos
        //首先需要创建TableSource
        TableSource tableSource = new TableSourceImpl(buildDataSource(tables.element("dataSource")));
        //查看是否需要转换成驼峰式
        boolean convertCamel = "true".equals(map.get("convertCamel"));
        //全表生成  优先于 指定生成
        boolean allTables = "true".equals(map.get("allTables"));
        String[] tableNames = map.containsKey("tableNames") ? map.get("tableNames").split(",") : new String[0];
        List<TableInfo> tableInfos = allTables ? tableSource.getAll(convertCamel) : tableSource.getTableInfos(convertCamel, tableNames);
        codeGenerator.setTableInfos(tableInfos);
    }


    /**
     * 构建dataSource
     * */
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
     * 构建model配置
     * */
    private void buildModelConfig() throws Exception {
        Element tables = root.element("tables");
        //获取Model标签
        Element model = tables.element("model");
        if(model != null){
            //进行注入
            ReflectUtils.simpleInject(codeGenerator.getModel(), parseAttribute(model));
        }
    }

    /**
     * 构建模板配置
     * */
    private void buildTemplates() throws Exception {
        Element tables = root.element("tables");
        //获取templates标签
        Element templates = tables.element("templates");
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
                //进行注入
                ReflectUtils.simpleInject(config, attributeMap);
                //解析property标签并且进行设置
                config.setPropertyMap(parseProperties(template));
            }
        }
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
