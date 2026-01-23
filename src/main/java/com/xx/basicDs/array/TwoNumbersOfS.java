package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/21
 * <p>
 * 两数之和
 * LeetCode 167. 两数之和II - 输入有序数组  Medium
 * <p>
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。
 * 如果有多对数字的和等于s，则输出任意一对即可。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * 你所设计的解决方案必须只使用常量级的额外空间。
 * <p>
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 * <p>
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
 * <p>
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 */
public class TwoNumbersOfS implements Answer {

    public static void main(String[] args) {
        new TwoNumbersOfS().answerTwo();
    }

    /**
     * 最简单的 无脑遍历
     */
    @Override
    public void answer() {
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