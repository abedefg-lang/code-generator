package pers.tom.generator5.renderresult;

import org.springframework.lang.NonNull;

/**
 * @author lijia
 * @description 文件形式的渲染结果
 * @date 2021-03-09 11:22
 */
public class FileRenderResult implements RenderResult{

    private final String content;

    private final String writePath;

    public FileRenderResult(@NonNull String content,
                            @NonNull String writePath){
        this.content = content;
        this.writePath = writePath;
    }

    @Override
    public String getResult() {
        return this.content;
    }

    /**
     * 获取写入路径
     * @return 返回path
     */
    public String getWritePath(){
        return this.writePath;
    }
}
