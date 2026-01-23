package com.xx.algorithm.dynamicProgram.二维DP;

import com.xx.Answer;

/**
 * 两个子序列的最大点积
 * LeetCode  1458. Hard
 * <p>
 * 给你两个数组 nums1 和 nums2 。
 * 请你返回 nums1 和 nums2 中两个长度相同的 非空 子序列的最大点积。
 * 数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，
 * 但不能改变数字间相对顺序。比方说，[2,3,5] 是 [1,2,3,4,5] 的一个子序列而 [1,5,3] 不是。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums1 = [2,1,-2,5], nums2 = [3,0,-6]
 * 输出：18
 * 解释：从 nums1 中得到子序列 [2,-2] ，从 nums2 中得到子序列 [3,-6] 。
 * 它们的点积为 (2*3 + (-2)*(-6)) = 18 。
 * <p>
 * 示例 2：
 * 输入：nums1 = [3,-2], nums2 = [2,-6,7]
 * 输出：21
 * 解释：从 nums1 中得到子序列 [3] ，从 nums2 中得到子序列 [7] 。
 * 它们的点积为 (3*7) = 21 。
 * <p>
 * 示例 3：
 * 输入：nums1 = [-1,-1], nums2 = [1,1]
 * 输出：-1
 * 解释：从 nums1 中得到子序列 [-1] ，从 nums2 中得到子序列 [1] 。
 * 它们的点积为 -1 。
 * <p>
 * 提示：
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 1000
 * <p>
 * Tag：动态规划
 */
public class MaxDotProductOfTwoSubsequences implements Answer {
    public static void main(String[] args) {
        new MaxDotProductOfTwoSubsequences().answer();
    }

    @Override
    public void answer() {
        // int[] nums1 = new int[]{2,1,-2,5};
        // int[] nums2 = new int[]{3,0,-6};

        // int[] nums1 = new int[]{3,-2};
        // int[] nums2 = new int[]{2,-6,7};

        // int[] nums1 = new int[]{-1, -1};
        // int[] nums2 = new int[]{1, 1};

        int[] nums1 = new int[]{-1, -1, 5};
        int[] nums2 = new int[]{1, 1, 5};
        System.out.println(maxDotProduct(nums1, nums2));
    }

    /**
     * 一次过~！
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            if (i > 0) {
                dp[i][0] = Math.max(dp[i - 1][0], nums1[i] * nums2[0]);
            } else {
                dp[i][0] = nums1[i] * nums2[0];
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (i > 0) {
                dp[0][i] = Math.max(dp[0][i - 1], nums1[0] * nums2[i]);
            } else {
                dp[0][i] = nums1[0] * nums2[i];
            }
        }
        // 可以选nums1[i] * nums2[j]这组，也可以不选这组。也可以选了这组，丢弃掉前面的
        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                dp[i][j] = computeMax(dp[i - 1][j - 1], dp[i - 1][j],
                        dp[i][j - 1], dp[i - 1][j - 1] + nums1[i] * nums2[j], nums1[i] * nums2[j]);
            }
        }
        return dp[nums1.length - 1][nums2.length - 1];
    }

    private int computeMax(Integer data1, Integer... data2) {
        int max = data1;
        for (Integer data : data2) {
            max = Math.max(max, data);
        }
        return max;
    }

    @Override
    public Object initData() {
        return null;
    }
}
