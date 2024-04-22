package com.xx.algorithm.dynamicProgram.背包01;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/4/22
 * <p>
 * 组合总和Ⅳ
 * LeetCode 377.  Medium
 * <p>
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 * <p>
 * 输入：nums = [9], target = 3
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 * <p>
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 * <p>
 * Tag:只求最终结果，但是不要具体明细的。有限考虑dp  动态规划  完全背包问题
 */
public class SumOfCombinationIV implements Answer {

    public static void main(String[] args) {
        new SumOfCombinationIV().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums = {1, 2, 3};
        int target = 35;
        System.out.println(combinationSum4Dp(nums, target));
    }

    /**
     * 只求最终结果，但是不要具体明细的。有限考虑dp
     * 参考《最少的硬币数》
     */
    public int combinationSum4Dp(int[] nums, int target) {
        int[] dp = new int[target + 1];
        for (int num : nums) {
            if (num <= target) {
                dp[num] = 1;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int num : nums) {
                if (i + num < dp.length) {
                    dp[i + num] = dp[i] + dp[i + num];
                }
            }
        }

        return dp[target];
    }


    public int result = 0;

    public int combinationSum4BT(int[] nums, int target) {
        Arrays.sort(nums);
        computeSum(nums, target, 0);
        return result;
    }

    /**
     * 注意：此题与《允许重复选择元素的组合》不同，112 211算作两个。
     * 顺序不同的序列被视作不同的组合
     */
    private void computeSum(int[] nums, int target, int sum) {
        if (sum == target) {
            result++;
            return;
        }
        if (sum > target) {
            return;
        }
        for (int num : nums) {
            // 选这个
            int newSum = sum + num;
            if (newSum > target) {
                return;
            }
            computeSum(nums, target, newSum);
            // 不选这个
            //computeSum(nums, target, sum);
        }
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
