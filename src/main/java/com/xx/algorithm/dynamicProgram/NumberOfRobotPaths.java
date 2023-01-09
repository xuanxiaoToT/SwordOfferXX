package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author 玄霄
 * @CreateDate 2022/12/12
 * 机器人路径的数目
 * 一个机器人从m×n的格子的左上角出发，它每步要么向
 * 下要么向右，直到抵达格子的右下角。请计算机器人从左上角到达
 * 右下角的路径的数目。
 * 例如，如果格子的大小是3×3，那么机器人
 * 从左上角到达右下角有6条符合条件的不同路径
 */
public class NumberOfRobotPaths implements Answer {

    public static void main(String[] args) {
        new NumberOfRobotPaths().answerOne();
    }

    /**
     * 同《最大礼物》
     * 矩阵路径问题
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int m = data[0];
        int n = data[1];
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{3, 3};
    }
}