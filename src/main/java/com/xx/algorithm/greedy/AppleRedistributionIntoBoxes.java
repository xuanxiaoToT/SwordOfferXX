package com.xx.algorithm.greedy;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 重新分装苹果
 * LeetCode 3074 Easy
 * <p>
 * 给你一个长度为 n 的数组 apple 和另一个长度为 m 的数组 capacity 。
 * 一共有 n 个包裹，其中第 i 个包裹中装着 apple[i] 个苹果。同时，还有 m 个箱子，第 i 个箱子的容量为 capacity[i] 个苹果。
 * 请你选择一些箱子来将这 n 个包裹中的苹果重新分装到箱子中，返回你需要选择的箱子的 最小 数量。
 * 注意，同一个包裹中的苹果可以分装到不同的箱子中。
 * <p>
 * 示例 1：
 * 输入：apple = [1,3,2], capacity = [4,3,1,5,2]
 * 输出：2
 * 解释：使用容量为 4 和 5 的箱子。
 * 总容量大于或等于苹果的总数，所以可以完成重新分装。
 * <p>
 * 示例 2：
 * 输入：apple = [5,5,5], capacity = [2,4,2,7]
 * 输出：4
 * 解释：需要使用所有箱子。
 * <p>
 * 提示：
 * 1 <= n == apple.length <= 50
 * 1 <= m == capacity.length <= 50
 * 1 <= apple[i], capacity[i] <= 50
 * 输入数据保证可以将包裹中的苹果重新分装到箱子中。
 * <p>
 * Tag：贪心  排序  最大堆
 */
public class AppleRedistributionIntoBoxes implements Answer {
    public static void main(String[] args) {
        new AppleRedistributionIntoBoxes().answer();
    }

    @Override
    public void answer() {
        // int[] apple = new int[]{1,3,2};
        // int[] capacity = new int[]{4,3,1,5,2};

        int[] apple = new int[]{5, 5, 5};
        int[] capacity = new int[]{2, 4, 2, 7};
        System.out.println(minimumBoxes(apple, capacity));
    }

    /**
     * 简单排序
     */
    public int minimumBoxes(int[] apple, int[] capacity) {
        int sum = Arrays.stream(apple).sum();
        Arrays.sort(capacity);
        int result = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            int thisCapacity = capacity[i];
            if (sum > 0) {
                result++;
            }
            sum = sum - thisCapacity;
        }
        return result;
    }

    /**
     * 最大堆
     */
    public int minimumBoxes2(int[] apple, int[] capacity) {
        int sum = Arrays.stream(apple).sum();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int cap : capacity) {
            maxHeap.add(cap);
        }
        int result = 0;
        while (sum > 0) {
            Integer poll = maxHeap.poll();
            sum = sum - poll;
            result++;
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
