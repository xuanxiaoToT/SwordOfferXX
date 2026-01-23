package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;
import com.xx.util.NumberUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/5
 * <p>
 * 不含连续1的非负整数
 * LeetCode 600. Hard
 * <p>
 * 给定一个正整数 n ，请你统计在 [0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
 * <p>
 * 示例 1:
 * 输入: n = 5
 * 输出: 5
 * 解释:
 * 下面列出范围在 [0, 5] 的非负整数与其对应的二进制表示：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * 其中，只有整数 3 违反规则（有两个连续的 1 ），其他 5 个满足规则。
 * <p>
 * 示例 2:
 * 输入: n = 1
 * 输出: 2
 * <p>
 * 示例 3:
 * 输入: n = 2
 * 输出: 3
 * <p>
 * 提示:
 * 1 <= n <= 10^9
 */
public class NonNegativeIntegersWithoutConsecutiveOnes implements Answer {

    public static void main(String[] args) {
        new NonNegativeIntegersWithoutConsecutiveOnes().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int n = 5;
        System.out.println(findIntegers(n));
    }

    /**
     * 思路正确，但是会超时
     */
    public int findIntegers(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int deep = 0;
        int maxDeep = Integer.toBinaryString(n).length();
        while (!queue.isEmpty() && deep <= 31) {
            int size = queue.size();
            if (deep == maxDeep) {
                return size;
            }
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                int next = poll << 1;
                if (next <= n) {
                    if ((poll & 1) == 1) {
                        queue.add(next);
                    } else {
                        queue.add(next);
                        int nextOne = NumberUtil.setBit(next, 1);
                        if (nextOne <= n) {
                            queue.add(nextOne);
                        }
                    }
                }
            }
            deep++;
        }
        return 0;
    }

    /**
     * 利用动态规划
     * todo：原理 0806
     */
    public int findIntegers2(int n) {
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            if ((n & val) != 0) {
                res += dp[i + 1];
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }
        return res;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
