package com.xx.algorithm.search;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/14
 * <p>
 * 二维数组中的查找
 * leetCode 240. 搜索二维矩阵 II
 * <p>
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Find2DArrayII implements Answer {

    public static void main(String[] args) {
        Find2DArrayII find2DArrayII = new Find2DArrayII();
        //目标值
        int targetData = -10;
        find2DArrayII.answerTwo(targetData);
    }

    /**
     * 解1：先对行二分，再对列二分的方法是错误的~！
     */
    @Override
    public void answer() {
        // 错误解法略
    }

    // 如果 左下角元素 大于了目标值，则目标值一定在该行的上方， 左下角元素 所在行可以消去。
    // 如果 左下角元素 小于了目标值，则目标值一定在该列的右方， 左下角元素 所在列可以消去。
    private void answerTwo(int targetData) {
        int[][] dataArray = initData();
        int rowId = dataArray.length - 1;
        int colId = 0;
        while (colId < dataArray[0].length && rowId >= 0) {
            if (dataArray[rowId][colId] == targetData) {
                System.out.println(rowId + " " + colId);
                break;
            }
            if (dataArray[rowId][colId] > targetData) {
                rowId--;
            }
            if (rowId >= 0 && dataArray[rowId][colId] < targetData) {
                colId++;
            }
        }
        System.out.println("AAA");
    }


    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        //int[][] testDate = {{1, 4, 7, 11, 15},
        //        {2, 5, 8, 12, 19},
        //        {3, 6, 9, 16, 22},
        //        {10, 13, 14, 17, 24},
        //        {18, 21, 23, 26, 30}};

        //return testDate;

        return new int[][]{{-5}};
    }
}