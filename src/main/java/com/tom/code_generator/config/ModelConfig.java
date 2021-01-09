package com.tom.code_generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tom
 * @description 模型对象
 * @date 2021/1/4 22:38
 */
@Data
@Accessors(chain = true)
@Deprecated
public class ModelConfig {

    /**是否使用了spring*/
    private boolean springModel;

    /**是否使用了mybatisPlus*/
    private boolean mybatisPlusModel;

    /**是否启用swagger2*/
    private boolean enableSwagger2;

    /**
     * 获取默认的modelConfig
     * @return 返回对象
     */
    public static ModelConfig getDefaultModelConfig(){
        return new ModelConfig()
                .setSpringModel(true);
    }
}
