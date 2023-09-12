package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/6
 * <p>
 * 最长递增子序列
 * LeetCode 300
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 */
public class LongestIncreasingSubsequence implements Answer {

    public static void main(String[] args) {
        new LongestIncreasingSubsequence().answerOne();
    }

    /**
     * 解1：简单DP试试
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int[] dp = new int[data.length];
        Arrays.parallelSetAll(dp, i -> 1);
        int max = 1;
        for (int i = 0; i < data.length; i++) {
            System.out.println(Arrays.toString(dp));
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] > data[i]) {
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
                    max = Math.max(max, dp[j]);
                }
            }
        }
        System.out.println(max);
        System.out.println(Arrays.toString(dp));
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        //return new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        return new int[]{0, 1, 0, 3, 2, 3};
    }
}
