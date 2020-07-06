package com.utils;

/**
 * 将String类型转化成其他类型
 * @param <T> 转化的类型
 */
@FunctionalInterface
public interface StringConverter<T> {


    T convert(String source);
}
