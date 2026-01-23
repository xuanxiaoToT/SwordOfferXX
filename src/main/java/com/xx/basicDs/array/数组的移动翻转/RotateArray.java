package com.xx.basicDs.array.数组的移动翻转;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/8
 * <p>
 * 轮转数组
 * LeetCode 189.
 * <p>
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */
public class RotateArray implements Answer {

    public static void main(String[] args) {
        new RotateArray().answerTwo();
    }

    /**
     * 解1：使用额外数组。
     * 空间复杂度 O(K)
     */
    @Override
    public void answer() {
        int k = 3;
        int[] nums = initData();
        // 做题时注意边界条件
        if (k >= nums.length) {
            k = k % nums.length;
        }
        int[] temp = new int[k];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = nums[nums.length - k + i];
        }
        for (int i = nums.length - 1; i >= k; i--) {
            nums[i] = nums[i - k];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 空间复杂度为 O(1) 的 原地 算法
     * 用一个temp来放，然后挨个挪动即可。
     * 时间复杂度O(kN)
     */
    public void answerTwo() {
        int k = 3;
        int[] nums = initData();
        // 做题时注意边界条件
        if (k >= nums.length) {
            k = k % nums.length;
        }
        for (int i = 0; i < k; i++) {
            int temp = nums[nums.length - 1];
            for (int j = nums.length - 1; j >= 1; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 4, 5, 6, 7};
    }
}
