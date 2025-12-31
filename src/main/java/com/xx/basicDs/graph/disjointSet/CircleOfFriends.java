package com.xx.basicDs.graph.disjointSet;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/30
 * <p>
 * 朋友圈
 * <p>
 * 假设一个班级中有n个学生。学生之间有些是朋友，有些
 * 不是。朋友关系是可以传递的。例如，A是B的直接朋友，B是C的直
 * 接朋友，那么A是C的间接朋友。定义朋友圈就是一组直接朋友或间
 * 接朋友的学生。输入一个n×n的矩阵M表示班上的朋友关系，如果
 * M[i][j]=1，那么学生i和学生j是直接朋友。请计算该班级中朋友圈
 * 的数目。
 * <p>
 * 易得：朋友关系是对称的，也就是说，A和B是朋友，那么B和A自
 * 然也是朋友。因此，输入的矩阵M是沿着对角线对称的。一个人和他自
 * 己是朋友，也就是说矩阵M中对角线上的所有数字都是1。
 * <p>
 * 例如，输入数组[[1，1，0]，[1，1，0]，[0，0，1]]，学生0和
 * 学生1是朋友，他们组成一个朋友圈；学生2一个人组成一个朋友圈。
 * 因此，该班级中朋友圈的数目是2。
 * <p>
 * Tag：并查集
 */
public class CircleOfFriends implements Answer {

    public static void main(String[] args) {
        new CircleOfFriends().answerTwo();
    }

    /**
     * 解：类似求小岛的数目，直接利用广度或深度优先遍历来做即可。
     */
    @Override
    public void answerOne() {
        // 解法略
    }

    /**
     * 使用并查集来解决
     * 并查集主要用于解决一些元素分组的问题。它管理一系列不相交的集合，并支持两种操作：
     * <p>
     * 合并（Union）：把两个不相交的集合合并为一个集合。
     * 查询（Find）：查询两个元素是否在同一个集合中。
     * <p>
     * 求子图的数目，最大子图的节点数等，都可以用并查集。
     * 一般做题的步骤：先初始化子图(既每个图包含一个节点)，然后将符合条件的彼此
     * 进行连接合并。合并时，需要判断(既查询是否已经存在在这个子图中)
     */
    private void answerTwo() {
        int[][] data = initData();
        int[] fatherNode = new int[data.length];
        Arrays.parallelSetAll(fatherNode, i -> i);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                // i和j是朋友，则将其合并为一个图
                if (data[i][j] == 1) {
                    unionFather(fatherNode, i, j);
                }
            }
        }
        // 还有几个独立的图，就是有几个朋友圈
        int result = 0;
        for (int i = 0; i < fatherNode.length; i++) {
            if (fatherNode[i] == i) {
                result++;
            }
        }
        System.out.println(result);
        System.out.println(Arrays.toString(fatherNode));
    }

    // 查找对应节点的根节点
    private int findRoot(int[] fatherNode, int i) {
        if (fatherNode[i] == i) {
            return fatherNode[i];
        } else {
            // 向上遍历
            return findRoot(fatherNode, fatherNode[i]);
        }
    }

    // 将两个合并为一个图。
    private void unionFather(int[] fatherNode, int i, int j) {
        // 判断这两个的根节点
        int fatherRootI = findRoot(fatherNode, i);
        int fatherRootJ = findRoot(fatherNode, j);
        // 不同的根节点，说明此时不在一个图里，则将这两个点合并
        // if (fatherRootI != fatherRootJ) {
        //     fatherNode[j] = i;
        // }
        // 根节点压缩
        if (fatherRootI != fatherRootJ) {
            fatherNode[fatherRootJ] = fatherRootI;
        }
    }


    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
    }
}