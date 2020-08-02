package com.utils;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TypeMappingUtil {

    /**存储字段类型  和java类型的映射*/
    private static Map<String, Class<?>> typeConverter;

    //将jdbc中的数据类型和对应的java类型存储
    static{
        typeConverter = new HashMap<>(32);
        typeConverter.put("int", int.class);
        typeConverter.put("varchar", String.class);
        typeConverter.put("date", Date.class);
        typeConverter.put("datetime", Date.class);
        typeConverter.put("timestamp", Timestamp.class);
        typeConverter.put("char", String.class);
        typeConverter.put("text", String.class);
        typeConverter.put("longtext", String.class);
        typeConverter.put("longvarchar", String.class);
        typeConverter.put("numeric", BigDecimal.class);
        typeConverter.put("decimal", BigDecimal.class);
        typeConverter.put("bit", boolean.class);
        typeConverter.put("tinyint", byte.class);
        typeConverter.put("smallint", short.class);
        typeConverter.put("integer", int.class);
        typeConverter.put("bigint", long.class);
        typeConverter.put("real", float.class);
        typeConverter.put("float", float.class);
        typeConverter.put("double", double.class);
        typeConverter.put("binary", byte[].class);
        typeConverter.put("time", Time.class);

    }

    /**
     * 获取一个字段类型对应的java类型
     * @param columnType 字段类型
     * @return java类型
     */
    public static Class<?> getJavaType(String columnType){
        return typeConverter.get(columnType);
    }

    /**
     * 通过一个java类型 获取所有对应的数据库类型
     * 一个java类型可能对应多个数据库类型
     * @param javaType  java类型
     * @return 返回集合
     */
    public static List<String> getColumnType(Class<?> javaType){
        List<String> result = new ArrayList<>();
        for(Map.Entry<String, Class<?>> entry : typeConverter.entrySet()){
            if(entry.getValue() == javaType){
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public static void register(String dbType, Class<?> javaType){
        typeConverter.put(dbType, javaType);
    }
}
