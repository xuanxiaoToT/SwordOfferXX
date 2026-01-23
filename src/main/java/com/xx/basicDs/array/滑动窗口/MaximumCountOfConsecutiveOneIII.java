package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/21
 * <p>
 * 最大连续1的个数III
 * LeetCode 1004.  Medium
 * <p>
 * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * <p>
 * 示例 2：
 * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 * 0 <= k <= nums.length
 */
public class MaximumCountOfConsecutiveOneIII implements Answer {
    public static void main(String[] args) {
        new MaximumCountOfConsecutiveOneIII().answer();
    }

    //如果可以翻转最多K个0
    @Override
    public void answer() {
        int[] nums = {0, 0, 1, 1, 1, 0, 0};
        int k = 0;
        System.out.println(computeMaxCountOne(nums, k));
    }

    /**
     * 注意考虑K=0的情况
     */
    public int computeMaxCountOne(int[] nums, int k) {
        int max = 0;
        int oneCount = nums[0] == 1 ? 1 : 0;
        int zeroCount = nums[0] == 0 ? 1 : 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (zeroCount <= k || left == right) {
                if (zeroCount <= k) {
                    max = Math.max(zeroCount + oneCount, max);
                }
                right++;
                if (right < nums.length) {
                    if (nums[right] == 1) {
                        oneCount++;
                    } else {
                        zeroCount++;
                    }
                }
            } else {
                if (nums[left] == 0) {
                    zeroCount--;
                } else {
                    oneCount--;
                }
                left++;
            }
        }
        return max;
    }

    @Override
    public Object initData() {
        return null;
    }
}