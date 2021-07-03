package pers.tom.generator6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pers.tom.generator6.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

/**
 * @author tom
 * @description 写入处理器
 * @date 2021/7/3 20:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public abstract class WriteResultHandler implements GenerateResultHandler{

    /**输出根路径 默认是当前项目路径*/
    protected String outputRootPath = System.getProperty("user.dir");

    /**当文件已存在时是否允许覆盖*/
    protected boolean overwrite;

    @Override
    public void handle(Template template, Map<String, Object> param, Object result) {

        //获取输出路径
        String writePath = this.getWritePath(template, param);
        File file = new File(writePath);
        //当文件不存在 或者 允许覆盖的时候才写入
        if(!file.exists() || overwrite){
            //如果父级文件夹不存在 进行创建
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //写入文件
            try(FileWriter writer = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(writer)){
                bw.write(result.toString());
                bw.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    protected abstract String getWritePath(Template template, Map<String, Object> param);

}
