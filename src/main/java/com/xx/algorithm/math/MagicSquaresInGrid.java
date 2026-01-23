package com.xx.algorithm.math;

import com.xx.Answer;

import java.util.TreeSet;

/**
 * 矩阵中的幻方
 * LeetCode 840. Medium
 * <p>
 * 3 x 3 的幻方是一个填充有 从 1 到 9  的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
 * 给定一个由整数组成的row x col 的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？
 * 注意：虽然幻方只能包含 1 到 9 的数字，但 grid 可以包含最多15的数字。
 * <p>
 * 示例 1：
 * 输入: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]
 * 输出: 1
 * 解释:
 * 下面的子矩阵是一个 3 x 3 的幻方：
 * <p>
 * 而这一个不是：
 * <p>
 * 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
 * <p>
 * 示例 2:
 * 输入: grid = [[8]]
 * 输出: 0
 * <p>
 * 提示:
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 10
 * 0 <= grid[i][j] <= 15
 * <p>
 * Tag: 数学性质
 */
public class MagicSquaresInGrid implements Answer {
    public static void main(String[] args) {
        new MagicSquaresInGrid().answer();
    }

    @Override
    public void answer() {
        // int[][] grid = new int[][]{{4, 3, 8, 4}, {9, 5, 1, 9}, {2, 7, 6, 2}};
        int[][] grid = new int[][]{{4, 3, 8, 5, 5, 5}, {9, 5, 1, 5, 5, 5}, {2, 7, 6, 5, 5, 5}};
        System.out.println(numMagicSquaresInside(grid));
    }

    /**
     * 要判断一个 3x3 的矩阵是否为幻方，必须同时满足以下几个严格条件：
     * <p>
     * 数字构成：必须包含 1 到 9 的所有数字，且 不重复。
     * 和相等：每一行、每一列、两条对角线的和必须相等。
     * <p>
     * 数学推导（关键点）：
     * 1 到 9 的总和是 45。
     * 因为有 3 行，且每行和相等，所以每一行的和必须是 45 / 3 = 15。
     * 因此，幻方常数（每行/列/对角线之和）必须固定为 15。
     * 中心点性质：通过数学推导（四条穿过中心的线的和 - 3倍中心值 = 总和），可以得出 3x3 幻方的 中心元素必须是 5。
     * 这是一个非常有用的快速筛选条件：如果一个 3x3 子矩阵的中心不是 5，它一定不是幻方。
     */
    public int numMagicSquaresInside(int[][] grid) {
        if (grid.length < 3 || grid[0].length < 3) {
            return 0;
        }
        int result = 0;
        int left = 0;
        int right = 2;
        int top = 0;
        int bottom = 2;

        while (right < grid[0].length && bottom < grid.length) {
            boolean flag = whetherIsHuanFang(left, right, top, bottom, grid);
            if (flag) {
                result++;
            }
            left++;
            right++;
            if (right >= grid[0].length) {
                left = 0;
                right = 2;
                top++;
                bottom++;
            }
        }
        return result;
    }

    private boolean whetherIsHuanFang(int left, int right, int top, int bottom, int[][] grid) {
        if (grid[top + 1][left + 1] != 5) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>();
        // 计算每行是否为15,
        for (int i = top; i <= bottom; i++) {
            set.add(grid[i][left]);
            set.add(grid[i][left + 1]);
            set.add(grid[i][right]);
            int sum = grid[i][left] + grid[i][left + 1] + grid[i][right];
            if (sum != 15) {
                return false;
            }
        }
        if (set.size() != 9) {
            return false;
        }
        if (set.pollFirst() != 1) {
            return false;
        }
        if (set.pollLast() != 9) {
            return false;
        }
        // 计算每列的
        for (int i = left; i <= right; i++) {
            int sum = grid[top][i] + grid[top + 1][i] + grid[bottom][i];
            if (sum != 15) {
                return false;
            }
        }
        // 计算斜边
        int sum1 = grid[top][left] + grid[top + 1][left + 1] + grid[bottom][right];
        if (sum1 != 15) {
            return false;
        }
        int sum2 = grid[bottom][left] + grid[bottom - 1][left + 1] + grid[top][right];
        if (sum2 != 15) {
            return false;
        }
        return true;
    }

    @Override
    public Object initData() {
        return null;
    }
}
