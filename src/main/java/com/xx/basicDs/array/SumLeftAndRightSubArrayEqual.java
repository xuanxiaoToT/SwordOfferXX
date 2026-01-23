package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/4
 * <p>
 * 左右两边子数组的和相等
 * <p>
 * 输入一个整数数组，如果一个数字左边的子数组的数字
 * 之和等于右边的子数组的数字之和，那么返回该数字的下标。如果
 * 存在多个这样的数字，则返回最左边一个数字的下标。如果不存在
 * 这样的数字，则返回-1。
 * <p>
 * 例如，在数组[1，7，3，6，2，9]中，
 * 下标为3的数字（值为6）的左边3个数字1、7、3的和与右边两个数字2
 * 和9的和相等，都是11，因此正确的输出值是3。
 */
public class SumLeftAndRightSubArrayEqual implements Answer {

    public static void main(String[] args) {
        new SumLeftAndRightSubArrayEqual().answer();
    }

    /**
     * something
     */
    @Override
    public void answer() {
        int[] nums = initData();
        int[] sumTemp = new int[nums.length];
        sumTemp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumTemp[i] = nums[i] + sumTemp[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            int right = sumTemp[nums.length - 1] - sumTemp[i];
            int left = sumTemp[i] - nums[i];
            if (left == right) {
                System.out.println(nums[i]);
                return;
            }
        }
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 7, 3, 6, 2, 9};
    }
}