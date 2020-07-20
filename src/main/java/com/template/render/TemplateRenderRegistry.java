package com.template.render;

import com.template.render.impl.FreemarkerRender;
import com.template.render.impl.VelocityRender;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板引擎的注册表
 */
public class TemplateRenderRegistry {

    private static final Map<String, TemplateRender> engineMap;

    static {
        engineMap = new HashMap<>(8);
        engineMap.put("velocity", new VelocityRender());
        engineMap.put("freemarker", new FreemarkerRender());
    }

    public static TemplateRender getEngine(String engineName){
        return engineMap.get(engineName);
    }


    public static TemplateRender register(String engineName, TemplateRender engine){
        return engineMap.put(engineName, engine);
    }
}
