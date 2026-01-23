package com.xx.algorithm.search;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/19
 * 山峰数组的顶部
 * <p>
 * 在一个长度大于或等于3的数组中，任意相邻的两个数字
 * 都不相等。该数组的前若干数字是递增的，之后的数字是递减的，
 * 因此它的值看起来像一座山峰。请找出山峰顶部，即数组中最大值
 * 的位置。
 * <p>
 * 例如，在数组[1，3，5，4，2]中，最大值是5，输出它在
 * 数组中的下标2。
 * <p>
 * 前半边递增，后半边抵减。
 */
public class TopOfTheMountainArray implements Answer {

    public static void main(String[] args) {
        int temp = 3 / 2;
        System.out.println(temp);
        new TopOfTheMountainArray().answer();
    }

    /**
     * 与《搜索旋转排序数组》题目一样，但是只需要找最大的那个即可
     */
    @Override
    public void answer() {
        int[] nums = initData();
        int left = 0;
        int right = nums.length - 1;
        while (left <= right && right >= 0) {
            int mid = (left + right) / 2;
            if (mid == 0 || mid == nums.length - 1) {
                System.out.println(mid + " " + nums[mid]);
                return;
            } else {
                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                    System.out.println(mid + " " + nums[mid]);
                    return;
                } else {
                    if (nums[mid + 1] > nums[mid]) {
                        left = mid + 1;
                    }
                    if (nums[mid] < nums[mid - 1]) {
                        right = mid - 1;
                    }
                }
            }
        }
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        // int[] temp = new int[]{1, 3, 5, 10, 4, 2};
        // int[] temp = new int[]{1, 3, 5, 10, 12, 14};
        int[] temp = new int[]{14, 13, 12, 5, 4, 3, 1};
        return temp;
    }
}