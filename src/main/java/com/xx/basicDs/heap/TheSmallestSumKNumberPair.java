package com.xx.basicDs.heap;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/8
 * <p>
 * 给定两个递增排序的整数数组，从两个数组中各取一个
 * 数字u和v组成一个数对（u，v），请找出和最小的k个数对。例如，
 * 输入两个数组[1，5，13，21]和[2，4，9，15]，和最小的3个数对
 * 为（1，2）、（1，4）和（2，5）。
 *
 */
public class TheSmallestSumKNumberPair implements Answer {

    public static void main(String[] args) {
        new TheSmallestSumKNumberPair().answerOne();
    }

    /**
     * 1.最笨的，就是全部遍历，然后吧数据放入最大堆
     * 2.因为是递增，可以按顺序判断。
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