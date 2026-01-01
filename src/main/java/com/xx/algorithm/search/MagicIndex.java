package com.xx.algorithm.search;

import com.xx.Answer;

/**
 * 魔术索引
 * LeetCode Easy
 *
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，
 * 若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
 *
 * 示例 1：
 *  输入：nums = [0, 2, 3, 4, 5]
 *  输出：0
 *  说明：0下标的元素为0
 *
 * 示例 2：
 *  输入：nums = [1, 1, 1]
 *  输出：1
 *
 * 说明：
 * nums长度在[1, 1000000]之间
 * 此题为原书中的 Follow-up，即数组中可能包含重复元素的版本
 *
 * Tag：跳跃查询
 */
public class MagicIndex implements Answer {
    public static void main(String[] args) {
        new MagicIndex().answerOne();
    }

    @Override
    public void answerOne() {
        // int[] nums = new int[]{0, 2, 3, 4, 5};
        // int[] nums = new int[]{1, 1, 1, 1};
        int[] nums = new int[]{0, 0, 2};
        System.out.println(findMagicIndex(nums));
    }

    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == i) {
                return i;
            }
            // 如果num[i]<i很小，那前面就已经满足了，所以num[i]肯定是大于i的。
            // 这个时候中间的结果其实可以不用遍历了，因为在i到达num[i]之前，num[i]都是大于i的，那不如直接跑到这个num[i]这里，
            i = Math.max(nums[i], i + 1);
        }
        return 0;
    }

    /**
     * 不能用二分法~！：
     * 局限性：
     * nums[mid] < mid时
     * 如果你想让nums[mid]更大，那应该往右找。但也可以让mid更小，往左找。
     * 所以无法用二分查找来做这个题。尤其是包含重复数据时。
     * 比如int[] nums = new int[]{0, 0, 2};
     */
    public int findMagicIndexError(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == mid) {
                index = Math.min(index, mid);
                right = mid - 1;
            }
            if (nums[mid] > mid) {
                right = mid - 1;
            }
            // 问题来了；往左还是往右呢？
            if (nums[mid] < mid) {
                left = mid + 1;
            }
        }
        return index;
    }

    @Override
    public Object initData() {
        return null;
    }
}
