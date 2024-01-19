package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/19
 * <p>
 * 子数组最大平均数I
 * LeetCode 643. Medium
 * <p>
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 任何误差小于 10^-5 的答案都将被视为正确答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * 示例 2：
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * Tag：滑动窗口
 */
public class MaximumAverageOfSubArrays implements Answer {

    public static void main(String[] args) {
        new MaximumAverageOfSubArrays().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums = {-1};
        int k = 1;
        System.out.println(findMaxAverage(nums, k));
    }

    /**
     * 滑动窗口
     * 注意:不要每次都计算均值，先求最大值，最后返回时再计算！
     */
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;
        int right = k - 1;
        int sum = 0;
        int max = 0;
        for (int i = 0; i < k; i++) {
            sum = sum + nums[i];
        }
        max = sum;
        while (right < nums.length) {
            right++;
            if (right < nums.length) {
                sum = sum + nums[right];
                sum = sum - nums[left];
                left++;
                max = Math.max(sum, max);
            }
        }
        return max * 1.0 / k;
    }

    /**
     * 其它解法
     */
    public double findMaxAverage2(int[] nums, int k) {
        int sumNums = 0;
        if (nums.length < 1 || k > nums.length || k < 0) {
            return 0;
        }
        for (int i = 0; i < k; i++) {
            sumNums += nums[i];
        }
        int sum = sumNums;
        for (int right = k; right < nums.length; right++) {
            sumNums = sumNums + nums[right] - nums[right - k];
            sum = Math.max(sum, sumNums);
        }
        return (double) sum / k;
    }

    @Override
    public Object initData() {
        return null;
    }
}