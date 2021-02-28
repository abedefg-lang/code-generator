package pers.tom.generator4.utils;

import java.util.Collection;

/**
 * @author lijia
 * @description 集合的工具类
 * @date 2021-02-23 10:23
 */
public class CollectionUtils2 {

    /**
     * 判断一个集合是否含有元素
     * @param collection collection
     * @return 返回boolean
     */
    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }
}
