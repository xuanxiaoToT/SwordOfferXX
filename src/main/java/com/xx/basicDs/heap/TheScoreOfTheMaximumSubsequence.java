package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/19
 * <p>
 * 最大子序列的分数
 * LeetCode 2542. Medium
 * <p>
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两者长度都是 n ，再给你一个正整数 k 。
 * 你必须从 nums1 中选一个长度为 k 的 子序列 对应的下标。
 * <p>
 * 对于选择的下标 i0 ，i1 ，...， ik - 1 ，你的 分数 定义如下：
 * nums1 中下标对应元素求和，乘以 nums2 中下标对应元素的 最小值 。
 * 用公式表示： (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]) 。
 * 请你返回 最大 可能的分数。
 * 一个数组的 子序列 下标是集合 {0, 1, ..., n-1} 中删除若干元素得到的剩余集合，也可以不删除任何元素。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
 * 输出：12
 * 解释：
 * 四个可能的子序列分数为：
 * - 选择下标 0 ，1 和 2 ，得到分数 (1+3+3) * min(2,1,3) = 7 。
 * - 选择下标 0 ，1 和 3 ，得到分数 (1+3+2) * min(2,1,4) = 6 。
 * - 选择下标 0 ，2 和 3 ，得到分数 (1+3+2) * min(2,3,4) = 12 。
 * - 选择下标 1 ，2 和 3 ，得到分数 (3+3+2) * min(1,3,4) = 8 。
 * 所以最大分数为 12 。
 * <p>
 * 示例 2：
 * 输入：nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
 * 输出：30
 * 解释：
 * 选择下标 2 最优：nums1[2] * nums2[2] = 3 * 10 = 30 是最大可能分数。
 * <p>
 * 提示：
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[j] <= 10^5
 * 1 <= k <= n
 */
public class TheScoreOfTheMaximumSubsequence implements Answer {

    public static void main(String[] args) {
        new TheScoreOfTheMaximumSubsequence().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums1 = {2, 1, 14, 12};
        int[] nums2 = {11, 7, 13, 6};
        int k = 3;
        System.out.println(computeMaxScore2(nums1, nums2, k));
    }

    /**
     * 超时！
     */
    public long computeMaxScore(int[] nums1, int[] nums2, int k) {
        List<int[]> min2 = new ArrayList<>();
        List<int[]> max1 = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            max1.add(new int[]{nums1[i], i});
            min2.add(new int[]{nums2[i], i});
        }
        max1.sort(Comparator.comparingInt(dto -> dto[0]));
        min2.sort(Comparator.comparingInt(dto -> dto[0]));
        Set<Integer> hasVis = new HashSet<>();
        long maxResult = Integer.MIN_VALUE;
        for (int i = 0; i <= min2.size() - k; i++) {
            int min = min2.get(i)[0];
            int index = min2.get(i)[1];
            long sum = 0;
            int count = 0;
            for (int j = max1.size() - 1; j >= 0; j--) {
                int[] maxTemp = max1.get(j);
                if (k == 1) {
                    sum += nums1[index];
                    break;
                } else {
                    if (maxTemp[1] != index && !hasVis.contains(maxTemp[1])) {
                        count++;
                        sum += maxTemp[0];
                    }
                    if (count == k - 1) {
                        sum += nums1[index];
                        break;
                    }
                }
            }
            hasVis.add(index);
            maxResult = Math.max((long) sum * min, maxResult);
        }
        return maxResult;
    }

    /**
     * 2选最小 1选最大
     * <p>
     * 难点：
     * 1.要想到排序，2选最小，排序完之后就可以按顺序遍历，只用考虑nums2了。
     * 2.nums2里面要选k-1个最大值，此时需要想到堆。
     * 3.堆里仅需要保留k-1个，而不是每次全丢进去然后选出k-1个。则此时应该用最小堆。
     * 4.逆序遍历nums2可以只用往堆中放一次。
     * 5.往堆中放元素的同时进行sum值的计算。
     */
    public long computeMaxScore2(int[] nums1, int[] nums2, int k) {
        long result = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int length = nums1.length;
        int[][] nums = new int[nums2.length][2];
        for (int i = 0; i < length; i++) {
            nums[i] = new int[]{nums1[i], nums2[i]};
        }
        //2选最小
        Arrays.sort(nums, Comparator.comparing(dto -> dto[1]));
        long preSum = 0;
        for (int i = 0; i < k - 1; i++) {
            int[] node = nums[nums.length - i - 1];
            preSum = putVal(minHeap, node[0], k - 1, preSum);
        }

        for (int i = nums.length - k; i >= 0; i--) {
            int[] node = nums[i];
            int min = node[1];
            long sum = node[0];
            sum = preSum + sum;
            result = Math.max(result, (long) min * sum);
            preSum = putVal(minHeap, node[0], k - 1, preSum);
        }
        return result;
    }

    private long putVal(PriorityQueue<Integer> minHeap, int val, int capacity, long sum) {
        if (capacity == 0) {
            return 0;
        }
        if (minHeap.size() < capacity) {
            minHeap.add(val);
            return sum + val;
        } else {
            if (!minHeap.isEmpty() && val > minHeap.peek()) {
                minHeap.add(val);
                Integer poll = minHeap.poll();
                return sum + val - poll;
            }
        }
        return sum;
    }

    @Override
    public Object initData() {
        return null;
    }
}