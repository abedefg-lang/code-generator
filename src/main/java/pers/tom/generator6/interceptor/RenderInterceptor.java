package pers.tom.generator6.interceptor;

import org.springframework.lang.NonNull;

import pers.tom.generator6.template.Template;

import java.util.Map;

/**
 * @author tom
 * @description 渲染拦截器
 * @date 2021/6/12 23:17
 */
public interface RenderInterceptor {

    /**
     * 前置渲染
     * @param template 准备渲染的模板
     * @param param 渲染参数
     * @return 返回是否执行后续渲染
     */
    default boolean preRender(@NonNull Template template, Map<String, Object> param){
        return true;
    }

    /**
     * 后置渲染
     * @param template 渲染完成的模板
     * @param param 渲染参数
     * @param result 渲染结果
     */
    default void postRender(@NonNull Template template, Map<String, Object> param, Object result){

    }
}
