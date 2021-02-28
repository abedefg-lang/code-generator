package generator4;


import org.junit.Test;
import pers.tom.generator4.template.Template;
import pers.tom.generator4.template.VelocityTemplate;

/**
 * @author tom
 * @description
 * @date 2021/2/28 23:22
 */
public class RenderTest {

    @Test
    public void test1(){
        TestRenderData renderData = new TestRenderData("tom", "18");
        Template template = new VelocityTemplate("test", "src\\test\\resources\\testTemplate.vm");
        System.out.println(template.rendering(renderData));
    }
}
