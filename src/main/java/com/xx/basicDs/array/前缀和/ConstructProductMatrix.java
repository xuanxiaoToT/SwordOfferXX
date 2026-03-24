package com.xx.basicDs.array.前缀和;

import com.xx.Answer;
import com.xx.algorithm.other.ProductExceptSelf;

import java.util.Arrays;

/**
 * 构造乘积矩阵
 * LeetCode 2906. Medium
 * <p>
 * 给你一个下标从 0 开始、大小为 n * m 的二维整数矩阵 grid ，定义一个下标从 0 开始、大小为 n * m 的的二维矩阵 p。如果满足以下条件，则称 p 为 grid 的 乘积矩阵 ：
 * 对于每个元素 p[i][j] ，它的值等于除了 grid[i][j] 外所有元素的乘积。乘积对 12345 取余数。
 * 返回 grid 的乘积矩阵。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,2],[3,4]]
 * 输出：[[24,12],[8,6]]
 * 解释：p[0][0] = grid[0][1] * grid[1][0] * grid[1][1] = 2 * 3 * 4 = 24
 * p[0][1] = grid[0][0] * grid[1][0] * grid[1][1] = 1 * 3 * 4 = 12
 * p[1][0] = grid[0][0] * grid[0][1] * grid[1][1] = 1 * 2 * 4 = 8
 * p[1][1] = grid[0][0] * grid[0][1] * grid[1][0] = 1 * 2 * 3 = 6
 * 所以答案是 [[24,12],[8,6]] 。
 * <p>
 * 示例 2：
 * 输入：grid = [[12345],[2],[1]]
 * 输出：[[2],[0],[0]]
 * 解释：p[0][0] = grid[0][1] * grid[0][2] = 2 * 1 = 2
 * p[0][1] = grid[0][0] * grid[0][2] = 12345 * 1 = 12345. 12345 % 12345 = 0 ，所以 p[0][1] = 0
 * p[0][2] = grid[0][0] * grid[0][1] = 12345 * 2 = 24690. 24690 % 12345 = 0 ，所以 p[0][2] = 0
 * 所以答案是 [[2],[0],[0]] 。
 * <p>
 * 提示：
 * 1 <= n == grid.length <= 10^5
 * 1 <= m == grid[i].length <= 10^5
 * 2 <= n * m <= 10^5
 * 1 <= grid[i][j] <= 10^9
 * <p>
 * Tag:前缀和  二维前缀和
 */
public class ConstructProductMatrix implements Answer {
    public static void main(String[] args) {
        new ConstructProductMatrix().answer();
    }

    @Override
    public void answer() {
        int[][] grid = {{1, 2}, {3, 4}};
        System.out.println(Arrays.deepToString(constructProductMatrix(grid)));
    }

    /**
     * 如果将2维度压缩成一维
     * 其等同于{@link ProductExceptSelf}
     */
    public int[][] constructProductMatrix(int[][] grid) {
        final int MOD = 12345;
        int n = grid.length, m = grid[0].length;
        int[][] p = new int[n][m];

        long suffix = 1;
        // 逆序
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                p[i][j] = (int) suffix;
                suffix = suffix * grid[i][j] % MOD;
            }
        }

        long prefix = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) ((long) p[i][j] * prefix % MOD);
                prefix = prefix * grid[i][j] % MOD;
            }
        }
        return p;
    }

    @Override
    public Object initData() {
        return null;
    }
}
