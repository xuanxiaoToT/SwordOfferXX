package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author 玄霄
 * @CreateDate 2023/3/5
 * 最大正方形
 * LeetCoce 221
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
 */
public class MaximalSquare implements Answer {

    public static void main(String[] args) {
        new MaximalSquare().answerTwo();
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
     */
    public void answerTwo() {
        int max = 0;
        int[][] data = initData();
        int[][] dp = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1) {
                    dp[i][j] = 1;
                    max = Math.max(1, max);
                }
                if (i > 0 && j > 0 && data[i][j] == 1 && data[i - 1][j] == 1 && data[i][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(dp[i][j] * dp[i][j], max);
                }
            }
        }
        System.out.println(max);
        System.out.println(Arrays.deepToString(dp));
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
