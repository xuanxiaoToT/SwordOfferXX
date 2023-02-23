package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/1/9
 * <p>
 * 最大乘积子数组
 * * LeetCode  152
 * <p>
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 子数组 是数组的连续子序列。
 * <p>
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 提示:
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 */
public class MaximumProductSubarray implements Answer {

    public static void main(String[] args) {
        new MaximumProductSubarray().answerOne();
    }

    /**
     * 解1：直接采用DP策略
     * 关键：考虑正负号的问题~！！！
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。
     * 因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
     * <p>
     * 例如[2, 3, -2, 1, -4]
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int[] dpMax = new int[data.length];
        int[] dpMin = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            if (data[i] > 0) {
                if (i > 0) {
                    dpMax[i] = Math.max(dpMax[i - 1] * data[i], data[i]);
                    dpMin[i] = Math.min(dpMin[i - 1] * data[i], data[i]);
                } else {
                    dpMax[i] = data[i];
                    dpMin[i] = data[i];
                }
            } else {
                if (i > 0) {
                    dpMax[i] = Math.max(dpMin[i - 1] * data[i], data[i]);
                    dpMin[i] = Math.min(dpMax[i - 1] * data[i], data[i]);
                } else {
                    dpMax[i] = data[i];
                    dpMin[i] = data[i];
                }
            }
        }
        System.out.println(Arrays.toString(dpMax));
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{2, 3, -2, 1, -4};
    }

}
