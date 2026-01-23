package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/9
 * <p>
 * 长度最小的子数组
 * LeetCode 209. Medium
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法
 */
public class SubArrayWithTheSmallestLength implements Answer {

    public static void main(String[] args) {
        new SubArrayWithTheSmallestLength().answer();
    }

    @Override
    public void answer() {
        int[] nums = initData();
        int result = theSmallestLengthSubArray(nums, 5);
        System.out.println(result);
    }

    public int theSmallestLengthSubArray(int[] nums, int target) {
        int left = 0;
        int right = 0;
        int resultLength = 0;
        int sumTemp = nums[left];

        while (left <= right && right < nums.length) {
            if (sumTemp >= target) {
                resultLength = resultLength == 0 ? right - left + 1 : Math.min(resultLength, right - left + 1);
                sumTemp = sumTemp - nums[left];
                left++;
            } else {
                right++;
                if (right < nums.length) {
                    sumTemp = sumTemp + nums[right];
                }
            }
        }
        return resultLength;
    }

    @Override
    public int[] initData() {
        // return new int[]{2, 3, 1, 2, 4, 3};
        return new int[]{1, 1, 1, 1, 1, 1};
    }
}