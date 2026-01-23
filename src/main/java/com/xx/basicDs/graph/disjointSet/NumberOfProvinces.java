package com.xx.basicDs.graph.disjointSet;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/8
 * <p>
 * 省份数量
 * LeetCode 547. Medium
 * <p>
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 * 而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * <p>
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * <p>
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 * <p>
 * Tag: 深度优先遍历  并查集
 */
public class NumberOfProvinces implements Answer {
    public static void main(String[] args) {
        new NumberOfProvinces().answer();
    }

    @Override
    public void answer() {
        int[][] data = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum2(data));
    }

    /**
     * 深度优先遍历
     */
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

    /**
     * 并查集
     */
    public int findCircleNum2(int[][] isConnected) {
        int[] fatherNode = new int[isConnected.length];
        Arrays.parallelSetAll(fatherNode, i -> i);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[0].length; j++) {
                //i和j是朋友，则将其合并为一个图
                if (isConnected[i][j] == 1) {
                    unionFather(fatherNode, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < fatherNode.length; i++) {
            if (fatherNode[i] == i) {
                count++;
            }
        }
        return count;
    }

    private void unionFather(int[] fatherNode, int i, int j) {
        // 判断这两个的根节点
        int fatherRootI = findRoot(fatherNode, i);
        int fatherRootJ = findRoot(fatherNode, j);
        //合并到一个图里
        if (fatherRootI != fatherRootJ) {
            fatherNode[fatherRootJ] = fatherRootI;
        }
    }

    private int findRoot(int[] fatherNode, int i) {
        if (fatherNode[i] == i) {
            return i;
        } else {
            return findRoot(fatherNode, fatherNode[i]);
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}