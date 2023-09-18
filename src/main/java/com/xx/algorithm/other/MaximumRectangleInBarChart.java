package com.xx.algorithm.other;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/14
 * <p>
 * 柱状图中最大的矩形
 * LeetCode 84   困难
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * <p>
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * 提示：
 * 1 <= heights.length <=10**5
 * 0 <= heights[i] <= 10**4
 */
public class MaximumRectangleInBarChart implements Answer {

    public static void main(String[] args) {
        new MaximumRectangleInBarChart().answerOne();
    }

    /**
     * 解法1：无脑遍历
     * 毫不意外的会超出时间限制
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            int heigh = data[i];
            for (int j = i; j < data.length; j++) {
                heigh = Math.min(data[j], heigh);
                max = Math.max(heigh * (j - i + 1), max);
            }
        }
        System.out.println(max);
    }

    public void answerTwo() {

    }

    @Override
    public int[] initData() {
        // return new int[]{2, 1, 5, 6, 2, 3};
        //return new int[]{2, 4};
        return new int[]{2, 1, 2};
    }
}
