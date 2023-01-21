package com.xx.basicDs.hash;

import com.xx.domain.BiLinkNode;

import java.util.HashMap;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/31
 * 最近最少使用缓存
 * <p>
 * 请设计实现一个最近最少使用（Least Recently Used，
 * LRU）缓存，要求如下两个操作的时间复杂度都是O（1）。
 * ● get（key）：如果缓存中存在键key，则返回它对应的值；否则返回-1。
 * ● put（key，value）：如果缓存中之前包含键key，则它的值设为
 * value；否则添加键key及对应的值value。在添加一个键时，如果缓存
 * 容量已经满了，则在添加新键之前删除最近最少使用的键（缓存中
 * 最长时间没有被使用过的元素）。
 */
public class LeastRecentlyUsedCache {

    private HashMap<String, Integer> map;
    private BiLinkNode biLinkList;
    private int maxSize;

    /**
     * 使用map+双向链表
     * 删除时，删除头部元素即可
     * 插入或使用时，将该元素放置在链表尾部。
     */
    public LeastRecentlyUsedCache() {

    }

    public Integer get(String key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        //用过的元素，移动到双向链表的尾部
        return map.get(key);
    }

}