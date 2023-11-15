package com.xx.basicDs.graph.shortestPath;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/15
 * <p>
 * 阈值距离内邻居最少的城市
 * LeetCode 1334. Medium
 * <p>
 * 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，
 * 其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。
 * 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。
 * 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
 * <p>
 * 示例 1：
 * 输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * 输出：3
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
 * 城市 0 -> [城市 1, 城市 2]
 * 城市 1 -> [城市 0, 城市 2, 城市 3]
 * 城市 2 -> [城市 0, 城市 1, 城市 3]
 * 城市 3 -> [城市 1, 城市 2]
 * 城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
 * <p>
 * 示例 2：
 * 输入：n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 * 输出：0
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
 * 城市 0 -> [城市 1]
 * 城市 1 -> [城市 0, 城市 4]
 * 城市 2 -> [城市 3, 城市 4]
 * 城市 3 -> [城市 2, 城市 4]
 * 城市 4 -> [城市 1, 城市 2, 城市 3]
 * 城市 0 在阈值距离 2 以内只有 1 个邻居城市。
 * <p>
 * 提示：
 * 2 <= n <= 100
 * 1 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti, distanceThreshold <= 10^4
 * 所有 (fromi, toi) 都是不同的。
 * <p>
 * Tag: Dijkstra算法 图的最短路径 无向图
 */
public class TheCityWithTheLeastNeighborsWithinThresholdDistance implements Answer {

    public static void main(String[] args) {
        new TheCityWithTheLeastNeighborsWithinThresholdDistance().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int n = 4;
        int[][] edges = initData();
        int distanceThreshold = 4;
        System.out.println(findTheCity(n, edges, distanceThreshold));
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int result = Integer.MAX_VALUE;
        int index = 0;
        Map<Integer, Map<Integer, Integer>> integerListMap = initGraph(edges, n);
        for (int i = 0; i < n; i++) {
            int minCityNum = computeShortestDisByDijkstra(i, integerListMap, n, distanceThreshold);
            if (result >= minCityNum) {
                index = i;
                result = minCityNum;
            }
        }
        return index;
    }

    /**
     * 利用Dijkstra算法求解单源，最短路径
     */
    private int computeShortestDisByDijkstra(int start, Map<Integer, Map<Integer, Integer>> graphMap, int n, int distanceThreshold) {
        int result = 0;
        //flag[i]=true表示"顶点start"到"顶点i"的最短路径已成功获取
        boolean[] flag = new boolean[n];
        //未求出
        int[] undetermined = new int[n];
        //已求出
        int[] determined = new int[n];
        // 前驱节点，prev[i]的值是"顶点vs"到"顶点i"的最短路径所经历的全部顶点中，位于"顶点i"之前的那个顶点。
        //int[] prev = new int[n];

        Map<Integer, Integer> startNext = graphMap.get(start);
        if (startNext == null) {
            return 0;
        }

        for (int i = 0; i < n; i++) {
            flag[i] = false;
            if (i == start) {
                //prev[i] = start;
                flag[i] = true;
                determined[i] = 0;
            } else {
                Integer iValue = startNext.get(i);
                undetermined[i] = iValue == null ? Integer.MAX_VALUE : iValue;
                //prev[i] = iValue == null ? -1 : start;
            }
        }

        //从U中找出路径最短的顶点，并将其加入到determined中
        for (int i = 0; i < determined.length; i++) {
            int minDis = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < n; j++) {
                if (!flag[j] && undetermined[j] < minDis) {
                    minDis = undetermined[j];
                    minIndex = j;
                }
            }
            if (minDis == Integer.MAX_VALUE) {
                continue;
            }
            determined[minIndex] = minDis;
            if (determined[minIndex] <= distanceThreshold) {
                result++;
            }
            flag[minIndex] = true;
            Map<Integer, Integer> nextNodes = graphMap.get(minIndex);

            //修正当前最短路径和前驱顶点（更新U中剩余顶点对应的路径）
            for (int j = 0; j < n; j++) {
                if (!flag[j]) {
                    Integer dis = nextNodes.get(j);
                    int temp = dis == null ? Integer.MAX_VALUE : minDis + dis;
                    if (undetermined[j] > temp) {
                        //prev[j] = minIndex;
                        undetermined[j] = temp;
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(determined));
        return result;
    }

    private Map<Integer, Map<Integer, Integer>> initGraph(int[][] edges, int n) {
        Map<Integer, Map<Integer, Integer>> graphMap = new HashMap<>(n);
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int dis = edge[2];
            if (graphMap.containsKey(from)) {
                graphMap.get(from).put(to, dis);
            } else {
                Map<Integer, Integer> tempMap = new HashMap<>();
                tempMap.put(to, dis);
                graphMap.put(from, tempMap);
            }
            if (graphMap.containsKey(to)) {
                graphMap.get(to).put(from, dis);
            } else {
                Map<Integer, Integer> tempMap = new HashMap<>();
                tempMap.put(from, dis);
                graphMap.put(to, tempMap);
            }
        }
        return graphMap;
    }


    /**
     * LeetCode 官解
     * 使用Dijkstra算法求解
     */
    public int findTheCityLeetCode(int n, int[][] edges, int distanceThreshold) {
        int[] ans = {Integer.MAX_VALUE / 2, -1};
        int[][] dis = new int[n][n];
        boolean[][] vis = new boolean[n][n];
        int[][] mp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dis[i], Integer.MAX_VALUE / 2);
            Arrays.fill(mp[i], Integer.MAX_VALUE / 2);
        }
        for (int[] eg : edges) {
            int from = eg[0], to = eg[1], weight = eg[2];
            mp[from][to] = mp[to][from] = weight;
        }
        for (int i = 0; i < n; ++i) {
            dis[i][i] = 0;
            for (int j = 0; j < n; ++j) {
                int t = -1;
                for (int k = 0; k < n; ++k) {
                    if (!vis[i][k] && (t == -1 || dis[i][k] < dis[i][t])) {
                        t = k;
                    }
                }
                for (int k = 0; k < n; ++k) {
                    dis[i][k] = Math.min(dis[i][k], dis[i][t] + mp[t][k]);
                }
                vis[i][t] = true;
            }
        }
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if (dis[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= ans[0]) {
                ans[0] = cnt;
                ans[1] = i;
            }
        }
        return ans[1];
    }


    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
    }
}
