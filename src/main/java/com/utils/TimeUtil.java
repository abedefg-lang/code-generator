package com.utils;

import java.time.LocalDateTime;

public class TimeUtil {

    /**获取当前时间*/
    public static String getCurrentTime(){
        String dateStr =  LocalDateTime.now().toString().replace("T", " ");
        return dateStr.substring(0, dateStr.lastIndexOf("."));
    }
}
