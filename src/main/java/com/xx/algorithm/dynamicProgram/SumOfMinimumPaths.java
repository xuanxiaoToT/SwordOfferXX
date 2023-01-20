package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/13
 * 最小路径之和
 * 在一个m×n（m、n均大于0）的格子中，每个位置都有一
 * 个数字。一个机器人每步只能向下或向右，请计算它从格子的左上
 * 角到达右下角的路径的数字之和的最小值。例如，从图14.8中3×3
 * 的格子的左上角到达右下角的路径的数字之和的最小值是8，图中数
 * 字之和最小的路径用灰色背景表示。
 * <p>
 * 此题同《最大礼物的价值》
 */
public class SumOfMinimumPaths implements Answer {
    public static void main(String[] args) {
        new SumOfMinimumPaths().answerOne();
    }

    /**
     * fix:同样  可以优化dp使用的存储空间。变为2两行的即可
     */
    @Override
    public void answerOne() {
        int[][] data = initData();
        int[][] dp = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + data[i][j];
                } else {
                    if (i > 0) {
                        dp[i][j] = data[i][j] + dp[i - 1][j];
                    }
                    if (j > 0) {
                        dp[i][j] = data[i][j] + dp[i][j - 1];
                    }
                    if (i == 0 && j == 0) {
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
        return new int[][]{{1, 3, 1}, {2, 5, 2}, {3, 4, 1}};
    }
}