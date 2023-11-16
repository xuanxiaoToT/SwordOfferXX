package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

import java.util.Arrays;

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
 * <p>
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
        System.out.println(longestAlternatingSubarray3(nums, threshold));
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
     * 分组循环
     * 适用场景：按照题目要求，数组会被分割成若干组，且每一组的判断/处理逻辑是一样的。
     * <p>
     * 核心思想：
     * 外层循环负责遍历组之前的准备工作（记录开始位置），和遍历组之后的统计工作（更新答案最大值）。
     * 内层循环负责遍历组，找出这一组在哪结束。
     * 这个写法的好处是，各个逻辑块分工明确，也不需要特判最后一组（易错点）。以我的经验，这个写法是所有写法中最不容易出 bug 的，推荐大家记住。
     * 时间复杂度乍一看是 O(n2)，但注意变量 i 只会增加，不会重置也不会减少。
     * 所以二重循环总共循环 O(n)次，所以时间复杂度是 O(n)。
     */
    public int longestAlternatingSubarray2(int[] nums, int threshold) {
        int n = nums.length;
        int ans = 0, i = 0;
        while (i < n) {
            if (nums[i] > threshold || nums[i] % 2 != 0) {
                i++; // 直接跳过
                continue;
            }
            int start = i; // 记录这一组的开始位置
            i++; // 开始位置已经满足要求，从下一个位置开始判断
            while (i < n && nums[i] <= threshold && nums[i] % 2 != nums[i - 1] % 2) {
                i++;
            }
            // 从 start 到 i-1 是满足题目要求的子数组
            ans = Math.max(ans, i - start);
        }
        return ans;
    }

    /**
     * 采用动态规划来做
     */
    public int longestAlternatingSubarray3(int[] nums, int threshold) {
        int n = nums.length;
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= threshold && nums[i] % 2 == 0) {
                dp[i] = Math.max(dp[i], 1);
            }
            if (i >= 1 && dp[i - 1] >= 1 && nums[i] <= threshold && (nums[i] % 2 != nums[i - 1] % 2)) {
                dp[i] = Math.max(dp[i], dp[i - 1] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return max;
    }

    @Override
    public int[] initData() {
        return new int[]{2, 3, 4, 5};
    }
}