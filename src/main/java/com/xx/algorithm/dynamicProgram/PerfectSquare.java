package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/2/27
 * <p>
 * 完全平方数
 * LeetCode 279
 * <p>
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * <p>
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 */
public class PerfectSquare implements Answer {

    public static void main(String[] args) {
        new PerfectSquare().answerOne();
    }

    /**
     * 解1：动态规划，参考《最少的硬币个数》
     */
    @Override
    public void answerOne() {
        int n = initData();
        int[] dp = new int[n + 1];
        for (int i = 0; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < Math.sqrt(i) + 1; j++) {
                int square = j * j;
                if (i >= square) {
                    if (dp[i - square] < min) {
                        min = dp[i - square];
                    }
                }
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = 0;
            } else {
                dp[i] = min + 1;
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);
    }

    public int answerTwo() {
        int n = initData();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 最坏的情况就是每次+1
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                // 动态转移方程
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 输出数据
     */
    @Override
    public Integer initData() {
        return 13;
    }
}
