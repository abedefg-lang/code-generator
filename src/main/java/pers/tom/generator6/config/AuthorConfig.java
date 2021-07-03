package pers.tom.generator6.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tom
 * @description 作者配置
 * @date 2021/7/3 0:37
 */
@Data
@Accessors(chain = true)
public class AuthorConfig {

    /**作者名*/
    private String name;

    /**邮箱*/
    private String email;

    /**电话*/
    private String telephone;
}
