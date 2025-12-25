package com.xx.algorithm.greedy;

import com.xx.Answer;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 幸福值最大化的选择方案
 * LeetCode 3075. Medium
 * <p>
 * 给你一个长度为 n 的数组 happiness ，以及一个 正整数 k 。
 * n 个孩子站成一队，其中第 i 个孩子的 幸福值 是 happiness[i] 。你计划组织 k 轮筛选从这 n 个孩子中选出 k 个孩子。
 * 在每一轮选择一个孩子时，所有 尚未 被选中的孩子的 幸福值 将减少 1 。注意，幸福值 不能 变成负数，且只有在它是正数的情况下才会减少。
 * 选择 k 个孩子，并使你选中的孩子幸福值之和最大，返回你能够得到的 最大值 。
 * <p>
 * 示例 1：
 * 输入：happiness = [1,2,3], k = 2
 * 输出：4
 * 解释：按以下方式选择 2 个孩子：
 * - 选择幸福值为 3 的孩子。剩余孩子的幸福值变为 [0,1] 。
 * - 选择幸福值为 1 的孩子。剩余孩子的幸福值变为 [0] 。注意幸福值不能小于 0 。
 * 所选孩子的幸福值之和为 3 + 1 = 4 。
 * <p>
 * 示例 2：
 * 输入：happiness = [1,1,1,1], k = 2
 * 输出：1
 * 解释：按以下方式选择 2 个孩子：
 * - 选择幸福值为 1 的任意一个孩子。剩余孩子的幸福值变为 [0,0,0] 。
 * - 选择幸福值为 0 的孩子。剩余孩子的幸福值变为 [0,0] 。
 * 所选孩子的幸福值之和为 1 + 0 = 1 。
 * <p>
 * 示例 3：
 * 输入：happiness = [2,3,4,5], k = 1
 * 输出：5
 * 解释：按以下方式选择 1 个孩子：
 * - 选择幸福值为 5 的孩子。剩余孩子的幸福值变为 [1,2,3] 。
 * 所选孩子的幸福值之和为 5 。
 * <p>
 * 提示：
 * 1 <= n == happiness.length <= 2 * 105
 * 1 <= happiness[i] <= 108
 * 1 <= k <= n
 * <p>
 * Tag：贪心  排序  最小堆
 */
public class MaximizeHappinessOfSelectedChildren implements Answer {
    public static void main(String[] args) {
        new MaximizeHappinessOfSelectedChildren().answerOne();
    }

    @Override
    public void answerOne() {
        int[] happiness = new int[]{1, 2, 3};
        int k = 2;

        // int[] happiness = new int[]{1, 1, 1, 1};
        // int k = 2;

        // int[] happiness = new int[]{2, 3, 4, 5};
        // int k = 3;
        System.out.println(maximumHappinessSum(happiness, k));
    }

    /**
     * 排序加 贪心
     */
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long result = 0;
        int j = 0;
        for (int i = happiness.length - 1; i >= happiness.length - k; i--) {
            if (happiness[i] - j > 0) {
                result = result + happiness[i] - j;
            } else {
                return result;
            }
            j++;
        }
        return result;
    }

    /**
     * 最小堆法
     */
    public long maximumHappinessSumOld(int[] happiness, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < happiness.length; i++) {
            putHeap(minHeap, happiness[i], k);
        }
        long result = 0;
        for (int i = k - 1; i >= 0; i--) {
            Integer poll = minHeap.poll();
            if (poll > i) {
                result = result + poll - i;
            }
        }

        return result;
    }

    private void putHeap(PriorityQueue<Integer> minHeap, Integer val, int k) {
        if (minHeap.size() < k) {
            minHeap.add(val);
        } else {
            if (val > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(val);
            }
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}
