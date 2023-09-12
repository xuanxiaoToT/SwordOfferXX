package com.xx.algorithm.dynamicProgram;


import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/1
 * <p>
 * 环形房屋盗窃
 * <p>
 * 一条环形街道上有若干房屋。输入一个数组表示该条街道上的房屋内财产的数量。
 * 如果这条街道上相邻的两幢房屋被盗就会自动触发报警系统。
 * 请计算小偷在这条街道上最多能偷取的财产的数量。
 * <p>
 * 例如，街道上5家的财产用数组[2，3，4，5，3]表示，如
 * 果小偷到下标为1和3的房屋内盗窃，那么他能偷取到价值为8的财
 * 物，这是他在不触发报警系统的情况下能偷取到的最多的财物，如
 * 图14.4所示。被盗的房屋上方用特殊符号标出。
 */
public class RingHouseTheft implements Answer {

    public static void main(String[] args) {
        new RingHouseTheft().answerOne();
    }

    /**
     * 用一个flag来标记当前房子是否是最后一个。来判断环
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int[] dp = new int[data.length];
        boolean[] flag = new boolean[data.length];
        flag[0] = true;
        dp[0] = data[0];
        for (int i = 1; i < data.length - 1; i++) {
            if (i >= 2) {
                if (dp[i - 2] + data[i] > dp[i - 1]) {
                    dp[i] = dp[i - 2] + data[i];
                    flag[i] = flag[i - 2];
                } else {
                    dp[i] = dp[i - 1];
                    flag[i] = flag[i - 1];
                }
            } else {
                dp[i] = Math.max(data[i], dp[i - 1]);
                if (data[i] > dp[i - 1]) {
                    dp[i] = data[i];
                    flag[i] = false;
                } else {
                    dp[i] = dp[i - 1];
                    flag[i] = true;
                }
            }
        }

        // 最后一个
        int last = data.length - 1;
        // flag[last - 2]为true 表明偷过了第一家，此时不能偷最后一家。
        if (dp[last - 2] + data[last] > dp[last - 1] && !flag[last - 2]) {
            dp[last] = dp[last - 2] + data[last];
            flag[last] = flag[last - 2];
        } else {
            dp[last] = dp[last - 1];
            flag[last] = flag[last - 1];
        }

        System.out.println(Arrays.toString(dp));
    }

    private void answerTwo() {
        //可以将问题分解为两个子问题
        // 一个问题是求小偷从标号为0开始到标号为n-2结束的房屋内能偷得的最多财物数量。
        // 另一个问题是求小偷从标号为1开始到标号为n-1结束的房屋内能偷得的最多财物数量。
        //  解法略，需要执行两边
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{2, 3, 4, 5, 3};
    }
}