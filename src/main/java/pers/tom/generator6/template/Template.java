package pers.tom.generator6.template;

import org.springframework.lang.NonNull;

import java.io.*;
import java.util.Map;

/**
 * @author lijia
 * @description 模板
 * @date 2021/6/12 22:58
 */
public interface Template {

    /**默认字符集*/
    String DEFAULT_ENCODING = "UTF-8";

    /**
     * 获取模板名称
     * @return name
     */
    String getName();

    /**
     * 渲染数据 返回结果
     * @param param 渲染参数
     * @return 渲染结果
     */
    String render(Map<String, Object> param);

    /**
     * 将结果写入到指定文件
     * @param param 渲染参数
     * @param file file
     */
    void render(Map<String, Object> param, @NonNull File file);

    /**
     * 渲染数据并将结果输出
     * @param param 渲染参数
     * @param writer writer
     */
    void render(Map<String, Object> param, @NonNull Writer writer);
}
