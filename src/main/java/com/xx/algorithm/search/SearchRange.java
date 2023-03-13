package com.xx.algorithm.search;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/5
 * <p>
 * 在排序数组中查找元素的第一个和最后一个位置
 * LeetCode 34.
 * <p>
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。
 * 请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class SearchRange implements Answer {

    public static void main(String[] args) {
        new SearchRange().answerOne();
    }

    /**
     * 解1：用两次二分法
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int target = 8;
        int[] result = solution(data, target);
        System.out.println(Arrays.toString(result));
    }


    public int[] solution(int[] nums, int target) {
        //两个二分查找.
        if (nums.length < 1 || nums == null)
            return new int[]{-1, -1};
        if (nums.length == 1 && nums[0] == target)
            return new int[]{0, 0};
        int left = searchStart(nums, 0, nums.length - 1, target);
        int right = searchEnd(nums, 0, nums.length - 1, target);
        return new int[]{left, right};
    }

    public int searchStart(int[] nums, int left, int right, int target) {

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                if (mid - 1 < 0) {
                    return mid;
                }
                if (mid - 1 >= 0 && nums[mid - 1] == target) {
                    right = mid - 1;
                }
                if (mid - 1 >= 0 && nums[mid - 1] != target) {
                    return mid;
                }
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int searchEnd(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                if (mid >= nums.length - 1) {
                    return mid;
                }
                if (mid + 1 < nums.length && nums[mid + 1] == target) {
                    left = mid + 1;
                }
                if (mid + 1 < nums.length && nums[mid + 1] != target) {
                    return mid;
                }
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{5, 7, 7, 8, 8, 10};
    }
}
