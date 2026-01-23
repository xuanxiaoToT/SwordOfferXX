package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/8
 * <p>
 * 数组中的第K个最大元素
 * LeetCode 215 Medium
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * <p>
 * 提示：
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * Tag: 最小堆 分治法 快速排序 计数排序
 */
public class TheKLargestNumberOfDataStreams implements Answer {


    public static void main(String[] args) {
        new TheKLargestNumberOfDataStreams().answer();
    }

    /**
     * 求第K大，用最小堆。
     * 求第K小，用最大堆。
     * 时间复杂度O(N*LogN)
     */
    @Override
    public void answer() {
        int[] nums = initData();
        int k = 5;
        System.out.println(findKthLargest(nums, k));
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int num : nums) {
            add(heap, num, k);
        }
        return heap.peek() == null ? 0 : heap.peek();
    }

    /**
     * 方法2：计数排序
     * 注意题目：
     * -10^4 <= nums[i] <= 10^4
     */
    public int findKthLargest2(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[21000];
        for (int i = 0; i < n; i++) {
            //+10000是将负数往右移
            f[nums[i] + 10000]++;
        }
        int cnt = 0;
        for (int i = 21000 - 1; i >= 0; i--) {
            cnt += f[i];
            if (cnt >= k) {
                return i - 10000;
            }
        }
        return -1;
    }

    /**
     * 方法3：分治，快排
     * 参看快排
     */
    public int findKthLargest3(int[] nums, int k) {
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        return quickSelect(numList, k);
    }

    private int quickSelect(List<Integer> nums, int k) {
        // 随机选择基准数
        Random rand = new Random();
        int pivot = nums.get(rand.nextInt(nums.size()));
        // 将大于、小于、等于 pivot 的元素划分至 big, small, equal 中
        List<Integer> big = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        for (int num : nums) {
            if (num > pivot)
                big.add(num);
            else if (num < pivot)
                small.add(num);
            else
                equal.add(num);
        }
        // 第 k 大元素在 big 中，递归划分
        if (k <= big.size())
            return quickSelect(big, k);
        // 第 k 大元素在 small 中，递归划分
        if (nums.size() - small.size() < k)
            return quickSelect(small, k - nums.size() + small.size());
        // 第 k 大元素在 equal 中，直接返回 pivot
        return pivot;
    }

    private void add(PriorityQueue<Integer> heap, int num, int k) {
        if (heap.size() < k) {
            heap.add(num);
        } else {
            if (num > heap.peek()) {
                heap.poll();
                heap.add(num);
            }
        }
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    }
}