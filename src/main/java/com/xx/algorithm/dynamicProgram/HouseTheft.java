package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/1
 * <p>
 * 房屋偷盗
 * LeetCode 198 打家劫舍
 * <p>
 * 输入一个数组表示某条街道上的一排房屋内财产的数量。
 * 如果这条街道上相邻的两幢房屋被盗就会自动触发报警系统。
 * 请计算小偷在这条街道上最多能偷取到多少财产。
 * <p>
 * 例如，街道上5幢房屋内的财产用数组[2，3，4，5，3]表示，如果小偷到下标为0、2
 * 和4的房屋内盗窃，那么他能偷取到价值为9的财物，这是他在不触
 * 发报警系统的情况下能偷取到的最多的财物，如图14.3所示。被盗
 * 的房屋上方用特殊符号标出。
 */
public class HouseTheft implements Answer {

    public static void main(String[] args) {
        new HouseTheft().answerOne();
    }

    /**
     * something
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int[] dp = new int[data.length];
        dp[0] = data[0];
        for (int i = 1; i < data.length; i++) {
            if (i >= 2) {
                dp[i] = Math.max(dp[i - 2] + data[i], dp[i - 1]);
            } else {
                dp[i] = Math.max(data[i], dp[i - 1]);
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{2, 3, 4, 5, 3};
    }
}