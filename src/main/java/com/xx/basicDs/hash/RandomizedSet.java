package com.xx.basicDs.hash;

import java.util.HashMap;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/31
 * 插入、删除和随机访问都是O（1）的容器
 * 设计一个数据结构，使如下3个操作的时间复杂度都是O(1)。
 * ● insert（value）：如果数据集中不包含一个数值，则把它添加到数据集中。
 * ● remove（value）：如果数据集中包含一个数值，则把它删除。
 * ● getRandom()：随机返回数据集中的一个数值，要求数据集中每个
 * 数字被返回的概率都相同。
 */
public class RandomizedSet {

    public RandomizedSet(){

    }

    //利用list和map
    //list负责存放数据以及随机读取,map存放数组下标,
    private HashMap<Integer, Integer> map;
    private List<Integer> list;

    /**
     * insert和random暂时不用
     */
    public boolean remove(int value) {
        if (!map.containsKey(value)) {
            return false;
        }
        // 跟最后一个值的位置互换
        int index = map.get(value);
        map.remove(value);
        map.put(map.get(list.size() - 1), index);
        list.set(index, list.get(list.size() - 1));

        list.remove(list.size() - 1);

        return true;
    }

}