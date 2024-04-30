package com.xx.algorithm.sort;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/4/30
 * <p>
 * 将矩阵按对角线排序
 * LeetCode 1329. Medium
 * <p>
 * 矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。例如，矩阵 mat 有 6 行 3 列，
 * 从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。
 * 给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
 * <p>
 * 示例 1：
 * 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * <p>
 * 示例 2：
 * 输入：mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
 * 输出：[[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 * <p>
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 * <p>
 * Tag:数组的排序算法
 */
public class SortByDiagonally implements Answer {

    public static void main(String[] args) {
        new SortByDiagonally().answerOne();
    }

    @Override
    public void answerOne() {
        int[][] mat = {{3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}};
        mat = diagonalSort(mat);
        System.out.println(Arrays.deepToString(mat));
    }

    /**
     * 对角线的头部
     * 即第一行 和 第一列
     */
    public int[][] diagonalSort(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            sortOneDiagonally(mat, i, 0);
        }
        for (int i = 1; i < mat[0].length; i++) {
            sortOneDiagonally(mat, 0, i);
        }
        return mat;
    }

    /**
     * 从对角线的头出发，进行选择排序
     */
    public void sortOneDiagonally(int[][] mat, int startX, int startY) {
        for (int i = startX, j = startY; i < mat.length && j < mat[0].length; i++, j++) {
            int min = mat[i][j];
            int minI = i;
            int minJ = j;
            for (int k = i + 1, l = j + 1; k < mat.length && l < mat[0].length; k++, l++) {
                if (mat[k][l] < min) {
                    min = mat[k][l];
                    minI = k;
                    minJ = l;
                }
            }
            mat[minI][minJ] = mat[i][j];
            mat[i][j] = min;
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}
