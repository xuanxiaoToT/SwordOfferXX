package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/13
 * <p>
 * 最少的硬币个数
 * LeetCode 322  零钱兑换  Medium
 * <p>
 * 给定不同面额的硬币 coins 和金额 amount，计算凑成总金额所需的最少的硬币个数
 * <p>
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 * <p>
 * Tag: 动态规划  记忆化搜索
 */
public class MinimumNumberOfCoins implements Answer {

    public static void main(String[] args) {
        new MinimumNumberOfCoins().answer();
    }

    /**
     * 如果将每种面额的硬币看成一种物品，而将目标总额看成
     * 背包的容量，那么这个问题等价于求将背包放满时物品的最少件数。
     * 值得注意的是，这里每种面额的硬币可以使用任意多次，因此这个问
     * 题不再是0-1背包问题，而是一个无界背包问题（也叫完全背包问题）。
     */
    @Override
    public void answer() {
        int[] coins = new int[]{2};
        int amount = 3;
        System.out.println(coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.parallelSetAll(dp, i -> max);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount] == max ? -1 : dp[amount];
    }

    /**
     * 输入数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 3, 9, 10};
    }
}