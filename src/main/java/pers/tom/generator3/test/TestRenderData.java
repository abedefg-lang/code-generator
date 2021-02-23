package pers.tom.generator3.test;

import pers.tom.generator3.task.data.TemplateRenderData;

import java.util.Map;

/**
 * @author lijia
 * @description
 * @date 2021-02-23 15:30
 */
public class TestRenderData implements TemplateRenderData {

    @Override
    public String getName() {
        return "test data";
    }

    @Override
    public Map<String, Object> toMap() {
        return null;
    }
}
