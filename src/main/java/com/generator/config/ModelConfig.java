package com.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 生成的项目模型
 */
@Data
@Accessors(chain = true)
public class ModelConfig {

    /**是否使用了spring*/
    private boolean springModel;

    /**是否使用了mybatisPlus*/
    private boolean mybatisPlusModel;

    /**是否启用swagger2*/
    private boolean enableSwagger2;
}
