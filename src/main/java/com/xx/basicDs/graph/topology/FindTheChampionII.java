package com.xx.basicDs.graph.topology;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/5
 * <p>
 * 找到冠军II
 * <p>
 * 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。每支队伍也是 有向无环图（DAG） 上的一个节点。
 * 给你一个整数 n 和一个下标从 0 开始、长度为 m 的二维整数数组 edges 表示这个有向无环图，
 * 其中 edges[i] = [ui, vi] 表示图中存在一条从 ui 队到 vi 队的有向边。
 * 从 a 队到 b 队的有向边意味着 a 队比 b 队 强 ，也就是 b 队比 a 队 弱 。
 * 在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。
 * 如果这场比赛存在 唯一 一个冠军，则返回将会成为冠军的队伍。否则，返回 -1 。
 * <p>
 * 注意
 * 环 是形如 a1, a2, ..., an, an+1 的一个序列，且满足：节点 a1 与节点 an+1 是同一个节点；
 * 节点 a1, a2, ..., an 互不相同；对于范围 [1, n] 中的每个 i ，均存在一条从节点 ai 到节点 ai+1 的有向边。
 * 有向无环图 是不存在任何环的有向图。
 * <p>
 * 示例 1：
 * 输入：n = 3, edges = [[0,1],[1,2]]
 * 输出：0
 * 解释：1 队比 0 队弱。2 队比 1 队弱。所以冠军是 0 队。
 * <p>
 * 示例 2：
 * 输入：n = 4, edges = [[0,2],[1,3],[1,2]]
 * 输出：-1
 * 解释：2 队比 0 队和 1 队弱。3 队比 1 队弱。但是 1 队和 0 队之间不存在强弱对比。所以答案是 -1 。
 * <p>
 * 提示：
 * 1 <= n <= 100
 * m == edges.length
 * 0 <= m <= n * (n - 1) / 2
 * edges[i].length == 2
 * 0 <= edge[i][j] <= n - 1
 * edges[i][0] != edges[i][1]
 * 生成的输入满足：如果 a 队比 b 队强，就不存在 b 队比 a 队强
 * 生成的输入满足：如果 a 队比 b 队强，b 队比 c 队强，那么 a 队比 c 队强
 */
public class FindTheChampionII implements Answer {

    public static void main(String[] args) {
        new FindTheChampionII().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[][] edges = initData();
        System.out.println(findChampion2(4, edges));
    }

    /**
     * 比赛的时候第一时间想到的
     * 不需要遍历！！！
     */
    public int findChampion(int n, int[][] edges) {
        if (edges.length == 0) {
            return n == 1 ? 0 : -1;
        }
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
            if (graph.containsKey(edge[0])) {
                graph.get(edge[0]).add(edge[1]);
            } else {
                HashSet<Integer> set = new HashSet<>();
                set.add(edge[1]);
                graph.put(edge[0], set);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        int resultInt = 0;
        // 从入度为0的开始进行深度优先遍历
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                resultInt = i;
            }
        }
        if (queue.size() >= 2) {
            return -1;
        }
        List<Integer> result = new ArrayList<>();
        // 广度优先遍历来实现拓扑排序
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            result.add(poll);
            HashSet<Integer> set = graph.get(poll);
            if (set != null && !set.isEmpty()) {
                for (Integer next : set) {
                    // 入度-1
                    inDegree[next] = inDegree[next] - 1;
                    if (inDegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }
        if (result.size() == n) {
            return resultInt;
        } else {
            return -1;
        }
    }

    /**
     * 仅考察其入度即可，不需要构建graph，也不需要遍历
     */
    public int findChampion2(int n, int[][] edges) {
        if (edges.length == 0) {
            return n == 1 ? 0 : -1;
        }
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }
        int zeroCount = 0;
        int result = 0;
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                zeroCount++;
                result = i;
            }
        }
        if (zeroCount == 1) {
            return result;
        } else {
            return -1;
        }
    }

    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{0, 2}, {1, 3}, {1, 2}};
    }
}
