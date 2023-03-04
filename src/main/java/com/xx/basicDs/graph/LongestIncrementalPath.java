package com.xx.basicDs.graph;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/26
 * 最长递增路径
 * <p>
 * 输入一个整数矩阵，请求最长递增路径的长度。矩阵中
 * 的路径沿着上、下、左、右4个方向前行。
 * <p>
 * 例如，图15.14中矩阵的
 * 最长递增路径的长度为4，其中一条最长的递增路径为3→4→5→8，
 * 如阴影部分所示。
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
     * isFirst：表明第一次进入，此时还没有lastValue
     */
    private void myDiGui(int[][] data, int[][] flag, int i, int j, int temp, int lastValue, boolean isFirst) {
        if (!isNormal(data, i, j) || flag[i][j] == 1 || (data[i][j] <= lastValue && !isFirst)) {
            result = Math.max(result, temp);
            return;
        }
        flag[i][j] = 1;
        myDiGui(data, flag, i + 1, j, temp + 1, data[i][j], false);
        myDiGui(data, flag, i - 1, j, temp + 1, data[i][j], false);
        myDiGui(data, flag, i, j + 1, temp + 1, data[i][j], false);
        myDiGui(data, flag, i, j - 1, temp + 1, data[i][j], false);
    }

    private boolean isNormal(int[][] data, int i, int j) {
        return i >= 0 && i < data.length && j >= 0 && j < data[0].length;
    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{3, 4, 5}, {3, 2, 8}, {2, 2, 1}};
    }
}