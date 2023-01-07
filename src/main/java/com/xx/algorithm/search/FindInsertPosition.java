package com.xx.algorithm.search;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/10/18
 * 查找插入位置
 * 输入一个排序的整数数组nums和一个目标值t，如果数组
 * nums中包含t，则返回t在数组中的下标；如果数组nums中不包含t，
 * 则返回将t按顺序插入数组nums中的下标。假设数组中没有相同的数
 * 字。例如，输入数组nums为[1，3，6，8]，如果目标值t为3，则输
 * 出1；如果t为5，则返回2。
 */
public class FindInsertPosition implements Answer {

    public static void main(String[] args) {
        new FindInsertPosition().answerOne();
    }

    /**
     * something
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