package com.xx.basicDs.linked;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/28
 * <p>
 * 随机链表的复制
 * LeetCode 138. 复制带随机指针的链表
 * <p>
 * 请实现 copyRandomList 函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 */
public class RandomListCopy implements Answer {

    public static void main(String[] args) {
        RandomListCopy randomListCopy = new RandomListCopy();
        randomListCopy.answerTwo();
    }

    /**
     * 用一个Map将其存起来，假设其val都不相同，或者用uuid或hashcode，总之有唯一标识此节点。
     * 这样我们用其val当key，指向的当value即可。
     * 此方法的缺点是：如果value重复，就需要采用其他的key，比如自定义hash方法等。
     */
    @Override
    public void answerOne() {
        RandomList ori = initData();
        RandomList randomList = copyRandomList(ori);
        System.out.println("done");
    }


    /**
     * 我们需要在一开始把所有的节点都创建出来，避免 random 找不到指向，每个节点都通过 random 对应着一个新的节点，
     * 这种一一对应的关系，符合哈希表的特征。
     * <p>
     * 此时的哈希表以原链表的节点作为键，新创建的节点作为值。
     */
    public void answerTwo() {
        RandomList ori = initData();
        RandomList randomList = copyRandomList2(ori);
        System.out.println("done");
    }

    public RandomList copyRandomList2(RandomList ori) {
        RandomList oriPoint = ori;
        RandomList newPoint = null;

        Map<RandomList, RandomList> randomListMap = new HashMap<>();
        // 第一次遍历，创建节点，以及初始化map
        while (oriPoint != null) {
            RandomList tempNew = new RandomList(oriPoint.value);
            randomListMap.put(oriPoint, tempNew);
            oriPoint = oriPoint.next;
        }

        //第二次遍历，创建此列表
        oriPoint = ori;
        while (oriPoint != null) {
            newPoint = randomListMap.get(oriPoint);
            newPoint.next = randomListMap.get(oriPoint.next);
            newPoint.random = randomListMap.get(oriPoint.random);
            oriPoint = oriPoint.next;
        }

        return randomListMap.get(ori);
    }

    public RandomList copyRandomList(RandomList ori) {
        RandomList randomListCopy = new RandomList(ori.value);
        RandomList oriPoint = ori;
        RandomList newPoint = randomListCopy;

        Map<Integer, RandomList> randomListMap = new HashMap<>();

        while (oriPoint != null) {
            RandomList oriNext = oriPoint.next;
            RandomList oriRandom = oriPoint.random;
            if (oriNext != null) {
                if (randomListMap.containsKey(oriNext.value)) {
                    newPoint.next = randomListMap.get(oriNext.value);
                } else {
                    RandomList newNext = new RandomList(oriNext.value);
                    newPoint.next = newNext;
                }
            }
            if (oriRandom != null) {
                if (randomListMap.containsKey(oriRandom.value)) {
                    newPoint.random = randomListMap.get(oriRandom.value);
                } else {
                    RandomList randomTemp = new RandomList(oriRandom.value);
                    newPoint.random = randomTemp;
                    randomListMap.put(randomTemp.value, randomTemp);
                }
            }
            randomListMap.put(newPoint.value, newPoint);
            oriPoint = oriPoint.next;
            newPoint = newPoint.next;
        }

        return randomListCopy;
    }

    @Override
    public RandomList initData() {
        RandomList root = new RandomList(0);
        RandomList node1 = new RandomList(1);
        RandomList node2 = new RandomList(2);
        RandomList node3 = new RandomList(3);
        RandomList node4 = new RandomList(4);

        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node1.random = node3;
        node2.random = node3;
        node4.random = node2;
        return root;
    }

    public static class RandomList {
        public int value;
        public RandomList next;
        public RandomList random;

        public RandomList(int value) {
            this.value = value;
        }
    }
}