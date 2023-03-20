package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/2
 * <p>
 * 数组中和为0的3个数字
 * LeetCode 015
 * <p>
 * 输入一个数组，如何找出数组中所有和为0的3个数字的
 * 三元组？需要注意的是，返回值中不得包含重复的三元组。
 * <p>
 * 例如，
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
     */
    @Override
    public void answerOne() {

    }

    /**
     * 采用Map，然后O(N2)的遍历
     */
    private void answerThree() {

    }

    /**
     * something
     */
    @Override
    public Object initData() {
        return null;
    }
}
