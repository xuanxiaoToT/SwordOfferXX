package com.xx.algorithm.dynamicProgram.简单DP;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/31
 * <p>
 * 环形子数组的最大和
 * LeetCode 918.  Medium
 * <p>
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * <p>
 * 示例 2：
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * <p>
 * Tag: 动态规划  循环数组  分治
 */
public class TheMaximumSumOfCircularSubarrays implements Answer {

    public static void main(String[] args) {
        new TheMaximumSumOfCircularSubarrays().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        //int[] nums = new int[]{1, -2, 3, -2};
        //int[] nums = new int[]{5, -3, 5};
        //int[] nums = new int[]{9, -4, -7, 9};
        int[] nums = new int[]{2, -2, 2, 7, 8, 0};
        System.out.println(maxSubarraySumCircular(nums));
    }

    /**
     * leftDp[i]:表示0-i，目前左侧数组的最大值。此时右侧往左侧遍历，即可得出包含循环点的最大值
     */
    public int maxSubarraySumCircular(int[] nums) {
        //连续情况下的最大值
        int maxDp = Integer.MIN_VALUE;
        //不包含环形的最大值。参考<子数组的最大和>
        int[] dp = new int[nums.length];
        int[] leftDp = new int[nums.length];
        int sumLeft = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1) {
                sumLeft = sumLeft + nums[i];
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
                leftDp[i] = Math.max(leftDp[i - 1], sumLeft);
            } else {
                dp[i] = nums[i];
                leftDp[i] = sumLeft;
            }
            maxDp = Math.max(maxDp, dp[i]);
        }
        int rightSum = 0;
        int maxCir = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i > 0; i--) {
            rightSum = rightSum + nums[i];
            maxCir = Math.max(maxCir, rightSum + leftDp[i - 1]);
        }
        return Math.max(maxDp, maxCir);
    }

    /**
     * 超时！！！
     */
    public int maxSubarraySumCircularTimeout(int[] nums) {
        //连续情况下的最大值
        int maxDp = Integer.MIN_VALUE;
        //不包含环形的最大值。参考<子数组的最大和>
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            } else {
                dp[i] = nums[i];
            }
            maxDp = Math.max(maxDp, dp[i]);
        }

        // 包含环形节点的情况，暴力遍历。如何优化？？
        int left = 0;
        int right = nums.length - 1;
        int sum = 0;
        int maxCir = Integer.MIN_VALUE;
        for (int i = left; i < right; i++) {
            sum = sum + nums[i];
            int rightTemp = sum;
            for (int j = right; j > i; j--) {
                rightTemp = rightTemp + nums[j];
                maxCir = Math.max(maxCir, rightTemp);
            }
        }
        return Math.max(maxDp, maxCir);
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
