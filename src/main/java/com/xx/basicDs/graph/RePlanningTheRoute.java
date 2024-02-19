package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/19
 * <p>
 * 重新规划路线
 * LeetCode 1466. Medium
 * <p>
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。
 * 去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 * <p>
 * 示例 1：
 * 输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * 输出：3
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * <p>
 * 示例 2：
 * 输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * 输出：2
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * <p>
 * 示例 3：
 * 输入：n = 3, connections = [[1,0],[2,0]]
 * 输出：0
 * <p>
 * 提示：
 * 2 <= n <= 5 * 10^4
 * connections.length == n-1
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] <= n-1
 * connections[i][0] != connections[i][1]
 */
public class RePlanningTheRoute implements Answer {

    public static void main(String[] args) {
        new RePlanningTheRoute().answerOne();
    }

    @Override
    public void answerOne() {
        // int[][] connect = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        // int[][] connect = {{1, 0}, {1, 2}, {3, 2}, {3, 4}};
        int[][] connect = {{1, 0}, {2, 0}};
        System.out.println(minReorder(3, connect));
    }

    /**
     * 先按无向图来遍历，再判断每条边是否方向正确，不正确+1.
     * 可以用邻接表来替代map作为图边的表示
     */
    public int minReorder(int n, int[][] connections) {
        int result = 0;
        Map<Integer, Set<Integer>> directedMap = new HashMap<>();
        Map<Integer, Set<Integer>> undirectedMap = new HashMap<>();
        for (int[] data : connections) {
            int key = data[0];
            int val = data[1];
            if (directedMap.containsKey(key)) {
                directedMap.get(key).add(val);
            } else {
                Set<Integer> temp = new HashSet<>();
                temp.add(val);
                directedMap.put(key, temp);
            }
            if (undirectedMap.containsKey(key)) {
                undirectedMap.get(key).add(val);
            } else {
                Set<Integer> temp = new HashSet<>();
                temp.add(val);
                undirectedMap.put(key, temp);
            }
            if (undirectedMap.containsKey(val)) {
                undirectedMap.get(val).add(key);
            } else {
                Set<Integer> temp = new HashSet<>();
                temp.add(key);
                undirectedMap.put(val, temp);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int[] flag = new int[n];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                if (flag[poll] == 0) {
                    flag[poll] = 1;
                    Set<Integer> undirSet = undirectedMap.get(poll);
                    for (Integer next : undirSet) {
                        if (flag[next] == 0) {
                            queue.add(next);
                            Set<Integer> set = directedMap.get(next);
                            if (set == null || !set.contains(poll)) {
                                result++;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 最快
     */
    public int minReorderAnswer(int n, int[][] connections) {
        int res = 0;
        boolean[] isconnection = new boolean[n];
        isconnection[0] = true;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < connections.length; i++) {
            int u = connections[i][0];
            int v = connections[i][1];
            if (isconnection[v]) isconnection[u] = true;
            else if (isconnection[u]) {
                isconnection[v] = true;
                res++;
            } else list.add(i);
        }
        while (!list.isEmpty()) {
            for (int i = list.size() - 1; i >= 0; i--) {
                int u = connections[list.get(i)][0];
                int v = connections[list.get(i)][1];
                if (isconnection[v]) {
                    isconnection[u] = true;
                    list.remove(i);
                } else if (isconnection[u]) {
                    isconnection[v] = true;
                    res++;
                    list.remove(i);
                }
            }
        }
        return res;
    }


    @Override
    public Object initData() {
        return null;
    }
}