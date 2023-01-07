package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Stack;

/**
 * @author 玄霄
 * @CreateDate 2022/9/7
 * 直方图最大矩形面积
 * 直方图是由排列在同一基线上的相邻柱子组成的图形。
 * 输入一个由非负数组成的数组，数组中的数字是直方图中柱子的
 * 高。求直方图中最大矩形面积。假设直方图中柱子的宽都为1。例
 * 如，输入数组[3，2，5，4，6，1，4，2]，其对应的直方图如图6.3
 * 所示，该直方图中最大矩形面积为12，如阴影部分所示。
 */
public class HistogramMaxRectangularArea implements Answer {
    public static void main(String[] args) {
        new HistogramMaxRectangularArea().answerTwo();
    }

    /**
     * 方法一：最简单的，直接遍历O(N2)。相当于算出所有组合。然后求出最大值。
     */
    @Override
    public void answerOne() {

    }

    /**
     * 单调栈法：基本思想是确保保存在栈中的直方图的柱子的高度是递增排序的。
     * <p>
     * 原理：以某根柱子为顶的最大矩形，一定是从该柱子向两侧延伸直到遇
     * 到比它矮的柱子，这个最大矩形的高是该柱子的高，最大矩形的宽是
     * 两侧比它矮的柱子中间的间隔。
     * <p>
     * 实现：如果当前扫描到的柱子的高小于位于栈顶的柱子的高，那么将位
     * 于栈顶的柱子的下标出栈，并且计算以位于栈顶的柱子为顶的最大矩
     * 形面积。如果大于，则直接入栈。
     */
    private void answerTwo() {
        int[] inputData = initData();
        Stack<Integer> minIndexStack = new Stack<>();
        int max = 0;
        for (int i = 0; i < inputData.length; i++) {
            if (minIndexStack.isEmpty()) {
                minIndexStack.push(i);
            } else {
                while (!minIndexStack.isEmpty()) {
                    Integer peek = minIndexStack.peek();
                    int peekNum = inputData[peek];
                    if (inputData[i] > peekNum) {
                        minIndexStack.push(i);
                        break;
                    } else {
                        // 如果当前扫描到的柱子的高小于位于栈顶的柱子的高，那么将位
                        // 于栈顶的柱子的下标出栈，并且计算以位于栈顶的柱子为顶的最大矩形面积。
                        minIndexStack.pop();
                        Integer left = minIndexStack.isEmpty() ? 0 : minIndexStack.peek();
                        Integer right = i;
                        Integer area = (right - left + 1) * Math.min(inputData[left], inputData[right]);
                        if (area > max) {
                            max = area;
                            System.out.println(max + " " + left + " " + right);
                        }
                        if (minIndexStack.isEmpty() || inputData[i] > minIndexStack.peek()) {
                            minIndexStack.push(i);
                            break;
                        }
                    }
                }
            }
        }
        // 在对stack中剩余的下标进行计算。略
        System.out.println(minIndexStack);
    }

    /**
     * 方法三：不利用栈的结构，利用方法二的原理，每次以当前为中心进行计算，左右寻找比自己小的，然后计算。
     * 这样也可以
     */
    private void answerThree() {
        //    略
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{3, 2, 5, 4, 6, 1, 4, 2};
    }
}