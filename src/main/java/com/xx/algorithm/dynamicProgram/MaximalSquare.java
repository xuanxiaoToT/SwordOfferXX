package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/5
 * <p>
 * 最大正方形
 * LeetCoce 221 Medium
 * <p>
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 * <p>
 * Tag:  二维动态规划
 */
public class MaximalSquare implements Answer {

    public static void main(String[] args) {
        new MaximalSquare().answerOne();
    }

    /**
     * 解1：参考《矩阵中的最大矩形》解法
     * 解法略
     */
    @Override
    public void answerOne() {
        // 略
    }

    /**
     * 利用动态规划
     * 动态规划最重要的就是想清楚 d[i,j] 代表着什么
     * dp[i][j]代表：正方形以 data[i][j] 作为右下角时的最大边长值。
     */
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1;
                    max = Math.max(1, max);
                }
                if (i > 0 && j > 0 && matrix[i][j] == '1' && matrix[i - 1][j] == '1' && matrix[i][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(dp[i][j] * dp[i][j], max);
                }
            }
        }
        return max;
    }

    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        //return new int[][]{{1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};
        //return new int[][]{{0, 1}, {1, 0}};
        //return new int[][]{{0}};
        return new int[][]{{1, 1, 1, 1, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {0, 0, 1, 1, 1}};
    }
}
