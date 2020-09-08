package com.tablesource.nameconverter;

import com.utils.NameUtils;

/**
 * 转换类名 属性名
 */
public interface NameConverter {

    NameConverter NONE = new NameConverter(){};

    /**
     * 转换成类名  默认只将首字母大写
     * @param str 字符串
     * @return 返回className
     */
    default String toClassName(String str){
        return NameUtils.initialUppercase(str);
    }

    /**
     * 转换成属性名  默认不进行操作
     * @param str 字符串
     * @return 返回属性名
     */
    default String toPropertyName(String str){
        return str;
    }

}
