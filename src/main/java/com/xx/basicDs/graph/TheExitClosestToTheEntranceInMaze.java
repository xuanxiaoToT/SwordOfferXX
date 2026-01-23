package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/19
 * <p>
 * 迷宫中离入口最近的出口
 * LeetCode 1926. Medium
 * <p>
 * 给你一个 m x n 的迷宫矩阵 maze （下标从 0 开始），矩阵中有空格子（用 '.' 表示）和墙（用 '+' 表示）。同时给你迷宫的入口 entrance ，
 * 用 entrance = [entrancerow, entrancecol] 表示你一开始所在格子的行和列。
 * 每一步操作，你可以往 上，下，左 或者 右 移动一个格子。你不能进入墙所在的格子，你也不能离开迷宫。你的目标是找到离 entrance 最近 的出口。
 * 出口 的含义是 maze 边界 上的 空格子。entrance 格子 不算 出口。
 * 请你返回从 entrance 到最近出口的最短路径的 步数 ，如果不存在这样的路径，请你返回 -1 。
 * <p>
 * 示例 1：
 * 输入：maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
 * 输出：1
 * 解释：总共有 3 个出口，分别位于 (1,0)，(0,2) 和 (2,3) 。
 * 一开始，你在入口格子 (1,2) 处。
 * - 你可以往左移动 2 步到达 (1,0) 。
 * - 你可以往上移动 1 步到达 (0,2) 。
 * 从入口处没法到达 (2,3) 。
 * 所以，最近的出口是 (0,2) ，距离为 1 步。
 * <p>
 * 示例 2：
 * 输入：maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
 * 输出：2
 * 解释：迷宫中只有 1 个出口，在 (1,2) 处。
 * (1,0) 不算出口，因为它是入口格子。
 * 初始时，你在入口与格子 (1,0) 处。
 * - 你可以往右移动 2 步到达 (1,2) 处。
 * 所以，最近的出口为 (1,2) ，距离为 2 步。
 * <p>
 * 示例 3：
 * 输入：maze = [[".","+"]], entrance = [0,0]
 * 输出：-1
 * 解释：这个迷宫中没有出口。
 * <p>
 * 提示：
 * maze.length == m
 * maze[i].length == n
 * 1 <= m, n <= 100
 * maze[i][j] 要么是 '.' ，要么是 '+' 。
 * entrance.length == 2
 * 0 <= entrancerow < m
 * 0 <= entrancecol < n
 * entrance 一定是空格子。
 * <p>
 * Tag：图的遍历  寻找出口  广度优先
 */
public class TheExitClosestToTheEntranceInMaze implements Answer {
    public static void main(String[] args) {
        new TheExitClosestToTheEntranceInMaze().answer();
    }

    @Override
    public void answer() {
        // char[][] maze = {{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}};
        // char[][] maze = {{'+', '+', '+', '+'}, {'.', '.', '.', '.'}, {'+', '+', '+', '+'}};
        char[][] maze = {{'.', '+'}, {'+', '+'}};
        computeMinSteps(maze, new int[]{0, 0});
        System.out.println(this.minResult);
    }

    /**
     * 广度优先，注意遍历过的标记问题
     */
    public int nearestExit(char[][] maze, int[] entrance) {
        //方向
        int[][] dir = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[m][n];
        q.add(entrance);
        vis[entrance[0]][entrance[1]] = true;

        int ans = 0;
        while (!q.isEmpty()) {
            ans++;
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int[] curr = q.poll();
                for (int[] d : dir) {
                    int nextX = curr[0] + d[0];
                    int nextY = curr[1] + d[1];
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || vis[nextX][nextY]
                            || maze[nextX][nextY] == '+') {
                        //出界
                        continue;
                    }

                    if (nextX == 0 || nextX == m - 1 || nextY == 0 || nextY == n - 1) {
                        return ans;
                    }
                    q.add(new int[]{nextX, nextY});
                    vis[nextX][nextY] = true;
                }
            }

        }
        return -1;
    }


    int n = 0;
    int m = 0;
    int minResult = Integer.MAX_VALUE;

    public int computeMinSteps(char[][] maze, int[] entrance) {
        n = maze.length;
        m = maze[0].length;
        int[][] flag = new int[n][m];
        dfs(maze, flag, entrance[0], entrance[1], 0);
        return minResult == Integer.MAX_VALUE ? -1 : minResult;
    }


    /**
     * 深度优先遍历，超时了
     */
    public void dfs(char[][] maze, int[][] flag, int i, int j, int steps) {
        if (i >= n || i < 0 || j >= m || j < 0) {
            if (steps > 1) {
                minResult = Math.min(minResult, steps - 1);
            }
            return;
        }
        if (flag[i][j] == 1 || maze[i][j] == '+') {
            return;
        }
        flag[i][j] = 1;
        dfs(maze, flag, i + 1, j, steps + 1);
        dfs(maze, flag, i - 1, j, steps + 1);
        dfs(maze, flag, i, j + 1, steps + 1);
        dfs(maze, flag, i, j - 1, steps + 1);
        flag[i][j] = 0;
    }

    @Override
    public Object initData() {
        return null;
    }
}