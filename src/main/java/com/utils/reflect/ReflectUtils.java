package com.utils.reflect;

import com.utils.NameUtils;

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
            for(Field field : fields){
                Class<?> fieldType = field.getType();
                //当这个field没有被final修饰的时候才进行注入
                if((field.getModifiers() | Modifier.FINAL) != field.getModifiers()){
                    Object value = BasicTypeUtil.getValue(fieldType, valueMap.get(field.getName()));
                    if(value != null){
                        //获取set方法
                        Method setMethod = target.getClass().getMethod("set"+ NameUtils.initialUppercase(field.getName()), fieldType);
                        setMethod.invoke(target, value);
                    }
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
