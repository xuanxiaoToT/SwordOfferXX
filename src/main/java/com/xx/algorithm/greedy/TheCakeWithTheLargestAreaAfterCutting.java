package com.xx.algorithm.greedy;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/27
 * <p>
 * 切割后面积最大的蛋糕
 * LeetCode 1465. Medium
 * <p>
 * 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，其中：
 * horizontalCuts[i] 是从矩形蛋糕顶部到第  i 个水平切口的距离
 * verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离
 * 请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积 。
 * 由于答案可能是一个很大的数字，因此需要将结果 对 109 + 7 取余 后返回。
 * <p>
 * 示例 1：
 * 输入：h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * 输出：4
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。
 * <p>
 * 示例 2：
 * 输入：h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * 输出：6
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。
 * <p>
 * 示例 3：
 * 输入：h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * 输出：9
 * <p>
 * 提示：
 * 2 <= h, w <= 10^9
 * 1 <= horizontalCuts.length <= min(h - 1, 105)
 * 1 <= verticalCuts.length <= min(w - 1, 105)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * 题目数据保证 horizontalCuts 中的所有元素各不相同
 * 题目数据保证 verticalCuts 中的所有元素各不相同
 */
public class TheCakeWithTheLargestAreaAfterCutting implements Answer {

    public static void main(String[] args) {
        new TheCakeWithTheLargestAreaAfterCutting().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int h = 5;
        int w = 4;
        int[] hor = new int[]{1, 2, 4};
        int[] ver = new int[]{1, 3};
        System.out.println(maxArea(h, w, hor, ver));
    }

    public int maxArea(int h, int w, int[] hor, int[] ver) {
        int remainderNum = 1000000007;
        Arrays.sort(hor);
        Arrays.sort(ver);
        int maxLength = findMaxLength(w, ver);
        int maxHeight = findMaxHeight(h, hor);
        return (int) (((long) maxLength * (long) maxHeight) % remainderNum);
    }

    private int findMaxHeight(int h, int[] hor) {
        int maxHeight = 0;
        int lastHeight = 0;
        for (final int value : hor) {
            maxHeight = Math.max(value - lastHeight, maxHeight);
            lastHeight = value;
        }
        maxHeight = Math.max(h - lastHeight, maxHeight);
        return maxHeight;
    }

    private int findMaxLength(int w, int[] ver) {
        int findMaxLength = 0;
        int lastLength = 0;
        for (final int value : ver) {
            findMaxLength = Math.max(value - lastLength, findMaxLength);
            lastLength = value;
        }
        findMaxLength = Math.max(w - lastLength, findMaxLength);
        return findMaxLength;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
