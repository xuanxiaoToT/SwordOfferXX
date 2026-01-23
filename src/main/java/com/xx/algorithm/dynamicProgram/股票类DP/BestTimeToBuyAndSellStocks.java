package com.xx.algorithm.dynamicProgram.股票类DP;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/24
 * <p>
 * 买卖股票的最佳时机
 * LeetCode 121 Easy
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class BestTimeToBuyAndSellStocks implements Answer {

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStocks().answerTwo();
    }

    /**
     * 解1：DP法 DP(i,0)表示持有股票的状态,DP(i,1)表示未持有股票的状态。
     */
    @Override
    public void answer() {
        int[] price = initData();
        int[][] dp = new int[price.length][2];

        dp[0][0] = -price[0];
        dp[0][1] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -price[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + price[i], dp[i - 1][1]);
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * 解2：最简单的，双循环。解法略
     */
    public void answerTwo() {
        int[] price = initData();
        int max = 0;
        for (int i = price.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                max = Math.max(price[i] - price[j], max);
            }
        }
        System.out.println(max);
    }


    /**
     * 解2：单循环遍历法。购买股票使得其总是最低点买入。
     */
    public void answerThree() {
        int[] prices = initData();
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int price : prices) {
            if (price < minprice) {
                minprice = price;
            } else if (price - minprice > maxprofit) {
                maxprofit = price - minprice;
            }
        }
        System.out.println(maxprofit);
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{7, 1, 5, 3, 6, 4};
        //return new int[]{7, 6, 4, 3, 1};
    }
}
