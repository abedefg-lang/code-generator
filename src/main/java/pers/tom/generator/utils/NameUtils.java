package pers.tom.generator.utils;

import org.springframework.util.StringUtils;

/**
 * 处理一些与名字相关的工具类
 */
public class NameUtils {

    /**
     * 首字母大写
     */
    public static String initialUppercase(String str){

        //判断如果字符串为空  或者为空串  或者首字母本身就是大写  返回原字符串
        if(StringUtils.isEmpty(str) || isUppercase(str.charAt(0))) return str;
        char[] chars = str.toCharArray();
        chars[0] = (char)(chars[0] - 32);
        return new String(chars);
    }


    /**
     * 首字母小写
     */
    public static String initialLowercase(String str){

        //判断如果字符串为空  或者为空串  或者首字母本身就是小写写  返回原字符串
        if(StringUtils.isEmpty(str) || isLowerCase(str.charAt(0))) return str;
        char[] chars = str.toCharArray();
        chars[0] = (char)(chars[0] + 32);
        return new String(chars);
    }

    /**
     * 将其他命名规则转化为驼峰式  比如下划线  中划线  空格等于连接的方式
     */
    public static String convertHump(String str, String connector){
        String[] strings = str.split(connector);
        StringBuilder builder = new StringBuilder(strings[0]);
        for(int i = 1 ; i < strings.length ; i ++){
            builder.append(initialUppercase(strings[i]));
        }
        return new String(builder);
    }


    /**
     * 将一个字符串变成大驼峰
     * 先将字符串转化为小驼峰然后首字母大写
     */
    public static String convertGreatHump(String str, String connector){
        String className = convertHump(str, connector);
        return initialUppercase(className);
    }


    /**
     * 判断某个字符是否是大写字母
     */
    public static boolean isUppercase(char word){
        return 'A' <= word && 'Z' >= word;
    }

    /**
     * 判断某个字符是否是小写字母
     */
    public static boolean isLowerCase(char word){
        return 'a' <= word && 'z' >= word;
    }

}
