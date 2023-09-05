package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/5
 * <p>
 * 腐烂的橘子
 * LeetCode 994
 * <p>
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * -值 0 代表空单元格；
 * -值 1 代表新鲜橘子；
 * -值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * <p>
 * 示例 1：
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * <p>
 * 示例 3：
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 */
public class RottenOranges implements Answer {

    public static void main(String[] args) {
        new RottenOranges().answerTwo();
    }

    @Override
    public void answerOne() {
        int[][] grid = initData();
        int m = grid.length;
        int n = grid[0].length;
        // int[][] flag = ;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    int maxMinute = checkOranges(grid, i, j, new int[m][n], 0);
                    result = Math.min(result, maxMinute);
                }
            }
        }
    }

    /**
     * 深度优先遍历
     * 不行，不能解决多个源头同时开始的问题
     */
    @Deprecated
    private int checkOranges(int[][] grid, int i, int j, int[][] flag, int minute) {
        if (judgeBoundary(grid, i, j, flag)) {
            if (flag[i][j] == 1) {
                return minute;
            }
            flag[i][j] = 1;
            // 新鲜的
            if (grid[i][j] == 1) {
                minute = minute + 1;
                // grid[i][j] = 2;
            }
            if (grid[i][j] == 0) {
                return minute;
            }
            // 往左
            int left = checkOranges(grid, i - 1, j, flag, minute);
            // 往右
            int right = checkOranges(grid, i + 1, j, flag, minute);
            // 往下
            int down = checkOranges(grid, i, j + 1, flag, minute);
            // 往上
            int up = checkOranges(grid, i, j - 1, flag, minute);
            return Math.max(Math.max(left, right), Math.max(down, up));
        } else {
            return minute;
        }
    }

    /**
     * 多源，广度优先遍历
     */
    public void answerTwo() {
        int[][] grid = initData();
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int result = checkOrangesByBfs(grid, new int[m][n], queue);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    result = -1;
                    System.out.println(result);
                    return;
                }
            }
        }
        System.out.println(result);
    }

    private int checkOrangesByBfs(final int[][] grid, int[][] flag, final Queue<int[]> queue) {
        int minute = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean addFlag = false;
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int row = poll[0];
                int col = poll[1];
                if (flag[row][col] == 1) {
                    continue;
                }
                flag[row][col] = 1;
                if (grid[row][col] == 1) {
                    grid[row][col] = 2;
                    addFlag = true;
                }
                if (judgeBoundary(grid, row - 1, col, flag)) {
                    queue.add(new int[]{row - 1, col});
                }
                if (judgeBoundary(grid, row + 1, col, flag)) {
                    queue.add(new int[]{row + 1, col});
                }
                if (judgeBoundary(grid, row, col - 1, flag)) {
                    queue.add(new int[]{row, col - 1});
                }
                if (judgeBoundary(grid, row, col + 1, flag)) {
                    queue.add(new int[]{row, col + 1});
                }
            }
            minute = addFlag ? minute + 1 : minute;
        }
        return minute;
    }

    private boolean judgeBoundary(int[][] grid, int i, int j, int[][] flag) {
        return i < grid.length && i >= 0 && j < grid[0].length && j >= 0 && flag[i][j] == 0 && grid[i][j] > 0;
    }

    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        // return new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        // return new int[][]{{2, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        return new int[][]{{2, 1, 2}, {1, 2, 1}, {2, 1, 2}};
        // return new int[][]{{0, 1, 0}, {1, 2, 1}, {0, 1, 0}};
    }
}
