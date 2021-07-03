package pers.tom.generator6;

import pers.tom.generator6.template.Template;

import java.util.Map;

/**
 * @author lijia
 * @description 生成结果处理器
 *              对于不同的渲染结果 可能会进行不同的处理
 *              比如对文件进行写入处理 这就必须获取到该文件正确的写入路径(java文件的路径与生成内容的package有关)
 * @date 2021/7/3 20:39
 */
public interface GenerateResultHandler {

    /**
     * 处理逻辑
     * @param template 当前使用的模板
     * @param param 渲染参数
     * @param result 渲染结果
     */
    void handle(Template template, Map<String, Object> param, Object result);
}
