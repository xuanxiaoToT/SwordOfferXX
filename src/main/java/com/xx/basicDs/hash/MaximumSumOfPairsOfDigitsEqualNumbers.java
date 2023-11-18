package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/18
 * <p>
 * 数位和相等数对的最大和
 * LeetCode 2342. Medium
 * <p>
 * 给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。请你选出两个下标 i 和 j（i != j），
 * 且 nums[i] 的数位和 与  nums[j] 的数位和相等。
 * 请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值 。
 * <p>
 * 示例 1：
 * 输入：nums = [18,43,36,13,7]
 * 输出：54
 * 解释：满足条件的数对 (i, j) 为：
 * - (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
 * - (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
 * 所以可以获得的最大和是 54 。
 * <p>
 * 示例 2：
 * 输入：nums = [10,12,19,14]
 * 输出：-1
 * 解释：不存在满足条件的数对，返回 -1 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * <p>
 * Tag: 哈希Map  堆
 */
public class MaximumSumOfPairsOfDigitsEqualNumbers implements Answer {

    public static void main(String[] args) {
        new MaximumSumOfPairsOfDigitsEqualNumbers().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums = {368, 369, 307, 304, 384, 138, 90, 279, 35, 396, 114, 328, 251, 364, 300, 191, 438, 467, 183};
        System.out.println(maximumSum(nums));
    }

    /**
     * 枚举右，维护左
     * https://leetcode.cn/problems/max-sum-of-a-pair-with-equal-sum-of-digits/solutions/2531487/mei-ju-you-wei-hu-zuo-pythonjavacgojsrus-eoys/?envType=daily-question&envId=2023-11-18
     */
    public int maximumSum2(int[] nums) {
        int ans = -1;
        int[] mx = new int[82]; // 至多 9 个 9 相加
        for (int num : nums) {
            int s = computeSumByDigit(num); // num 的数位和
            if (mx[s] > 0) { // 说明左边也有数位和等于 s 的元素
                ans = Math.max(ans, mx[s] + num); // 更新答案的最大值
            }
            mx[s] = Math.max(mx[s], num); // 维护数位和等于 s 的最大元素
        }
        return ans;
    }


    /**
     * PriorityQueue<Integer> 太过笨重
     */
    public int maximumSum(int[] nums) {
        int maxSum = -1;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int sumByDigit = computeSumByDigit(num);
            if (map.containsKey(sumByDigit)) {
                heapAdd(map.get(sumByDigit), num, 2);
            } else {
                PriorityQueue<Integer> heap = new PriorityQueue<>(2);
                heapAdd(heap, num, 2);
                map.put(sumByDigit, heap);
            }
        }
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 2) {
                int maxValue = entry.getValue().poll() + entry.getValue().poll();
                maxSum = Math.max(maxValue, maxSum);
            }
        }
        return maxSum;
    }

    private int computeSumByDigit(int num) {
        int sum = 0;
        while (num > 0) {
            sum = sum + (num % 10);
            num = num / 10;
        }
        return sum;
    }

    private void heapAdd(PriorityQueue<Integer> heap, int num, int k) {
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
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
