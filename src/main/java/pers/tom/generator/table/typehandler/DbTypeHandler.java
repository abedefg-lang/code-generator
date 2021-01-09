package pers.tom.generator.table.typehandler;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author tom
 * @description 数据库类型处理器
 * @date 2021/1/7 21:03
 */
public class DbTypeHandler extends MappingRelationTypeHandler{

    @Override
    protected void init() {
        super.init();
        registerMapping("int", int.class);
        registerMapping("varchar", String.class);
        registerMapping("date", Date.class);
        registerMapping("datetime", Date.class);
        registerMapping("timestamp", Timestamp.class);
        registerMapping("char", String.class);
        registerMapping("text", String.class);
        registerMapping("longtext", String.class);
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

    @Override
    public Class<?> getJavaType(String originalType) {

        //判断是否有() 如果有需要截取
        int index = originalType.indexOf("(");
        originalType = index > 0 ? originalType.substring(0, index) : originalType;
        return super.getJavaType(originalType);
    }
}
