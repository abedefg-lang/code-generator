package com.tablesource.dialect;

import com.utils.reflect.ReflectUtils;
import lombok.Data;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@Data
public abstract class AbstractDialect implements Dialect{

    /**数据源*/
    protected DataSource dataSource;

    /**数据库名称*/
    protected String databaseName;


    public AbstractDialect(DataSource dataSource){
        Objects.requireNonNull(dataSource, "dataSource不能为null");
        this.dataSource = dataSource;
        //获取数据库名称
        try(Connection connection = dataSource.getConnection()){
            databaseName = connection.getCatalog();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查询list
     * @param sql 可执行的sql
     * @param target 目标类型
     * @param resultMap 数据库中的字段映射到对象的属性  key数据字段  value对象属性
     * @return 返回目标类型的list
     */
    protected <T>List<T>  selectList(String sql, Class<T> target, Map<String, String> resultMap){
        Objects.requireNonNull(target, "target不能为null");
        Objects.requireNonNull(resultMap, "resultMap不能为null");

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            //进行封装集合
            List<T> list = new ArrayList<>();
            //key对象属性  value是对应字段的值
            Map<String, String> map = new HashMap<>();
            T t;
            while(resultSet.next()){
                t = target.newInstance();
                //循环获取
                for(Map.Entry<String, String> entry : resultMap.entrySet()){
                    map.put(entry.getValue(), resultSet.getString(entry.getKey()));
                }
                //进行注入对象
                ReflectUtils.simpleInject(t, map);
                list.add(t);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
