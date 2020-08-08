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
    private boolean toCamel;

    /**表前缀 默认空串*/
    private String tablePrefix;

    @Override
    public String toClassName(String str) {
        if(tablePrefix != null && str.startsWith(tablePrefix)){
            //包含指定前缀  进行截取
            str = str.substring(tablePrefix.length());
        }
        return toCamel ? NameUtils.convertClassName(str, REGEX) : NameUtils.initialUppercase(str);
    }

    @Override
    public String toPropertyName(String str) {
        return toCamel ? NameUtils.convertCamel(str, REGEX) : str;
    }
}
