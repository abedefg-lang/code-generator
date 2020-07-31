package com.utils.converter;

import com.utils.NameUtils;

/**
 * 转换成驼峰式
 */
public class CamelNameConverter implements NameConverter{


    private static final String REGEX = "_";


    @Override
    public String toClassName(String str) {
        return NameUtils.convertClassName(str, REGEX);
    }

    @Override
    public String toPropertyName(String str) {
        return NameUtils.convertCamel(str, REGEX);
    }
}
