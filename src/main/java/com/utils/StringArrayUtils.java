package com.utils;

import org.apache.commons.lang.StringUtils;

/**
 * string数组的工具类
 */
public class StringArrayUtils {

    /**
     * 判断一个数组是否null 或者没有元素
     */
    public static boolean isEmpty(String[] array){
        return array == null || array.length == 0;
    }

    /**
     * 判断一个数组是否不为null 并且含有元素
     */
    public static boolean isNotEmpty(String[] array){
        return !isEmpty(array);
    }

    /**
     * 将数组中的所有字符串执行trim
     */
    public static String[] trim(String[] array){
        if(!isEmpty(array)){
            String[] result = new String[array.length];
            String str;
            for(int i = 0 ; i < array.length ; i ++){
                str = array[i];
                result[i] = StringUtils.isEmpty(str) ? str : str.trim();
            }
            return result;
        }
        return array;
    }

    /**
     * 判断一个字符串时候能够匹配上一个pattern数组中的某一个pattern
     */
    public static boolean isMatches(String[] patterns, String str){
        if(!isEmpty(patterns) && !StringUtils.isEmpty(str)){
            for(String pattern : patterns){
                if(str.matches(pattern)){
                    return true;
                }
            }
        }
        return false;
    }

}
