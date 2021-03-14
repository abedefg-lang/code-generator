package pers.tom.generator5;

import lombok.Data;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import pers.tom.generator5.renderdata.RenderData;
import pers.tom.generator5.renderdata.factory.RenderDataCollectionFactory;
import pers.tom.generator5.renderresult.FileRenderResult;
import pers.tom.generator5.template.FileTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author tom
 * @description 模板文件生成器
 * @date 2021/3/11 22:00
 */
@Data
public class TemplateFileGenerator {

    /**生成任务集合*/
    private List<GenerateTask<? extends RenderData>> taskList;

    /**当文件已存在时是否可以重写文件*/
    private boolean rewritable;

    /**
     * 核心生成逻辑
     */
    public void execute(){

        if(!CollectionUtils.isEmpty(taskList)){

        }
    }

    /**
     * 添加生成任务
     * @param task task
     */
    public void addGenerateTask(@NonNull GenerateTask<?> task){
        if(taskList == null){
            taskList = new ArrayList<>();
        }
        taskList.add(task);
    }


    /**
     * 生成任务对象
     */
    @Getter
    public static class GenerateTask<DATA extends RenderData>{

        /**需要生成的模板*/
        private final FileTemplate<DATA> template;

        /**对应渲染数据集合工厂*/
        private final RenderDataCollectionFactory<DATA> factory;

        public GenerateTask(@NonNull FileTemplate<DATA> template,
                            @NonNull RenderDataCollectionFactory<DATA> factory) {
            this.template = template;
            this.factory = factory;
        }

        public List<FileRenderResult> execute(){

            Collection<DATA> collection = factory.get();
            List<FileRenderResult> resultList = new ArrayList<>(collection.size());
            for(DATA data : collection){
                resultList.add(template.rendering(data));
            }
            return resultList;
        }
    }
}
