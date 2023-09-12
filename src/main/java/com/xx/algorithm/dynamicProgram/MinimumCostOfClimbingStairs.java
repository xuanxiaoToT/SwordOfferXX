package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/11/30
 * <p>
 * 爬楼梯的最少成本
 * <p>
 * 一个数组cost的所有数字都是正数，它的第i个数字表示
 * 在一个楼梯的第i级台阶往上爬的成本，在支付了成本cost[i]之后
 * 可以从第i级台阶往上爬1级或2级。假设台阶至少有2级，既可以从
 * 第0级台阶出发，也可以从第1级台阶出发，请计算爬上该楼梯的最
 * 少成本。
 * 例如，输入数组[1，100，1，1，100，1]，则爬上该楼梯
 * 的最少成本是4，分别经过下标为0、2、3、5的这4级台阶，如图
 * 14.1所示。
 */
public class MinimumCostOfClimbingStairs implements Answer {

    public static void main(String[] args) {
        new MinimumCostOfClimbingStairs().answerOne();
    }

    /**
     * something
     */
    @Override
    public void answerOne() {
        int[] cost = initData();
        // 其实只需要用到i-1 和 i-2的值，可以优化空间复杂度
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        for (int i = 1; i < cost.length; i++) {
            if (i > 1) {
                dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i]);
            } else {
                dp[i] = dp[i - 1] + cost[i];
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 100, 1, 1, 100, 1};
    }
}