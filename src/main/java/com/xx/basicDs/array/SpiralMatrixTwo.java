package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/19
 * <p>
 * 螺旋矩阵 II
 * LeetCode 59.
 * <p>
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 1->2->3
 * 8->9  4
 * 7<-6<-5
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 */
public class SpiralMatrixTwo implements Answer {

    public static void main(String[] args) {
        new SpiralMatrixTwo().answer();
    }

    /**
     * 解1：每轮循环完毕后,最外层便螺旋填充完毕,其内层剩余的仍旧是一个需要螺旋填充的空白,继续填充即可.
     */
    @Override
    public void answer() {
        int n = initData();
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while (num <= tar) {
            // left to right.
            for (int i = l; i <= r; i++) mat[t][i] = num++;
            t++;
            // top to bottom.
            for (int i = t; i <= b; i++) mat[i][r] = num++;
            r--;
            // right to left.
            for (int i = r; i >= l; i--) mat[b][i] = num++;
            b--;
            // bottom to top.
            for (int i = b; i >= t; i--) mat[i][l] = num++;
            l++;
        }
        System.out.println(Arrays.deepToString(mat));
    }

    /**
     * 输出数据
     */
    @Override
    public Integer initData() {
        return 4;
    }
}
