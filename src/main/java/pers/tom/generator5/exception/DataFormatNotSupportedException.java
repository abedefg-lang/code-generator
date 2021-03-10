package pers.tom.generator5.exception;

import org.springframework.lang.NonNull;
import pers.tom.generator5.renderdata.RenderData;
import pers.tom.generator5.template.Template;

/**
 * @author lijia
 * @description 数据格式不支持异常
 * @date 2021-03-10 18:07
 */
public class DataFormatNotSupportedException extends Exception{


    public DataFormatNotSupportedException(){}

    public DataFormatNotSupportedException(String message){
        super(message);
    }

    public DataFormatNotSupportedException(@NonNull Template template,
                                           @NonNull RenderData renderData){
        super(template.getClass() + " 不支持 " + renderData.getClass() + " 数据格式");
    }
}
