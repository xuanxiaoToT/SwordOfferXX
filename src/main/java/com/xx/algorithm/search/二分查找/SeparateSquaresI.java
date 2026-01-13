package com.xx.algorithm.search.二分查找;

import com.xx.Answer;

/**
 * 分割正方形I
 * LeetCode 3453.  Medium
 * <p>
 * 给你一个二维整数数组 squares ，其中 squares[i] = [xi, yi, li] 表示一个与 x 轴平行的正方形的左下角坐标和正方形的边长。
 * 找到一个最小的 y 坐标，它对应一条水平线，该线需要满足它以上正方形的总面积 等于 该线以下正方形的总面积。
 * 答案如果与实际答案的误差在 10-5 以内，将视为正确答案。
 * 注意：正方形 可能会 重叠。重叠区域应该被 多次计数 。
 * <p>
 * 示例 1：
 * 输入： squares = [[0,0,1],[2,2,1]]
 * 输出： 1.00000
 * 解释：
 * 任何在 y = 1 和 y = 2 之间的水平线都会有 1 平方单位的面积在其上方，1 平方单位的面积在其下方。最小的 y 坐标是 1。
 * <p>
 * 示例 2：
 * 输入： squares = [[0,0,2],[1,1,1]]
 * 输出： 1.16667
 * 解释：
 * 面积如下：
 * 线下的面积：7/6 * 2 (红色) + 1/6 (蓝色) = 15/6 = 2.5。
 * 线上的面积：5/6 * 2 (红色) + 5/6 (蓝色) = 15/6 = 2.5。
 * 由于线以上和线以下的面积相等，输出为 7/6 = 1.16667。
 * <p>
 * 提示：
 * 1 <= squares.length <= 5 * 10^4
 * squares[i] = [xi, yi, li]
 * squares[i].length == 3
 * 0 <= xi, yi <= 10^9
 * 1 <= li <= 10^9
 * 所有正方形的总面积不超过 10^12。
 * <p>
 * Tag：Double类型的二分查找
 */
public class SeparateSquaresI implements Answer {
    public static void main(String[] args) {
        new SeparateSquaresI().answerOne();
    }

    @Override
    public void answerOne() {
        // int[][] squares = new int[][]{{0, 0, 1}, {2, 2, 1}};
        int[][] squares = new int[][]{{0, 0, 2}, {1, 1, 1}};
        System.out.println(separateSquares(squares));
    }

    /**
     * double类型的二分查找
     * <p>
     * 注意：l的长度最高为10^9，而int的最大值为10^9，所以算面积的时候如果不转，就会溢出~！
     */
    public double separateSquares(int[][] squares) {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        double totalArea = 0;
        for (int[] square : squares) {
            int y = square[1];
            int length = square[2];
            minY = Math.min(y, minY);
            maxY = Math.max(y + length, maxY);
            // 注意溢出~！
            totalArea = totalArea + (double) length * length;
        }

        double precision = 1e-5;
        double mid = 0;
        // (maxY - minY) >= precision double精度类二分查找，关键点。
        while (minY <= maxY && (maxY - minY) >= precision) {
            mid = (minY + maxY) / 2;
            // 计算下方的面积
            double downArea = computeArea(squares, mid);
            if (downArea * 2 >= totalArea) {
                maxY = mid;
            } else {
                minY = mid;
            }
        }
        return mid;
    }

    private double computeArea(int[][] squares, double y) {
        double total = 0;
        for (int[] square : squares) {
            int yi = square[1];
            int l = square[2];
            if (yi >= y) {
                continue;
            }
            if (yi + l <= y) {
                total = total + (double) l * l;
            } else {
                total = total + (double) l * (y - yi);
            }
        }
        return total;
    }

    @Override
    public Object initData() {
        return null;
    }
}
