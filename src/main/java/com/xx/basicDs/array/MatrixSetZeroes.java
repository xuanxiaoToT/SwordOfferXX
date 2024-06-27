package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/10
 * <p>
 * 矩阵置零
 * 零矩阵
 * LeetCode 73
 * <p>
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。
 * 请使用 原地 算法。
 * <p>
 * 进阶：
 * 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 */
public class MatrixSetZeroes implements Answer {
    public static void main(String[] args) {
        new MatrixSetZeroes().answerOne();
    }

    /**
     * 解1：简单做
     */
    @Override
    public void answerOne() {
        int[][] matrix = initData();
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public void setZeroes(int[][] matrix) {
        HashSet<Integer> setJ = new HashSet<>();
        HashSet<Integer> setI = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    setI.add(i);
                    setJ.add(j);
                }
            }
        }
        setI.forEach(i -> {
            for (int k = 0; k < matrix[0].length; k++) {
                matrix[i][k] = 0;
            }
        });
        setJ.forEach(j -> {
            for (int k = 0; k < matrix.length; k++) {
                matrix[k][j] = 0;
            }
        });
    }

    /**
     * O(m + n) ：即用一个行，一个列来标识需要置0的行和列。
     * 常量：用二进制位，来标识，因为M和N最大也就是200.
     * 一个200位的byte值即可。
     * 做法略
     */
    public void answerTwo() {

    }

    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        //return new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        //return new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        return new int[][]{{0, 0, 0, 5}, {4, 3, 1, 4}, {0, 1, 1, 4}, {1, 2, 1, 3}, {0, 0, 1, 1}};
    }
}
