package com.tablesource.nameconverter;

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
    private String[] tablePrefixes;

    @Override
    public String toClassName(String str) {
        if(tablePrefixes != null && tablePrefixes.length > 0){
            for(String prefix : tablePrefixes){
                if(str.startsWith(prefix)){
                    str = str.substring(prefix.length());
                    break;
                }
            }
        }
        return toCamel ? NameUtils.convertClassName(str, REGEX) : NameUtils.initialUppercase(str);
    }

    @Override
    public String toPropertyName(String str) {
        return toCamel ? NameUtils.convertCamel(str, REGEX) : str;
    }
}
