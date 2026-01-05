package com.xx.algorithm.math;

import com.xx.Answer;

/**
 * 最大方阵和
 * LeetCode 1975. Medium
 * <p>
 * 给你一个 n x n 的整数方阵 matrix 。你可以执行以下操作 任意次 ：
 * <p>
 * 选择 matrix 中 相邻 两个元素，并将它们都 乘以 -1 。
 * 如果两个元素有 公共边 ，那么它们就是 相邻 的。
 * <p>
 * 你的目的是 最大化 方阵元素的和。请你在执行以上操作之后，返回方阵的 最大 和。
 * <p>
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,-1],[-1,1]]
 * 输出：4
 * 解释：我们可以执行以下操作使和等于 4 ：
 * - 将第一行的 2 个元素乘以 -1 。
 * - 将第一列的 2 个元素乘以 -1 。
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * 输出：16
 * 解释：我们可以执行以下操作使和等于 16 ：
 * - 将第二行的最后 2 个元素乘以 -1 。
 * <p>
 * 提示：
 * n == matrix.length == matrix[i].length
 * 2 <= n <= 250
 * -10^5 <= matrix[i][j] <= 10^5
 * <p>
 * Tag：数学  贪心
 */
public class MaximumMatrixSum implements Answer {
    public static void main(String[] args) {
        MaximumMatrixSum maximumMatrixSum = new MaximumMatrixSum();
        maximumMatrixSum.answerOne();
    }

    @Override
    public void answerOne() {
        // int[][] matrix = new int[][]{{1, -1}, {-1, 1}};
        // int[][] matrix = new int[][]{{1, 2, 3}, {-1, -2, -3}, {1, 2, 3}};
        int[][] matrix = new int[][]{{-1, 0, -1}, {-2, 1, 3}, {3, 2, 2}};
        System.out.println(maxMatrixSum(matrix));
    }

    /**
     *
     * 原理：
     * 如果矩阵中存在两个负数，那么经过移动，总是可以将其转换为两个正数。
     * 所以如果负数是偶数个，那么一定可以全部转换为正数。
     * <p>
     * 思路想到了，但是做的很糙，很多边界条件都没有想到
     * 比如为0的时候；
     * 比如最小值的计算问题。选择问题
     * 想清楚，测试清楚后，再进行提交，一定要注意看取值范围
     */
    public long maxMatrixSum(int[][] matrix) {
        long result = 0L;
        int fuCount = 0;
        int zeroCount = 0;
        int min = Integer.MAX_VALUE;
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (ints[j] < 0) {
                    fuCount++;
                }
                if (ints[j] == 0) {
                    zeroCount++;
                }
                min = Math.min(min, Math.abs(ints[j]));
                result = result + Math.abs(ints[j]);
            }
        }
        if (fuCount % 2 == 0 || zeroCount > 0) {
            return result;
        }
        return result + Math.abs(min) * -2L;
    }

    @Override
    public Object initData() {
        return null;
    }
}
