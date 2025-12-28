package com.xx.algorithm.search.二分查找;

import com.xx.Answer;

/**
 * 统计有序矩阵中的负数
 * LeetCode 1351 Easy
 * <p>
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非严格递减顺序排列。 请你统计并返回 grid 中 负数 的数目。
 * <p>
 * 示例 1：
 * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * 输出：8
 * 解释：矩阵中共有 8 个负数。
 * <p>
 * 示例 2：
 * 输入：grid = [[3,2],[1,0]]
 * 输出：0
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 * <p>
 * Tag：数组便利  二分查找
 */
public class CountNegativeNumbersInSortedMatrix implements Answer {
    public static void main(String[] args) {
        new CountNegativeNumbersInSortedMatrix().answerOne();
    }


    @Override
    public void answerOne() {
        // int[][] grid = new int[][]{{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
        int[][] grid = new int[][]{{3, 2}, {1, 0}};
        System.out.println(countNegatives(grid));
    }

    public int countNegatives(int[][] grid) {
        int result = 0;
        for (int[] ints : grid) {
            int hang = findHang(ints);
            if (hang == -1) {
                continue;
            } else {
                result += ints.length - hang;
            }
        }
        return result;
    }

    /**
     * 二分查找
     * 注意边界条件
     */
    private int findHang(int[] grid) {

        if (grid.length == 1) {
            if (grid[0] < 0) {
                return 0;
            }
            return -1;
        }

        int left = 0;
        int right = grid.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid >= 1) {
                if (grid[mid] < 0 && grid[mid - 1] >= 0) {
                    return mid;
                }
                if (grid[mid] >= 0) {
                    left = mid + 1;
                }
                if (grid[mid] < 0) {
                    right = mid - 1;
                }
            } else {
                if (grid[mid] < 0) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }


    @Override
    public Object initData() {
        return null;
    }
}
