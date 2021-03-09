package pers.tom.generator5.renderdata;

/**
 * @author lijia
 * @description java文件类型
 * @date 2021-03-09 12:20
 */
public enum JavaFileType {

    CLASS("class"),

    ABSTRACT_CLASS("abstract class"),

    INTERFACE("interface"),

    ENUM("enum"),

    ANNOTATION("@interface");

    private final String typeName;

    JavaFileType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
