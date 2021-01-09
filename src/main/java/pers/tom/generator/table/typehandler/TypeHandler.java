package pers.tom.generator.table.typehandler;

/**
 * @author tom
 * @description 类型处理器
 *              在生成java文件的时候可能需要对类型进行处理
 *              将其它类型转换成Class
 * @date 2021/1/7 13:49
 */
public interface TypeHandler{

    /**
     * 通过原来类型获取对应的Java类型
     * @param originalType 原来的类型
     * @return 返回Class
     */
    Class<?> getJavaType(String originalType);
}
