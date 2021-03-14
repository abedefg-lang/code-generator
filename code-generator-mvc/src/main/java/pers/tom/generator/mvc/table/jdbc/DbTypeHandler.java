package pers.tom.generator.mvc.table.jdbc;

import pers.tom.generator.basic.supports.JavaTypeHandler;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tom
 * @description 数据库类型处理器
 * @date 2021/1/7 21:03
 */
public class DbTypeHandler implements JavaTypeHandler {

    /**缓存类型映射*/
    private Map<String, Class<?>> typeMappingMap;


    public DbTypeHandler(){
        this.init();
    }

    /**
     * 注册映射关系
     * @param otherType otherType
     * @param type type
     */
    public void registerMapping(String otherType, Class<?> type){
        if (typeMappingMap == null) {
            typeMappingMap = new HashMap<>();
        }
        typeMappingMap.put(otherType, type);
    }


    @Override
    public Class<?> handle(String otherType) {

        //判断是否有() 如果有需要截取
        int index = otherType.indexOf("(");
        String realOtherType = index > 0 ? otherType.substring(0, index) : otherType;
        return typeMappingMap.get(realOtherType);
    }

    /**
     * 初始化类型映射
     */
    private void init() {
        registerMapping("int", int.class);
        registerMapping("varchar", String.class);
        registerMapping("date", Date.class);
        registerMapping("datetime", Date.class);
        registerMapping("timestamp", Timestamp.class);
        registerMapping("char", String.class);
        registerMapping("text", String.class);
        registerMapping("longtext", String.class);
        registerMapping("mediumtext", String.class);
        registerMapping("longvarchar", String.class);
        registerMapping("numeric", BigDecimal.class);
        registerMapping("decimal", BigDecimal.class);
        registerMapping("bit", boolean.class);
        registerMapping("tinyint", byte.class);
        registerMapping("smallint", short.class);
        registerMapping("integer", int.class);
        registerMapping("bigint", long.class);
        registerMapping("real", float.class);
        registerMapping("float", float.class);
        registerMapping("double", double.class);
        registerMapping("binary", byte[].class);
        registerMapping("time", Time.class);
    }
}
