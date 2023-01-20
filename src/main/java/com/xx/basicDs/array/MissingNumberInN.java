package com.xx.basicDs.array;

import com.xx.Answer;
import com.xx.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/18
 * 0～n-1中缺失的数字
 * 一个长度为 n - 1 的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围 0 ～ n - 1 之内。
 * 在范围 0 ～ n - 1 内的 n 个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * <p>
 * 示例 1:
 * 输入: [0,1,3]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 * <p>
 * 限制：
 * 1 <= 数组长度 <= 10000
 */
public class MissingNumberInN implements Answer {

    public static void main(String[] args) {
        new MissingNumberInN().answerOne();
    }

    /**
     * 采用桶排序的思想
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i] && nums[i] < nums.length) {
                ArrayUtil.swapNum(nums, nums[i], i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                System.out.println("缺的数为：" + i);
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    @Override
    public int[] initData() {
        return new int[]{0, 2, 3, 4, 6, 7, 1, 5, 9};
    }
}