package com.xx.basicDs.array;

import com.xx.Answer;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/5
 * <p>
 * 二维子矩阵的数字之和
 * <p>
 * 输入一个二维矩阵，如何计算给定左上角坐标和右下角
 * 坐标的子矩阵的数字之和？对于同一个二维矩阵，计算子矩阵的数
 * 字之和的函数可能由于输入不同的坐标而被反复调用多次。例如，
 * 输入图2.1中的二维矩阵，以及左上角坐标为（2，1）和右下角坐标
 * 为（4，3）的子矩阵，该函数输出8。
 */
public class SumIn2DSubArray implements Answer {

    public static void main(String[] args) {
        new SumIn2DSubArray().answerOne();
    }

    /**
     * 因为需要反复调用多次，所以每次都用O(mn)去计算就比较浪费了
     * 故使用一个中间结果表，来存放每行的中间结果。
     */
    @Override
    public void answerOne() {
        int[][] nums = initData();
        int[][] sumMidList = new int[nums.length][nums[0].length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if (j == 0) {
                    sumMidList[i][0] = nums[i][0];
                } else {
                    sumMidList[i][j] = nums[i][j] + sumMidList[i][j - 1];
                }
            }
        }

        int leftX = 2;
        int leftY = 1;
        int rightX = 4;
        int rightY = 3;
        int result = 0;
        for (int i = leftX; i <= rightX; i++) {
            if (leftY >= 1) {
                result += sumMidList[i][rightY] - sumMidList[i][leftY - 1];
            } else {
                result += sumMidList[i][rightY] - 0;
            }
        }
        System.out.println(result);
    }

    /**
     * 方法二：也是二维数组保存结果，但每个点保存的是到(0,0)点的矩形的结果。
     */
    private void answerTwo() {

    }

    /**
     * something
     */
    @Override
    public int[][] initData() {
        return DataFactory.generate2DArray();
    }
}