package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/13
 * <p>
 * 矩阵中的最大矩形
 * 最大矩形
 * LeetCode 85 Hard
 * <p>
 * 请在一个由0、1组成的矩阵中找出最大的只包含1的矩形
 * 并输出它的面积。
 * <p>
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * <p>
 * 示例 2：
 * 输入：matrix = [["0"]]
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：matrix = [["1"]]
 * 输出：1
 * <p>
 * 提示：
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= rows, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class LargestMatrixInMatrix implements Answer {
    public static void main(String[] args) {
        new LargestMatrixInMatrix().answerTwo();
    }

    /**
     * 最简单的方式，挨个遍历，以每个1的值为左上角，计算围成矩形的面积，然后求最大即可。
     * 注意，每个左上角都要算横向和纵向两次
     * 复杂度O(N2)
     * ！！似乎不太可行！！
     */
    @Override
    public void answerOne() {

    }


    /**
     * 按每一行为标准进行遍历，而对应的列则可以视为柱状图，则将问题转换为了<直方图最大矩形面积>的解法。
     */
    public void answerTwo() {
        int[][] input = initData();
        int[] temp = input[0].clone();
        for (int i = 1; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 1) {
                    temp[j] = temp[j] + 1;
                } else {
                    temp[j] = 0;
                }
            }
            System.out.println(Arrays.toString(temp));
            //  传入此temp，给  HistogramMaxRectangularArea 的方法来计算。
        }

    }

    /**
     * something
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 0, 1, 0, 0}, {0, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};
    }
}
