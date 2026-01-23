package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/7/15
 * <p>
 * 节点间通路
 * LeetCode Middle
 * <p>
 * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 * <p>
 * 示例1:
 * 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 * 输出：true
 * <p>
 * 示例2:
 * 输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
 * 输出 true
 * <p>
 * 提示：
 * 节点数量n在[0, 1e5]范围内。
 * 节点编号大于等于 0 小于 n。
 * 图中可能存在自环和平行边。
 * <p>
 * Tag：图的遍历  节点路径
 */
public class RouteBetweenNodes implements Answer {
    public static void main(String[] args) {
        new RouteBetweenNodes().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int n = 5;
        int[][] graph = new int[][]{{0, 1}, {0, 2}, {0, 4}, {0, 4}, {0, 1}, {1, 3}, {1, 4}, {1, 3}, {2, 3}, {3, 4}};
        int start = 0;
        int target = 4;
        System.out.println(findWhetherExistsPath(n, graph, start, target));
    }

    /**
     * BFS简单做
     * 注意有回环，需要用到hasVisited
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        if (target > n) {
            return false;
        }
        Map<Integer, Set<Integer>> mapGraph = new HashMap<>();
        for (int[] node : graph) {
            if (mapGraph.containsKey(node[0])) {
                mapGraph.get(node[0]).add(node[1]);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(node[1]);
                mapGraph.put(node[0], set);
            }
        }
        if (!mapGraph.containsKey(start)) {
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        Set<Integer> hasVisited = new HashSet<>();
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (hasVisited.contains(poll)) {
                continue;
            }
            hasVisited.add(poll);
            Set<Integer> set = mapGraph.get(poll);
            if (set != null) {
                if (set.contains(target)) {
                    return true;
                }
                queue.addAll(set);
            }
        }
        return false;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
