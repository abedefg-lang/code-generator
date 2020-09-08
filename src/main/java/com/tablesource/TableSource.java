package com.tablesource;

import com.tablesource.nameconverter.NameConverter;
import com.tablesource.dao.ColumnInfoDao;
import com.tablesource.dao.TableInfoDao;
import com.tablesource.dao.impl.ColumnInfoDaoImpl;
import com.tablesource.dao.impl.TableInfoDaoImpl;
import com.tablesource.entity.ColumnInfo;
import com.tablesource.entity.TableInfo;
import com.utils.StringArrayUtils;
import lombok.Data;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Data
public class TableSource{

    /**TableInfoDao  获取table的相关信息*/
    private TableInfoDao tableInfoDao;

    /**ColumnInfoDao 获取某个表的字段的相关信息*/
    private ColumnInfoDao columnInfoDao;

    /**名字转换器*/
    private NameConverter nameConverter;

    /**生成表名称的正则*/
    private String[] namePatterns;

    /**忽略表名称的正则 */
    private String[] ignoreNamePatterns;

    /**是否生成全表 全表生成的优先级最高*/
    private boolean allTables;

    public TableSource(){}

    public TableSource(DataSource dataSource){
        this.tableInfoDao = new TableInfoDaoImpl(dataSource);
        this.columnInfoDao = new ColumnInfoDaoImpl(dataSource);
    }

    public TableSource(TableInfoDao tableInfoDao, ColumnInfoDao columnInfoDao){
        this.tableInfoDao = tableInfoDao;
        this.columnInfoDao = columnInfoDao;
    }


    /**
     * 获取tableInfos
     * @return 返回list
     */
    public List<TableInfo> getTableInfos() {
        //获取匹配成功的表的信息
        List<TableInfo> tableInfos = tableInfoDao.selectListByName(getMatchingTableNames());
        //完善信息
        nameConverter = nameConverter != null ? nameConverter : NameConverter.NONE;
        improveInfo(tableInfos, nameConverter);
        return tableInfos;
    }

    /**
     * 获取匹配的表名
     * 全表生成优先于 名字匹配
     * @return 返回表名
     */
    private String[] getMatchingTableNames(){
        //获取所有表名
        List<String> tableNames = tableInfoDao.selectAllTables();
        //如果是全表 直接返回
        if(allTables) return tableNames.toArray(new String[0]);

        List<String> matchNames = new ArrayList<>();
        //获取所有的表名进行匹配
        for(String table : tableNames){
            //当该表名没有被忽略  并且需要生成添加到list
            if(!StringArrayUtils.isMatches(ignoreNamePatterns, table) &&
                    StringArrayUtils.isMatches(namePatterns, table)){
                matchNames.add(table);
            }
        }
        //返回数组
        return matchNames.toArray(new String[0]);
    }



    /**
     * 完善tableInfo的信息  设置这张表对应的字段信息进行
     * @param tableInfos  需要完善的tableInfos
     * @param converter  是否转化成驼峰式
     */
    private void improveInfo(List<TableInfo> tableInfos, NameConverter converter){
        //循环获取column
        List<ColumnInfo> columnBeanList;
        for(TableInfo tableInfo : tableInfos){
            String tableName = tableInfo.getTableName();
            columnBeanList = columnInfoDao.selectListByTableName(tableInfo.getTableName());
            if(converter != null){
                //进行转化名字
                columnBeanList.forEach(columnBean -> columnBean.setFieldName(converter.toPropertyName(columnBean.getColumnName())));
                tableInfo.setClassName(converter.toClassName(tableName));
            }
            //最后添加字段
            tableInfo.setColumnList(columnBeanList);
        }
    }
}
