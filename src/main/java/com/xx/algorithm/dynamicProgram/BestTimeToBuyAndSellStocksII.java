package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/2/23
 * <p>
 * 买卖股票的最佳时机 II
 * LeetCode 122
 * <p>
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * <p>
 * 注意点：本题与 买卖股票I 的不同是，可以进行多次交易，只限制每次手上仅一股。
 * <p>
 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 * 总利润为 4 + 3 = 7 。
 * <p>
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 总利润为 4 。
 * <p>
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
 * <p>
 * 回溯法，参考{@link com.xx.algorithm.backTracking.BestTimeToBuyAndSellStocksII}
 */
public class BestTimeToBuyAndSellStocksII implements Answer {

    private int max = 0;

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStocksII().answerOne();
    }

    /**
     * 解1：用动态规划来做。
     * 回溯法请参考backTracking里的解法。
     * <p>
     * 考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
     * 定义状态
     * dp[i][0]表示第 i 天交易完后手里没有股票的最大利润，
     * dp[i][1]表示第 i 天交易完后手里持有一支股票的最大利润（i 从 0开始）。
     */
    @Override
    public void answerOne() {
        int[] prices = initData();
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            if (i < 1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
            } else {
                //Max(今天什么都不做最大利润与前一天相同，或者今天卖出即前一天持有一只股票的状态变为卖出)
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                //Max(今天什么都不做持有的股票与昨天一致，或者今天买入即前一天未持有一只股票的状态变为买入)
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
        }
        System.out.println(dp[prices.length - 1][0]);
        System.out.println(Arrays.deepToString(dp));
    }


    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        //return new int[]{7, 1, 5, 3, 6, 4};
        return new int[]{1, 2, 3, 4, 5};
        //return new int[]{7, 6, 4, 3, 1};
    }
}
