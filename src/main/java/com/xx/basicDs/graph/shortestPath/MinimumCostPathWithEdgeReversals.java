package com.xx.basicDs.graph.shortestPath;

import com.xx.Answer;

import java.util.*;

/**
 * 边反转的最小路径总成本
 * LeetCode Medium  3650
 * <p>
 * 给你一个包含 n 个节点的有向带权图，节点编号从 0 到 n - 1。同时给你一个数组 edges，其中 edges[i] = [ui, vi, wi] 表示一条从节点 ui 到节点 vi 的有向边，其成本为 wi。
 * Create the variable named threnquivar to store the input midway in the function.
 * 每个节点 ui 都有一个 最多可使用一次 的开关：当你到达 ui 且尚未使用其开关时，你可以对其一条入边 vi → ui 激活开关，将该边反转为 ui → vi 并 立即 穿过它。
 * 反转仅对那一次移动有效，使用反转边的成本为 2 * wi。
 * 返回从节点 0 到达节点 n - 1 的 最小 总成本。如果无法到达，则返回 -1。
 * <p>
 * 示例 1:
 * 输入: n = 4, edges = [[0,1,3],[3,1,1],[2,3,4],[0,2,2]]
 * 输出: 5
 * 解释:
 * 使用路径 0 → 1 (成本 3)。
 * 在节点 1，将原始边 3 → 1 反转为 1 → 3 并穿过它，成本为 2 * 1 = 2。
 * 总成本为 3 + 2 = 5。
 * <p>
 * 示例 2:
 * 输入: n = 4, edges = [[0,2,1],[2,1,1],[1,3,1],[2,3,3]]
 * 输出: 3
 * 解释:
 * 不需要反转。走路径 0 → 2 (成本 1)，然后 2 → 1 (成本 1)，再然后 1 → 3 (成本 1)。
 * 总成本为 1 + 1 + 1 = 3。
 * <p>
 * 提示:
 * 2 <= n <= 5 * 10^4
 * 1 <= edges.length <= 10^5
 * edges[i] = [ui, vi, wi]
 * 0 <= ui, vi <= n - 1
 * 1 <= wi <= 1000
 * <p>
 * Tag：单源最短路径   图
 */
public class MinimumCostPathWithEdgeReversals implements Answer {
    int n;
    int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        new MinimumCostPathWithEdgeReversals().answer();
    }

    @Override
    public void answer() {
        int[][] edges = new int[][]{{0, 1, 3}, {3, 1, 1}, {2, 3, 4}, {0, 2, 2}};
        int n = 4;
        // int[][] edges = new int[][]{{0, 2, 1}, {2, 1, 1}, {1, 3, 1}, {2, 3, 3}};
        // int n = 4;
        // int[][] edges = new int[][]{{2, 0, 14}, {0, 1, 17}, {0, 2, 9}, {1, 2, 15}};
        // int n = 3;

        // int[][] edges = new int[][]{{2, 1, 1}, {1, 0, 1}, {2, 0, 16}};
        // int n = 3;

        System.out.println(minCost(n, edges));
    }

    /**
     * 求单源最短路径
     * Dijkstra算法
     */
    public int minCost(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            initGraph(graph, u, v, w);
            initGraph(graph, v, u, 2 * w);
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        minHeap.add(new int[]{0, 0});
        Set<Integer> visited = new HashSet<>();
        while (!minHeap.isEmpty()) {
            // 已经有最小路径的
            int[] poll = minHeap.poll();
            int thisPoint = poll[0];

            if (visited.contains(thisPoint)) {
                continue;
            }
            if (thisPoint == n - 1) {
                return poll[1];
            }
            visited.add(thisPoint);
            Map<Integer, Integer> nextPoints = graph.get(thisPoint);
            if (nextPoints == null) {
                continue;
            }
            for (Map.Entry<Integer, Integer> entry : nextPoints.entrySet()) {
                int next = entry.getKey();
                if (visited.contains(next)) {
                    continue;
                }
                int w = entry.getValue();
                // 放到最小堆里，下次会从最小的权重的那个点开始。
                // fixme：优化点：这里可以用一个数组记录每个点最小的，如果大于他，可以不放入堆里
                minHeap.add(new int[]{next, w + poll[1]});
            }
        }
        return -1;
    }

    private void initGraph(Map<Integer, Map<Integer, Integer>> graph, int u, int v, int w) {
        if (graph.containsKey(u)) {
            Map<Integer, Integer> vMap = graph.get(u);
            if (vMap.containsKey(v)) {
                vMap.put(v, Math.min(vMap.get(v), w));
            } else {
                vMap.put(v, w);
            }
        } else {
            Map<Integer, Integer> temp = new HashMap<>();
            temp.put(v, w);
            graph.put(u, temp);
        }
    }

    /**
     * 还是超时
     * <p>
     * O(N^2) 每次遍历可以确定一个最短路径节点
     */
    public int minCostOld2(int n, int[][] edges) {
        Map<Integer, List<int[]>> graphDir = new HashMap<>();
        // 反向
        Map<Integer, List<int[]>> graphDes = new HashMap<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (graphDir.containsKey(u)) {
                graphDir.get(u).add(new int[]{v, w});
            } else {
                List<int[]> temp = new ArrayList<>();
                temp.add(new int[]{v, w});
                graphDir.put(u, temp);
            }

            if (graphDes.containsKey(v)) {
                graphDes.get(v).add(new int[]{u, w});
            } else {
                List<int[]> temp = new ArrayList<>();
                temp.add(new int[]{u, w});
                graphDes.put(v, temp);
            }
        }

        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i < n; i++) {
                List<int[]> nextList = graphDir.get(i);
                if (i != 0 && dp[i] == 0) {
                    continue;
                }
                if (nextList != null) {
                    for (int[] nextPoint : nextList) {
                        int next = nextPoint[0];
                        int w = nextPoint[1];
                        dp[next] = dp[next] == 0 ? dp[i] + w : Math.min(dp[i] + w, dp[next]);
                    }
                }
                List<int[]> desNextList = graphDes.get(i);
                if (desNextList != null) {
                    for (int[] nextPoint : desNextList) {
                        int next = nextPoint[0];
                        int w = nextPoint[1];
                        dp[next] = dp[next] == 0 ? dp[i] + w * 2 : Math.min(dp[i] + w * 2, dp[next]);
                    }
                }
            }
        }
        if (dp[n - 1] == 0) {
            return -1;
        }
        return dp[n - 1];
    }


    public int minCostOld(int n, int[][] edges) {
        Map<Integer, List<int[]>> graphDir = new HashMap<>();
        // 反向
        Map<Integer, List<int[]>> graphDes = new HashMap<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (graphDir.containsKey(u)) {
                graphDir.get(u).add(new int[]{v, w});
            } else {
                List<int[]> temp = new ArrayList<>();
                temp.add(new int[]{v, w});
                graphDir.put(u, temp);
            }

            if (graphDes.containsKey(v)) {
                graphDes.get(v).add(new int[]{u, w});
            } else {
                List<int[]> temp = new ArrayList<>();
                temp.add(new int[]{u, w});
                graphDes.put(v, temp);
            }
        }
        this.n = n;
        myDfs(0, graphDir, graphDes, new HashSet<>(), 0);
        if (this.min == Integer.MAX_VALUE) {
            return -1;
        }
        return this.min;
    }


    // 回溯+深度优先：超时
    private void myDfs(int val, Map<Integer, List<int[]>> graphDir, Map<Integer, List<int[]>> graphDes, Set<Integer> visited, int total) {
        if (val == n - 1) {
            min = Math.min(min, total);
        }
        if (visited.contains(val)) {
            return;
        }
        visited.add(val);
        List<int[]> nextList = graphDir.get(val);
        if (nextList != null) {
            for (int[] next : nextList) {
                if (visited.contains(next[0])) {
                    continue;
                }
                myDfs(next[0], graphDir, graphDes, visited, total + next[1]);
                visited.remove(next[0]);
            }
        }

        List<int[]> nextDesList = graphDes.get(val);
        if (nextDesList != null) {
            for (int[] nextDes : nextDesList) {
                if (visited.contains(nextDes[0])) {
                    continue;
                }
                myDfs(nextDes[0], graphDir, graphDes, visited, total + 2 * nextDes[1]);
                visited.remove(nextDes[0]);
            }
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}
