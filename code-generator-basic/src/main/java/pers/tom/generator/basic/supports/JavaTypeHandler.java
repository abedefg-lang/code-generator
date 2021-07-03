package pers.tom.generator.basic.supports;


/**
 * @author tom
 * @description
 * @date 2021/3/13 11:43
 */
public interface JavaTypeHandler extends TypeHandler {


    Class<?> handle(String otherType);
}
