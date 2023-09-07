package com.xx.algorithm.search;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/7
 * <p>
 * 搜索二维矩阵
 * LeetCode 74.
 * <p>
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非递减顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；
 * 否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 */
public class Find2DArrayI implements Answer {

    public static void main(String[] args) {
        new Find2DArrayI().answerOne();
    }

    /**
     * 解1：先对最后一列二分,确定是在第几行
     */
    @Override
    public void answerOne() {
        int[][] data = initData();
        int target = 99;

        int targetRow = findRowNum(data, target);
        if (targetRow > data.length - 1) {
            System.out.println(false);
            return;
        }

        int[] targetRowData = data[targetRow];
        int start = 0;
        int end = targetRowData.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (targetRowData[mid] == target) {
                System.out.println(true);
                return;
            }
            if (targetRowData[mid] > target) {
                end = mid - 1;
            }
            if (targetRowData[mid] < target) {
                start = mid + 1;
            }
        }

        System.out.println(false);
    }

    private int findRowNum(int[][] data, int target) {
        int rowsCount = data.length;
        int colCount = data[0].length;
        int start = 0;
        int end = rowsCount - 1;

        while (start <= end && start < rowsCount) {
            int mid = (start + end) / 2;
            if (data[mid][colCount - 1] >= target) {
                if (mid == 0 || data[mid][colCount - 1] < target) {
                    return mid;
                }
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return end + 1;
    }

    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}, {61, 64, 65, 67}};
    }
}
