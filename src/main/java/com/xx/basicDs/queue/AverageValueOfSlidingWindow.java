package com.xx.basicDs.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 玄霄
 * @CreateDate 2022/9/20
 * <p>
 * 请实现如下类型MovingAverage，计算滑动窗口中所有数
 * 字的平均值，该类型构造函数的参数确定滑动窗口的大小，每次调
 * 用成员函数next时都会在滑动窗口中添加一个整数，并返回滑动窗
 * 口中所有数字的平均值。
 */
public class AverageValueOfSlidingWindow {

    public static void main(String[] args) {
        new MovingAverage(4).next(5);
    }

    public static class MovingAverage {

        private Queue<Integer> queue;
        private int capity;
        int sum;

        public MovingAverage(int size) {
            queue = new LinkedList<Integer>();
            capity = size;

        }

        public double next(int val) {
            queue.offer(val);
            sum += val;
            if (queue.size() > capity) {
                Integer poll = queue.poll();
                sum -= poll;
            }
            return sum * 1.0 / capity;
        }
    }
}