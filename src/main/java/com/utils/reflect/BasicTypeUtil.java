package com.utils.reflect;


import com.utils.StringConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来处理一些基础类型 与其包装类的工具类
 */
public class BasicTypeUtil {

    //通过对应的类型全名  找到对应的转换规则
    private static Map<String, StringConverter<?>> functionMap;

    //初始化
    static {
        //初始化map
        functionMap = new HashMap<>(20);
        //将String转化为Integer
        StringConverter<Integer> integerConverter = Integer::parseInt;
        //将String转化为Long
        StringConverter<Long> longConverter = Long::parseLong;
        //将String转化为Double
        StringConverter<Double> doubleConverter = Double::parseDouble;
        //将String转化为Float
        StringConverter<Float> floatConverter = Float::parseFloat;
        //将String转为Short
        StringConverter<Short> shortConverter = Short::parseShort;
        //将String转为Byte
        StringConverter<Byte> byteConverter = Byte::parseByte;
        //将String转为Boolean
        StringConverter<Boolean> booleanConverter = Boolean::parseBoolean;
        //添加到map  基本类型与包装类使用同一个转换器
        functionMap.put("int", integerConverter);
        functionMap.put("short", shortConverter);
        functionMap.put("boolean", booleanConverter);
        functionMap.put("long", longConverter);
        functionMap.put("byte", byteConverter);
        functionMap.put("float", floatConverter);
        functionMap.put("double", doubleConverter);
        functionMap.put("java.lang.Integer", integerConverter);
        functionMap.put("java.lang.Short", shortConverter);
        functionMap.put("java.lang.Boolean", booleanConverter);
        functionMap.put("java.lang.Long", longConverter);
        functionMap.put("java.lang.Byte", byteConverter);
        functionMap.put("java.lang.Float", floatConverter);
        functionMap.put("java.lang.Double", doubleConverter);
    }
    /**
     * 判断一个类型是否是基本类型
     */
    public static boolean isBasicType(Class<?> type){
        return functionMap.containsKey(type.getName());
    }

    /**
     * 判断一个类名是否是基本类型
     */
    public static boolean isBasicType(String className){
        return functionMap.containsKey(className);
    }

    /**
     * 将一个字符串转为其他类型
     */
    public static <T>T getValue(Class<T> target, String source){
        Object result = null;
        //首先判断是否是String类型
        if(source == null || target == String.class){
            result = source;
        }else if(isBasicType(target)){//判断s是否为null 并且判断target是否是基本类型
            result = functionMap.get(target.getName()).convert(source);
        }
        //返回结果
        return (T) result;

    }

}
