package com.utils.converter;

/**
 * 将String类型转化成其他类型
 * @param <T> 转化的类型
 */
public interface StringConverter<T> extends Converter<String, T>{

    @Override
    T convert(String source);

}
