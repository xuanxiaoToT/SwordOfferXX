package com.xx.basicDs.array.双指针;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/19
 * <p>
 * K和数对的最大数目
 * LeetCode 1679. Medium
 * <p>
 * 给你一个整数数组 nums 和一个整数 k 。
 * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
 * 返回你可以对数组执行的最大操作数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4], k = 5
 * 输出：2
 * 解释：开始时 nums = [1,2,3,4]：
 * - 移出 1 和 4 ，之后 nums = [2,3]
 * - 移出 2 和 3 ，之后 nums = []
 * 不再有和为 5 的数对，因此最多执行 2 次操作。
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,3,4,3], k = 6
 * 输出：1
 * 解释：开始时 nums = [3,1,3,4,3]：
 * - 移出前两个 3 ，之后nums = [1,4,3]
 * 不再有和为 6 的数对，因此最多执行 1 次操作。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 * <p>
 * Tag：双指针  排序搜索
 */
public class TheMaxCountOfkSum implements Answer {

    public static void main(String[] args) {
        new TheMaxCountOfkSum().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums = {3, 1, 3, 4, 3};
        int k = 6;
        System.out.println(maxOperations(nums, k));
    }

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int result = 0;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == k) {
                result++;
                left++;
                right--;
            } else {
                if (sum < k) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}