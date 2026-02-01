package com.xx.algorithm.sort;

import com.xx.Answer;

/**
 * 将数组分成最小总代价的子数组I
 * LeetCode 3010.  Easy
 * <p>
 * 给你一个长度为 n 的整数数组 nums 。
 * 一个数组的 代价 是它的 第一个 元素。比方说，[1,2,3] 的代价是 1 ，[3,4,1] 的代价是 3 。
 * 你需要将 nums 分成 3 个 连续且没有交集 的子数组。
 * 请你返回这些子数组的 最小 代价 总和 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,12]
 * 输出：6
 * 解释：最佳分割成 3 个子数组的方案是：[1] ，[2] 和 [3,12] ，总代价为 1 + 2 + 3 = 6 。
 * 其他得到 3 个子数组的方案是：
 * - [1] ，[2,3] 和 [12] ，总代价是 1 + 2 + 12 = 15 。
 * - [1,2] ，[3] 和 [12] ，总代价是 1 + 3 + 12 = 16 。
 * <p>
 * 示例 2：
 * 输入：nums = [5,4,3]
 * 输出：12
 * 解释：最佳分割成 3 个子数组的方案是：[5] ，[4] 和 [3] ，总代价为 5 + 4 + 3 = 12 。
 * 12 是所有分割方案里的最小总代价。
 * <p>
 * 示例 3：
 * 输入：nums = [10,3,1,1]
 * 输出：12
 * 解释：最佳分割成 3 个子数组的方案是：[10,3] ，[1] 和 [1] ，总代价为 10 + 1 + 1 = 12 。
 * 12 是所有分割方案里的最小总代价。
 * <p>
 * 提示：
 * 3 <= n <= 50
 * 1 <= nums[i] <= 50
 * <p>
 * Tag:模板代码 求双最小值
 */
public class DivideArrayIntoSubarraysWithMinimumCostI implements Answer {
    public static void main(String[] args) {
        new DivideArrayIntoSubarraysWithMinimumCostI().answer();
    }

    @Override
    public void answer() {
        // int[] nums = {1, 2, 3, 12};
        // int[] nums = {3, 2, 1, 1, 12};
        // int[] nums = {3, 1, 2, 12};
        // int[] nums = {3, 2, 1};
        int[] nums = {1, 6, 1, 5};
        System.out.println(minimumCost(nums));
    }

    public int minimumCost(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE / 2;
        for (int i = 1; i < n; i++) {
            if (nums[i] < min) {
                secondMin = min;
                min = nums[i];
            } else if (nums[i] < secondMin) {
                secondMin = nums[i];
            }
        }
        return nums[0] + min + secondMin;
    }

    @Override
    public Object initData() {
        return null;
    }
}
