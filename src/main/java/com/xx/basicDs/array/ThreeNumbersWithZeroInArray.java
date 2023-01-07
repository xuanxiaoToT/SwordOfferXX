package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/8/2
 * <p>
 * 数组中和为0的3个数字
 * <p>
 * 输入一个数组，如何找出数组中所有和为0的3个数字的
 * 三元组？需要注意的是，返回值中不得包含重复的三元组。例如，
 * 在数组[-1，0，1，2，-1，-4]中有两个三元组的和为0，它们分别
 * 是[-1，0，1]和[-1，-1，2]。
 */
public class ThreeNumbersWithZeroInArray implements Answer {

    public static void main(String[] args) {
        new ThreeNumbersWithZeroInArray().answerOne();
    }

    /**
     * 先排序，然后转变为 求两个数的值等于指定值的那个题。
     * 即：TwoNumbersOfS
     * <p>
     * 还有个问题是如何避免重复？
     */
    @Override
    public void answerOne() {

    }

    /**
     * something
     */
    @Override
    public Object initData() {
        return null;
    }
}
