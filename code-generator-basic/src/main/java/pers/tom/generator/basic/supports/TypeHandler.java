package pers.tom.generator.basic.supports;

import java.lang.reflect.Type;

/**
 * @author tom
 * @description 类型处理器
 * @date 2021/1/7 13:49
 */
public interface TypeHandler {


    Type handle(String otherType);
}
