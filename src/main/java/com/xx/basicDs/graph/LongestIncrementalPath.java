package com.xx.basicDs.graph;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/26
 * <p>
 * 最长递增路径
 * <p>
 * 输入一个整数矩阵，请求最长递增路径的长度。矩阵中
 * 的路径沿着上、下、左、右4个方向前行。
 * <p>
 * 例如，图中矩阵
 * 3 4 5
 * 3 2 8
 * 2 2 1
 * 的最长递增路径的长度为4，其中一条最长的递增路径为3→4→5→8。
 * <p>
 * 思路：
 * 这又是一个以矩阵为背景的经典题目。仍然可以将矩阵中
 * 的数字看成图中的节点。由于这个问题是关于递增路径的，因此只关
 * 心从较小的数字指向较大的数字的边，两个不同数字在图中对应的节
 * 点之间的边是有向边，针对这个问题构建出来的图是一个有向图。同
 * 时，由于图中所有边都是从较小的数字指向较大的数字，这样的边不
 * 可能形成环，因此构建出来的图一定是有向无环图。
 */
public class LongestIncrementalPath implements Answer {

    public static void main(String[] args) {
        new LongestIncrementalPath().answerOne();
    }

    int result = 1;

    /**
     * 解:深度利用深度优先遍历
     */
    @Override
    public void answerOne() {
        int[][] data = initData();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                myDiGui(data, new int[data.length][data[0].length], i, j, 0, data[i][j], true);
            }
        }
        System.out.println(result);
    }

    /**
     * 利用递归实现深度优先
     *
     * @param isFirst：表明第一次进入，此时还没有lastValue
     * @param flag：是否访问过
     */
    private void myDiGui(int[][] data, int[][] flag, int i, int j, int temp, int lastValue, boolean isFirst) {
        if (!isNormal(data, i, j) || flag[i][j] == 1 || (data[i][j] <= lastValue && !isFirst)) {
            result = Math.max(result, temp);
            return;
        }
        flag[i][j] = 1;
        // 向上下左右
        myDiGui(data, flag, i + 1, j, temp + 1, data[i][j], false);
        myDiGui(data, flag, i - 1, j, temp + 1, data[i][j], false);
        myDiGui(data, flag, i, j + 1, temp + 1, data[i][j], false);
        myDiGui(data, flag, i, j - 1, temp + 1, data[i][j], false);
        flag[i][j] = 0;
    }

    private boolean isNormal(int[][] data, int i, int j) {
        return i >= 0 && i < data.length && j >= 0 && j < data[0].length;
    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        //return new int[][]{{3, 4, 5}, {3, 2, 8}, {2, 2, 1}};
        return new int[][]{{1, 2, 3}, {6, 5, 4}, {7, 8, 9}};
    }
}