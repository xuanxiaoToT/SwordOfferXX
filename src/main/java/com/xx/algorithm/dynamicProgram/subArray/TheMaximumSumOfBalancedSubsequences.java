package com.xx.algorithm.dynamicProgram.subArray;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/5
 * <p>
 * 平衡子序列的最大和
 * LeetCode 100112  Hard
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums 。
 * nums 一个长度为 k 的 子序列 指的是选出 k 个 下标 i0 < i1 < ... < ik-1 ，如果这个子序列满足以下条件，我们说它是 平衡的 ：
 * 对于范围 [1, k - 1] 内的所有 j ，nums[ij] - nums[ij-1] >= ij - ij-1 都成立。
 * nums 长度为 1 的 子序列 是平衡的。
 * 请你返回一个整数，表示 nums 平衡 子序列里面的 最大元素和 。
 * 一个数组的 子序列 指的是从原数组中删除一些元素（也可能一个元素也不删除）后，剩余元素保持相对顺序得到的 非空 新数组。
 * <p>
 * 示例 1：
 * 输入：nums = [3,3,5,6]
 * 输出：14
 * 解释：这个例子中，选择子序列 [3,5,6] ，下标为 0 ，2 和 3 的元素被选中。
 * nums[2] - nums[0] >= 2 - 0 。
 * nums[3] - nums[2] >= 3 - 2 。
 * 所以，这是一个平衡子序列，且它的和是所有平衡子序列里最大的。
 * 包含下标 1 ，2 和 3 的子序列也是一个平衡的子序列。
 * 最大平衡子序列和为 14 。
 * <p>
 * 示例 2：
 * 输入：nums = [5,-1,-3,8]
 * 输出：13
 * 解释：这个例子中，选择子序列 [5,8] ，下标为 0 和 3 的元素被选中。
 * nums[3] - nums[0] >= 3 - 0 。
 * 所以，这是一个平衡子序列，且它的和是所有平衡子序列里最大的。
 * 最大平衡子序列和为 13 。
 * <p>
 * 示例 3：
 * 输入：nums = [-2,-1]
 * 输出：-1
 * 解释：这个例子中，选择子序列 [-1] 。
 * 这是一个平衡子序列，而且它的和是 nums 所有平衡子序列里最大的。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class TheMaximumSumOfBalancedSubsequences implements Answer {

    public static void main(String[] args) {
        new TheMaximumSumOfBalancedSubsequences().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
    }

    /**
     * todo：等解析
     * fixme：做完
     */
    public long maxBalancedSubsequenceSumTrue(int[] nums) {

        return 0;
    }

    /**
     * 比赛时写的，第一版
     * 超时了~！
     */
    public long maxBalancedSubsequenceSum(int[] nums) {
        long[] dp = new long[nums.length];
        long maxTemp = nums[0];
        Arrays.parallelSetAll(dp, i -> nums[i]);
        Long max = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            maxTemp = Math.max(nums[i], maxTemp);
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] >= j - i) {
                    dp[j] = Math.max(dp[i] + nums[j], dp[j]);
                    max = Math.max(max, dp[j]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        if (max == Long.MIN_VALUE) {
            return maxTemp;
        } else {
            return Math.max(max, maxTemp);
        }
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return null;
    }
}
