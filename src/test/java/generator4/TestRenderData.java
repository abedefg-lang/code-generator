package generator4;

import lombok.AllArgsConstructor;
import lombok.Data;
import pers.tom.generator4.template.renderdata.RenderData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tom
 * @description
 * @date 2021/2/28 23:19
 */
@AllArgsConstructor
@Data
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
