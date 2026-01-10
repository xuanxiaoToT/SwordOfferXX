package com.xx.algorithm.recursion;

import com.xx.Answer;

import java.util.*;

/**
 * 迷路的机器人
 * LeetCode Medium
 * <p>
 * <p>
 * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
 * 设计一种算法，寻找机器人从左上角移动到右下角的路径。
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
 * <p>
 * 示例 1：
 * 输入：[[0,0,0],[0,1,0],[0,0,0]]
 * 输出：[[0,0],[0,1],[0,2],[1,2],[2,2]]
 * 解释：
 * 输入中标粗的位置即为输出表示的路径，即
 * 0 行 0 列（左上角） -> 0 行 1 列 -> 0 行 2 列 -> 1 行 2 列 -> 2 行 2 列（右下角）
 * <p>
 * 说明：r 和 c 的值均不超过 100。
 * <p>
 * Tag：深度优先遍历  递归   回朔
 */
public class RobotInAGrid implements Answer {
    List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        new RobotInAGrid().answerOne();
    }

    @Override
    public void answerOne() {
        int[][] obstacleGrid = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 0, 0}};
        System.out.println(pathWithObstacles(obstacleGrid));
    }

    /**
     * 第一遍超时
     * 问题有两个：
     * 1.如果一条路是死路，那么下次再来这里的时候，他还是会往下遍历，这样就重复了。需要用visited去解决(实际上可以将grid该位置为1的方式处理)。
     * 2.如果一条路是思路，那么path在这条路上加的节点，就都没用了，需要退栈。 所以在bfs方法的最下面需要删除这个
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        Set<List<Integer>> visited = new HashSet<>();
        dfs(obstacleGrid, 0, 0, new ArrayList<>(), visited);
        return result;
    }

    private void dfs(int[][] grid, int i, int j, List<List<Integer>> path, Set<List<Integer>> visited) {
        if (i >= grid.length || j >= grid[0].length) {
            return;
        }
        if (!result.isEmpty()) {
            return;
        }
        if (grid[i][j] == 1) {
            return;
        }
        if (visited.contains(Arrays.asList(i, j))) {
            return;
        }
        path.add(Arrays.asList(i, j));
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            result.addAll(path);
            return;
        }
        dfs(grid, i + 1, j, path, visited);
        dfs(grid, i, j + 1, path, visited);
        visited.add(Arrays.asList(i, j));
        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}
