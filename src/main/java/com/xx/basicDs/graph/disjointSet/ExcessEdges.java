package com.xx.basicDs.graph.disjointSet;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/1/3
 * <p>
 * 多余的边
 * <p>
 * 树可以看成无环的无向图。在一个包含n个节点（节点标
 * 号为从1到n）的树中添加一条边连接任意两个节点，这棵树就会变
 * 成一个有环的图。给定一个在树中添加了一条边的图，请找出这条
 * 多余的边（用这条边连接的两个节点表示）。输入的图用一个二维
 * 数组edges表示，数组中的每个元素是一条边的两个节点[u，v]（u
 * ＜v）。如果有多个答案，请输出在数组edges中最后出现的边。
 * <p>
 * 例如，如果输入数组edges为[[1，2]，[1，3]，[2，4]，[3，
 * 4]，[2，5]]，则它对应的无向图如图15.25所示。输出为边[3，4]
 * <p>
 * 只要是有环，那么理论上这环的任意一条边都行。
 */
public class ExcessEdges implements Answer {

    public static void main(String[] args) {
        new ExcessEdges().answerTwo();
    }

    /**
     * 解1：参考拓扑排序的方法，当不存在入度为1的点时(边两侧节点的入度都+1)，剩余的点便是一个环，
     * 此时环内的任一条边都可以作为答案(取在edges中最后出现的)。
     */
    @Override
    public void answerOne() {
        // 构建图
        int[][] data = initData();
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Set<Integer> had = new HashSet<>();
        for (int[] edge : data) {
            int node1 = edge[0];
            int node2 = edge[1];
            if (graph.containsKey(node1)) {
                graph.get(node1).add(node2);
            } else {
                HashSet<Integer> tempSet = new HashSet<>();
                tempSet.add(node2);
                graph.put(node1, tempSet);
            }
            if (graph.containsKey(node2)) {
                graph.get(node2).add(node1);
            } else {
                HashSet<Integer> tempSet = new HashSet<>();
                tempSet.add(node1);
                graph.put(node2, tempSet);
            }
            if (inDegree.containsKey(node1)) {
                inDegree.put(node1, inDegree.get(node1) + 1);
            } else {
                inDegree.put(node1, 1);
            }
            if (inDegree.containsKey(node2)) {
                inDegree.put(node2, inDegree.get(node2) + 1);
            } else {
                inDegree.put(node2, 1);
            }
        }

        // System.out.println(graph);

        // 广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        addOneInDegree(inDegree, queue);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            had.add(poll);
            Set<Integer> set = graph.get(poll);
            if (set != null && !set.isEmpty()) {
                for (Integer next : set) {
                    if (!had.contains(next)) {
                        inDegree.put(next, inDegree.get(next) - 1);
                        if (inDegree.get(next) <= 1) {
                            queue.add(next);
                        }
                    }
                }
            }
        }
        //不在had里的便是成为一个圈的。此时倒叙遍历data即可获得。
        for (int i = data.length - 1; i > 0; i--) {
            int[] edge = data[i];
            if (!had.contains(edge[0]) && !had.contains(edge[1])) {
                System.out.println(Arrays.toString(edge));
                return;
            }
        }
        System.out.println(had);
    }

    private void addOneInDegree(Map<Integer, Integer> inDegree, Queue<Integer> queue) {
        inDegree.forEach((key, value) -> {
            if (value == 1) {
                queue.add(key);
            }
        });
    }

    /**
     * 解2：利用并查集
     * 先将所有的点看作单个的子图，然后再按照边进行子图合并。
     * <p>
     * 如果两个节点属于同一个子图，添加一条边连接这两个节点就会形成一个环。
     * 如果两个节点不属于同一个子图，那么正常合并即可。
     */
    public void answerTwo() {
        int[][] data = initData();
        // 如果一定存在环，那么边的个数就等于节点的个数。
        Map<Integer, Integer> fatherMap = new HashMap<>(data.length);
        List<int[]> result = new ArrayList<>();
        for (int[] edge : data) {
            int node1 = edge[0];
            int node2 = edge[1];
            if (!fatherMap.containsKey(node1)) {
                fatherMap.put(node1, node1);
            }
            if (!fatherMap.containsKey(node2)) {
                fatherMap.put(node2, node2);
            }
        }

        for (int[] edge : data) {
            int node1 = edge[0];
            int node2 = edge[1];
            unionNode(fatherMap, node1, node2, result);
        }
        System.out.println(result);
    }

    private void unionNode(Map<Integer, Integer> fatherMap, int node1, int node2, List<int[]> result) {
        int root1 = findRoot(fatherMap, node1);
        int root2 = findRoot(fatherMap, node2);
        if (root1 == root2) {
            // 两个点属于同一个子图，故其是一个环
            result.add(new int[]{node1, node2});
        } else {
            fatherMap.put(root2, root1);
        }
    }

    private int findRoot(Map<Integer, Integer> fatherMap, int node) {
        if (fatherMap.get(node) == node) {
            return node;
        } else {
            return findRoot(fatherMap, fatherMap.get(node));
        }
    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 2}, {2, 5}, {5, 6}};
    }
}