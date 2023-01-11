package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 玄霄
 * @CreateDate 2022/12/26
 * <p>
 * 所有路径
 * <p>
 * 一个有向无环图由n个节点（标号从0到n-1，n≥2）组
 * 成，请找出从节点0到节点n-1的所有路径。图用一个数组graph表
 * 示，数组的graph[i]包含所有从节点i能直接到达的节点。例如，输
 * 入数组graph为[[1，2]，[3]，[3]，[]]，则输出两条从节点0到节
 * 点3的路径，分别为0→1→3和0→2→3，
 */
public class AllPaths implements Answer {

    public static void main(String[] args) {
        new AllPaths().answerOne();
    }

    /**
     * 解:采用深度优先搜索
     */
    @Override
    public void answerOne() {
        int[][] graph = initData();
        List<List<Integer>> result = new ArrayList<>();
        myDiGui(graph, 0, result, new ArrayList<>());
        System.out.println(result);
    }

    /**
     * 递归法实现的深度优先
     */
    private void myDiGui(int[][] graph, int index, List<List<Integer>> result, List<Integer> temp) {
        temp.add(index);
        if (index == graph.length - 1 && graph[index].length == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        int[] ints = graph[index];
        for (int i = 0; i < ints.length; i++) {
            myDiGui(graph, ints[i], result, new ArrayList<>(temp));
        }
    }

    /**
     * 利用栈法来做
     */
    private void zhan() {

    }

    /**
     *
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 2}, {3}, {3}, {}};
    }
}
