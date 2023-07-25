package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/13
 * <p>
 * 最佳买卖股票时机含冷冻期
 * LeetCode 309.
 * <p>
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 示例 2:
 * 输入: prices = [1]
 * 输出: 0
 */
public class BestTimeToBuyAndSellStocksFreezingPeriod implements Answer {

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStocksFreezingPeriod().answerTwo();
    }

    /**
     * 解1：三种状态：持有股票状态、不持有股票状态(不在冷冻期)、处于冷冻期内(一定是不持有股票)
     * 0：不持有股票
     * 1：持有
     */
    @Override
    public void answerOne() {
        int[] prices = initData();
        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (i >= 2) {
                //持有股票。昨天就持有、今天新买的(分为昨天没有和前天卖出两种情况)
                //昨天没有又分为两类情况，即前天没有，前天有。其中前天有不能存在，因为昨天卖出，今天就是冷冻期，不允许买入。
                //那么只能是前天没有，此时跟前天卖出是一种状态，即都是dp[i - 2][0]。
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            } else {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * 尝试用三个状态来做：
     * 要注意，其代表的是第i天结束后的状态。
     * 0.没有股票，且不在冷静期
     * 1.持有股票
     * 2.没有股票，昨天刚卖
     * <p>
     * 注意：f(i)代表的是i天结束后的状态，不是i天当时的状态。
     */
    public void answerTwo() {
        int[] prices = initData();
        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        for (int i = 1; i < prices.length; i++) {
            //持有股票。昨天就持有、今天新买的
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            //未持有股票。并且不能卖，因为卖完就算冷静期了，就属于[i][2]了
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = dp[i - 1][1] + prices[i];

        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {

        return new int[]{1, 2, 3, 0, 2};
        //return new int[]{6, 1, 6, 4, 3, 0, 2};
    }
}
