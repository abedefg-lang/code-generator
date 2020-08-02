package com.generator;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 生成的项目模型
 * 使用了什么框架
 */
@Data
@Accessors(chain = true)
public class ModelConfig {

    /**是否使用了spring*/
    private boolean springModel;

    /**是否使用了mybatisPlus*/
    private boolean mybatisPlusModel;
}
