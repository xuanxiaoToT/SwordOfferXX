package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/15
 * 排列的数目
 * 给定一个非空的正整数数组nums和一个目标值t，数组中
 * 的所有数字都是唯一的，请计算数字之和等于t的所有排列的数目。
 * 数组中的数字可以在排列中出现任意次。例如，输入数组[1，2，
 * 3]，目标值t为3，那么总共有4个组合的数字之和等于3，它们分别
 * 为{1，1，1}、{1，2}、{2，1}及{3}。
 */
public class NumberOfPermutations implements Answer {

    public static void main(String[] args) {
        new NumberOfPermutations().answerOne();
    }

    /**
     * 参考《最少的硬币数》
     * 同他一样，是个完全背包问题
     */
    @Override
    public void answerOne() {
        int target = 3;
        int[] data = initData();
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int sum = 0;
            for (int datum : data) {
                if (i >= datum) {
                    sum += dp[i - datum];
                }
            }
            if (sum == 0) {
                dp[i] = 0;
            } else {
                dp[i] += sum;
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    /**
     * 输入数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3};
    }
}