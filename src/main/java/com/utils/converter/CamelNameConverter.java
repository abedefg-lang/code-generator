package com.utils.converter;

import com.utils.NameUtils;
import lombok.Data;

/**
 * 驼峰式的转换器
 */
@Data
public class CamelNameConverter implements NameConverter{

    private static final String REGEX = "_";

    /**是否转化为驼峰式*/
    private boolean camel;

    @Override
    public String toClassName(String str) {
        return camel ? NameUtils.convertClassName(str, REGEX) : NameUtils.initialUppercase(str);
    }

    @Override
    public String toPropertyName(String str) {
        return camel ? NameUtils.convertCamel(str, REGEX) : str;
    }
}
