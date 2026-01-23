package com.xx.algorithm.search.二分查找;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/25
 * <p>
 * 统计和小于目标的下标对数目
 * LeetCode 2824. Easy
 * <p>
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 target ，
 * 请你返回满足 0 <= i < j < n 且 nums[i] + nums[j] < target 的下标对 (i, j) 的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,1,2,3,1], target = 2
 * 输出：3
 * 解释：总共有 3 个下标对满足题目描述：
 * - (0, 1) ，0 < 1 且 nums[0] + nums[1] = 0 < target
 * - (0, 2) ，0 < 2 且 nums[0] + nums[2] = 1 < target
 * - (0, 4) ，0 < 4 且 nums[0] + nums[4] = 0 < target
 * 注意 (0, 3) 不计入答案因为 nums[0] + nums[3] 不是严格小于 target 。
 * <p>
 * 示例 2：
 * 输入：nums = [-6,2,5,-2,-7,-1,3], target = -2
 * 输出：10
 * 解释：总共有 10 个下标对满足题目描述：
 * - (0, 1) ，0 < 1 且 nums[0] + nums[1] = -4 < target
 * - (0, 3) ，0 < 3 且 nums[0] + nums[3] = -8 < target
 * - (0, 4) ，0 < 4 且 nums[0] + nums[4] = -13 < target
 * - (0, 5) ，0 < 5 且 nums[0] + nums[5] = -7 < target
 * - (0, 6) ，0 < 6 且 nums[0] + nums[6] = -3 < target
 * - (1, 4) ，1 < 4 且 nums[1] + nums[4] = -5 < target
 * - (3, 4) ，3 < 4 且 nums[3] + nums[4] = -9 < target
 * - (3, 5) ，3 < 5 且 nums[3] + nums[5] = -3 < target
 * - (4, 5) ，4 < 5 且 nums[4] + nums[5] = -8 < target
 * - (4, 6) ，4 < 6 且 nums[4] + nums[6] = -4 < target
 * <p>
 * 提示：
 * 1 <= nums.length == n <= 50
 * -50 <= nums[i], target <= 50
 * <p>
 * Tag：二分查找 双指针 排序
 */
public class SumOfPairsLessThanTheTarget implements Answer {

    public static void main(String[] args) {
        new SumOfPairsLessThanTheTarget().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(countPairs2(nums, 9));
    }

    /**
     * 解：二分查找。
     * 同{@link TheSuccessLogarithmOfSpellsAndPotions}
     */
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int res = 0;
        for (int i = 1; i < nums.size(); i++) {
            //只扫描i左边的，这样可以避免重复
            int k = binarySearch(nums, 0, i - 1, target - nums.get(i));
            res += k;
        }
        return res;
    }

    /**
     * 双指针
     * i-j之间的都符合，即：(i,i+1),(i,i+2)...到j为止
     */
    public int countPairs2(List<Integer> nums, int target) {
        Collections.sort(nums);
        int res = 0;
        for (int i = 0, j = nums.size() - 1; i < j; i++) {
            while (i < j && nums.get(i) + nums.get(j) >= target) {
                j--;
            }
            res += j - i;
        }
        return res;
    }


    public int binarySearch(List<Integer> nums, int left, int right, int target) {
        int res = right + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
