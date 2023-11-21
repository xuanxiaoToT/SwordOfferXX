package com.xx.algorithm.search.二分查找;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/18
 * <p>
 * 搜索插入位置
 * LeetCode 35. Easy
 * <p>
 * 输入一个排序的整数数组nums和一个目标值t，如果数组
 * nums中包含t，则返回t在数组中的下标；如果数组nums中不包含t，
 * 则返回将t按顺序插入数组nums中的下标。假设数组中没有相同的数
 * 字。
 * <p>
 * 例如，输入数组nums为[1，3，6，8]，如果目标值t为3，则输
 * 出1；如果t为5，则返回2。
 * <p>
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * <p>
 * Tag：二分查找  数组
 */
public class FindInsertPosition implements Answer {

    public static void main(String[] args) {
        new FindInsertPosition().answerOne();
    }

    /**
     * 经典的二分题
     */
    @Override
    public void answerOne() {
        int target = 100;
        int[] nums = initData();
        int start = 0;
        int end = nums.length - 1;

        while (start <= end && start < nums.length) {
            int mid = (start + end) / 2;
            if (nums[mid] >= target) {
                if (mid == 0 || nums[mid - 1] < target) {
                    System.out.println(mid);
                    return;
                }
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // e在前，s在后
        System.out.println(end + 1);
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 3, 6, 8, 10};
    }
}