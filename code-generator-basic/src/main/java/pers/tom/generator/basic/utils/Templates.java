package pers.tom.generator.basic.utils;

import pers.tom.generator.basic.exception.RenderException;
import pers.tom.generator.basic.template.Template;

import java.util.Map;

/**
 * @author lijia
 * @description 模板工具类
 * @date 2021-03-18 18:28
 */
public class Templates {


    public static final Template EMPTY_TEMPLATE = new EmptyTemplate();


    public static Template emptyTemplate(){
        return EMPTY_TEMPLATE;
    }

    /**
     * 空模板 不做任何事
     */
    public static class EmptyTemplate implements Template{

        /**渲染结果*/
        private static final Object RESULT = new Object();

        @Override
        public String getName() {
            return "emptyTemplate";
        }

        @Override
        public Object rendering(Map<String, Object> renderParam) throws RenderException {
            return RESULT;
        }
    }
}
