package com.xx.algorithm.dynamicProgram.简单DP;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/22
 * <p>
 * 网格中的最小路径代价
 * LeetCode 2304.  Medium
 * <p>
 * 给你一个下标从 0 开始的整数矩阵 grid ，矩阵大小为 m x n ，由从 0 到 m * n - 1 的不同整数组成。你可以在此矩阵中，
 * 从一个单元格移动到 下一行 的任何其他单元格。如果你位于单元格 (x, y) ，且满足 x < m - 1 ，
 * 你可以移动到 (x + 1, 0), (x + 1, 1), ..., (x + 1, n - 1) 中的任何一个单元格。注意： 在最后一行中的单元格不能触发移动。
 * 每次可能的移动都需要付出对应的代价，代价用一个下标从 0 开始的二维数组 moveCost 表示，该数组大小为 (m * n) x n ，
 * 其中 moveCost[i][j] 是从值为 i 的单元格移动到下一行第 j 列单元格的代价。从 grid 最后一行的单元格移动的代价可以忽略。
 * grid 一条路径的代价是：所有路径经过的单元格的 值之和 加上 所有移动的 代价之和 。
 * 从 第一行 任意单元格出发，返回到达 最后一行 任意单元格的最小路径代价。
 * <p>
 * 示例 1：
 * 输入：grid = [[5,3],[4,0],[2,1]], moveCost = [[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]
 * 输出：17
 * 解释：最小代价的路径是 5 -> 0 -> 1 。
 * - 路径途经单元格值之和 5 + 0 + 1 = 6 。
 * - 从 5 移动到 0 的代价为 3 。
 * - 从 0 移动到 1 的代价为 8 。
 * 路径总代价为 6 + 3 + 8 = 17 。
 * <p>
 * 示例 2：
 * 输入：grid = [[5,1,2],[4,0,3]], moveCost = [[12,10,15],[20,23,8],[21,7,1],[8,1,13],[9,10,25],[5,3,2]]
 * 输出：6
 * 解释：
 * 最小代价的路径是 2 -> 3 。
 * - 路径途经单元格值之和 2 + 3 = 5 。
 * - 从 2 移动到 3 的代价为 1 。
 * 路径总代价为 5 + 1 = 6 。
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 50
 * grid 由从 0 到 m * n - 1 的不同整数组成
 * moveCost.length == m * n
 * moveCost[i].length == n
 * 1 <= moveCost[i][j] <= 100
 * <p>
 * Tag: 动态规划  记忆化搜索
 */
public class MinimumPathCostInNetworks implements Answer {

    public static void main(String[] args) {
        new MinimumPathCostInNetworks().answerOne();
    }

    @Override
    public void answerOne() {
        int[][] grid = new int[][]{{5, 3}, {4, 0}, {2, 1}};
        int[][] moveCost = new int[][]{{9, 8}, {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}};
        // int[][] grid = new int[][]{{5, 1, 2}, {4, 0, 3}};
        // int[][] moveCost = new int[][]{{12, 10, 15}, {20, 23, 8}, {21, 7, 1}, {8, 1, 13}, {9, 10, 25}, {5, 3, 2}};
        System.out.println(minPathCost(grid, moveCost));
    }

    public int minPathCost(int[][] grid, int[][] moveCost) {
        Map<Integer, int[]> map = initMap(moveCost);
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0] = grid[0].clone();
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i + 1 < grid.length) {
                    //计算下一行的dp
                    for (int k = 0; k < grid[0].length; k++) {
                        //dp[i][j]里面已经包含grid[i][j]的值了，不再加
                        int nextNode = grid[i + 1][k] + map.get(grid[i][j])[k];
                        int total = dp[i][j] + nextNode;
                        dp[i + 1][k] = dp[i + 1][k] != 0 ? Math.min(dp[i + 1][k], total) : total;
                        if (i + 1 == grid.length - 1) {
                            result = Math.min(dp[i + 1][k], result);
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return result;
    }

    private Map<Integer, int[]> initMap(final int[][] moveCost) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < moveCost.length; i++) {
            for (int j = 0; j < moveCost[0].length; j++) {
                map.put(i, moveCost[i]);
            }
        }
        return map;
    }


    public int dfs(int i, int j, int[][] grid, int[][] moveCost) {
        if (i == 0) {
            return grid[i][j];
        }
        if (memo[i][j] >= 0) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int k = 0; k < grid[0].length; k++) {
            res = Math.min(res, dfs(i - 1, k, grid, moveCost) + moveCost[grid[i - 1][k]][j] + grid[i][j]);
        }
        memo[i][j] = res;
        return res;
    }

    /**
     * 记忆化搜索
     * 我们枚举最后一行的所有单元格 (m−1,j)，对 (m−1,j) 进行深度优先搜索，得到 dfs(m−1,j)取其中的最小值。
     * <p>
     * https://leetcode.cn/problems/minimum-path-cost-in-a-grid/solutions/2529083/wang-ge-zhong-de-zui-xiao-lu-jing-dai-ji-qqqf/?envType=daily-question&envId=2023-11-22
     */
    public int[][] memo;

    public int minPathCostByLeetCode(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dfs(m - 1, j, grid, moveCost));
        }
        return res;
    }


    @Override
    public int[][] initData() {
        return null;
    }
}