package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/13
 * <p>
 * 分割等和子集
 * LeetCode 416
 * <p>
 * 给定一个非空的正整数数组，请判断能否将这些数字分
 * 成和相等的两部分。
 * <p>
 * 例如，如果输入数组为[3，4，1]，将这些数字
 * 分成[3，1]和[4]两部分，它们的和相等，因此输出true；如果输入
 * 数组为[1，2，3，5]，则不能将这些数字分成和相等的两部分，因
 * 此输出false。
 * <p>
 * 换个角度，就是能否选出一组数字，等于SUM/2
 */
public class DividingEqualSumSubsets implements Answer {
    public static void main(String[] args) {
        new DividingEqualSumSubsets().answerOne();
    }

    /**
     * 解:用函数f（i，j）表示能否从前i个物品（物品标号分别为0，1，…，i-1）中选择若
     * 干物品放满容量为j的背包。
     * <p>
     * 如果总共有n个物品，背包的容量为t，那么f（n，t）就是问题的解。
     * <p>
     * 当判断能否从前i个物品中选择若干物品放满容量为j的背包时，
     * 对标号为i-1的物品有两个选择。一个选择是将标号为i-1的物品放入
     * 背包中，如果能从前i-1个物品（物品标号分别为0，1，…，i-2）中
     * 选择若干物品放满容量为j-nums[i-1]的背包（即f（i-1，j-nums[i-
     * 1]）为true），那么f（i，j）就为true。另一个选择是不将标号为i-
     * 1的物品放入背包中，如果从前i-1个物品中选择若干物品放满容量为j
     * 的背包（即f（i-1，j）为true），那么f（i，j）也为true。
     * 当j等于0时，即背包的容量为0，不论有多少个物品，只要什么物
     * 品都不选择，就能使选中的物品的总重量为0，因此f（i，0）都为
     * true。
     * 当i等于0时，即物品的数量为0，肯定无法用0个物品来放满容量
     * 大于0的背包，因此当j大于0时f（0，j）都为false。
     * <p>
     * fix：同样的，dp可以仅用两行替代
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int sum = Arrays.stream(data).sum();
        if (sum % 2 != 0) {
            System.out.println("false");
            return;
        }
        boolean[][] dp = new boolean[data.length + 1][sum / 2 + 1];
        for (int i = 1; i <= data.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j == 0) {
                    dp[i][j] = true;
                } else {
                    // 不选
                    dp[i][j] = dp[i - 1][j];
                    if (!dp[i][j] && j >= data[i - 1]) {
                        // 选，并且前一个要有预留
                        dp[i][j] = dp[i - 1][j - data[i - 1]];
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(dp));

    }

    /**
     * 输入数据
     */
    @Override
    public int[] initData() {
        return new int[]{3, 4, 1};
    }
}