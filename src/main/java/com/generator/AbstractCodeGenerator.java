package com.generator;

import com.tablesource.info.TableInfo;
import com.utils.file.FileUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.*;
import java.util.List;

/**
 * 生成代码的逻辑有很多种
 * 但是有很多配置是固定的 比如需要生成的表  写入的路径...
 * 还有不管是生成的逻辑的怎样最后都要写入到文件
 */
@Data
@Accessors(chain = true)
public abstract class AbstractCodeGenerator implements CodeGenerator{

    /**代码写入的根路径  默认是项目的根路径*/
    protected String outRootPath = System.getProperty("user.dir");

    /**代码存放的父级包  默认时以com为父级包*/
    protected String parentPackage = "com";

    /**当文件已经存在时  是否重写文件*/
    protected boolean overwriteFile;

    /**作者*/
    protected String author = "";

    /**需要生成的表的信息*/
    protected List<TableInfo> tableInfos;

    /**
     * 将代码写入指定的位置
     * @param code  生成的代码
     * @param posteriorPath  后半段路径  由于这个类中可以获取到outRootPath parentPackage
     *                      所以对于一个文件路径来说  会变化的就是后面的部分
     */
    protected void writeCode(String code, String posteriorPath){
        //拼接全路径
        String path = outRootPath+"\\"+parentPackage.replace(".", "\\")+"\\"+posteriorPath;
        File file = new File(path);
        //判断执行写入逻辑的条件
        //当file不存在时 或者 overwriteFile为true的时候
        if (!file.exists() || overwriteFile){
            //如果文件夹不存在  进行创建
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //开始写入内容
            FileUtils.write(file, code, false);
        }
    }
}
