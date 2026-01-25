package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

/**
 * 移除前缀使数组严格递增
 * leetCode 100971 Easy
 * <p>
 * 给你一个整数数组 nums。
 * 你需要从 nums 中 恰好 移除一个前缀（可以为空）。
 * 返回一个整数，表示被移除的前缀的 最小 长度，使得剩余的数组 严格递增 。
 * 数组的 前缀 是从数组的起始位置开始，一直延伸到任意位置的子数组。
 * 如果一个数组的每个元素都严格大于它的前一个元素（若存在），则称该数组 严格递增 。
 * <p>
 * 示例 1：
 * 输入： nums = [1,-1,2,3,3,4,5]
 * 输出： 4
 * 解释：
 * 移除前缀 prefix = [1, -1, 2, 3] 后，剩余数组为 [3, 4, 5]，严格递增。
 * <p>
 * 示例 2：
 * 输入： nums = [4,3,-2,-5]
 * 输出： 3
 * 解释：
 * 移除前缀 prefix = [4, 3, -2] 后，剩余数组为 [-5]，严格递增。
 * <p>
 * 示例 3：
 * 输入： nums = [1,2,3,4]
 * 输出： 0
 * 解释：
 * 数组 nums = [1, 2, 3, 4] 已经是严格递增的，因此移除空前缀即可。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * Tag: 数组  遍历
 *
 */
public class MinimumPrefixRemovalMakeArrayStrictlyIncreasing implements Answer {
    public static void main(String[] args) {
        new MinimumPrefixRemovalMakeArrayStrictlyIncreasing().answer();
    }

    @Override
    public void answer() {
        // int[] nums = new int[]{1, -1, 2, 3, 3, 4, 5};
        // int[] nums = new int[]{4,3,-2,-5};
        // int[] nums = new int[]{1, 2, 3, 4, 5};
        int[] nums = new int[]{2, 2, 3, 4, 5};
        System.out.println(minimumPrefixLength(nums));
    }

    /**
     * 倒序遍历 nums，如果发现 nums[i−1]≥nums[i]，那么前缀 [0,i−1] 必须删除，其长度为 i。
     */
    public int minimumPrefixLength(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] >= nums[i]) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public Object initData() {
        return null;
    }
}
