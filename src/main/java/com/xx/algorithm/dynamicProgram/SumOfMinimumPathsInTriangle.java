package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/13
 * <p>
 * 三角形中最小路径之和
 * <p>
 * 在一个由数字组成的三角形中，第1行有1个数字，第2行
 * 有2个数字，以此类推，第n行有n个数字。例如，图14.9是一个包含
 * 4行数字的三角形。如果每步只能前往下一行中相邻的数字，请计算
 * 从三角形顶部到底部的路径经过的数字之和的最小值。如图14.9所
 * 示，从三角形顶部到底部的路径数字之和的最小值为11，对应的路
 * 径经过的数字用阴影表示。
 */
public class SumOfMinimumPathsInTriangle implements Answer {

    public static void main(String[] args) {
        new SumOfMinimumPathsInTriangle().answerOne();
    }

    /**
     * 矩阵路径问题
     */
    @Override
    public void answerOne() {
        int[][] data = initData();
        int[][] dp = new int[data.length][data[data.length - 1].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j > i) {
                    continue;
                }
                if (i > 0 && j > 0) {
                    // 三角结构：从上面来，从左斜上方来 一共2个方向
                    if (dp[i - 1][j] > 0) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + data[i][j];
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + data[i][j];
                    }
                } else {
                    if (i > 0) {
                        dp[i][j] = dp[i - 1][j] + data[i][j];
                    }
                    if (i == 0) {
                        dp[i][j] = data[i][j];
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * something
     */
    @Override
    public int[][] initData() {
        return new int[][]{{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
    }
}