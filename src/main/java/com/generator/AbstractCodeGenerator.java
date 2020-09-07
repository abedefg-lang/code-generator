package com.generator;

import com.generator.config.GlobalConfig;
import com.tablesource.entity.TableInfo;
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

    /**全局的配置信息*/
    protected GlobalConfig global;

    /**需要生成的表的信息*/
    protected List<TableInfo> tableInfos;

    /**
     * 将代码写入指定的位置
     * @param code  生成的代码
     * @param path  写入的路径
     */
    protected void writeCode(String code, String path){
        //创建文件
        File file = FileUtils.createFile(path);
        //当file不存在时 或者 允许覆盖文件的时候才进行执行
        if (!file.exists() || global.isOverwriteFile()){
            //如果文件夹不存在  进行创建
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //写入代码
            FileUtils.write(file, code, false);
        }
    }

    /**
     * 获取父级的写入路径  所有的文件都写入到这个路径下
     * outPath + parentPackage
     * @return 返回字符串
     */
    protected String getParentWritePath(){
        return global.getOutRootPath()+"\\"+global.getParentPackage().replace(".", "\\");
    }
}
