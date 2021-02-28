package pers.tom.generator3.task.data;

import lombok.Data;

import java.util.Map;

/**
 * @author lijia
 * @description 模板渲染数据
 * @date 2021/2/22 22:39
 */
@Data
public abstract class TemplateRenderData {

    /**标识该数据的名称*/
    private String name;

    /**数据的分组名称  */
    private String groupName;

    /**
     * 转换成map
     * @return 返回map
     */
    public abstract Map<String, Object> toMap();
}
