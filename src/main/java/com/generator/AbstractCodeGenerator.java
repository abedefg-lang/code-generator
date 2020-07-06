package com.generator;

import com.tablesource.info.TableInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.*;
import java.util.List;

/**
 * 生成代码的逻辑有很多种
 * 但是有很多配置 比如需要生成的表  写入的路径等都是相同的
 * 还有不管是生成的逻辑的怎样最后都要落地到文件  也就是需要写入文件的逻辑
 * 将这些重合的部分放到这个类中
 */
@Data
@Accessors(chain = true)
public abstract class AbstractCodeGenerator implements CodeGenerator{

    /**代码写入的根路径*/
    protected String outRootPath = System.getProperty("user.dir");

    /**代码存放的父级包*/
    protected String parentPackage = "com";

    /**当文件已经存在时  是否重写文件*/
    protected boolean overwriteFile;

    /**需要生成的表的信息*/
    protected List<TableInfo> tableInfos;

    /**
     * 将代码写入指定的位置
     * @param code  生成的代码
     * @param path  写入路径
     */
    protected void writeCode(String code, String path){
        System.out.println(path);
        File file = new File(path);
        //判断执行写入逻辑的条件
        //当file不存在时 或者 overwriteFile为true的时候
        if (!file.exists() || overwriteFile){
            //如果文件夹不存在  进行创建
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //开始写入内容
            try (FileOutputStream outStream = new FileOutputStream(file);
                 OutputStreamWriter writer = new OutputStreamWriter(outStream);
                 BufferedWriter sw = new BufferedWriter(writer)) {
                sw.write(code);
                sw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
