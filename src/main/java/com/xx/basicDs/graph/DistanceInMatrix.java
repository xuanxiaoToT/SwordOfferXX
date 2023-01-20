package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/20
 * 矩阵中的距离
 * 输入一个由0、1组成的矩阵M，请输出一个大小相同的矩
 * 阵D，矩阵D中的每个格子是矩阵M中对应格子离最近的0的距离。水
 * 平或竖直方向相邻的两个格子的距离为1。假设矩阵M中至少有一个
 * 0。
 */
public class DistanceInMatrix implements Answer {

    public static void main(String[] args) {
        new DistanceInMatrix().answerTwo();
    }

    /**
     * 解:深度优先遍历--解法错误~！
     * 关键的问题是：已经遍历过的1点，如何处理再遍历的问题。
     */
    @Override
    public void answerOne() {
        int[][] data = initData();
        int[][] result = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1) {
                    result[i][j] = myDiGui(data, i, j, new HashSet<>(), 0);
                } else {
                    result[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(result));
    }

    /**
     * DP思想的延伸，以每个节点0位开始，做广度优先遍历。
     * 问题的关键是避免重复遍历的逻辑
     */
    private void answerTwo() {
        int[][] data = initData();
        int[][] result = new int[data.length][data[0].length];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 0) {
                    queue.add(new int[]{i, j});
                    result[i][j] = 0;
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int tempI = poll[0];
            int tempJ = poll[1];
            int dist = result[tempI][tempJ];
            // 上
            if (whetherGuang(data, tempI - 1, tempJ)) {
                // 通过比较距离可以避免重复访问某个格子
                if (dist + 1 < result[tempI - 1][tempJ]) {
                    result[tempI - 1][tempJ] = dist + 1;
                    queue.add(new int[]{tempI - 1, tempJ});
                }
            }
            // 下
            if (whetherGuang(data, tempI + 1, tempJ)) {
                if (dist + 1 < result[tempI + 1][tempJ]) {
                    result[tempI + 1][tempJ] = dist + 1;
                    queue.add(new int[]{tempI + 1, tempJ});
                }
            }
            // 左
            if (whetherGuang(data, tempI, tempJ - 1)) {
                if (dist + 1 < result[tempI][tempJ - 1]) {
                    result[tempI][tempJ - 1] = dist + 1;
                    queue.add(new int[]{tempI, tempJ - 1});
                }
            }
            // 右
            if (whetherGuang(data, tempI, tempJ + 1)) {
                if (dist + 1 < result[tempI][tempJ + 1]) {
                    result[tempI][tempJ + 1] = dist + 1;
                    queue.add(new int[]{tempI, tempJ + 1});
                }
            }
        }
        System.out.println(Arrays.deepToString(result));
    }

    private int myDiGui(int[][] data, int i, int j, HashSet<String> flag, int distance) {
        if (whether(data, i, j, flag)) {
            if (data[i][j] == 1) {
                flag.add(i + "_" + j);
                int left = myDiGui(data, i, j - 1, flag, distance + 1);
                int right = myDiGui(data, i, j + 1, flag, distance + 1);
                int up = myDiGui(data, i - 1, j, flag, distance + 1);
                int down = myDiGui(data, i + 1, j, flag, distance + 1);
                return Math.min(Math.min(left, right), Math.min(up, down));
            } else {
                return distance;
            }
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private boolean whether(int[][] data, int i, int j, HashSet<String> flag) {
        return i >= 0 && i < data.length && j >= 0 && j < data[0].length && !flag.contains(i + "_" + j);
    }

    private boolean whetherGuang(int[][] data, int i, int j) {
        return i >= 0 && i < data.length && j >= 0 && j < data[0].length;
    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{0, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    }
}
