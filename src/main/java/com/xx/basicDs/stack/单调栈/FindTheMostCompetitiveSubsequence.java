package com.xx.basicDs.stack.单调栈;

import com.xx.Answer;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/24
 * <p>
 * 找出最具竞争力的子序列
 * LeetCode 1673. Medium
 * <p>
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 * 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，
 * 那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 * <p>
 * 示例 1：
 * 输入：nums = [3,5,2,6], k = 2
 * 输出：[2,6]
 * 解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
 * <p>
 * 示例 2：
 * 输入：nums = [2,4,3,3,5,4,9,6], k = 4
 * 输出：[2,3,3,4]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 * <p>
 * Tag:单调栈  最小堆
 */
public class FindTheMostCompetitiveSubsequence implements Answer {

    public static void main(String[] args) {
        new FindTheMostCompetitiveSubsequence().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        // int[] nums = {2, 4, 3, 3, 5, 4, 9, 6};
        // int k = 4;
        int[] nums = {3, 5, 2, 6};
        int k = 2;
        System.out.println(Arrays.toString(mostCompetitive(nums, k)));
    }

    /**
     * 这种求某个区间内的最小值的
     * 利用单调栈！！
     * <p>
     * 根据题目对竞争力的定义，我们可以发现越小的数字放置的位置越前，对应的子序列越具竞争力。
     * 我们可以用类似单调栈的思想尽量将更小的元素放到子序列的前面，令 nums 的大小为 n，遍历数组 nums，假设当前访问的下标为 i，对数字 nums[i] 执行以下操作：
     * 记栈中的元素数目为 m，我们不断地进行操作直到不满足条件：
     * 如果 m>0 且 m+n−i>k 且单调栈的栈顶元素大于 nums[i]，
     * 那么说明栈顶元素可以被当前数字 nums[i] 替换，弹出单调栈的栈顶元素。
     * 将 nums[i] 压入栈中。
     * 最后返回栈中自下而上的前 k 个元素为结果。
     *
     */
    public int[] mostCompetitive3(int[] nums, int k) {
        Stack<Integer> stack = new Stack<Integer>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 在弹出栈顶元素之前，必须保证栈中元素个数加上剩余元素个数是大于 k 的。
            while (!stack.isEmpty() && n - i + stack.size() > k && stack.peek() > nums[i]) {
                stack.pop();
            }
            stack.push(nums[i]);
        }
        int[] res = new int[k];
        while (stack.size() > k) {
            stack.pop();
        }
        for (int i = k - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }


    /**
     * 2：改进法，既然computeMinIndex方法在求最小值的过程中，很多重复性的遍历
     * 那么将其放到最小堆中，这样只需要排序一次。但仍然有浪费现象，比如
     * 当index < left时。
     */
    public int[] mostCompetitive2(int[] nums, int k) {
        if (nums.length == k) {
            return nums;
        }
        int[] result = new int[k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(nums.length, (o1, o2) -> {
            if (nums[o1] == nums[o2]) {
                return o1 - o2;
            }
            return nums[o1] - nums[o2];
        });
        for (int i = 0; i < nums.length - k; i++) {
            minHeap.add(i);
        }
        int lastIndex = -1;
        for (int i = 0; i < k; i++) {
            int left = lastIndex + 1;
            int right = nums.length - k + i;
            minHeap.add(right);
            Integer index;
            do {
                index = minHeap.poll();
            } while (!minHeap.isEmpty() && index < left);
            result[i] = nums[index];
            lastIndex = index;
        }
        return result;
    }

    /**
     * 最先想到的：遍历K次，每次求[left,right]之间的最小值
     * 超时！！
     * 因为：computeMinIndex方法在求最小值的过程中，很多重复性的遍历
     */
    public int[] mostCompetitive(int[] nums, int k) {
        if (nums.length == k) {
            return nums;
        }
        int[] result = new int[k];
        int lastIndex = -1;
        for (int i = 0; i < k; i++) {
            int index = computeMinIndex(nums, lastIndex + 1, nums.length - k + i);
            lastIndex = index;
            result[i] = nums[index];
        }
        return result;
    }

    private int computeMinIndex(int[] nums, int left, int end) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = left; i <= end; i++) {
            if (nums[i] < min) {
                index = i;
                min = nums[i];
            }
        }
        return index;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
