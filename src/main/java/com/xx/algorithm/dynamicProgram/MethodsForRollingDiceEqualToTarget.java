package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/24
 * <p>
 * 掷骰子等于目标和的方法数
 * LeetCode 1155  Medium
 * <p>
 * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
 * 给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
 * 答案可能很大，你需要对 10^9 + 7 取模 。
 * <p>
 * 示例 1：
 * 输入：n = 1, k = 6, target = 3
 * 输出：1
 * 解释：你扔一个有 6 个面的骰子。
 * 得到 3 的和只有一种方法。
 * <p>
 * 示例 2：
 * 输入：n = 2, k = 6, target = 7
 * 输出：6
 * 解释：你扔两个骰子，每个骰子有 6 个面。
 * 得到 7 的和有 6 种方法：1+6 2+5 3+4 4+3 5+2 6+1。
 * <p>
 * 示例 3：
 * 输入：n = 30, k = 30, target = 500
 * 输出：222616187
 * 解释：返回的结果必须是对 109 + 7 取模。
 * <p>
 * 提示：
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 */
public class MethodsForRollingDiceEqualToTarget implements Answer {

    public static void main(String[] args) {
        new MethodsForRollingDiceEqualToTarget().answer();
    }

    /**
     * 分组背包，有n个组，每个组只能选一次，每个组有多个物品(k)，问填满容量为target的背包有几种方式。解题思路是：
     * 1.01背包的解法里套一个内循环，用于循环组内的物品
     * 2.同时有个细节，传统的01背包可以装到背包里，也可以不装到背包里，而这套题不行，必须要装，所以上一次的状态不能累加到当前的状态里，
     * 内循环之前要清零上一次的状态。换句话说，如果不清零，就意味着当前组/筛子可以跳过。
     * <p>
     * fixme：可以优化，减少循环次数
     */
    @Override
    public void answer() {
        int[] data = initData();
        int n = data[0];
        int k = data[1];
        int target = data[2];
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        //n个骰子
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                //k个面
                for (int l = 1; l <= k; l++) {
                    if (j >= l && i >= 1) {
                        dp[i][j] = (int) ((dp[i - 1][j - l] + dp[i][j]) % (Math.pow(10, 9) + 7));
                    }
                }
            }
        }
        System.out.println(dp[n][target]);
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum/solutions/2490436/zhi-tou-zi-deng-yu-mu-biao-he-de-fang-fa-eewv/?envType=daily-question&envId=2023-10-24
     */
    public int answerTwo(int n, int k, int target) {
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = target; j >= 0; --j) {
                f[j] = 0;
                for (int x = 1; x <= k; ++x) {
                    if (j - x >= 0) {
                        f[j] = (int) ((f[j] + f[j - x]) % (Math.pow(10, 9) + 7));
                    }
                }
            }
        }
        return f[target];
    }


    @Override
    public int[] initData() {
        // return new int[]{1, 6, 3};
        return new int[]{2, 6, 7};
    }
}