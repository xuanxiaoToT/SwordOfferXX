package com.xx.basicDs.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/31
 * <p>
 * 插入、删除和随机访问都是O（1）的容器
 * LeetCode 380. O(1) 时间插入、删除和获取随机元素
 * <p>
 * 设计一个数据结构，使如下3个操作的时间复杂度都是O(1)。
 * ● insert（value）：如果数据集中不包含一个数值，则把它添加到数据集中。
 * ● remove（value）：如果数据集中包含一个数值，则把它删除。
 * ● getRandom()：随机返回数据集中的一个数值，要求数据集中每个
 * 数字被返回的概率都相同。
 * <p>
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 */
public class RandomizedSet {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        boolean r1 = randomizedSet.remove(0);
        boolean r2 = randomizedSet.remove(0);
        boolean r3 = randomizedSet.insert(0);
        System.out.println(randomizedSet.getRandom());
        boolean r4 = randomizedSet.remove(0);
        boolean r5 = randomizedSet.insert(0);
    }

    public RandomizedSet() {
        map = new HashMap<>();
        list = new LinkedList<>();
        random = new Random();
    }


    /**
     * 利用list和map
     * list负责存放数据以及随机读取,map存放数组下标,
     */
    private HashMap<Integer, Integer> map;
    private List<Integer> list;
    private Random random;

    public boolean insert(int value) {
        if (map.containsKey(value)) {
            return false;
        }
        list.add(value);
        map.put(value, list.size() - 1);
        return true;
    }

    /**
     * 跟最后一个值的位置互换.将删除的元素放列表最后,然后删除
     * 如果直接list.remove  则map对应的下标将全部发生变化
     */
    public boolean remove(int value) {
        if (!map.containsKey(value)) {
            return false;
        }
        int removeIndex = map.get(value);
        if (removeIndex == list.size() - 1){
            map.remove(value);
            list.remove(list.size() - 1);
            return true;
        } else {
            map.remove(value);
            int lastValue = list.get(list.size() - 1);
            map.put(lastValue, removeIndex);
            list.set(removeIndex, lastValue);
            list.remove(list.size() - 1);
            return true;
        }
    }

    public int getRandom() {
        int i = random.nextInt(list.size());
        return list.get(i);
    }

}