package com.xx.basicDs.graph;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/9
 * <p>
 * 岛屿数量
 * leetcode 200  Medium
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1：
 * 输入：grid = [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：grid = [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出：3
 */
public class NumberOfIslands implements Answer {

    public static void main(String[] args) {
        new NumberOfIslands().answer();
    }

    /**
     * 解1：简单的深度优先遍历或广度优先遍历都可以
     * 缺点：会改变原有数据
     * 可以用flag替代
     */
    @Override
    public void answer() {
        char[][] grid = initData();
        if (grid.length == 0 || grid == null) {
            System.out.println(0);
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs_lands(grid, i, j);
                }
            }
        }
        System.out.println(res);
    }

    private void dfs_lands(char[][] grid, int i, int j) {
        int count = 0;
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
        }
        dfs_lands(grid, i + 1, j);
        dfs_lands(grid, i - 1, j);
        dfs_lands(grid, i, j + 1);
        dfs_lands(grid, i, j - 1);
    }

    /**
     * 输出数据
     */
    @Override
    public char[][] initData() {
        return new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
    }
}
