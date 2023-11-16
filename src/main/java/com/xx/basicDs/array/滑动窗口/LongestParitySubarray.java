package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/16
 * <p>
 * 最长奇偶子数组
 * LeetCode 2760. Easy
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 threshold 。
 * 请你从 nums 的子数组中找出以下标 l 开头、下标 r 结尾 (0 <= l <= r < nums.length) 且满足以下条件的 最长子数组 ：
 * - nums[l] % 2 == 0
 * - 对于范围 [l, r - 1] 内的所有下标 i ，nums[i] % 2 != nums[i + 1] % 2
 * - 对于范围 [l, r] 内的所有下标 i ，nums[i] <= threshold
 * 以整数形式返回满足题目要求的最长子数组的长度。
 * <p>
 * 注意：子数组 是数组中的一个连续非空元素序列。
 * <p>
 * 示例 1：
 * 输入：nums = [3,2,5,4], threshold = 5
 * 输出：3
 * 解释：在这个示例中，我们选择从 l = 1 开始、到 r = 3 结束的子数组 => [2,5,4] ，满足上述条件。
 * 因此，答案就是这个子数组的长度 3 。可以证明 3 是满足题目要求的最大长度。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2], threshold = 2
 * 输出：1
 * 解释：
 * 在这个示例中，我们选择从 l = 1 开始、到 r = 1 结束的子数组 => [2] 。
 * 该子数组满足上述全部条件。可以证明 1 是满足题目要求的最大长度。
 * <p>
 * 示例 3：
 * 输入：nums = [2,3,4,5], threshold = 4
 * 输出：3
 * 解释：
 * 在这个示例中，我们选择从 l = 0 开始、到 r = 2 结束的子数组 => [2,3,4] 。
 * 该子数组满足上述全部条件。
 * 因此，答案就是这个子数组的长度 3 。可以证明 3 是满足题目要求的最大长度。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 1 <= threshold <= 100
 *
 * Tag: 滑动窗口  数组
 */
public class LongestParitySubarray implements Answer {
    public static void main(String[] args) {
        new LongestParitySubarray().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums = initData();
        int threshold = 4;
        System.out.println(longestAlternatingSubarray(nums, threshold));
    }

    /**
     * 滑动窗口
     * 第一版写法。
     * 复杂了
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int left = 0;
        int right = 0;
        int result = 0;
        while (right < nums.length && left <= right) {
            if (nums[left] % 2 != 0 || nums[left] > threshold) {
                left++;
                right = left;
            } else {
                result = result == 0 ? 1 : result;
                if (left == right) {
                    right++;
                } else {
                    if (nums[right] % 2 != nums[right - 1] % 2 && nums[right] <= threshold) {
                        result = Math.max(result, right - left + 1);
                        right++;
                    } else {
                        left = right;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 参考其他人的写法
     */
    public int longestAlternatingSubarray2(int[] nums, int threshold) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 != 0) continue;
            if (nums[i] > threshold) continue;
            int right = i + 1;
            while (right < n && nums[right] <= threshold && (nums[right] % 2 != nums[right - 1] % 2)) {
                right++;
            }
            ans = Math.max(ans, right - i);
            i = right - 1;
        }
        return ans;
    }

    @Override
    public int[] initData() {
        return new int[]{2, 3, 4, 5};
    }
}