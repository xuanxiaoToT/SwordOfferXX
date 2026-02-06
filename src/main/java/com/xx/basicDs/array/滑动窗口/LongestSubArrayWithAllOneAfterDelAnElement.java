package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/22
 * <p>
 * 删掉一个元素以后全为1的最长子数组
 * LeetCode 1493. Medium
 * <p>
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 * 如果不存在这样的子数组，请返回 0 。
 * <p>
 * 提示 1：
 * <p>
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 要么是 0 要么是 1 。
 * <p>
 * Tag:滑动窗口
 */
public class LongestSubArrayWithAllOneAfterDelAnElement implements Answer {
    public static void main(String[] args) {
        new LongestSubArrayWithAllOneAfterDelAnElement().answer();
    }

    @Override
    public void answer() {
        int[] nums = {1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1};
        System.out.println(computeLongestSubArray(nums));
    }

    /**
     * 思路同：
     * {@link MaximumCountOfConsecutiveOneIII}
     * 滑动窗口，固定左，然后枚举右。直到不符合条件后，再动左
     */
    public int computeLongestSubArray(int[] nums) {
        int max = 0;
        int oneCount = nums[0] == 1 ? 1 : 0;
        int zeroCount = nums[0] == 0 ? 1 : 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (left == right || zeroCount <= 1) {
                if (zeroCount == 1) {
                    max = Math.max(oneCount, max);
                }
                if (zeroCount < 1) {
                    max = Math.max(oneCount - 1, max);
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