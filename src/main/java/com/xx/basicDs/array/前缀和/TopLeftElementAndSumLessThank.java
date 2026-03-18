package com.xx.basicDs.array.前缀和;

import com.xx.Answer;

/**
 * 元素和小于等于 k 的子矩阵的数目
 * LeetCode  3070. Medium-
 * <p>
 * 给你一个下标从 0 开始的整数矩阵 grid 和一个整数 k。
 * 返回包含 grid 左上角元素、元素和小于或等于 k 的 子矩阵的数目。
 * <p>
 * 示例 1：
 * 输入：grid = [[7,6,3],[6,6,1]], k = 18
 * 输出：4
 * 解释：如上图所示，只有 4 个子矩阵满足：包含 grid 的左上角元素，并且元素和小于或等于 18 。
 * <p>
 * 示例 2：
 * 输入：grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
 * 输出：6
 * 解释：如上图所示，只有 6 个子矩阵满足：包含 grid 的左上角元素，并且元素和小于或等于 20 。
 * <p>
 * * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= n, m <= 1000
 * 0 <= grid[i][j] <= 1000
 * 1 <= k <= 10^9
 * <p>
 * Tag：前綴和
 */
public class TopLeftElementAndSumLessThank implements Answer {
    public static void main(String[] args) {
        new TopLeftElementAndSumLessThank().answer();
    }

    @Override
    public void answer() {
        int[][] grid = {{7, 2, 9}, {1, 5, 0}, {2, 6, 6}};
        int k = 20;
        System.out.println(countSubmatrices(grid, k));
    }

    public int countSubmatrices(int[][] grid, int k) {
        // 前缀和。ij表示右下角
        int[][] preSum = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i > 0 && j > 0) {
                    preSum[i][j] = grid[i][j] + preSum[i][j - 1] + preSum[i - 1][j] - preSum[i - 1][j - 1];
                    continue;
                }
                if (i > 0) {
                    preSum[i][j] = grid[i][j] + preSum[i - 1][j];
                    continue;
                }
                if (j > 0) {
                    preSum[i][j] = grid[i][j] + preSum[i][j - 1];
                    continue;
                }
                if (i == 0 && j == 0) {
                    preSum[i][j] = grid[i][j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (preSum[i][j] <= k) {
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public Object initData() {
        return null;
    }
}
