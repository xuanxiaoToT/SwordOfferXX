package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/9
 *
 * 逃离火灾
 *
 */
public class EscapeFromFire implements Answer {

    public static void main(String[] args) {
        new EscapeFromFire().answerOne();
    }

    @Override
    public void answerOne() {
        int[][] grid = initData();
        System.out.println(maximumMinutes(grid));
    }

    private int maxRemainder = 1000000000;
    private int result = 0;
    private boolean suc = false;

    /**
     * 0:草地
     * 1:火
     * 2:墙
     */
    public int maximumMinutes(int[][] grid) {
        // 每分钟的状态存储完毕
        bfsVisit(grid);
        // 遍历人物本身的路径
        dfsPeople(grid, maxRemainder, new int[grid.length][grid[0].length], 0, 0, 0);
        if (suc) {
            return result;
        } else {
            return -1;
        }


    }

    private void dfsPeople(int[][] grid, int remainderMinute, int[][] hasVisited, int minute, int i, int j) {
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1) {
            return;
        }
        if (hasVisited[i][j] == 1 || grid[i][j] >= 1) {
            return;
        }

        hasVisited[i][j] = 1;
        int remainderTime = maxRemainder;
        // 到这里的时候是否着火,着火时间如果小于当前时间说明来的时候已经着火
        if (grid[i][j] < 0) {
            int temp = -1 * grid[i][j] - minute - 1;
            if (temp < 0) {
                //如果到终点时，刚着火，也可以算成功
                if (i == grid.length - 1 && j == grid[0].length - 1 && temp == -1) {
                    //终点可以多算一分钟，因为可以按刚好烧到计算
                    result = Math.max(result, Math.min(temp, remainderMinute));
                    suc = true;
                    return;
                } else {
                    return;
                }
            } else {
                remainderTime = temp;
            }
        }
        //System.out.println(Arrays.deepToString(hasVisited));
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            // 到终点
            result = Math.max(result, Math.min(remainderTime + 1, remainderMinute));
            suc = true;
            hasVisited[i][j] = 0;
            return;
        }
        dfsPeople(grid, Math.min(remainderTime, remainderMinute), hasVisited, minute + 1, i + 1, j);
        dfsPeople(grid, Math.min(remainderTime, remainderMinute), hasVisited, minute + 1, i - 1, j);
        dfsPeople(grid, Math.min(remainderTime, remainderMinute), hasVisited, minute + 1, i, j + 1);
        dfsPeople(grid, Math.min(remainderTime, remainderMinute), hasVisited, minute + 1, i, j - 1);
        hasVisited[i][j] = 0;
    }

    private void bfsVisit(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] hasVisited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int minute = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                hasVisited[poll[0]][poll[1]] = 1;
                grid[poll[0]][poll[1]] = grid[poll[0]][poll[1]] == 0 ? -1 * minute : grid[poll[0]][poll[1]];
                List<int[]> nextList = generateNext(poll, hasVisited, grid);
                queue.addAll(nextList);
            }
            minute++;
        }
        //System.out.println(Arrays.deepToString(hasVisited));
        System.out.println(Arrays.deepToString(grid));
    }

    private List<int[]> generateNext(int[] poll, int[][] hasVisited, int[][] grid) {
        List<int[]> result = new ArrayList<>();
        int hang = poll[0];
        int lie = poll[1];
        if (hang + 1 < grid.length && hasVisited[hang + 1][lie] == 0 && grid[hang + 1][lie] == 0) {
            result.add(new int[]{hang + 1, lie});
        }
        if (hang - 1 >= 0 && hasVisited[hang - 1][lie] == 0 && grid[hang - 1][lie] == 0) {
            result.add(new int[]{hang - 1, lie});
        }
        if (lie + 1 < grid[0].length && hasVisited[hang][lie + 1] == 0 && grid[hang][lie + 1] == 0) {
            result.add(new int[]{hang, lie + 1});
        }
        if (lie - 1 >= 0 && hasVisited[hang][lie - 1] == 0 && grid[hang][lie - 1] == 0) {
            result.add(new int[]{hang, lie - 1});
        }
        return result;
    }

    @Override
    public int[][] initData() {
        //return new int[][]{{0, 2, 0, 0, 0, 0, 0}, {0, 0, 0, 2, 2, 1, 0}, {0, 2, 0, 0, 1, 2, 0}, {0, 0, 2, 2, 2, 0, 2}, {0, 0, 0, 0, 0, 0, 0}};
        //return new int[][]{{0,0,0,0},{0,1,2,0},{0,2,0,0}};
        //return new int[][]{{0,0,0},{2,2,0},{1,2,0}};
        //return new int[][]{{0, 2, 0, 0, 1}, {0, 2, 0, 2, 2}, {0, 2, 0, 0, 0}, {0, 0, 2, 2, 0}, {0, 0, 0, 0, 0}};
        //return new int[][]{{0, 1}, {0, 0}};
        //return new int[][]{{0, 0, 0, 0, 0}, {0, 2, 0, 2, 0}, {0, 2, 0, 2, 0}, {0, 2, 1, 2, 0}, {0, 2, 2, 2, 0}, {0, 0, 0, 0, 0}};
        return new int[][]{{0, 0, 0, 0, 0, 0}, {0, 2, 2, 2, 2, 0}, {0, 0, 0, 1, 2, 0}, {0, 2, 2, 2, 2, 0}, {0, 0, 0, 0, 0, 0}};
    }
}