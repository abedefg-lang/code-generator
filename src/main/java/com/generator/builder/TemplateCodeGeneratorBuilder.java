package com.generator.builder;


import com.config.SimpleTemplateFactory;
import com.config.TemplateConfig;
import com.generator.TemplateCodeGenerator;
import com.generator.VelocityEngineCodeGenerator;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.tablesource.TableSource;
import com.tablesource.TableSourceImpl;
import com.tablesource.info.TableInfo;
import com.utils.ReflectUtils;
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
public class TemplateCodeGeneratorBuilder implements CodeGeneratorBuilder {

    /**需要构建的生成器*/
    private TemplateCodeGenerator codeGenerator;

    /**根标签*/
    private Element root;


    public TemplateCodeGeneratorBuilder(String classPath){
        try {
            //解析传递进来的文件路径
            SAXReader reader = new SAXReader();
            //获取url
            URL url = Thread.currentThread().getContextClassLoader().getResource(classPath);
            Objects.requireNonNull(url, "找不到文件: " + classPath);
            root = reader.read(url).getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TemplateCodeGenerator build() {
        try{
            createInstance();
            buildBasicConfig();
            buildModelConfig();
            buildTemplates();
        }catch (Exception e){
            e.printStackTrace();
        }
        return codeGenerator;
    }

    /**
     * 创建实例
     * */
    private void createInstance() throws Exception{
        //查看有没有配置type标签
        Element type = root.element("type");
        //如果没有配置使用Velocity模板引擎
        codeGenerator = (type == null) ? new VelocityEngineCodeGenerator() : (TemplateCodeGenerator) Class.forName(type.getTextTrim()).newInstance();
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
        //获取property内容
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
            for(Element template : list){
                TemplateConfig config = buildTemplate(template);
                if(config != null) {
                    codeGenerator.putTemplateConfig(config);
                }
            }
        }
    }

    /**解析单个template标签*/
    private TemplateConfig buildTemplate(Element template) throws Exception {
        TemplateConfig config = null;
        //判断传递进来的是否是template标签
        if(template != null && "template".equals(template.getName())){
            Map<String, String> map = this.parseAttribute(template);
            //首先通过name属性到SimpleTemplateFactory工厂查看 有没有这个config
            config = SimpleTemplateFactory.getTemplateConfig(map.get("name"));
            //如果工厂中没有进行创建
            if(config == null){
                config = new TemplateConfig();
            }
            //解析attribute  进行注入
            ReflectUtils.simpleInject(config, map);
            //解析property标签并且进行设置
            config.setPropertyMap(parseProperties(template));
        }
        return config;
    }


    /**解析一个标签的属性 封装成一个Map*/
    private Map<String, String> parseAttribute(Element element){
        Map<String, String> map = null;
        if(element != null){
            List<Attribute> list = element.attributes();
            map = new HashMap<>(list.size());
            for(Attribute attribute : list){
                map.put(attribute.getName(), attribute.getValue());
            }
        }
        return map;
    }

    /**解析一个标签下的所有property标签*/
    private Map<String, String> parseProperties(Element parent){
        Map<String, String> map = null;
        if(parent != null){
            //获取所有property标签
            List<Element> properties = parent.elements("property");
            map = new HashMap<>(properties.size());
            for(Element property : properties){
                //获取name属性  value属性
                map.put(property.attributeValue("name"), property.attributeValue("value"));
            }
        }
        return map;
    }

}
