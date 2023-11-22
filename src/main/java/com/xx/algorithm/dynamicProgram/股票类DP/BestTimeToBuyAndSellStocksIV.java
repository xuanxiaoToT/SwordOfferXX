package com.xx.algorithm.dynamicProgram.股票类DP;

import com.xx.Answer;
import com.xx.util.MathUtil;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/31
 * <p>
 * 买卖股票的最佳时机 IV
 * LeetCode 188.
 * <p>
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * <p>
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */
public class BestTimeToBuyAndSellStocksIV implements Answer {
    public static void main(String[] args) {
        new BestTimeToBuyAndSellStocksIV().answerTwo();
    }

    /**
     * 解1：三个状态的dp
     * [i][j][k]:
     * i表示第几天。
     * j表示是否持有。0：不持有。1：持有。
     * k表示已经交易了k次，购买后的当天更新。
     * 可以参考{@link BestTimeToBuyAndSellStocksIII}的answerOne解法
     * <p>
     * 关键点：注意null值的处理。null表示不可能存在的情况。
     * 对于卖出时，不可能存在时，便不允许卖出。
     * 对于买入时，不可能存在时(前值)，表示本次第一次买入，应该按0处理。
     */
    @Override
    public void answerOne() {
        int k = 2;
        int[] prices = initData();
        Integer[][][] dp = new Integer[prices.length][2][k + 1];
        dp[0][0][0] = 0;
        dp[0][1][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = dp[i - 1][0][0];
            dp[i][1][0] = null;
            for (int j = 1; j <= k; j++) {
                dp[i][0][j] = dp[i - 1][1][j] != null ? MathUtil.intMaxByNull(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]) : dp[i - 1][0][j];
                dp[i][1][j] = MathUtil.intMaxByNull(dp[i - 1][1][j], MathUtil.nvl(dp[i - 1][0][j - 1], 0) - prices[i]);
            }
        }
        System.out.println(Arrays.deepToString(dp[prices.length - 1][0]));
        System.out.println(MathUtil.intMaxByNull(0, dp[prices.length - 1][0]));
    }

    /**
     * 同样的，本题也可以用二元dp来处理。
     * 参考{@link BestTimeToBuyAndSellStocksIII}的answerTwo解法
     * <p>
     * 0 不持有，0次完成。
     * 1 持有，1次完成。
     * 2 不持有，1次完成。
     * 3 持有，2次完成。
     * 4 不持有，2次完成
     * 以此类推。
     */
    public void answerTwo() {
        int k = 2;
        int[] prices = initData();
        Integer[][] dp = new Integer[prices.length][2 * k + 1];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = null;
            for (int j = 1; j <= 2 * k; j++) {
                if (j % 2 != 0) {
                    //奇数，持有
                    dp[i][j] = MathUtil.intMaxByNull(dp[i - 1][j], MathUtil.nvl(dp[i - 1][j - 1], 0) - prices[i]);
                } else {
                    //偶数，不持有
                    dp[i][j] = dp[i - 1][j - 1] != null ? MathUtil.intMaxByNull(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]) : dp[i - 1][j];
                }
            }
            //System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(Arrays.toString(dp[dp.length - 1]));
    }


    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{3, 2, 6, 5, 0, 3};
    }
}
