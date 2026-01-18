package com.xx.basicDs.array.前缀和;

import com.xx.Answer;

/**
 * 最大的幻方
 * LeetCode  1895.  Medium
 * <p>
 * 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。
 * 幻方中的整数 不需要互不相同 。显然，每个 1 x 1 的方格都是一个幻方。
 * 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
 * <p>
 * 示例 1：
 * 输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * 输出：3
 * 解释：最大幻方尺寸为 3 。
 * 每一行，每一列以及两条对角线的和都等于 12 。
 * - 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
 * - 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
 * - 对角线的和：5+4+3 = 6+4+2 = 12
 * <p>
 * 示例 2：
 * 输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 * 输出：2
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 10^6
 * <p>
 * Tag: 前缀和
 */
public class LargestMagicSquare implements Answer {
    public static void main(String[] args) {
        new LargestMagicSquare().answerOne();
    }

    @Override
    public void answerOne() {
        int[][] grid = new int[][]{{7, 1, 4, 5, 6}, {2, 5, 1, 6, 4}, {1, 5, 4, 3, 2}, {1, 2, 7, 3, 4}};
        System.out.println(largestMagicSquare(grid));
    }

    /**
     * 前缀和，注意多加一位
     */
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 行前缀和：pRow[i][j] 表示grid[i][0..j-1]的和
        int[][] pRow = new int[m][n + 1];
        // 列前缀和：pCol[j][i] 表示grid[0..i-1][j]的和
        int[][] pCol = new int[n][m + 1];

        // 初始化前缀和数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pRow[i][j + 1] = pRow[i][j] + grid[i][j];
                pCol[j][i + 1] = pCol[j][i] + grid[i][j];
            }
        }

        int ans = 1;
        int maxPossibleK = Math.min(m, n);
        // 遍历所有可能的边长k，找到最大的满足条件的k
        for (int k = 1; k <= maxPossibleK; k++) {
            if (check(k, grid, pRow, pCol, m, n)) {
                ans = k;
            }
        }
        return ans;
    }

    /**
     * 检查是否存在边长为k的魔法方阵
     *
     * @param length 方阵边长
     * @param grid   原始网格
     * @param pRow   行前缀和数组
     * @param pCol   列前缀和数组
     * @param m      网格行数
     * @param n      网格列数
     * @return 是否存在符合条件的魔法方阵
     */
    private boolean check(int length, int[][] grid, int[][] pRow, int[][] pCol, int m, int n) {
        // 遍历所有可能的左上角起点(i,j)
        for (int i = 0; i + length <= m; i++) {
            for (int j = 0; j + length <= n; j++) {
                // 以第一行的和作为基准和s
                int target = pRow[i][j + length] - pRow[i][j];
                boolean isValid = true;
                int diagonal1 = 0; // 左上到右下的对角线和
                int diagonal2 = 0; // 右上到左下的对角线和

                // 检查每一行、每一列的和，同时计算两条对角线的和
                for (int l = 0; l < length; l++) {
                    // 检查第i+l行的和是否等于基准和
                    if (pRow[i + l][j + length] - pRow[i + l][j] != target) {
                        isValid = false;
                        break;
                    }
                    // 检查第j+l列的和是否等于基准和
                    if (pCol[j + l][i + length] - pCol[j + l][i] != target) {
                        isValid = false;
                        break;
                    }
                    // 累加两条对角线的元素
                    diagonal1 += grid[i + l][j + l];
                    diagonal2 += grid[i + length - 1 - l][j + l];
                }

                // 所有行、列和都相等，且两条对角线和也等于基准和
                if (isValid && diagonal1 == target && diagonal2 == target) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object initData() {
        return null;
    }
}
