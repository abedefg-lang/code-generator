package com.template.render.impl;

import com.template.render.TemplateRender;

import java.util.Map;

public class FreemarkerRender implements TemplateRender {

    @Override
    public String rendering(String classpath, Map<String, Object> map) {
        return "freemarker模板引擎 暂时还没有实现";
    }

    @Override
    public String getName() {
        return "freemarker";
    }
}
