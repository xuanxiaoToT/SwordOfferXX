package com.xx.basicDs.graph;

import com.xx.Answer;

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
 * 则其groph画出来如图：    0--3   则此图是二分图。
 * |  |
 * 1--2
 * 而如果输入graph为[[1，2，3]，[0，2]，[0，1，3]，[0，2]]，那么该图是一个非二分图
 */
public class BipartiteGraph implements Answer {

    public static void main(String[] args) {
        new BipartiteGraph().answerOne();
    }

    /**
     * 解:二分图的节点可以分成两种不同的类型，任意一条边的两个节点分别属于两种不同的类型。
     */
    @Override
    public void answerOne() {

    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};
    }
}
