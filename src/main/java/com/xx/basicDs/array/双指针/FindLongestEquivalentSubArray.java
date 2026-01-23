package com.xx.basicDs.array.双指针;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/23
 * <p>
 * 找出最长等值子数组
 * LeetCode 2831.  Medium
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 * 如果子数组中所有元素都相等，则认为子数组是一个 等值子数组 。注意，空数组是 等值子数组 。
 * 从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。
 * 子数组 是数组中一个连续且可能为空的元素序列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,2,3,1,3], k = 3
 * 输出：3
 * 解释：最优的方案是删除下标 2 和下标 4 的元素。
 * 删除后，nums 等于 [1, 3, 3, 3] 。
 * 最长等值子数组从 i = 1 开始到 j = 3 结束，长度等于 3 。
 * 可以证明无法创建更长的等值子数组。
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,2,2,1,1], k = 2
 * 输出：4
 * 解释：最优的方案是删除下标 2 和下标 3 的元素。
 * 删除后，nums 等于 [1, 1, 1, 1] 。
 * 数组自身就是等值子数组，长度等于 4 。
 * 可以证明无法创建更长的等值子数组。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= nums.length
 * 0 <= k <= nums.length
 * <p>
 * Tag:双指针  子数组
 */
public class FindLongestEquivalentSubArray implements Answer {
    public static void main(String[] args) {
        new FindLongestEquivalentSubArray().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        List<Integer> nums = Arrays.asList(1, 3, 2, 3, 1, 3);
        int k = 3;
        System.out.println(longestEqualSubarray(nums, k));
    }

    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums.get(i), x -> new ArrayList<>()).add(i);
        }
        int ans = 0;
        for (List<Integer> vec : pos.values()) {
            for (int i = 0, j = 0; i < vec.size(); i++) {
                /* 缩小窗口，直到不同元素数量小于等于 k */
                while (vec.get(i) - vec.get(j) - (i - j) > k) {
                    j++;
                }
                ans = Math.max(ans, i - j + 1);
            }
        }
        return ans;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
