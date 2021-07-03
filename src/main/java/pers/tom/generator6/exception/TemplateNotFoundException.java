package pers.tom.generator6.exception;

/**
 * @author tom
 * @description
 * @date 2021/6/13 10:51
 */
public class TemplateNotFoundException extends CodeGeneratorException{

    public TemplateNotFoundException(String templateName){
        super("找不到该模板 templateName : " + templateName);
    }
}

