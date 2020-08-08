package com.utils;

import java.util.HashMap;

/**
 * 返回ajax数据
 */
public class AjaxResult extends HashMap<String, Object> {

    public static final int OK_CODE = 200;

    public static final int ERROR_CODE = 500;

    public static AjaxResult ok(){
            return codeAndMsg(OK_CODE, "success");
        }

    public static AjaxResult ok(String message){
        return codeAndMsg(OK_CODE, message);
    }

    public static AjaxResult error(){
        return codeAndMsg(ERROR_CODE, "fail");
    }

    public static AjaxResult error(String message){
        return codeAndMsg(ERROR_CODE, message);
    }

    public static AjaxResult codeAndMsg(int code, String message){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("code", code);
        ajaxResult.put("msg", message);
        return ajaxResult;
    }

    public AjaxResult putData(String name, Object data){
        super.put(name, data);
        return this;
    }

}
