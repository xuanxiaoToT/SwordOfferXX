package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 移山所需的最少秒数
 * LeetCode 3296. Medium+
 * <p>
 * 给你一个整数 mountainHeight 表示山的高度。
 * 同时给你一个整数数组 workerTimes，表示工人们的工作时间（单位：秒）。
 * 工人们需要 同时 进行工作以 降低 山的高度。对于工人 i :
 * 山的高度降低 x，需要花费 workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x 秒。例如：
 * 山的高度降低 1，需要 workerTimes[i] 秒。
 * 山的高度降低 2，需要 workerTimes[i] + workerTimes[i] * 2 秒，依此类推。
 * 返回一个整数，表示工人们使山的高度降低到 0 所需的 最少 秒数。
 * <p>
 * 示例 1：
 * 输入： mountainHeight = 4, workerTimes = [2,1,1]
 * 输出： 3
 * 解释：
 * 将山的高度降低到 0 的一种方式是：
 * 工人 0 将高度降低 1，花费 workerTimes[0] = 2 秒。
 * 工人 1 将高度降低 2，花费 workerTimes[1] + workerTimes[1] * 2 = 3 秒。
 * 工人 2 将高度降低 1，花费 workerTimes[2] = 1 秒。
 * 因为工人同时工作，所需的最少时间为 max(2, 3, 1) = 3 秒。
 * <p>
 * 示例 2：
 * 输入： mountainHeight = 10, workerTimes = [3,2,2,4]
 * 输出： 12
 * 解释：
 * 工人 0 将高度降低 2，花费 workerTimes[0] + workerTimes[0] * 2 = 9 秒。
 * 工人 1 将高度降低 3，花费 workerTimes[1] + workerTimes[1] * 2 + workerTimes[1] * 3 = 12 秒。
 * 工人 2 将高度降低 3，花费 workerTimes[2] + workerTimes[2] * 2 + workerTimes[2] * 3 = 12 秒。
 * 工人 3 将高度降低 2，花费 workerTimes[3] + workerTimes[3] * 2 = 12 秒。
 * 所需的最少时间为 max(9, 12, 12, 12) = 12 秒。
 * <p>
 * 示例 3：
 * 输入： mountainHeight = 5, workerTimes = [1]
 * 输出： 15
 * <p>
 * 解释：
 * 这个示例中只有一个工人，所以答案是 workerTimes[0] + workerTimes[0] * 2 + workerTimes[0] * 3 + workerTimes[0] * 4 + workerTimes[0] * 5 = 15 秒。
 * <p>
 * 提示：
 * 1 <= mountainHeight <= 10^5
 * 1 <= workerTimes.length <= 10^4
 * 1 <= workerTimes[i] <= 10^6
 *
 * Tag: 堆模拟
 */
public class MinimumNumberOfSecondsToMakeMountainHeightZero implements Answer {
    public static void main(String[] args) {
        new MinimumNumberOfSecondsToMakeMountainHeightZero().answer();
    }

    @Override
    public void answer() {
        // int mountainHeight = 10;
        // int[] workerTimes = {3, 2, 2, 4};

        int mountainHeight = 5;
        int[] workerTimes = {1, 5};
        System.out.println(minNumberOfSeconds(mountainHeight, workerTimes));
    }

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int length = workerTimes.length;
        long res = 0;

        PriorityQueue<long[]> minHeap = new PriorityQueue<>(Comparator.comparing(dto -> dto[0]));
        for (int t : workerTimes) {
            minHeap.offer(new long[]{t, t, t});
        }

        long ans = 0;
        while (mountainHeight-- > 0) {
            // 工作后总用时，当前工作（山高度降低 1）用时，workerTimes[i]
            long[] top = minHeap.poll();
            long total = top[0], cur = top[1], base = top[2];
            ans = total; // 最后一个出堆的 total 即为答案
            minHeap.offer(new long[]{total + cur + base, cur + base, base});
        }
        return ans;
    }

    @Override
    public Object initData() {
        return null;
    }
}
