package pers.tom.generator3.task.template;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tom
 * @description 模板配置
 * @date 2021/2/22 22:43
 */
@Data
@Accessors(chain = true)
public class TemplateInfo {

    /**模板名称*/
    private String name;
}
