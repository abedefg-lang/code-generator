package com.utils.converter;

@FunctionalInterface
public interface Converter<S, T> {

    T convert(S s);
}
