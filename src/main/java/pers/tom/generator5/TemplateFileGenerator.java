package pers.tom.generator5;

import lombok.Data;
import lombok.NonNull;

import pers.tom.generator5.renderdata.factory.RenderDataCollectionFactory;
import pers.tom.generator5.template.FileTemplate;

import java.util.List;

/**
 * @author tom
 * @description 代码生成器
 * @date 2021/3/11 22:00
 */
public class CodeGenerator {

    /**渲染任务集合*/
    private List<GenerateTask> taskList;

    /**当文件已存在时是否可以重写文件*/
    private boolean rewritable;

    /**
     * 核心生成逻辑
     */
    public void generate(){


    }


    /**
     * 生成任务对象
     */
    @Data
    public static class GenerateTask{

        /**需要生成的模板*/
        @NonNull
        private FileTemplate<?> template;

        /**对应渲染数据集合工厂*/
        @NonNull
        private RenderDataCollectionFactory<?> factory;

    }
}
