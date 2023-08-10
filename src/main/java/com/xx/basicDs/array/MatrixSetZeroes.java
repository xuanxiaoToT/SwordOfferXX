package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/10
 * <p>
 * 矩阵置零
 * LeetCode 73
 * <p>
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。
 * 请使用 原地 算法。
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
        System.out.println(Arrays.deepToString(matrix));
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
