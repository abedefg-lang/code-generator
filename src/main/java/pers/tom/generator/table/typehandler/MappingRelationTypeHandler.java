package pers.tom.generator.table.typehandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tom
 * @description 映射关系的typeHandler
 *              一个类型对应一个java类型
 * @date 2021/1/7 13:57
 */
public class MappingRelationTypeHandler implements TypeHandler{

    /**缓存类型映射*/
    protected Map<String, Class<?>> typeMappingMap;

    public MappingRelationTypeHandler(){
        this.init();
    }

    /**
     * 初始化方法
     */
    protected void init(){
        typeMappingMap = new HashMap<>();
    }

    @Override
    public Class<?> getJavaType(String originalType) {
        return typeMappingMap.get(originalType);
    }

    /**
     * 允许覆盖的注册映射关系
     * @param originalType 原来的类型
     * @param javaType java类型
     */
    public void registerMapping(String originalType, Class<?> javaType){
        this.registerMapping(originalType, javaType, true);
    }

    /**
     * 注册映射关系
     * @param originalType 原来的类型
     * @param javaType java类型
     * @param cover 是否允许覆盖  如果不允许覆盖会抛出异常
     */
    public void registerMapping(String originalType, Class<?> javaType, boolean cover){
        if(!cover && typeMappingMap.containsKey(originalType)){
            throw new RuntimeException("originalType : "+originalType+" 已存在");
        }
        typeMappingMap.put(originalType, javaType);
    }

}
