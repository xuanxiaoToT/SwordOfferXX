package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/6
 * <p>
 * 最大连续 1 的个数
 * LeetCode 485. Easy
 * <p>
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * <p>
 * 示例 2:
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1.
 * <p>
 * Tag: 数组的遍历
 */
public class MaximumNumberOfConsecutiveOne implements Answer {

    public static void main(String[] args) {
        new MaximumNumberOfConsecutiveOne().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[] nums = {1, 1, 0, 1, 1, 1};
        System.out.println(findMaxConsecutiveOnes(nums));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
