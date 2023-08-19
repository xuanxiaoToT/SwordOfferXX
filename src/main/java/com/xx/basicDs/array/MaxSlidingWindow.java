package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/19
 * <p>
 * 滑动窗口最大值
 * LeetCode 239.
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 */
public class MaxSlidingWindow implements Answer {

    public static void main(String[] args) {
        new MaxSlidingWindow().answerOne();
    }

    /**
     * 解1：时间不足，时间复杂度太高
     */
    @Override
    public void answerOne() {
        int k = 3;
        int[] nums = initData();
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        if (k == 1) {
            result = nums.clone();
        }

        int left = 0;
        int right = k - 1;
        while (right < nums.length) {
            if (index == 0) {
                result[index] = findMax(left, right, nums);
            } else {
                int lastMax = result[index - 1];
                if (nums[right] >= lastMax) {
                    result[index] = nums[right];
                } else {
                    if (nums[left - 1] < lastMax) {
                        result[index] = lastMax;
                    } else {
                        result[index] = findMax(left, right, nums);
                    }
                }
            }
            index++;
            left++;
            right++;
        }
        System.out.println(Arrays.toString(result));
    }

    /**
     * 使用堆来做
     */
    public void answerTwo() {
        int k = 3;
        int[] nums = initData();
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        PriorityQueue<Integer> minheap = new PriorityQueue<>();



    }

    private void addStack(PriorityQueue<Integer> heap, Integer num, int k) {
        if (heap.size() < k) {
            heap.add(num);
        } else {
            if (num > heap.peek()) {
                heap.poll();
                heap.add(num);
            }
        }
    }

    private Integer findMax(final int left, final int right, int[] nums) {
        int maxTemp = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            maxTemp = Math.max(maxTemp, nums[i]);
        }
        return maxTemp;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 3, -1, -3, 5, 3, 6, 7};
    }
}
