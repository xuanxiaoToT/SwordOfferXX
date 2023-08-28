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
        new MaxSlidingWindow().answerTwo();
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
     * 使用堆来做。
     * 对于本题而言，初始时，我们将数组 nums 的前 k 个元素放入优先队列中。每当我们向右移动窗口时，我们就可以把一个新的元素放入优先队列中，
     * 此时堆顶的元素就是堆中所有元素的最大值。然而这个最大值可能并不在滑动窗口中，在这种情况下，这个值在数组 nums中的位置出现在滑动窗口左边界的左侧。
     * 因此，当我们后续继续向右移动窗口时，这个值就永远不可能出现在滑动窗口中了，我们可以将其永久地从优先队列中移除。
     * <p>
     * 我们不断地移除堆顶的元素，直到其确实出现在滑动窗口中。此时，堆顶元素就是滑动窗口中的最大值。为了方便判断堆顶元素与滑动窗口的位置关系，
     * 我们可以在优先队列中存储二元组 (num,index)，表示元素 num 在数组中的下标为 index。
     */
    public void answerTwo() {
        int k = 1;
        int[] nums = initData();
        int[] result = new int[nums.length - k + 1];
        PriorityQueue<int[]> maxheap = new PriorityQueue<int[]>((pair1, pair2) -> {
            return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
        });

        for (int i = 0; i < k; ++i) {
            maxheap.offer(new int[]{nums[i], i});
        }

        int left = 0;
        int right = k - 1;
        int index = 0;
        while (right < nums.length) {
            int[] peek = maxheap.peek();

            while (peek[1] < left) {
                maxheap.poll();
                peek = maxheap.peek();
            }
            result[index] = peek[0];

            index++;
            left++;
            right++;
            if (right < nums.length) {
                maxheap.add(new int[]{nums[right], right});
            }
        }
        System.out.println(Arrays.toString(result));

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
        //return new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        return new int[]{1, -1};
    }
}
