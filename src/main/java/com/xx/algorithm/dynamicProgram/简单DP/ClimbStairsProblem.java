package com.xx.algorithm.dynamicProgram.简单DP;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/10
 * <p>
 * 爬楼梯
 * LeetCode 70.  Easy
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * <p>
 * 提示：
 * 1 <= n <= 45
 * <p>
 * Tag: 一维动态规划
 */
public class ClimbStairsProblem implements Answer {

    public static void main(String[] args) {
        new ClimbStairsProblem().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        System.out.println(climbStairs(3));
    }

    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
