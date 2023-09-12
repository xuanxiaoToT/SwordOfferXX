package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/13
 * <p>
 * 最少的硬币个数
 * LeetCode 322
 * <p>
 * 给定不同面额的硬币 coins 和金额 amount，计算凑成总金额所需的最少的硬币个数
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11 输出: 3 解释: 11 = 5 + 5 + 1
 * coins = [1, 3, 9, 10], amount = 15 输出: 3
 */
public class MinimumNumberOfCoins implements Answer {

    public static void main(String[] args) {
        new MinimumNumberOfCoins().answerOne();
    }

    /**
     * * 如果将每种面额的硬币看成一种物品，而将目标总额看成
     * * 背包的容量，那么这个问题等价于求将背包放满时物品的最少件数。
     * * 值得注意的是，这里每种面额的硬币可以使用任意多次，因此这个问
     * * 题不再是0-1背包问题，而是一个无界背包问题（也叫完全背包问
     * * 题）。
     */
    @Override
    public void answerOne() {
        int[] coins = initData();
        Arrays.sort(coins);
        int amount = 15;
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin) {
                    if (dp[i - coin] < min) {
                        min = dp[i - coin];
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
    }

    private void answerTwo() {
        // 采用贪心算法，直接先拿大的，不够再拿后面的。以此类推。
        //  算法略
    }

    /**
     * 输入数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 3, 9, 10};
    }
}