package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

/**
 * 给 N x 3 网格图涂色的方案数
 * <p>
 * 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，
 * 且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
 * 给你网格图的行数 n 。
 * 请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
 * <p>
 * 示例 1：
 * 输入：n = 1
 * 输出：12
 * 解释：总共有 12 种可行的方法：
 * <p>
 * 示例 2：
 * 输入：n = 2
 * 输出：54
 * <p>
 * 示例 3：
 * 输入：n = 3
 * 输出：246
 * <p>
 * 示例 4：
 * 输入：n = 7
 * 输出：106494
 * <p>
 * 示例 5：
 * 输入：n = 5000
 * 输出：30228214
 * <p>
 * 提示：
 * n == grid.length
 * grid[i].length == 3
 * 1 <= n <= 5000
 * <p>
 * Tag:动态规划  数学归纳  图形规律
 * Tag：将图形分为两种，一种是左右两端相等的，一种是左右两端不等的。
 */
public class NumberOfWaysPaintGrid implements Answer {
    public static void main(String[] args) {
        new NumberOfWaysPaintGrid().answer();
    }

    @Override
    public void answer() {
        // int n = 1;
        int n = 3;
        System.out.println(numOfWays(n));
    }

    /**
     * a(n)：第n行是 T 型（两端列颜色不同，如 “红 - 黄 - 绿”） 的方案数；
     * b(n)：第n行是 S 型（两端列颜色相同，如 “红 - 黄 - 红”） 的方案数;
     * <p>
     * a(1)=6
     * b(1)=6
     * <p>
     * a(n)=2×a(n−1)+2×b(n−1)
     * b(n)=2×a(n−1)+3×b(n−1)
     * 总方案数(f(n) = a(n) + b(n)。
     */
    public int numOfWays(int n) {
        int mask = 1000000007;
        long[][] dp = new long[n][2];
        dp[0][0] = 6;
        dp[0][1] = 6;
        for (int i = 1; i < n; i++) {
            dp[i][0] = (2 * dp[i - 1][0] + 2 * dp[i - 1][1]) % mask;
            dp[i][1] = (2 * dp[i - 1][0] + 3 * dp[i - 1][1]) % mask;
        }
        return Math.toIntExact((dp[n - 1][0] + dp[n - 1][1]) % mask);
    }

    @Override
    public Object initData() {
        return null;
    }
}
