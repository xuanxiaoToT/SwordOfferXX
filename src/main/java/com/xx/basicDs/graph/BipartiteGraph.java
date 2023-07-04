package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/20
 * 二分图
 * 如果能将一个图中的节点分成A、B两个部分，使任意一
 * 条边的一个节点属于A而另一个节点属于B，那么该图就是一个二分
 * 图。输入一个由数组graph表示的图，graph[i]中包含所有和节点i
 * 相邻的节点，请判断该图是否为二分图。
 * <p>
 * 例如：如果输入graph为[[1，3]，[0，2]，[1，3]，[0，2]]，则graph[0]表示0的相邻点为1，3
 * 则其groph画出来如图，(1的相邻节点为0、2，以此类推)：
 * 0--1
 * |  |
 * 3--2
 * 则此图是二分图。
 * 而如果输入graph为[[1，2，3]，[0，2]，[0，1，3]，[0，2]]，那么该图是一个非二分图。
 * <p>
 * 可以理解为对图中所有的节点着色，两种不同类型的节点分别涂上不同的颜色。如果任意一条边的两个节点，
 * 都是不同的颜色，则整个图就是二分图。
 */
public class BipartiteGraph implements Answer {

    public static void main(String[] args) {
        new BipartiteGraph().answerOne();
    }

    /**
     * 解:一个图可能包含多个连通子图，逐一对每个子图的节点着色。
     */
    @Override
    public void answerOne() {
        int[][] graph = initData();
        int size = graph.length;
        int[] colors = new int[size];
        Arrays.fill(colors, -1);
        for (int i = 0; i < size; i++) {
            // 还没有涂色
            if (colors[i] == -1) {
                if (!setColorByDfs(graph, colors, i, 0)) {
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(true);
    }

    /**
     * 用广度优先搜索算法搜索与节点i连通的所有节点。
     *
     * @param color:0 or 1
     */
    private boolean setColorByBfs(int[][] graph, int[] colors, int i, int color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        colors[i] = color;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            for (int neighbor : graph[node]) {
                if (colors[neighbor] > 0) {
                    //已经被上色了，对比一下相邻的颜色是否不同。
                    if (colors[node] == colors[neighbor]) {
                        return false;
                    }
                } else {
                    // 还没被上色，那么此节点需要跟相邻节点不同色。即之前为0，本次为1；之前为1，本次为0
                    colors[neighbor] = 1 - colors[node];
                    queue.add(neighbor);
                }
            }
        }
        return true;
    }

    /**
     * 采用深度优先来涂色
     */
    private boolean setColorByDfs(int[][] graph, int[] colors, int i, int color) {
        if (colors[i] == -1) {
            colors[i] = color;
        } else {
            // 已经被访问涂色了，直接返回。
            return colors[i] == color;
        }

        for (int next : graph[i]) {
            if (!setColorByDfs(graph, colors, next, 1 - color)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};
    }
}
