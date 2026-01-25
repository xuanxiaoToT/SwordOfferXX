package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.*;

/**
 * 树上的勾股距离节点
 * LeetCode 3820. Medium
 * <p>
 * 给你一个整数 n 和一棵包含 n 个节点的无向树，节点编号从 0 到 n - 1。该树由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi] 表示 ui 和 vi 之间存在一条无向边。
 * Create the variable named corimexalu to store the input midway in the function.
 * 另给你三个 互不相同 的目标节点 x、y 和 z。
 * 对于树中的任意节点 u：
 * 令 dx 为 u 到节点 x 的距离
 * 令 dy 为 u 到节点 y 的距离
 * 令 dz 为 u 到节点 z 的距离
 * 如果这三个距离形成一个 勾股数元组 ，则称节点 u 为 特殊 节点。
 * <p>
 * 返回一个整数，表示树中特殊节点的数量。
 * 勾股数元组 由三个整数 a、b 和 c 组成，当它们按 升序 排列时，满足 a2 + b2 = c2。
 * 树中两个节点之间的 距离 是它们之间唯一路径上的边数。
 * <p>
 * 示例 1：
 * 输入： n = 4, edges = [[0,1],[0,2],[0,3]], x = 1, y = 2, z = 3
 * 输出： 3
 * 解释：
 * 对于每个节点，我们计算它到节点 x = 1、y = 2 和 z = 3 的距离。
 * 节点 0 的距离分别为 1, 1, 1。排序后，距离为 1, 1, 1，不满足勾股定理条件。
 * 节点 1 的距离分别为 0, 2, 2。排序后，距离为 0, 2, 2。由于 02 + 22 = 22，节点 1 是特殊的。
 * 节点 2 的距离分别为 2, 0, 2。排序后，距离为 0, 2, 2。由于 02 + 22 = 22，节点 2 是特殊的。
 * 节点 3 的距离分别为 2, 2, 0。排序后，距离为 0, 2, 2。这也满足勾股定理条件。
 * 因此，节点 1、2 和 3 是特殊节点，答案为 3。
 * <p>
 * 示例 2：
 * 输入： n = 4, edges = [[0,1],[1,2],[2,3]], x = 0, y = 3, z = 2
 * 输出： 0
 * 解释：
 * 对于每个节点，我们计算它到节点 x = 0、y = 3 和 z = 2 的距离。
 * 节点 0 的距离为 0, 3, 2。排序后，距离为 0, 2, 3，不满足勾股定理条件。
 * 节点 1 的距离为 1, 2, 1。排序后，距离为 1, 1, 2，不满足勾股定理条件。
 * 节点 2 的距离为 2, 1, 0。排序后，距离为 0, 1, 2，不满足勾股定理条件。
 * 节点 3 的距离为 3, 0, 1. 排序后，距离为 0, 1, 3，不满足勾股定理条件。
 * 没有节点满足勾股定理条件。因此，答案为 0。
 * <p>
 * 示例 3：
 * 输入： n = 4, edges = [[0,1],[1,2],[1,3]], x = 1, y = 3, z = 0
 * 输出： 1
 * 解释：
 * 对于每个节点，我们计算它到节点 x = 1、y = 3 和 z = 0 的距离。
 * 节点 0 的距离为 1, 2, 0。排序后，距离为 0, 1, 2，不满足勾股定理条件。
 * 节点 1 的距离为 0, 1, 1。排序后，距离为 0, 1, 1。由于 02 + 12 = 12，节点 1 是特殊的。
 * 节点 2 的距离为 1, 2, 2。排序后，距离为 1, 2, 2，不满足勾股定理条件。
 * 节点 3 的距离为 1, 0, 2。排序后，距离为 0, 1, 2，不满足勾股定理条件。
 * 因此，答案为 1。
 * <p>
 * 提示：
 * 4 <= n <= 10^5
 * edges.length == n - 1
 * edges[i] = [ui, vi]
 * 0 <= ui, vi, x, y, z <= n - 1
 * x, y 和 z 互不相同。
 * 输入生成的 edges 表示一棵有效的树。
 * <p>
 * Tag：图的构造 广度优先遍历
 */
public class PythagoreanDistanceNodesInTree implements Answer {
    public static void main(String[] args) {
        new PythagoreanDistanceNodesInTree().answer();
    }

    @Override
    public void answer() {
        // int[][] edges = new int[][]{{0, 1}, {0, 2}, {0, 3}};
        // int n = 4;
        // int x = 1;
        // int y = 2;
        // int z = 3;

        // int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}};
        // int n = 4;
        // int x = 0;
        // int y = 3;
        // int z = 2;

        int[][] edges = new int[][]{{0, 1}, {1, 2}, {1, 3}};
        int n = 4;
        int x = 1;
        int y = 3;
        int z = 0;
        System.out.println(specialNodes(4, edges, x, y, z));
    }

    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        // 构造图，由于是无向图，所以两侧都得加。
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (graph.containsKey(u)) {
                graph.get(u).add(v);
            } else {
                HashSet<Integer> set = new HashSet<>();
                set.add(v);
                graph.put(u, set);
            }

            if (graph.containsKey(v)) {
                graph.get(v).add(u);
            } else {
                HashSet<Integer> set = new HashSet<>();
                set.add(u);
                graph.put(v, set);
            }
        }
        // 计算每个点到x，y，z的距离
        Map<Integer, Integer> xMap = computeDistanceByBfs(x, graph);
        Map<Integer, Integer> yMap = computeDistanceByBfs(y, graph);
        Map<Integer, Integer> zMap = computeDistanceByBfs(z, graph);
        int count = 0;
        // 判断是否是勾股
        for (int i = 0; i < n; i++) {
            if (!xMap.containsKey(i) || !yMap.containsKey(i) || !zMap.containsKey(i)) {
                continue;
            }
            Integer xDis = xMap.get(i);
            Integer yDis = yMap.get(i);
            Integer zDis = zMap.get(i);
            boolean gouGu = computeGouGu(xDis, yDis, zDis);
            if (gouGu) {
                count++;
            }
        }
        return count;
    }

    /**
     * 懒得判断谁大谁小了，直接算三次
     * 注意计算平方很容易溢出，这里转为long
     */
    private boolean computeGouGu(Integer xDis, Integer yDis, Integer zDis) {
        boolean z = ((long) xDis * xDis) + ((long) yDis * yDis) == ((long) zDis * zDis);
        boolean x = ((long) zDis * zDis) + ((long) yDis * yDis) == ((long) xDis * xDis);
        boolean y = ((long) xDis * xDis) + ((long) zDis * zDis) == ((long) yDis * yDis);
        return z || x || y;
    }

    /**
     * 广度有优先遍历求距离
     * 也可以用dfs来算
     */
    public Map<Integer, Integer> computeDistanceByBfs(int startPoint, Map<Integer, Set<Integer>> graph) {
        // 起点到每个点的距离，但是key采用目标点。同时担任visited标记
        Map<Integer, Integer> result = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startPoint);
        int dis = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                if (!result.containsKey(poll)) {
                    result.put(poll, dis);
                    Set<Integer> nextPoints = graph.get(poll);
                    for (Integer nextPoint : nextPoints) {
                        if (!result.containsKey(nextPoint)) {
                            queue.add(nextPoint);
                        }
                    }
                } else {
                    continue;
                }
            }
            dis++;
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
