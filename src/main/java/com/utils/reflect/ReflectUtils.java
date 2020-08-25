package com.utils.reflect;

import com.utils.NameUtils;
import com.utils.converter.StringConverter;
import com.utils.converter.StringConverterRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReflectUtils {

    /**
     * 对一个对象进行注入  通过set方法进行注入
     */
    public static void simpleInject(Object target, Map<String, String> valueMap) throws Exception{
        //判断target是否为null
        if(target != null && valueMap != null){
            //获取属性
            Field[] fields = getAllField(target.getClass());
            StringConverter<?> converter;
            Method setMethod;
            for(Field field : fields){
                Class<?> fieldType = field.getType();
                //当这个属性有对应的值时  并且当前属性没有被final修饰 才进行操作
                if(valueMap.containsKey(field.getName()) && (field.getModifiers() | Modifier.FINAL) != field.getModifiers()){
                    //获取转换器
                    converter = StringConverterRegistry.getStringConverter(fieldType);
                    //如果转换器为null 抛出异常
                    if(converter == null){
                        throw new RuntimeException("找不到对应类型的com.utils.converter.StringConverter, fieldName " + field.getName() + ", fieldType " + fieldType);
                    }
                    //获取对应的set方法
                    setMethod = target.getClass().getMethod("set" + NameUtils.initialUppercase(field.getName()), fieldType);
                    //执行set方法
                    setMethod.invoke(target, converter.convert(valueMap.get(field.getName())));
                }
            }
        }
    }

    /**
     * 获取一个类型所有的属性  包括父类的私有属性
     */
    public static Field[] getAllField(Class<?> type){
        List<Field> fieldList = new ArrayList<>();
        Class<?> clazz = type;
        //当这个类型是Object的时候停止循环
        while(clazz != null){
            Field[] fields = clazz.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));
            clazz = clazz.getSuperclass();
        }
        return fieldList.toArray(new Field[0]);
    }


}
