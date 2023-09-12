package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/2
 * <p>
 * 最长斐波那契数列
 * <p>
 * 输入一个没有重复数字的单调递增的数组，数组中至少
 * 有3个数字，请问数组中最长的斐波那契数列的长度是多少？例如，
 * 如果输入的数组是[1，2，3，4，5，6，7，8]，由于其中最长的斐
 * 波那契数列是1、2、3、5、8，因此输出是5。
 * <p>
 * 将数组记为A，A[i]表示数组中下标为i的数字。对于每个j（0≤j＜i），A[j]都有可能是
 * 在某个斐波那契数列中A[i]前面的一个数字。如果存在一个k（0≤k＜j）满足A[k]+A[j]=A[i]，
 * 那么这3个数字就组成了一个斐波那契数列。这个以A[i]为结尾、前一个数字是A[j]的斐波那契数列是在以A[j]为结尾、前一个数字是A[k]的序列的基础上增加一个数字A[i]，
 * 因此前者的长度是在后者的长度的基础上加1。
 */
public class LongestFibonacciSeries implements Answer {
    public static void main(String[] args) {
        new LongestFibonacciSeries().answerOne();
    }

    /**
     * dp得用二维数组dp[I][J] = 表示data[J]结尾并且上一个数是data[I]的序列的最长值
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int[][] dp = new int[data.length][data.length];
        Map<Integer, Integer> tempMap = new HashMap<>();
        tempMap.put(data[0], 0);
        tempMap.put(data[1], 1);
        for (int i = 2; i < data.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = data[i] - data[j];
                if (tempMap.containsKey(temp) && temp != data[j]) {
                    int k = tempMap.get(temp);
                    if (k < j) {
                        if (dp[k][j] == 0) {
                            dp[k][j] = 2;
                        }
                        dp[j][i] = dp[k][j] + 1;
                    } else {
                        dp[j][i] = 2;
                    }
                } else {
                    dp[j][i] = 2;
                }
            }
            tempMap.put(data[i], i);
        }
        System.out.println(Arrays.deepToString(dp));
    }


    private void answerTwo() {
        int[] data = initData();
        Map<Integer, Integer> tempMap = new HashMap<>();
        int[][] dp = new int[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            tempMap.put(data[i], i);
        }
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < i; j++) {
                int k = tempMap.getOrDefault(data[i] - data[j], -1);
                if (k >= 0 && k < j) {
                    dp[i][j] = dp[j][k] + 1;
                } else {
                    dp[i][j] = 2;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8};
    }
}
