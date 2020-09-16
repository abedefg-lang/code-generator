# code-generator
代码生成器
根据数据库中的表信息，生成指定的组件代码，项目本身提供Entity，Dao，Service，ServiceImp，Controller，可以随意选择自己需要的模板。

可以使用正则表达式匹配表名，不同风格的代码(MybatisPlus风格，使用Lombok，Swagger2等)。

项目支持功能的扩展，可以根据自己需求修改新增组件模板，也可以修改已有模板，可以自定义渲染模板的模板引擎，对不同的数据库产品也有较好的支持

在使用方面可以通过xml配置相关信息
```xml
<?xml version="1.0" encoding="UTF-8" ?>

<generator xmlns="http://www.w3school.com.cn">
    <!--outRootPath：代码最外层包的父级  比如 如果项目是maven结构 需要配置src\main\java-->
    <!--parentPackage：代码统一存放的父级包 包全名-->
    <context outRootPath="" parentPackage="">
        
        <!--allTables：生成所有表  优先级最高-->
        <!--namePatterns,ignoreNamePatterns 都是使用正则进行匹配  当一张表没有匹配成功ignoreNamePatterns并且匹配成功namePatterns才会生成相关代码-->
        <tables namePatterns="" 
                ignoreNamePatterns=""
                allTables="">
          
            <dialect>
                <dataSource driver="" url="" username="" password=""/>
            </dialect>

        </tables>
       
        <templates>
            ...
            <!--name: 组件模板的名称  项目本身提供基本的模板(entity,dao,service,serviceImpl,controller) 如果需要使用这些模板  只需要配置对应的name-->
            <!--templateClassPath：使用到的模板的类路径  需要在后缀名前加上生成文件的后缀  比如entity.java.vm  这个模板就是用来生成java文件的-->
            <!--targetFileName：生成的文件名 比如想要这个组件前面加上Test前缀 可以配置targetFileName="Test#{className}" -->
            <!--targetPackage：存放代码的包  并不是全名 比如想要存放dao组件的包名叫mapper 可以配置 targetPackage="mapper"-->
            <!--engine：引擎-->
            <template name="" templateClassPath="" targetFileName="" targetPackage="" engine="">
            </template>
            ...
        </templates>
      
        ...
    </context>
</generator>
```
对于在生成的时候修改数据库字段命名方式  可以配置以下下内容
```xml
 <tables namePatterns="sys_.*">
            ...
          
            <nameConverter class="com.tablesource.nameconverter.CamelNameConverter">
                <!--是否转换为驼峰式-->
                <property name="toCamel" value="true"/>
                <!--需要去掉的前缀   如果有多个用英文逗号分割-->
                <property name="tablePrefixes" value="sys_,abc"/>
            </nameConverter>
            
          ...  
  </tables> 
```
在类型转化方面 如果需要进行一些特别的转化可以配置一下内容
```xml
<generator xmlns="http://www.w3school.com.cn">

    <context>
        ...
      
        <typeMappings>
            <typeMapping dbType="int" javaType="java.lang.Integer"/>
            ...
        </typeMappings>
      
        ...  
    </context>
</generator>
```
可以配置model  生成不同风格的代码
```xml
<generator xmlns="http://www.w3school.com.cn">

    <context>
        ...
        <!--springModel：会生成spring相关的基本注解 @Controller @Service @Autowired-->
        <!--mybatisPlusModel：将不会生成dao，service，serviceImpl中的基本方法  换成继承或实现相关接口或父类   
            并且会在entity模板中生成相关注解@TableName  @TableId  @TableFiled-->
        <!--enableSwagger2：生成相关注解-->
        <model springModel="" mybatisPlusModel="" enableSwagger2=""/>
        ...  
    </context>
</generator>
```
通过以下java代码进行生成
```java
public static void main(String[] args) throws Exception {

        CodeGeneratorBuilder builder = new XmlTemplateCodeGeneratorBuilder("classpath:generator.xml");
        CodeGenerator codeGenerator = builder.build();

        codeGenerator.generate();
}
```
