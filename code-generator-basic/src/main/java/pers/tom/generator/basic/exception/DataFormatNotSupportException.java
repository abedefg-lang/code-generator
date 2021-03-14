package pers.tom.generator.basic.exception;

import org.springframework.lang.NonNull;
import pers.tom.generator.basic.renderdata.RenderData;

/**
 * @author tom
 * @description 数据格式不支持异常
 * @date 2021/3/13 12:01
 */
public class DataFormatNotSupportException extends RuntimeException{

    public DataFormatNotSupportException(String message){
        super(message);
    }

    public DataFormatNotSupportException(@NonNull RenderData renderData){
        super("不支持 " + renderData.getClass() + " 格式");
    }
}
