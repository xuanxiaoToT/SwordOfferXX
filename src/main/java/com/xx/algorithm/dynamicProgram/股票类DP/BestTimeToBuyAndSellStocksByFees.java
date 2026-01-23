package com.xx.algorithm.dynamicProgram.股票类DP;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/1
 * <p>
 * 买卖股票的最佳时机含手续费
 * LeetCode 714
 * <p>
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，
 * 在卖出它之前你就不能再继续购买股票了。返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1：
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * <p>
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 */
public class BestTimeToBuyAndSellStocksByFees implements Answer {
    public static void main(String[] args) {
        new BestTimeToBuyAndSellStocksByFees().answer();
    }

    /**
     * 解1：解法完全同{@link BestTimeToBuyAndSellStocksII}
     */
    @Override
    public void answer() {
        int fee = 2;
        int[] prices = initData();
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            if (i < 1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
            } else {
                //Max(今天什么都不做最大利润与前一天相同，或者今天卖出即前一天持有一只股票的状态变为卖出)
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
                //Max(今天什么都不做持有的股票与昨天一致，或者今天买入即前一天未持有一只股票的状态变为买入)
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        //return new int[]{1, 3, 7, 5, 10, 3};
        return new int[]{1, 3, 2, 8, 4, 9};
    }
}
