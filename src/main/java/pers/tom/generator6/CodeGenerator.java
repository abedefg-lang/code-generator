package pers.tom.generator6;

import lombok.Data;
import org.springframework.lang.NonNull;
import pers.tom.generator6.renderdata.RenderData;
import pers.tom.generator6.template.FileTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author lijia
 * @description 代码生成器
 * @date 2021-03-12 13:21
 */
@Data
public class CodeGenerator {

    /**当生成的文件已经存在时 是否可以重写*/
    private boolean rewritable;

    /**
     * 核心生成逻辑
     * @param template template
     * @param renderData renderData
     * @param writePath 文件写入路径
     */
    public void generate(@NonNull FileTemplate template,
                         @NonNull RenderData renderData,
                         @NonNull String writePath){

        String content = template.rendering(renderData.toMap());
        this.writeCode(writePath, content);
    }

    /**
     * 将代码写入到指定目录
     * @param writePath 写入路径
     * @param content 内容
     */
    private void writeCode(String writePath, String content){

        File file = new File(writePath);
        //当文件不存在 或者 允许重写的时候才写入
        if(!file.exists() || rewritable){
            //如果父级文件夹不存在 进行创建
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //写入文件
            try(FileWriter writer = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(writer)){
                bw.write(content);
                bw.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
