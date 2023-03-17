package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/21
 * <p>
 * 两数之和
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * <p>
 * 示例 1:
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * <p>
 * 示例 2:
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 */
public class TwoNumbersOfS implements Answer {

    public static void main(String[] args) {
        new TwoNumbersOfS().answerTwo();
    }

    /**
     * 最简单的 无脑遍历
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        int target = 40;
        for (int num : nums) {
            int temp = target - num;
            if (temp > 0) {
                for (int j = nums.length - 1; nums[j] >= temp; j--) {
                    if (nums[j] == temp) {
                        System.out.println(num + " " + nums[j]);
                        return;
                    }
                }
            } else {
                break;
            }
        }
    }


    /**
     * 注意题目交代了是"递增"序列
     * 故采用双指针法
     */
    private void answerTwo() {
        int[] nums = initData();
        int target = 40;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                System.out.println(nums[left] + " " + nums[right]);
                return;
            }
            if (sum > target) {
                right--;
            }
            if (sum < target) {
                left++;
            }
        }
    }

    /**
     * 采用额外的map来存储，本质是判断是否存在 Sum-num[i] 这个值。
     */
    private void answerThree() {

    }

    @Override
    public int[] initData() {
        return new int[]{10, 26, 30, 31, 47, 60};
    }
}