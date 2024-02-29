package com.xx.algorithm.dynamicProgram.二维DP;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/12
 * <p>
 * 最大价值的礼物
 * <p>
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * <p>
 * 举例：
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 */
public class MaximumValueOfGifts implements Answer {

    private int maxResult;
    private int maxRow;
    private int maxCol;

    public static void main(String[] args) {
        new MaximumValueOfGifts().answerOne();
    }

    @Override
    public void answerOne() {
        int[][] data = initData();
        maxRow = data.length;
        maxCol = data[0].length;
        find(data, 0, 0, 0);
        System.out.println(maxResult);
    }

    // 回溯法
    private void find(int[][] data, int row, int col, int currentSum) {
        if (row >= maxRow || col >= maxCol) {
            return;
        }
        if (row == maxRow - 1 && col == maxCol - 1) {
            currentSum = currentSum + data[row][col];
            if (currentSum > maxResult) {
                maxResult = currentSum;
            }
            return;
        }
        if (row < maxRow) {
            find(data, row + 1, col, currentSum + data[row][col]);
        }
        if (col < maxCol) {
            find(data, row, col + 1, currentSum + data[row][col]);
        }
    }

    // 采用DP策略。该位置的最大值=Max(左侧,上方)+自己的值。
    public void answerTwo() {
        int[][] data = initData();
        int row = data.length;
        int col = data[0].length;

        int[][] dp = new int[row][col];

        // 初始化 dp[0][0]，由于只有一个元素
        // 所以 dp[0][0] 的最优解就是 grid[0][0] 这个元素
        dp[0][0] = data[0][0];

        // i 从 1 遍历到 n - 1
        // 获取第 0 行中第 i 列的最优解
        // 由于每次只能向下或者向右移动一步，此时只能向右移动一步
        for (int i = 1; i < col; i++) {
            // 所以对于只有一行的情况，当前位置的最优解等于前一列的最优解加上该列的值
            dp[0][i] = dp[0][i - 1] + data[0][i];
        }

        // j 从 1 遍历到 n - 1
        // 获取第 j 行中第 0 列的最优解
        for (int j = 1; j < row; j++) {
            // 所以对于只有一列的情况，当前位置的最优解等于前一行的最优解加上该行的值
            dp[j][0] = dp[j - 1][0] + data[j][0];
        }

        // 接下来从第 1 行到第 m - 1 行
        // 从第 1 列到底 n - 1 列
        // 填充二维数组 grid 里面的值
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // 由于每次只能向下或者向右移动一步
                // 位置 (i,j) 的最优解
                // 等于当前位置上方位置(i-1,j)的最优解和左侧位置(i,j-1)的最优解的较大值
                // 再加上当前位置的值
                int max = Math.max(dp[i - 1][j], dp[i][j - 1]);
                // 把较大值和
                dp[i][j] = max + data[i][j];
            }
        }
        // 返回从起始位置到最后一个位置的最大礼物价值
        System.out.println(dp[row - 1][col - 1]);
    }

    @Override
    public int[][] initData() {
        return new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    }
}