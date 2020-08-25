package com.utils.converter;

import com.utils.StringArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class StringConverterRegistry {

    /**存储转换器*/
    private static Map<String, StringConverter<?>> converterMap;

    //初始化
    static {
        //初始化map
        converterMap = new HashMap<>(32);
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
        //将String转换成String
        StringConverter<String> stringConverter = source -> source;
        //将String字符串按照","拆分成数组
        StringConverter<String[]> arrayConverter = source -> StringArrayUtils.trim(source.split(","));
        //添加到map  基本类型与包装类使用同一个转换器
        converterMap.put("int", integerConverter);
        converterMap.put("short", shortConverter);
        converterMap.put("boolean", booleanConverter);
        converterMap.put("long", longConverter);
        converterMap.put("byte", byteConverter);
        converterMap.put("float", floatConverter);
        converterMap.put("double", doubleConverter);
        converterMap.put("java.lang.Integer", integerConverter);
        converterMap.put("java.lang.Short", shortConverter);
        converterMap.put("java.lang.Boolean", booleanConverter);
        converterMap.put("java.lang.Long", longConverter);
        converterMap.put("java.lang.Byte", byteConverter);
        converterMap.put("java.lang.Float", floatConverter);
        converterMap.put("java.lang.Double", doubleConverter);
        converterMap.put("java.lang.String", stringConverter);
        converterMap.put(String[].class.getName(), arrayConverter);
    }

    /**
     * 通过类型获取converter
     */
    public static StringConverter<?> getStringConverter(Class<?> target){
        return converterMap.get(target.getName());
    }

    /**
     * 通过类型名字获取converter
     */
    public static StringConverter<?> getStringConverter(String className){
        return converterMap.get(className);
    }

    /**
     * 注册方法
     */
    public static void register(Class<?> clazz, StringConverter<?> converter){
        converterMap.put(clazz.getName(), converter);
    }

    /**
     * 注册
     */
    public static void register(String className, StringConverter<?> converter){
        converterMap.put(className, converter);
    }




}
