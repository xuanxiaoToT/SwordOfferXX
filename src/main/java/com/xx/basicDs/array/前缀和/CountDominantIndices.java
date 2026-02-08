package com.xx.basicDs.array.前缀和;

import com.xx.Answer;

/**
 * 统计主导元素下标数
 * LeetCode 100985. Easy
 * <p>
 * 给你一个长度为 n 的整数数组 nums。
 * <p>
 * 当下标 i 满足以下条件时，该下标处的元素被称为 主导元素：nums[i] > average(nums[i + 1], nums[i + 2], ..., nums[n - 1])
 * 你的任务是统计数组中 主导元素 的下标数。
 * 平均值 是指一组数的总和除以该组数的个数得到的值。
 * 注意：数组的 最右边元素 不算作 主导元素 。
 * <p>
 * 示例 1：
 * 输入： nums = [5,4,3]
 * 输出： 2
 * 解释：
 * 在下标 i = 0 处，值 5 是主导元素，因为 5 > average(4, 3) = 3.5。
 * 在下标 i = 1 处，值 4 是主导元素，相对于子数组 [3]。
 * 下标 i = 2 不是主导元素，因为它右侧没有元素。因此答案是 2。
 * <p>
 * 示例 2：
 * 输入： nums = [4,1,2]
 * 输出： 1
 * 解释：
 * 在下标 i = 0 处，值 4 是主导元素，相对于子数组 [1, 2]。
 * 在下标 i = 1 处，值 1 不是主导元素。
 * 下标 i = 2 不是主导元素，因为它右侧没有元素。因此答案是 1。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * <p>
 * Tag: 前缀和
 */
public class CountDominantIndices implements Answer {
    public static void main(String[] args) {
        new CountDominantIndices().answer();
    }

    @Override
    public void answer() {
        // int[] nums = {5, 4, 3};
        int[] nums = {4, 1, 2};
        System.out.println(dominantIndices(nums));
    }

    public int dominantIndices(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        Double[] right = new Double[nums.length];
        int count = 0;
        int sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            count++;
            sum += nums[i];
            right[i] = (sum * 1.0) / count;
        }
        int result = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > right[i + 1]) {
                result++;
            }
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
