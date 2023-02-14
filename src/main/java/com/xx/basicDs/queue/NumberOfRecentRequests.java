package com.xx.basicDs.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/20
 * 最近请求次数
 * <p>
 * 请实现如下类型RecentCounter，它是统计过去3000ms内
 * 的请求次数的计数器。该类型的构造函数RecentCounter初始化计数
 * 器，请求数初始化为0；函数ping（int t）在时间t添加一个新请求
 * （t表示以毫秒为单位的时间），并返回过去3000ms内（时间范围为
 * [t-3000，t]）发生的所有请求数。假设每次调用函数ping的参数t
 * 都比之前调用的参数值大。
 */
public class NumberOfRecentRequests {
    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(10));
        System.out.println(recentCounter.ping(3000));
    }

    public static class RecentCounter {

        private Queue<Integer> queue;
        int size;
        int timeSum;

        public RecentCounter() {
            queue = new LinkedList<>();
            size = 0;
            timeSum = 0;
        }

        // 注意t是递增的
        public int ping(int t) {
            queue.offer(t);
            timeSum += t;

            while (true) {
                Integer var = queue.peek();
                timeSum = timeSum - var;
                if (timeSum >= 3000) {
                    queue.poll();
                } else {
                    timeSum += var;
                    break;
                }
            }
            return queue.size();
        }
    }
}