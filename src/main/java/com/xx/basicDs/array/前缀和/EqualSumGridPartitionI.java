package com.xx.basicDs.array.前缀和;

import com.xx.Answer;

/**
 * 等和矩阵分割I
 * LeetCode 3546. Medium
 * <p>
 * 给你一个由正整数组成的 m x n 矩阵 grid。你的任务是判断是否可以通过 一条水平或一条垂直分割线 将矩阵分割成两部分，使得：
 * <p>
 * 分割后形成的每个部分都是 非空 的。
 * 两个部分中所有元素的和 相等 。
 * 如果存在这样的分割，返回 true；否则，返回 false。
 * <p>
 * 示例 1：
 * 输入： grid = [[1,4],[2,3]]
 * 输出： true
 * 解释：
 * 在第 0 行和第 1 行之间进行水平分割，得到两个非空部分，每部分的元素之和为 5。因此，答案是 true。
 * <p>
 * 示例 2：
 * 输入： grid = [[1,3],[2,4]]
 * 输出： false
 * 解释：
 * 无论是水平分割还是垂直分割，都无法使两个非空部分的元素之和相等。因此，答案是 false。
 * <p>
 * 提示：
 * 1 <= m == grid.length <= 10^5
 * 1 <= n == grid[i].length <= 10^5
 * 2 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^5
 * <p>
 * Tag:前缀和  等和分割
 */
public class EqualSumGridPartitionI implements Answer {
    public static void main(String[] args) {
        new EqualSumGridPartitionI().answer();
    }

    @Override
    public void answer() {
        int[][] grid = {{1, 4}, {2, 3}};
        System.out.println(canPartitionGrid(grid));
    }

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] hang = new int[m];
        int[] lie = new int[n];
        long sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                hang[i] += grid[i][j];
                lie[j] += grid[i][j];
                sum += grid[i][j];
            }
        }
        if (sum % 2 == 1)
            return false;
        long target = sum / 2;
        long temp = 0;
        for (int k : hang) {
            temp += k;
            if (temp == target)
                return true;
        }
        temp = 0;
        for (int j : lie) {
            temp += j;
            if (temp == target)
                return true;
        }
        return false;
    }

    @Override
    public Object initData() {
        return null;
    }
}
