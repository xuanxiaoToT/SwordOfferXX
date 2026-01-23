package com.xx.algorithm.search;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/14
 * <p>
 * 寻找重复数
 * LeetCode 287.
 * <p>
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），
 * 可知至少存在一个重复的整数。假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 */
public class FindDuplicates implements Answer {

    public static void main(String[] args) {
        new FindDuplicates().answer();
    }

    /**
     * 解1：可以使用桶排序，但是不允许修改数组
     * 由于也不允许额外空间，所以不能用map或set
     * 最简单的：可以O(N2)遍历
     * <p>
     * 解2：可以利用环检测来做
     * 找到环后呢？-> 快慢指针~！
     */
    @Override
    public void answer() {
        int[] nums = initData();
        //利用循环检测.
        //因为不能修改数组,所以桶排序无法实现,但仍旧可以利用桶排序的原理.
        //把坐标和数值之间相互转换，而由于重复数字的存在，那么一定会形成环，用快慢指针可以找到环并确定环的起始位置
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        System.out.println(ptr1);
    }

    /**
     * 抄的答案：二分查找
     * 「二分查找」的思路是先猜一个数（搜索范围 [left..right] 里位于中间的数 mid），
     * 然后统计原始数组中 小于等于 mid 的元素的个数 count：
     * 如果 count 严格大于 mid。根据 抽屉原理，重复元素就在区间 [left..mid] 里；
     * <p>
     * 否则，重复元素可以在区间 [mid + 1..right] 里找到，其实只要第 1 点是对的，这里肯定是对的。
     */
    public void answerTwo() {

        int[] nums = initData();
        int len = nums.length;

        // 在 [1..n] 查找 nums 中重复的元素
        int left = 1;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            // nums 中小于等于 mid 的元素的个数
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                // 下一轮搜索的区间 [left..mid]
                right = mid;
            } else {
                // 下一轮搜索的区间 [mid + 1..right]
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 3, 4, 2, 2, 5, 6, 7, 8};
    }
}
