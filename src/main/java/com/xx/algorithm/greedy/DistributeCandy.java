package com.xx.algorithm.greedy;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/26
 * <p>
 * 分发糖果
 * LeetCode 135. Hard
 * <p>
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 * <p>
 * 示例 1：
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * <p>
 * 示例 2：
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 * <p>
 * 提示：
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 */
public class DistributeCandy implements Answer {

    public static void main(String[] args) {
        new DistributeCandy().answer();
    }

    /**
     * 这道题目一定是要确定一边之后，再确定另一边，例如比较每一个孩子的左边，然后再比较右边，如果两边一起考虑一定会顾此失彼。
     * 先确定右边评分大于左边的情况（也就是从前向后遍历）
     * 此时局部最优：只要右边评分比左边大，右边的孩子就多一个糖果，全局最优：相邻的孩子中，评分高的右孩子获得比左边孩子更多的糖果
     * 局部最优可以推出全局最优。
     * <p>
     * 如果ratings[i] > ratings[i - 1] 那么[i]的糖 一定要比[i - 1]的糖多一个，
     * 所以贪心：candyVec[i] = candyVec[i - 1] + 1
     */
    @Override
    public void answer() {
        int[] ratings = initData();
        int[] dp = new int[ratings.length];
        //从左往右,上升结果
        for (int i = 0; i < ratings.length; i++) {
            dp[i] = 1;
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int sum = 0;
        //从右往左，下降结果
        for (int i = ratings.length - 1; i >= 0; i--) {
            if (i < ratings.length - 1 && ratings[i] > ratings[i + 1]) {
                dp[i] = dp[i + 1] >= dp[i] ? dp[i + 1] + 1 : dp[i];
            }
            sum = sum + dp[i];
        }
        System.out.println(sum);
        System.out.println(Arrays.toString(dp));
    }

    @Override
    public int[] initData() {
        // 5
        // return new int[]{1, 0, 2};
        // 4
        //return new int[]{1, 2, 2};
        return new int[]{1, 2, 87, 87, 87, 2, 1};
        //return new int[]{1, 3, 4, 5, 2};
    }
}