package com.xx.algorithm.dynamicProgram.简单DP;

import com.xx.Answer;

/**
 * 三步问题
 * LeetCode Easy
 * <p>
 * 三步问题。有个小孩正在上楼梯，楼梯有 n 阶台阶，小孩一次可以上 1 阶、2 阶或 3 阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模 1000000007。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：4
 * 说明：有四种走法
 * <p>
 * 示例 2：
 * 输入：n = 5
 * 输出：13
 * <p>
 * 提示:
 * n 范围在[1, 1000000]之间
 * <p>
 * Tag: 动态规划
 *
 * {@link ClimbStairsProblem} 同：爬楼梯问题
 */
public class ThreeStepsProblem implements Answer {
    public static void main(String[] args) {
        new ThreeStepsProblem().answerOne();
    }

    public int waysToStep(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 4;
        }
        int mask = 1000000007;
        long[] dp = new long[n];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for (int i = 3; i < n; i++) {
            // 等于之前的可能数+我现在只上1步，只上2步，只上3步的结果
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % mask;
        }
        return Math.toIntExact(dp[n - 1] % mask);
    }

    @Override
    public void answerOne() {
        int n = 76;
        System.out.println(waysToStep(n));
    }

    @Override
    public Object initData() {
        return null;
    }
}
