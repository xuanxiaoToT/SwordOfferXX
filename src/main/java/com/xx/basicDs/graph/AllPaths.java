package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/26
 * <p>
 * 所有路径
 * <p>
 * 一个有向无环图由n个节点（标号从0到n-1，n≥2）组
 * 成，请找出从节点0到节点n-1的所有路径。图用一个数组graph表
 * 示，数组的graph[i]包含所有从节点i能直接到达的节点。例如，输
 * 入数组graph为[[1，2]，[3]，[3]，[]]，则输出两条从节点0到节
 * 点3的路径，分别为0→1→3和0→2→3，
 * <p>
 * 思路：
 * 这个题目要求找出有向无环图中从节点0到节点n-1的所有
 * 路径，自然需要搜索图中的所有节点。通常可以用广度优先搜索或深
 * 度优先搜索完成图的搜索。由于这个题目要求列出从节点0到节点n-1
 * 的所有路径，因此深度优先搜索是更合适的选择。
 */
public class AllPaths implements Answer {

    public static void main(String[] args) {
        new AllPaths().answer();
    }

    /**
     * 解:采用深度优先搜索
     */
    @Override
    public void answer() {
        int[][] graph = initData();
        List<List<Integer>> result = new ArrayList<>();
        myDiGui(graph, 0, result, new ArrayList<>());
        System.out.println(result);
    }

    /**
     * 递归法实现的深度优先
     * 很像回溯
     */
    private void myDiGui(int[][] graph, int index, List<List<Integer>> result, List<Integer> temp) {
        temp.add(index);
        if (index == graph.length - 1 && graph[index].length == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        int[] nextList = graph[index];
        for (int next : nextList) {
            myDiGui(graph, next, result, new ArrayList<>(temp));
        }
        temp.remove(temp.size() - 1);
    }

    /**
     *
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 2}, {3}, {3}, {}};
    }
}
