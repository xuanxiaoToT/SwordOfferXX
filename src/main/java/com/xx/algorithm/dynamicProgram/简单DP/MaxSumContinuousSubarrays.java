package com.xx.algorithm.dynamicProgram.简单DP;

import com.xx.Answer;

import java.util.Arrays;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/5
 * <p>
 * 连续子数组的最大和
 * LeetCode 053 最大子数组和
 * <p>
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * <p>
 * 示例 1：
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 */
public class MaxSumContinuousSubarrays implements Answer {

    public static void main(String[] args) {
        new MaxSumContinuousSubarrays().answerOne();
    }

    // dp策略
    @Override
    public void answerOne() {
        List<Integer> list = initData();
        Integer[] maxRecord = new Integer[list.size()];
        maxRecord[0] = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (maxRecord[i - 1] + list.get(i) < list.get(i)) {
                maxRecord[i] = list.get(i);
            } else {
                maxRecord[i] = maxRecord[i - 1] + list.get(i);
            }
        }
        System.out.println(Arrays.toString(maxRecord));
    }

    public void answerTwo() {
        Integer[] nums = (Integer[]) initData().toArray();
        // 因为后面会使用到 nums 的长度
        // 所以先进行判空操作
        // 如果数组 nums 为空，返回 0
        if (nums.length == 0) {
            return;
        }
        // 获取数组的长度
        int n = nums.length;
        // 设置一个数组 dp，长度和数组 nums 长度一致
        // dp[0] 表示以第 0 个元素结尾的最大子数组的和
        // dp[1] 表示以第 1 个元素结尾的最大子数组的和
        // dp[i] 表示以第 i 个元素结尾的最大子数组的和
        int[] dp = new int[n];
        // dp[0] 表示以第 0 个元素结尾的最大子数组的和
        // 初始化 dp[0]
        dp[0] = nums[0];
        // 变量 maxNum 表示数组 dp 中最大的那个值
        // 即 maxNum 表示最大的连续字段和
        int maxNum = dp[0];
        // 从 1 开始遍历数组 nums
        for (int i = 1; i < n; i++) {
            // 在遍历的过程中，去获取以第 i 个元素结尾的最大子数组的和
            // 如果以 nums[i-1]结尾的最大字段和为正数
            // 那么以第 i 个元素结尾的最大子数组的和就是自己本身加上以 nums[i-1]结尾的最大字段和
            if (dp[i - 1] > 0) {
                // dp[i-1] 是正数
                // 所以 dp[i] 的值为 nums[i] 加上 dp[i-1]
                // 因为 正数 + 变量 > 变量
                // dp[i -1] + nums[i] > nums[i]
                dp[i] = dp[i - 1] + nums[i];
                // 否则 dp[i-1] 不是正数，为负数或者 0
            } else {
                // 那么 dp[i] 的值为 nums[i]
                // 因为 负数 + 变量 < 变量
                // dp[i -1] + nums[i] < nums[i]
                dp[i] = nums[i];
            }
            // 在更新 dp[i] 的过程中，更新 maxNum 的值
            // 如果此时 dp[i] 的值大于了 maxNum
            if (maxNum < dp[i]) {
                // 那么 maxNum 更新为 dp[i]
                maxNum = dp[i];
            }
        }

        // 最后返回 maxNum
        System.out.println(maxNum);
    }

    @Override
    public List<Integer> initData() {
        return Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4);
    }
}