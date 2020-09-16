package com.tablesource;

import com.tablesource.dialect.Dialect;
import com.tablesource.dialect.info.ColumnInfo;
import com.tablesource.dialect.info.TableInfo;
import com.tablesource.nameconverter.NameConverter;
import com.utils.StringArrayUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableSource {

    /**方言*/
    private Dialect dialect;

    /**名字转换器*/
    private NameConverter nameConverter;

    /**生成表名称的正则*/
    private String[] namePatterns;

    /**忽略表名称的正则 */
    private String[] ignoreNamePatterns;

    /**是否生成全表 全表生成的优先级最高*/
    private boolean allTables;

    /**
     * 获取tableInfos
     * @return 返回list
     */
    public List<TableInfo> getTableInfos() {
        //获取匹配成功的表的信息
        List<TableInfo> tableInfos = getMatchingTableInfos();
        //完善信息
        improveInfo(tableInfos);
        return tableInfos;
    }

    /**
     * 获取匹配的表信息
     * 全表生成优先于 名字匹配
     * @return 返回表名
     */
    private List<TableInfo> getMatchingTableInfos(){
        //获取所有tableInfo
        List<TableInfo> tableInfos = dialect.selectTableInfos();
        //如果配置了全表生成  直接返回
        if(allTables) return tableInfos;
        //如果不是全表  需要进行筛选
        List<TableInfo> matchTableInfos = new ArrayList<>();
        for(TableInfo info : tableInfos){
            //当该表名没有被忽略  并且需要生成添加到list
            if(!StringArrayUtils.isMatches(ignoreNamePatterns, info.getTableName()) &&
                    StringArrayUtils.isMatches(namePatterns, info.getTableName())){
                matchTableInfos.add(info);
            }
        }
        return matchTableInfos;
    }



    /**
     * 完善tableInfo的信息  设置这张表对应的字段信息进行
     * @param tableInfos  需要完善的tableInfos
     */
    private void improveInfo(List<TableInfo> tableInfos){
        //循环获取column
        List<ColumnInfo> columnList;
        for(TableInfo tableInfo : tableInfos){
            String tableName = tableInfo.getTableName();
            columnList = dialect.selectColumnInfos(tableInfo.getTableName());
            if(nameConverter != null){
                //进行转化名字
                columnList.forEach(columnBean -> columnBean.setFieldName(nameConverter.toPropertyName(columnBean.getColumnName())));
                tableInfo.setClassName(nameConverter.toClassName(tableName));
            }
            //最后添加字段
            tableInfo.setColumnList(columnList);
        }
    }
}
