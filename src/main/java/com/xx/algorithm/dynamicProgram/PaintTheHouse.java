package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/1
 * <p>
 * 粉刷房子
 * <p>
 * 一排n幢房子要粉刷成红色、绿色和蓝色，不同房子被粉
 * 刷成不同颜色的成本不同。用一个n×3的数组表示n幢房子分别用3
 * 种颜色粉刷的成本。要求任意相邻的两幢房子的颜色都不一样，请
 * 计算粉刷这n幢房子的最少成本。
 * 例如，粉刷3幢房子的成本分别为
 * [[17，2，16]，[15，14，5]，[13，3，1]]，如果分别将这3幢房子
 * 粉刷成绿色、蓝色和绿色，那么粉刷的成本是10，是最少的成本。
 */
public class PaintTheHouse implements Answer {

    public static void main(String[] args) {
        new PaintTheHouse().answer();
    }

    /**
     * f(i,j) 第i个房子，刷j颜色。
     */
    @Override
    public void answer() {
        int[][] data = initData();
        // dp可以精简，每次需要的是一维数组 长度为3即可。
        int[][] dp = new int[data.length][3];
        dp[0] = data[0];
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                switch (j) {
                    case 0:
                        dp[i][j] = Math.min(dp[i - 1][1], dp[i - 1][2]) + data[i][j];
                        break;
                    case 1:
                        dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][2]) + data[i][j];
                        break;
                    case 2:
                        dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][1]) + data[i][j];
                        break;
                    default:
                        break;
                }
            }
        }
        // 求dp[data.length-1]中的最小值即可
        System.out.println(Arrays.toString(dp[data.length - 1]));
    }

    /**
     * something
     */
    @Override
    public int[][] initData() {
        return new int[][]{{17, 2, 16}, {15, 14, 5}, {13, 3, 1}};
    }
}