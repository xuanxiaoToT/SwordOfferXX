package com.xx.algorithm.search.二分查找;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/22
 * <p>
 * 寻找峰值
 * LeetCode 162 Medium
 * <p>
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * <p>
 * Tag: 二分查找  数组  爬坡法
 */
public class FindPeakValue implements Answer {

    public static void main(String[] args) {
        new FindPeakValue().answer();
    }

    /**
     * 解1：必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     * 表明每次只能遍历一半。
     * 又因为 你可以假设 nums[-1] = nums[n] = -∞ 。
     * 所以立即联想到二分法。
     * 二分方向为数值增加的方向
     */
    @Override
    public void answer() {
        int[] nums = initData();
        System.out.println(findPeakElement(nums));
    }

    /**
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * 注意条件！！
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid > 0 && mid + 1 < nums.length) {
                //凸型
                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                    return mid;
                }
                //峰值在左侧
                if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) {
                    right = mid - 1;
                }
                //峰值在右侧
                if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
                    left = mid + 1;
                }
                // 凹形
                if (nums[mid - 1] > nums[mid] && nums[mid] < nums[mid + 1]) {
                    left = mid + 1;
                }
            }
            if (mid == 0) {
                if (nums[mid] > nums[mid + 1]) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
            if (mid == nums.length - 1) {
                if (nums[mid] > nums[mid - 1]) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return 0;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 3, 2, 1};
    }
}
