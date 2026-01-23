package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/14
 * <p>
 * 加减的目标值
 * leetcode 494 目标和
 * <p>
 * 给定一个非空的正整数数组和一个目标值S，如果为每个
 * 数字添加“+”或“-”运算符，请计算有多少种方法可以使这些整
 * 数的计算结果为S。
 * <p>
 * 例如，如果输入数组[2，2，2]并且S等于2，有3
 * 种添加“+”或“-”的方法使结果为2，它们分别是2+2-2=2、2-
 * 2+2=2及-2+2+2=2
 */
public class TargetValueOfAdditionAndSubtraction implements Answer {

    private int result = 0;

    public static void main(String[] args) {
        new TargetValueOfAdditionAndSubtraction().answerTwo();
    }

    /**
     * 解:先做数学运算!
     * 为输入的数组中的有些数字添加“+”，有些数字添加“-”。如果所有添加
     * “+”的数字之和为p，所有添加“-”的数字之和为q，按照题目的要
     * 求，p-q=S。如果累加数字中的所有数字，就能得到整个数组的数字之
     * 和，记为sum，即p+q=sum。将这两个等式的左右两边分别相加，就可
     * 以得到2p=S+sum，即p=（S+sum）/2。
     * 即能否选择N个值，使得其和为P
     * 经数学运算换算后，即可转换为0-1背包问题
     * 则其解法同《DividingEqualSumSubsets》
     */
    @Override
    public void answer() {
        int s = 2;
        int[] data = initData();
        int sum = Arrays.stream(data).sum();
        if ((s + sum) % 2 != 0) {
            System.out.println("00000");
            return;
        }
        int p = (s + sum) / 2;
        int[][] dp = new int[data.length + 1][p + 1];
        for (int i = 0; i <= data.length; i++) {
            for (int j = 0; j <= p; j++) {
                if (j == 0 || i == 0) {
                    if (j == 0) {
                        dp[i][j] = 1;
                    }
                    if (i == 0) {
                        dp[i][j] = 0;
                    }
                    if (j == 0 && i == 0) {
                        dp[i][j] = 1;
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= data[i - 1]) {
                        dp[i][j] += dp[i - 1][j - data[i - 1]];
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * 使用回溯法来做
     */
    public void answerTwo() {
        int target = 3;
        int[] nums = initData();
        answerTwoDiGui(nums, 0, 0, target);
        System.out.println(this.result);
    }

    private void answerTwoDiGui(int[] nums, int index, int temp, int target) {
        if (temp == target && index >= nums.length) {
            this.result++;
        }
        if (index >= nums.length) {
            return;
        }
        answerTwoDiGui(nums, index + 1, temp + nums[index], target);
        answerTwoDiGui(nums, index + 1, temp - nums[index], target);
    }

    /**
     * 输入数据
     */
    @Override
    public int[] initData() {
        // return new int[]{2, 2, 2};
        return new int[]{1, 1, 1, 1, 1};
    }
}
