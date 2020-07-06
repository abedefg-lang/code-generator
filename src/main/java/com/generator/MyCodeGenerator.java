package com.generator;

import com.tablesource.info.TableInfo;

public class MyCodeGenerator extends TemplateCodeGenerator {

    @Override
    public void generate() {
        if(tableInfos != null){
            for(TableInfo info : tableInfos){
                System.out.println(info);
            }
        }
    }
}
