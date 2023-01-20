package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/8
 * 出现频率最高的k个数字
 */
public class KDigitsWithTheHighestFrequency implements Answer {

    public static void main(String[] args) {
        new KDigitsWithTheHighestFrequency().answerOne();
    }

    /**
     * something
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        int k = 3;
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        Map<Integer, Integer> map = new HashMap<>();
        // 先全部转为map
        for (Integer num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        System.out.println(map);
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            add(heap, integerIntegerEntry, k);
        }
        System.out.println(heap.peek());
    }

    private void add(PriorityQueue<Map.Entry<Integer, Integer>> heap, Map.Entry<Integer, Integer> num, int k) {
        if (heap.size() < k) {
            heap.add(num);
        } else {
            if (num.getValue() > heap.peek().getValue()) {
                heap.poll();
                heap.add(num);
            }
        }
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5, 6, 6, 7, 8, 9, 9, 9, 9, 10};
    }
}