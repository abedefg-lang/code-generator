package pers.tom.generator3.handler;

import lombok.Data;

import pers.tom.generator3.task.TemplateRenderTask;
import pers.tom.generator3.task.data.TemplateRenderData;
import pers.tom.generator3.task.template.TemplateInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author lijia
 * @description 可写入文件的结果处理器
 * @date 2021-02-23 16:34
 */
@Data
public class WritableRenderResultHandler implements TemplateRenderResultHandler{

    /**输出文件夹路径*/
    private String outputFolderPath;

    /**当文件已存在时 是否能够覆盖文件*/
    private boolean overwriteAble;

    public WritableRenderResultHandler(String outputFolderPath){
        this.outputFolderPath = outputFolderPath;
    }

    @Override
    public void handle(TemplateRenderTask renderTask) {

        String path = this.getWritePath(renderTask);
        File file = new File(path);
        //如果父文件夹不存在进行创建
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        //当文件不存在 或者 可以覆盖文件时才写入
        if(!file.exists() || overwriteAble){
            try(FileWriter writer = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(writer)){
                bw.write(renderTask.getRenderResult().toString());
                bw.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private String getWritePath(TemplateRenderTask renderTask){

        TemplateInfo templateInfo = renderTask.getTemplateInfo();
        TemplateRenderData renderData = renderTask.getRenderData();
        return outputFolderPath + File.separator + templateInfo.getTargetFolder() + File.separator + renderData.getName() + "." + templateInfo.getTargetFileFormat();
    }
}
