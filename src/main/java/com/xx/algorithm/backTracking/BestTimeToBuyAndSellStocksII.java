package com.xx.algorithm.backTracking;

import com.xx.Answer;

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
 *
 * 关于动态规划的解法，参考{@link com.xx.algorithm.dynamicProgram.股票类DP.BestTimeToBuyAndSellStocksII}
 */
public class BestTimeToBuyAndSellStocksII implements Answer {

    private int max = 0;

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStocksII().answerOne();
    }

    /**
     * 解1：回溯法
     */
    @Override
    public void answerOne() {
        int[] prices = initData();
        diGui(0, prices, 0, false, 0);
        System.out.println(max);
    }

    private void diGui(int index, int[] prices, int temp, boolean hasBuy, int buyP) {
        if (index >= prices.length) {
            return;
        }
        int f = temp;
        // 可以卖出
        if (hasBuy) {
            // 卖
            temp = temp + prices[index] - buyP;
            this.max = Math.max(temp, max);
            diGui(index + 1, prices, temp, false, 0);

            // 不卖
            diGui(index + 1, prices, f, hasBuy, buyP);
        } else {
            // 买入
            diGui(index + 1, prices, temp, true, prices[index]);
            // 什么都不做
            diGui(index + 1, prices, temp, hasBuy, buyP);
        }
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        //return new int[]{7, 1, 5, 3, 6, 4};
        //return new int[]{1, 2, 3, 4, 5};
        return new int[]{7, 6, 4, 3, 1};
    }
}
