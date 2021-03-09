package pers.tom.generator5.renderresult;

/**
 * @author lijia
 * @description 可写入的渲染结果
 * @date 2021-03-09 11:22
 */
public class WritableRenderResult implements RenderResult{

    private final String content;

    public WritableRenderResult(String content){
        this.content = content;
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
        return null;
    }
}
