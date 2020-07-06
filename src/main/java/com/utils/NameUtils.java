package com.utils;

/**
 * 处理一些与名字相关的工具类
 */
public class NameUtils {

    /**首字母大写*/
    public static String initialUppercase(String str){
        //判断如果字符串为空  或者为空串  或者首字母本身就是大写  返回原字符串
        if(str == null || "".equals(str) || isUppercase(str.charAt(0))) return str;
        char[] chars = str.toCharArray();
        chars[0] = (char)(chars[0] - 32);
        return new String(chars);
    }

    /**首字母小写*/
    public static String initialLowercase(String str){
        //判断如果字符串为空  或者为空串  或者首字母本身就是小写写  返回原字符串
        if(str == null || "".equals(str) || isLowerCase(str.charAt(0))) return str;
        char[] chars = str.toCharArray();
        chars[0] = (char)(chars[0] + 32);
        return new String(chars);
    }

    /**将其他命名规则转化为驼峰式  比如下划线  中划线  空格等于连接的方式*/
    public static String convertCamel(String str, String regex){
        String[] strings = str.split(regex);
        StringBuilder builder = new StringBuilder(strings[0]);
        for(int i = 1 ; i < strings.length ; i ++){
            builder.append(initialUppercase(strings[i]));
        }
        return new String(builder);
    }

    /**将一个字符串变成类名   也就是大驼峰式*/
    public static String convertClassName(String str, String regex){
        String className = convertCamel(str, regex);
        return initialUppercase(className);
    }

    /**驼峰式转化为另外一种命名方式*/
    public static String camelConvertAnotherType(String name, String regex){
        //特判
        if(name == null || "".equals(name) || regex == null || "".equals(regex)) return name;
        //首先将字符首字母变小写
        initialLowercase(name);
        char[] word = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        int start = 0;
        for(int i = 0 ; i < word.length ; i ++){
            //当一个字符是大写的时候  执行操作
            if(isUppercase(word[i])){
                //从起点截取到当前位置 再拼接上正则
                builder.append(new String(word, start, i-start)).append(regex);
                //再将当前位置的字符小写
                word[i] = (char)(word[i] + 32);
                start = i;
                i --;
            }
        }
        //拼接上最后一截字符串
        builder.append(new String(word, start, word.length-start));
        return builder.toString();
    }

    /**将一种命名方式转化为另外一种方式*/
    public static String convertAnotherType(String str, String oriRegex, String newRegex){
        String[] strings = str.split(oriRegex);
        StringBuilder builder = new StringBuilder(strings[0]);
        for(int i = 1 ; i < strings.length ; i ++){
            builder.append(newRegex).append(strings[i]);
        }
        return new String(builder);
    }

    /**获取一个类全名的简单名字*/
    public static String getSimpleName(String className){
        int index = className.lastIndexOf(".");
        return (index > 0) ? className.substring(index+1) : className;
    }


    /**
     * 判断是否是大写字母
     */
    public static boolean isUppercase(char word){
        return 'A' <= word && 'Z' >= word;
    }

    /**判断是否是小写字母*/
    public static boolean  isLowerCase(char word){
        return 'a' <= word && 'z' >= word;
    }
}
