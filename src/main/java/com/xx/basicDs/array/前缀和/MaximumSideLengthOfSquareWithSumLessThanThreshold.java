package com.xx.basicDs.array.前缀和;

import com.xx.Answer;

/**
 * 元素和小于等于阈值的正方形的最大边长
 * LeetCode 1292. Medium
 * <p>
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 * <p>
 * 示例 1：
 * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * 输出：2
 * 解释：总和小于或等于 4 的正方形的最大边长为 2，如图所示。
 * <p>
 * 示例 2：
 * 输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * 输出：0
 * <p>
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 300
 * 0 <= mat[i][j] <= 10^4
 * 0 <= threshold <= 10^5
 * <p>
 * Tag:前缀和 二维前缀和
 */
public class MaximumSideLengthOfSquareWithSumLessThanThreshold implements Answer {
    public static void main(String[] args) {
        new MaximumSideLengthOfSquareWithSumLessThanThreshold().answerOne();
    }

    @Override
    public void answerOne() {

    }

    /**
     * 二维前缀和 + 二分查找
     * 用P[i][j]表示二维前缀和，及ij为右下角，左上角为0.0的正方形面积。
     * 这样可以用O1的时间计算出正方形面积了。
     * <p>
     * 然后求解边长的时候，可以使用二分查找，因为边长1-min(m,n)，试的时候可以从二分之一处开始，而不必从1到最后遍历。
     *
     */
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + mat[i][j];
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 边长为 ans+1 的正方形，左上角在 (i, j)，右下角在 (i+ans, j+ans)
                while (i + ans < m && j + ans < n && query(sum, i, j, i + ans, j + ans) <= threshold) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // 返回左上角在 (r1, c1)，右下角在 (r2, c2) 的子矩阵元素和
    private int query(int[][] sum, int r1, int c1, int r2, int c2) {
        return sum[r2 + 1][c2 + 1] - sum[r2 + 1][c1] - sum[r1][c2 + 1] + sum[r1][c1];
    }

    /**
     * 一维前缀和
     */
    public int maxSideLengthOld(int[][] grid, int threshold) {
        int result = 0;
        int[][] prefixSum = new int[grid.length][grid[0].length];
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixSum[i][j] = j > 0 ? prefixSum[i][j - 1] + grid[i][j] : grid[i][j];
                if (grid[i][j] <= threshold) {
                    result = 1;
                }
            }
        }
        if (result < 1) {
            return result;
        }
        for (int length = 2; length <= Math.min(m, n); length++) {
            fLag:
            for (int i = 0; i <= m - length; i++) {
                for (int j = 0; j <= n - length; j++) {
                    if (computeSquareSum(i, j, prefixSum, length) <= threshold) {
                        result = length;
                        break fLag;
                    }
                }
            }
        }
        return result;
    }

    public int computeSquareSum(int x, int y, int[][] prefixSum, int length) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum = sum + prefixSum[x + i][y + length - 1] - (y > 1 ? prefixSum[x + i][y - 1] : 0);
        }
        return sum;
    }

    @Override
    public Object initData() {
        return null;
    }
}
