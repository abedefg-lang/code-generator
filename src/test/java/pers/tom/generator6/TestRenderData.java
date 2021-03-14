package pers.tom.generator6;

import lombok.AllArgsConstructor;
import lombok.Data;
import pers.tom.generator.basic.renderdata.RenderData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijia
 * @description
 * @date 2021-03-12 14:07
 */
@Data
@AllArgsConstructor
public class TestRenderData implements RenderData {

    private String name;

    private String age;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        return map;
    }
}
