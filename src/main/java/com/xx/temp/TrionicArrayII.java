package com.xx.temp;

import com.xx.Answer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 三段式数组II
 * LeetCode 3640. Hard
 * <p>
 * 给你一个长度为 n 的整数数组 nums。
 * 三段式子数组 是一个连续子数组 nums[l...r]（满足 0 <= l < r < n），并且存在下标 l < p < q < r，使得：
 * nums[l...p] 严格 递增，
 * nums[p...q] 严格 递减，
 * nums[q...r] 严格 递增。
 * 请你从数组 nums 的所有三段式子数组中找出和最大的那个，并返回其 最大 和。
 * <p>
 * 示例 1：
 * 输入：nums = [0,-2,-1,-3,0,2,-1]
 * 输出：-4
 * 解释：
 * 选择 l = 1, p = 2, q = 3, r = 5：
 * nums[l...p] = nums[1...2] = [-2, -1] 严格递增 (-2 < -1)。
 * nums[p...q] = nums[2...3] = [-1, -3] 严格递减 (-1 > -3)。
 * nums[q...r] = nums[3...5] = [-3, 0, 2] 严格递增 (-3 < 0 < 2)。
 * 和 = (-2) + (-1) + (-3) + 0 + 2 = -4。
 * <p>
 * 示例 2:
 * 输入: nums = [1,4,2,7]
 * 输出: 14
 * 解释:
 * 选择 l = 0, p = 1, q = 2, r = 3：
 * nums[l...p] = nums[0...1] = [1, 4] 严格递增 (1 < 4)。
 * nums[p...q] = nums[1...2] = [4, 2] 严格递减 (4 > 2)。
 * nums[q...r] = nums[2...3] = [2, 7] 严格递增 (2 < 7)。
 * 和 = 1 + 4 + 2 + 7 = 14。
 * <p>
 * 提示:
 * 4 <= n = nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 保证至少存在一个三段式子数组。
 * <p>
 * Tag：分组循环
 */
public class TrionicArrayII implements Answer {
    public static void main(String[] args) {
        new TrionicArrayII().answer();
    }

    @Override
    public void answer() {
        // int[] nums = {0, -2, -1, -3, 0, 2, -1};
        // int[] nums = {1,4,2,7};
        // int[] nums = {1, 2, 0, 0, -1, 3, 4, 5, 4, 3, 6, 7};
        // int[] nums = {467, 941, -696, -288};
        // int[] nums = {1, 4, 2, 2, 3, 1, 2};
        int[] nums = {1, 3, 2, 4, 1};
        System.out.println(maxSumTrionic(nums));
    }

    /**
     *
     * 状态机BP
     * 链接：https://leetcode.cn/problems/trionic-array-ii/solutions/3741020/fen-zu-xun-huan-on-shi-jian-o1-kong-jian-ewr5/
     */
    public long maxSumTrionic(int[] nums) {
        final long NEG_INF = Long.MIN_VALUE / 2; // 除 2 防止下面加法（和负数相加）溢出
        long ans = NEG_INF;
        long f1 = NEG_INF;
        long f2 = NEG_INF;
        long f3 = NEG_INF;
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i - 1];
            int y = nums[i];
            f3 = x < y ? Math.max(f3, f2) + y : NEG_INF;
            f2 = x > y ? Math.max(f2, f1) + y : NEG_INF;
            f1 = x < y ? Math.max(f1, x) + y : NEG_INF;
            ans = Math.max(ans, f3);
        }
        return ans;
    }

    /**
     * 三次提交错误
     * 错误1：
     * 题目要求的是long，没细看，直接用的int，导致溢出错误
     * 错误2：
     * left拐点左侧一个和right右侧拐点，都需要直接计算上。第一次算的时候漏了，另外跳出时，应该与他的上一个对比。
     * 错误3：
     * 拐点的匹配问题，要求一定是最近的进行匹配。如果left的右侧还有更近的left，就需要替代。
     */
    public long maxSumTrionicOld(int[] nums) {
        long res = Long.MIN_VALUE;
        Queue<Integer> peek = new LinkedList<>();
        Queue<Integer> bottom = new LinkedList<Integer>();
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1] && nums[i] > nums[i - 1]) {
                peek.add(i);
            }
            if (nums[i] < nums[i + 1] && nums[i] < nums[i - 1]) {
                bottom.add(i);
            }
        }

        while (!peek.isEmpty() && !bottom.isEmpty()) {
            Integer left = peek.poll();
            Integer right = bottom.poll();
            if (left < right) {
                // left已经小于right了，这里是寻找是否还有更近的left
                while (!peek.isEmpty() && peek.peek() < right) {
                    left = peek.poll();
                }
            } else {
                // left都大于right，表明这个right不可用。直接丢弃，用下一个进行匹配。
                while (!bottom.isEmpty() && right < left) {
                    right = bottom.poll();
                }
            }
            long computeMax = computeMax(nums, left, right);
            res = Math.max(res, computeMax);
        }
        return res;
    }

    public long computeMax(int[] nums, int left, int right) {
        if (left >= right) {
            return Long.MIN_VALUE;
        }
        long sumMid = 0;
        for (int i = left; i <= right; i++) {
            sumMid += nums[i];
            if (i > left && nums[i - 1] == nums[i]) {
                return Long.MIN_VALUE;
            }
        }
        long sumLeft = nums[left - 1];
        long tempSum = nums[left - 1];
        for (int i = left - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                tempSum = tempSum + nums[i];
                sumLeft = Math.max(sumLeft, tempSum);
            } else {
                break;
            }
        }
        long sumRight = nums[right + 1];
        tempSum = nums[right + 1];
        for (int i = right + 2; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                tempSum = tempSum + nums[i];
                sumRight = Math.max(sumRight, tempSum);
            } else {
                break;
            }
        }
        return sumMid + sumLeft + sumRight;
    }

    @Override
    public Object initData() {
        return null;
    }
}
