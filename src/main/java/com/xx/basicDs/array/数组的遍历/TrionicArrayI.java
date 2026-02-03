package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

/**
 * 三段式数组I
 * LeetCode 3637. Easy
 * <p>
 * 给你一个长度为 n 的整数数组 nums。
 * 如果存在索引 0 < p < q < n − 1，使得数组满足以下条件，则称其为 三段式数组（trionic）：
 * nums[0...p] 严格 递增，
 * nums[p...q] 严格 递减，
 * nums[q...n − 1] 严格 递增。
 * 如果 nums 是三段式数组，返回 true；否则，返回 false。
 * <p>
 * 示例 1:
 * 输入: nums = [1,3,5,4,2,6]
 * 输出: true
 * 解释:
 * 选择 p = 2, q = 4：
 * nums[0...2] = [1, 3, 5] 严格递增 (1 < 3 < 5)。
 * nums[2...4] = [5, 4, 2] 严格递减 (5 > 4 > 2)。
 * nums[4...5] = [2, 6] 严格递增 (2 < 6)。
 * <p>
 * 示例 2:
 * 输入: nums = [2,1,3]
 * 输出: false
 * 解释:
 * 无法选出能使数组满足三段式要求的 p 和 q 。
 * <p>
 * 提示:
 * 3 <= n <= 100
 * -1000 <= nums[i] <= 1000
 * <p>
 * Tag：数组  排序
 */
public class TrionicArrayI implements Answer {
    public static void main(String[] args) {
        new TrionicArrayI().answer();
    }

    @Override
    public void answer() {
        // int[] nums = {1, 3, 5, 4, 2, 6};
        // int[] nums = {1, 3, 5, 4, 2, 6, 4};
        // int[] nums = {5, 4, 3, 2, 1};
        int[] nums = {1, 2, 4, 5, 4, 3, 2, 1, 2, 4, 5};
        System.out.println(isTrionic2(nums));
    }

    /**
     * 如果题目改成「增减增减增」，难道要写五个循环吗？
     * <p>
     * 计算拐点的数目即可
     */
    public boolean isTrionic(int[] nums) {
        // 一开始必须是递增的
        if (nums[0] >= nums[1]) {
            return false;
        }
        int cnt = 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                return false;
            }
            // 此点的左右趋势是否相等。
            if ((nums[i - 2] < nums[i - 1]) != (nums[i - 1] < nums[i])) {
                cnt++;
            }
        }
        // 一定是增减增
        return cnt == 3;
    }

    /**
     * 计算拐点的数目即可
     */
    public boolean isTrionic2(int[] nums) {
        // 一开始必须是递增的
        if (nums[0] >= nums[1]) {
            return false;
        }
        int cnt = 0;
        int last = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (last == nums[i]) {
                return false;
            }
            if (cnt % 2 == 0) {
                if (last > nums[i]) {
                    cnt++;
                }
            } else {
                if (last < nums[i]) {
                    cnt++;
                }
            }
            last = nums[i];
        }
        return cnt == 2;
    }

    /**
     * 按部就班模拟
     */
    public boolean isTrionicOld(int[] nums) {
        if (nums.length == 3) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        int last = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == last) {
                return false;
            }
            if (last > nums[i]) {
                left = i - 1;
                break;
            }
            last = nums[i];
        }
        last = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] == last) {
                return false;
            }
            if (last < nums[i]) {
                right = i + 1;
                break;
            }
            last = nums[i];
        }
        if (left == right || left == 0 || right == nums.length - 1) {
            return false;
        }
        last = nums[left];
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > last) {
                return false;
            }
            last = nums[i];
        }
        return true;
    }

    @Override
    public Object initData() {
        return null;
    }
}
