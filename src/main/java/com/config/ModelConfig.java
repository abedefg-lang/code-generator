package com.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 生成的模型
 * 使用了什么框架
 */
@Data
@Accessors(chain = true)
public class ModelConfig {

    /**是否使用了lombok*/
    private boolean lombokModel;

    /**是否使用了spring*/
    private boolean springModel;

    /**是否使用了mybatisPlus*/
    private boolean mybatisPlusModel;
}
