package com.xx.basicDs.array.三元组问题;

import com.xx.Answer;
import com.xx.basicDs.array.数组的遍历.TheThirdLargestNumber;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/15
 * <p>
 * 递增的三元子序列
 * LeetCode 334. Medium
 * <p>
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，
 * 返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * <p>
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * <p>
 * 示例 3：
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class IncrementalTernarySubsequence implements Answer {

    public static void main(String[] args) {
        new IncrementalTernarySubsequence().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums = {2, 1, 5, 0, 4, 6};
        System.out.println(increasingTriplet(nums));
    }

    public boolean increasingTriplet(int[] nums) {
        int[] leftMin = new int[nums.length];
        int[] rightMax = new int[nums.length];
        int max = nums[nums.length - 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            rightMax[i] = max;
            max = Math.max(max, nums[i]);
        }
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            leftMin[i] = min;
            min = Math.min(min, nums[i]);
            if (leftMin[i] < nums[i] && nums[i] < rightMax[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 贪心法
     * <p>
     * 此题通俗的做法是暴力求解 这里采用贪心做。
     * 题目的意思是要有升序子序列（3个），那么只要我们分别维护第一小和第二小的值，当遍历到比这两个值都大的值后 即视为成功。
     * <p>
     * 可以想象一下 若数组中有符合条件的序列 那么一定会满足第三个值一定比前两个大 那么也一定会大于两个最小值
     * 参考：{@link TheThirdLargestNumber}
     */
    public boolean increasingTriplet2(int[] nums) {
        int len = nums.length;
        int first = Integer.MAX_VALUE, sec = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= sec) {
                sec = num;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
