package com.xx.basicDs.array.统计数组中的元素;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/24
 * <p>
 * 缺失的第一个正数
 * LeetCode 041
 * <p>
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * <p>
 * Tag：原地哈希  数组
 */
public class FindFirstMissingPositive implements Answer {

    public static void main(String[] args) {
        new FindFirstMissingPositive().answer();
    }

    /**
     * 解1：最简单的很容易想出来，即用set来做。但是空间复杂度会超。
     * 利用桶排序hash的思想
     * 如果数组中包含 x∈[1,N]，那么恢复后，数组的第 x−1个元素为 x。
     */
    @Override
    public void answer() {
        int[] nums = {3, 4, -1, 1};
        System.out.println(firstMissingPositive(nums));
    }

    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                // System.out.println(i + 1);
                return i + 1;
            }
        }
        return len + 1;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{6, 7, 8, 9};
    }
}
