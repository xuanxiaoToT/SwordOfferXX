package com.xx.algorithm.dynamicProgram.简单DP;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/28
 * <p>
 * 第N个泰波那契数
 * LeetCode 1137. Easy
 * <p>
 * 泰波那契序列 Tn 定义如下：
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * <p>
 * 示例 2：
 * 输入：n = 25
 * 输出：1389537
 * <p>
 * 提示：
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 * <p>
 * Tag:动态规划
 */
public class TribonacciNumberSeries implements Answer {

    public static void main(String[] args) {
        new TribonacciNumberSeries().answer();
    }

    @Override
    public void answer() {
        System.out.println(computeResult(4));
    }

    public int computeResult(int n) {
        int[] num = new int[3];
        num[0] = 0;
        num[1] = 1;
        num[2] = 1;
        if (n < 3) {
            return num[n];
        }
        for (int i = 3; i <= n; i++) {
            int temp = num[0] + num[1] + num[2];
            num[0] = num[1];
            num[1] = num[2];
            num[2] = temp;
        }
        return num[2];
    }

    @Override
    public Object initData() {
        return null;
    }
}