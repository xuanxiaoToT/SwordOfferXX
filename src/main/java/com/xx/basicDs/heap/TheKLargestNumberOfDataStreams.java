package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.PriorityQueue;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/8
 * 数据流的第k大数字
 * 请设计一个类型KthLargest，它每次从一个数据流中读
 * 取一个数字，并得出数据流已经读取的数字中第k（k≥1）大的数
 * 字。该类型的构造函数有两个参数：一个是整数k，另一个是包含数
 * 据流中最开始数字的整数数组nums（假设数组nums的长度大于k）。
 * 该类型还有一个函数add，用来添加数据流中的新数字并返回数据流
 * 中已经读取的数字的第k大数字。
 */
public class TheKLargestNumberOfDataStreams implements Answer {


    public static void main(String[] args) {
        new TheKLargestNumberOfDataStreams().answerOne();
    }

    /**
     * something
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        int k = 5;
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);

        for (int i = 0; i < nums.length; i++) {
            add(heap, nums[i], k);
        }

        System.out.println(heap.peek());
    }

    private void add(PriorityQueue<Integer> heap, int num, int k) {
        if (heap.size() < k) {
            heap.add(num);
        } else {
            if (num > heap.peek()) {
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
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    }
}