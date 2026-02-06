package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 使数组平衡的最少移除数目
 * LeetCode 3634. Medium
 * <p>
 * 给你一个整数数组 nums 和一个整数 k。
 * 如果一个数组的 最大 元素的值 至多 是其 最小 元素的 k 倍，则该数组被称为是 平衡 的。
 * 你可以从 nums 中移除 任意 数量的元素，但不能使其变为 空 数组。
 * 返回为了使剩余数组平衡，需要移除的元素的 最小 数量。
 * 注意：大小为 1 的数组被认为是平衡的，因为其最大值和最小值相等，且条件总是成立。
 * <p>
 * 示例 1:
 * 输入：nums = [2,1,5], k = 2
 * 输出：1
 * 解释：
 * 移除 nums[2] = 5 得到 nums = [2, 1]。
 * 现在 max = 2, min = 1，且 max <= min * k，因为 2 <= 1 * 2。因此，答案是 1。
 * <p>
 * 示例 2:
 * 输入：nums = [1,6,2,9], k = 3
 * 输出：2
 * 解释：
 * 移除 nums[0] = 1 和 nums[3] = 9 得到 nums = [6, 2]。
 * 现在 max = 6, min = 2，且 max <= min * k，因为 6 <= 2 * 3。因此，答案是 2。
 * <p>
 * 示例 3:
 * 输入：nums = [4,6], k = 2
 * 输出：0
 * 解释：
 * 由于 nums 已经平衡，因为 6 <= 4 * 2，所以不需要移除任何元素。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^5
 * <p>
 * Tag: 滑动窗口 双指针  TreeMap
 */
public class MinimumRemovalsToBalanceArray implements Answer {

    public static void main(String[] args) {
        new MinimumRemovalsToBalanceArray().answer();
    }


    @Override
    public void answer() {
        // int[] nums = {2, 1, 5};
        // int k = 2;

        // int[] nums = {1, 6, 2, 9};
        // int k = 3;

        // int[] nums = {4, 6};
        // int k = 2;

        // int[] nums = {4, 60};
        // int k = 1;

        int[] nums = {8, 99, 65, 16, 39};
        int k = 3;
        System.out.println(minRemoval(nums, k));
    }

    /**
     * 滑动窗口+双指针
     */
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int maxSave = 0;
        int left = 0;
        // i表示当前最大，也就是最右节点
        for (int i = 0; i < nums.length; i++) {
            while ((long) nums[left] * k < nums[i]) {
                left++;
            }
            maxSave = Math.max(maxSave, i - left + 1);
        }
        return nums.length - maxSave;
    }

    /**
     * 用treeMap
     */
    public int minRemovalOld(int[] nums, int k) {
        if (nums == null || nums.length == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        TreeMap<Long, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put((long) nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            // 找到小于或等于指定键的最大键。而val是下标，这样遍历完了，就可以发现最小值了
            Long floorKey = map.floorKey((long) nums[i] * k);
            Integer right = map.get(floorKey);
            // i代表其左侧的都去掉了，right表示右侧的都去掉了
            res = Math.min(nums.length - 1 - right + i, res);
        }
        return res;
    }

    @Override
    public Object initData() {
        return null;
    }
}
