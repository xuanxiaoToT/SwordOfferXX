package com.xx.algorithm.greedy;

import com.xx.Answer;

import java.util.Arrays;

/**
 * 重新排列后的最大子矩阵
 * LeetCode 1727. Medium
 * <p>
 * 给你一个二进制矩阵 matrix ，它的大小为 m x n ，你可以将 matrix 中的 列 按任意顺序重新排列。
 * <p>
 * 请你返回最优方案下将 matrix 重新排列后，全是 1 的最大子矩阵面积。
 * <p>
 * 示例 1：
 * 输入：matrix = [[0,0,1],[1,1,1],[1,0,1]]
 * 输出：4
 * 解释：你可以按照上图方式重新排列矩阵的每一列。
 * 最大的全 1 子矩阵是上图中加粗的部分，面积为 4 。
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,0,1,0,1]]
 * 输出：3
 * 解释：你可以按照上图方式重新排列矩阵的每一列。
 * 最大的全 1 子矩阵是上图中加粗的部分，面积为 3 。
 * <p>
 * 示例 3：
 * 输入：matrix = [[1,1,0],[1,0,1]]
 * 输出：2
 * 解释：由于你只能整列整列重新排布，所以没有比面积为 2 更大的全 1 子矩形。
 * <p>
 * 示例 4：
 * 输入：matrix = [[0,0],[0,0]]
 * 输出：0
 * 解释：由于矩阵中没有 1 ，没有任何全 1 的子矩阵，所以面积为 0 。
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m * n <= 10^5
 * matrix[i][j] 要么是 0 ，要么是 1 。
 * <p>
 * Tag：矩阵  贪心  排序
 */
public class LargestSubmatrixWithRearrangements implements Answer {
    public static void main(String[] args) {
        new LargestSubmatrixWithRearrangements().answer();
    }

    @Override
    public void answer() {
        // int[][] matrix = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}};
        int[][] matrix = {{1, 1, 1, 0, 0}};
        System.out.println(largestSubmatrix(matrix));
    }

    /**
     * temp类似前缀和，求每个列的当前行为底的，每个矩形高度。
     * 然后再遍历每一行，如果最终矩形以这个行为底，则其最大值是多少
     * 排序后是个倒梯形，长度乘以高度就行，高度都不用算最小值
     */
    public int largestSubmatrix(int[][] matrix) {
        int[][] temp = new int[matrix.length][matrix[0].length];
        for (int lie = 0; lie < matrix[0].length; lie++) {
            for (int hang = 0; hang < matrix.length; hang++) {
                if (matrix[hang][lie] == 0) {
                    temp[hang][lie] = 0;
                } else {
                    temp[hang][lie] = hang >= 1 ? temp[hang - 1][lie] + 1 : 1;
                }
            }
        }
        int res = 0;
        for (int hang = 0; hang < matrix.length; hang++) {
            int[] bottom = temp[hang];
            Arrays.sort(bottom);
            int length = 0;
            for (int i = bottom.length - 1; i >= 0; i--) {
                length++;
                int high = bottom[i];
                if (high == 0) {
                    break;
                }
                res = Math.max(res, length * high);
            }
        }
        return res;
    }

    @Override
    public Object initData() {
        return null;
    }
}
