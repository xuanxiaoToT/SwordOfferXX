package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/4/27
 * <p>
 * 爱生气的书店老板
 * LeetCode  1052. Medium
 * <p>
 * 有一个书店老板，他的书店开了 n 分钟。每分钟都有一些顾客进入这家商店。给定一个长度为 n 的整数数组 customers ，
 * 其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 minutes 分钟不生气，但却只能使用一次。
 * 请你返回 这一天营业下来，最多有多少客户能够感到满意 。
 * <p>
 * 示例 1：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
 * 输出：16
 * 解释：书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * <p>
 * 示例 2：
 * 输入：customers = [1], grumpy = [0], minutes = 1
 * 输出：1
 * <p>
 * 提示：
 * n == customers.length == grumpy.length
 * 1 <= minutes <= n <= 2 * 10^4
 * 0 <= customers[i] <= 1000
 * grumpy[i] == 0 or 1
 * <p>
 * Tag：滑动窗口
 */
public class AngryBookstoreOwner implements Answer {

    public static void main(String[] args) {
        new AngryBookstoreOwner().answer();
    }

    @Override
    public void answer() {
        int[] customers = {4, 10, 10};
        int[] grumpy = {1, 1, 0};
        int x = 2;
        System.out.println(maxSatisfied(customers, grumpy, x));
    }

    /**
     * 连续N分钟不生气，等于长度为N的滑动窗口
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int result = 0;
        for (int i = 0; i < minutes; i++) {
            result += customers[i];
        }
        // 这些时间点，本身就不生气，无论如何都会满意
        for (int i = minutes; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                result += customers[i];
            }
        }
        int max = result;
        for (int right = minutes; right < customers.length; right++) {
            int left = right - minutes;
            // 原来生气的，现在变为不生气了
            if (grumpy[right] == 1) {
                result += customers[right];
            }
            // 左侧原来就是生气的，踢出这个窗口后，需要减去
            if (grumpy[left] == 1) {
                result -= customers[left];
            }
            max = Math.max(max, result);
        }
        return max;
    }

    @Override
    public Object initData() {
        return null;
    }
}
