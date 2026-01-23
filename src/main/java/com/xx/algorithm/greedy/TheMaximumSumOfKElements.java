package com.xx.algorithm.greedy;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/15
 * <p>
 * K个元素的最大和
 * LeetCode 2656. Easy
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。你需要执行以下操作 恰好 k 次，最大化你的得分：
 * 1.从 nums 中选择一个元素 m 。
 * 2.将选中的元素 m 从数组中删除。
 * 3.将新元素 m + 1 添加到数组中。
 * 4.你的得分增加 m 。
 * 请你返回执行以上操作恰好 k 次后的最大得分。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4,5], k = 3
 * 输出：18
 * 解释：我们需要从 nums 中恰好选择 3 个元素并最大化得分。
 * 第一次选择 5 。和为 5 ，nums = [1,2,3,4,6] 。
 * 第二次选择 6 。和为 6 ，nums = [1,2,3,4,7] 。
 * 第三次选择 7 。和为 5 + 6 + 7 = 18 ，nums = [1,2,3,4,8] 。
 * 所以我们返回 18 。
 * 18 是可以得到的最大答案。
 * <p>
 * 示例 2：
 * 输入：nums = [5,5,5], k = 2
 * 输出：11
 * 解释：我们需要从 nums 中恰好选择 2 个元素并最大化得分。
 * 第一次选择 5 。和为 5 ，nums = [5,5,6] 。
 * 第二次选择 6 。和为 6 ，nums = [5,5,7] 。
 * 所以我们返回 11 。
 * 11 是可以得到的最大答案。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 1 <= k <= 100
 * <p>
 * Tag: 贪心
 */
public class TheMaximumSumOfKElements implements Answer {

    public static void main(String[] args) {
        new TheMaximumSumOfKElements().answer();
    }

    /**
     * 解1：简单的脑筋急转弯题
     * m + 1放回去，依然是最大值
     */
    @Override
    public void answer() {
        int[] nums = initData();
        int k = 3;
        System.out.println(maximizeSum(nums, k));
    }

    public int maximizeSum(int[] nums, int k) {
        int resultSum = 0;
        int max = Arrays.stream(nums).max().getAsInt();
        for (int i = 0; i < k; i++) {
            resultSum += (max + i);
        }
        return resultSum;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 4, 5};
    }
}
