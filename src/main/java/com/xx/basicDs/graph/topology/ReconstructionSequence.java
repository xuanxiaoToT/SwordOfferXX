package com.xx.basicDs.graph.topology;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/27
 * 重建序列
 * 长度为n的数组org是数字1～n的一个排列，seqs是若干
 * 序列，请判断数组org是否为可以由seqs重建的唯一序列。重建的序
 * 列是指seqs所有序列的最短公共超序列，即seqs中的任意序列都是
 * 该序列的子序列。
 * <p>
 * 例如，如果数组org为[4，1，5，2，6，3]，而seqs为[[5，2，
 * 6，3]，[4，1，5，2]]，因为用[[5，2，6，3]，[4，1，5，2]]可以
 * 重建出唯一的序列[4，1，5，2，6，3]，所以返回true。如果数组org
 * 为[1，2，3]，而seqs为[[1，2]，[1，3]]，因为用[[1，2]，[1，3]]
 * 可以重建出两个序列，[1，2，3]或[1，3，2]，所以返回false。
 * <p>
 * 运用拓扑排序方法
 */
public class ReconstructionSequence implements Answer {

    public static void main(String[] args) {
        new ReconstructionSequence().answer();
    }

    /**
     * 解
     */
    @Override
    public void answer() {
        int[] org = new int[]{1, 2, 3};
        int[][] seqs = initData();
        Map<Integer, Integer> inDegree = new HashMap<>(org.length);
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        // 构建graph
        for (int[] seq : seqs) {
            for (int j = 1; j < seq.length; j++) {
                int last = seq[j - 1];
                int it = seq[j];
                if (graph.containsKey(last)) {
                    if (!graph.get(last).contains(it)) {
                        putDegree(inDegree, it);
                        graph.get(last).add(it);
                    }
                } else {
                    HashSet<Integer> valueSet = new HashSet<>();
                    valueSet.add(it);
                    graph.put(last, valueSet);
                    putDegree(inDegree, it);
                }
            }
        }
        // System.out.println(graph);
        Queue<Integer> queue = new LinkedList<>();
        int orgIndex = 0;
        if (inDegree.get(org[0]) != null) {
            System.out.println("false");
            return;
        }
        queue.add(org[0]);
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size != 1) {
                System.out.println(false);
            }
            Integer poll = queue.poll();
            //检查poll与org
            if (org[orgIndex++] != poll) {
                System.out.println(false);
            }
            for (int i = 0; i < size; i++) {
                Set<Integer> set = graph.get(poll);
                if (set != null) {
                    for (Integer next : set) {
                        Integer nextIndegree = inDegree.get(next);
                        if (nextIndegree - 1 == 0) {
                            queue.add(next);
                        }
                        inDegree.put(next, nextIndegree - 1);
                    }
                }
            }
        }
    }


    private void putDegree(Map<Integer, Integer> inDegree, int it) {
        if (inDegree.containsKey(it)) {
            inDegree.put(it, inDegree.get(it) + 1);
        } else {
            inDegree.put(it, 1);
        }
    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        // return new int[][]{{5, 2, 6, 3}, {4, 1, 5, 2}};
        return new int[][]{{1, 2}, {1, 3}};
    }
}
