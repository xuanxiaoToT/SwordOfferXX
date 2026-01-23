package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/29
 * <p>
 * 使数组变美的最小增量运算数
 * LeetCode 2919  Medium2
 * <p>
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和一个整数 k 。
 * 你可以执行下述 递增 运算 任意 次（可以是 0 次）：
 * 从范围 [0, n - 1] 中选则一个下标 i ，并将 nums[i] 的值加 1 。
 * 如果数组中任何长度 大于或等于 3 的子数组，其 最大 元素都大于或等于 k ，则认为数组是一个 美丽数组 。
 * 以整数形式返回使数组变为 美丽数组 需要执行的 最小 递增运算数。
 * 子数组是数组中的一个连续 非空 元素序列。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,0,0,2], k = 4
 * 输出：3
 * 解释：可以执行下述递增运算，使 nums 变为美丽数组：
 * 选择下标 i = 1 ，并且将 nums[1] 的值加 1 -> [2,4,0,0,2] 。
 * 选择下标 i = 4 ，并且将 nums[4] 的值加 1 -> [2,4,0,0,3] 。
 * 选择下标 i = 4 ，并且将 nums[4] 的值加 1 -> [2,4,0,0,4] 。
 * 长度大于或等于 3 的子数组为 [2,4,0], [4,0,0], [0,0,4], [2,4,0,0], [4,0,0,4], [2,4,0,0,4] 。
 * 在所有子数组中，最大元素都等于 k = 4 ，所以 nums 现在是美丽数组。
 * 可以证明无法用少于 3 次递增运算使 nums 变为美丽数组。
 * 因此，答案为 3 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,3,3], k = 5
 * 输出：2
 * 解释：可以执行下述递增运算，使 nums 变为美丽数组：
 * 选择下标 i = 2 ，并且将 nums[2] 的值加 1 -> [0,1,4,3] 。
 * 选择下标 i = 2 ，并且将 nums[2] 的值加 1 -> [0,1,5,3] 。
 * 长度大于或等于 3 的子数组为 [0,1,5]、[1,5,3]、[0,1,5,3] 。
 * 在所有子数组中，最大元素都等于 k = 5 ，所以 nums 现在是美丽数组。
 * 可以证明无法用少于 2 次递增运算使 nums 变为美丽数组。
 * 因此，答案为 2 。
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,2], k = 1
 * 输出：0
 * 解释：在这个示例中，只有一个长度大于或等于 3 的子数组 [1,1,2] 。
 * 其最大元素 2 已经大于 k = 1 ，所以无需执行任何增量运算。
 * 因此，答案为 0 。
 * <p>
 * 提示：
 * 3 <= n == nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 */
public class MinNumberToMakeAnArrayBeautiful implements Answer {

    public static void main(String[] args) {
        new MinNumberToMakeAnArrayBeautiful().answer();
    }

    /**
     * 解1：
     * 思考的时候一直以i为开始的三元组进行dp思考，而不是以i为结束。
     */
    @Override
    public void answer() {
        //int[] nums = new int[]{2, 3, 0, 0, 2};
        //int k = 4;
        //int[] nums = new int[]{0, 1, 3, 3};
        //int k = 5;
        int[] nums = new int[]{4, 0, 10, 2, 10, 6};
        int k = 8;
        System.out.println(minIncrementOperations(nums, k));
    }

    /**
     * 创建一个大小为 n（其中 n 为 nums 的大小）的 dp 数组，dp[i] 表示当使第 i 项大于等于 k 并使前 i 项变为美丽数组时的最小修改次数.
     * 那么可以得到状态转移方程：
     * <p>
     * dp[i]=min{dp[i−3],dp[i−2],dp[i−1]}+max{0,k−nums[i]}
     * 边界处理：当 i<3 时，dp[i]=max{0,k−nums[i]}。
     * 使原数组变为美丽数组的最小修改次数 ans=min{dp[n−3],dp[n−2],dp[n−1]}。
     * 这样做的空间复杂度为 O(n)，通过滚动数组可以优化到 O(1)。
     */
    public long minIncrementOperations(int[] nums, int k) {
        long[] dp = new long[nums.length];
        for (int i = 0; i < 3; i++) {
            dp[i] = Math.max(0, k - nums[i]);
        }
        for (int i = 3; i < nums.length; i++) {
            dp[i] = findMin(dp[i - 1], dp[i - 2], dp[i - 3]) + Math.max(0, k - nums[i]);
        }
        return findMin(dp[nums.length - 1], dp[nums.length - 2], dp[nums.length - 3]);
    }

    public long findMin(long num1, long... numbs) {
        long min = num1;
        for (long num : numbs) {
            min = Math.min(num, min);
        }
        return min;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
