package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/13
 * 背包问题
 * 有 n 个物品和一个承重为 m 的背包. 给定数组 W表示每个物品的重量和数组 V 表示每个物品的价值。
 * 问最多能装入背包的总价值是多大?
 * 例：n=5，m = 10, W = [2, 2, 6, 5, 4], V = [6, 3, 5, 4, 6]
 * <p>
 * 结果为：15
 */
public class KnapsackProblem implements Answer {

    public static void main(String[] args) {
        new KnapsackProblem().answerOne();
    }

    /**
     * 解:result[n] = C>W[n] ? max(result[n-1]+W[n],result[n-1]) : result[n-1];
     */
    @Override
    public void answerOne() {
        int[][] data = initData();
        int[] weight = data[0];
        int[] value = data[1];
        int n = 5;
        int m = 10;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    // i==0：一个物品不选，那无论剩余背包容量多少，都是0
                    // j==0：背包容量为0，无论怎么选都是0;
                    dp[i][j] = 0;
                } else {
                    // 不选当前物品
                    int noChoose = dp[i - 1][j];
                    if (j >= weight[i - 1]) {
                        // 选择第i个物品，之前预留加上其本身价值。
                        // dp[i - 1][j - weight[i - 1]]表示i-1个物品之前，并且需要预留出放入i个物品的背包容量
                        int choose = dp[i - 1][j - weight[i - 1]] + value[i - 1];
                        dp[i][j] = Math.max(noChoose, choose);
                    } else {
                        dp[i][j] = noChoose;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{2, 2, 6, 5, 4}, {6, 3, 5, 4, 6}};
    }
}
