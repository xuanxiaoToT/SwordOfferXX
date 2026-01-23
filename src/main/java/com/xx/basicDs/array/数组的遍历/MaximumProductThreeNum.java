package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/10
 * <p>
 * 三个数的最大乘积
 * LeetCode 628.  Easy
 * <p>
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：24
 * <p>
 * 示例 3：
 * 输入：nums = [-1,-2,-3]
 * 输出：-6
 * <p>
 * 提示：
 * 3 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 * <p>
 * Tag:排序  遍历
 */
public class MaximumProductThreeNum implements Answer {

    public static void main(String[] args) {
        new MaximumProductThreeNum().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[] nums = {-1, -2, -3, -4, -5};
        System.out.println(maximumProduct(nums));
    }

    /**
     * 排序后再处理
     * 如果数组中全是非负数，则排序后最大的三个数相乘即为最大乘积；
     * 如果全是非正数，则最大的三个数相乘同样也为最大乘积。
     * 如果数组中有正数有负数，则最大乘积既可能是三个最大正数的乘积，也可能是两个最小负数（即绝对值最大）与最大正数的乘积。
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    /**
     * 实际上只要求出数组中最大的三个数以及最小的两个数，因此我们可以不用排序，用线性扫描直接得出这五个数。
     */
    public int maximumProduct2(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
