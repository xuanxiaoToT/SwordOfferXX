package com.xx.algorithm.dynamicProgram.股票类DP;

import com.xx.Answer;
import com.xx.util.MathUtil;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/25
 * <p>
 * 买卖股票的最佳时机 III
 * LeetCode 123.
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，
 * 这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，
 * 这笔交易所能获得利润 = 4-1 = 3 。
 * <p>
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 示例 4：
 * 输入：prices = [1]
 * 输出：0
 */
public class BestTimeToBuyAndSellStocksIII implements Answer {

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStocksIII().answerTwo();
    }

    /**
     * 解1：先分析股票的状态：i天，是否持有股票、两次机会是否用完。一共两种状态
     * 故我们假设其DP(i,j,k) i表示天数，j表示持有股票，k表示进行了几次交易。
     * 比如 dp[2][1][1] 表示2天，持有股票，进行了1次交易。
     * 注意：买入后，就算进行了一次交易。
     */
    @Override
    public void answerOne() {
        Integer[] prices = initData();
        Integer[][][] dp = new Integer[prices.length][2][3];
        dp[0][0][0] = 0;
        dp[0][0][1] = null;
        dp[0][0][2] = null;
        dp[0][1][0] = null;
        dp[0][1][1] = -prices[0];
        dp[0][1][2] = null;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = dp[i - 1][0][0];
            //不持有股票，且进行了1次交易，则只有把之前的卖出
            dp[i][0][1] = dp[i - 1][1][1] != null ? MathUtil.intMaxByNull(dp[i - 1][0][1], dp[i - 1][1][1] + prices[i]) : dp[i - 1][0][1];
            //不持有股票，且进行了2次交易，则只有把之前的卖出
            dp[i][0][2] = dp[i - 1][1][2] != null ? MathUtil.intMaxByNull(dp[i - 1][0][2], dp[i - 1][1][2] + prices[i]) : dp[i - 1][0][2];
            // 不可能存在
            dp[i][1][0] = null;
            //想持有股票，且进行了1次交易，则需要本次买入，且之前为0次交易。
            dp[i][1][1] = MathUtil.intMaxByNull(dp[i - 1][1][1], MathUtil.nvl(dp[i - 1][0][0], 0) - prices[i]);
            //想持有股票，且进行了2次交易，则需要本次买入，且之前为1次交易。
            dp[i][1][2] = MathUtil.intMaxByNull(dp[i - 1][1][2], MathUtil.nvl(dp[i - 1][0][1], 0) - prices[i]);

            System.out.println(Arrays.deepToString(dp));
        }

        System.out.println(MathUtil.intMaxByNull(0, dp[prices.length - 1][0]));
    }

    /**
     * 尝试用二维状态
     * 每天交易完成后有5个状态：
     * 0:不持有，且未完成交易。
     * 1:不持有，且完成1次交易。
     * 2:不持有，且完成2次交易。
     * 3:持有，且完成1次交易。
     * 4:持有，且完成2次交易。
     */
    public void answerTwo() {
        Integer[] prices = initData();
        Integer[][] dp = new Integer[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = null;
        dp[0][2] = null;
        dp[0][3] = -prices[0];
        dp[0][4] = null;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = MathUtil.intMaxByNull(dp[i - 1][1], dp[i - 1][3] + prices[i]);
            dp[i][2] = dp[i - 1][4] != null ? MathUtil.intMaxByNull(dp[i - 1][2], dp[i - 1][4] + prices[i]) : dp[i - 1][2];
            dp[i][3] = MathUtil.intMaxByNull(dp[i - 1][3], dp[i - 1][0] - prices[i]);
            dp[i][4] = MathUtil.intMaxByNull(dp[i - 1][4], MathUtil.nvl(dp[i - 1][1], 0) - prices[i]);
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * 输出数据
     */
    @Override
    public Integer[] initData() {
        return new Integer[]{3, 3, 5, 0, 0, 3, 1, 4};
        //return new Integer[]{1, 2, 3, 4, 5};
        //return new Integer[]{7, 6, 4, 3, 1};
    }
}
