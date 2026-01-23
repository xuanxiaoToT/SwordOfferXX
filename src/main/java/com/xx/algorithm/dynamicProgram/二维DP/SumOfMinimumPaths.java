package com.xx.algorithm.dynamicProgram.二维DP;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/13
 * <p>
 * 最小路径和
 * LeetCode 64. Medium
 * <p>
 * 在一个m×n（m、n均大于0）的格子中，每个位置都有一
 * 个数字。一个机器人每步只能向下或向右，请计算它从格子的左上
 * 角到达右下角的路径的数字之和的最小值。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 此题同《最大价值的礼物》
 * <p>
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 *
 * Tag：二维动态规划
 */
public class SumOfMinimumPaths implements Answer {
    public static void main(String[] args) {
        new SumOfMinimumPaths().answer();
    }

    /**
     * fix:同样  可以优化dp使用的存储空间。变为2两行的即可
     */
    @Override
    public void answer() {
        int[][] data = initData();
        System.out.println(minPathSum(data));
    }

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                } else {
                    if (i > 0) {
                        dp[i][j] = grid[i][j] + dp[i - 1][j];
                    }
                    if (j > 0) {
                        dp[i][j] = grid[i][j] + dp[i][j - 1];
                    }
                    if (i == 0 && j == 0) {
                        dp[i][j] = grid[i][j];
                    }
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    /**
     * something
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 3, 1}, {2, 5, 2}, {3, 4, 1}};
    }
}