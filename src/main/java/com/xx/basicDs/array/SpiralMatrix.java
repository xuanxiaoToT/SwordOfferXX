package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/19
 * <p>
 * 螺旋矩阵
 * LeetCode 054
 * <p>
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix implements Answer {

    public static void main(String[] args) {
        new SpiralMatrix().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[][] matrix = initData();
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            System.out.println(res);
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        while (left <= right && bottom >= top) {
            //从左往右
            for (int i = left; i <= right; i++)
                res.add(matrix[top][i]);
            top++;
            if (top > bottom)
                break;
            //右侧，从上往下
            for (int i = top; i <= bottom; i++)
                res.add(matrix[i][right]);
            right--;
            if (right < left)
                break;
            //下侧，从右往左
            for (int i = right; i >= left; i--)
                res.add(matrix[bottom][i]);
            bottom--;
            if (bottom < top)
                break;
            //左侧，从下往上
            for (int i = bottom; i >= top; i--)
                res.add(matrix[i][left]);
            left++;
            if (left > right)
                break;
            //每轮循环完毕后,最外层便螺旋填充完毕,其内层剩余的仍旧是一个需要螺旋填充的空白,继续填充即可.
        }
        System.out.println(res);
    }

    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
    }
}
