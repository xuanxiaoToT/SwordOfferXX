package com.xx.basicDs.hash;

import com.xx.domain.BiLinkNode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/31
 * <p>
 * 最近最少使用缓存
 * LeetCode 146. LRU 缓存
 * <p>
 * 请设计实现一个最近最少使用（Least Recently Used，
 * LRU）缓存，要求如下两个操作的时间复杂度都是O（1）。
 * ● get（key）：如果缓存中存在键key，则返回它对应的值；否则返回-1。
 * ● put（key，value）：如果缓存中之前包含键key，则它的值设为
 * value；否则添加键key及对应的值value。在添加一个键时，如果缓存
 * 容量已经满了，则在添加新键之前删除最近最少使用的键（缓存中
 * 最长时间没有被使用过的元素）。
 * <p>
 * 思路：inkedHashMap可以认为是HashMap+LinkedList。
 * 即它既使用HashMap操作数据结构，又使用LinkedList维护插入元素的先后顺序。
 */
public class LeastRecentlyUsedCache {

    private LinkedHashMap<String, Integer> map;
    private BiLinkNode biLinkList;
    private int maxSize;

    /**
     * 使用map+双向链表
     * 删除时，删除头部元素即可
     * 插入或使用时，将该元素放置在链表尾部。
     */
    public LeastRecentlyUsedCache(int capacity) {
        map = new LinkedHashMap<>(capacity, 0.75F, true);
        maxSize = capacity;
    }

    //用过的元素，移动到双向链表的尾部
    public Integer get(String key) {
        return map.getOrDefault(key, -1);
    }

    public void put(String key, Integer value) {
        map.put(key, value);
    }

    //@Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return map.size() > maxSize;
    }

}