package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.PriorityQueue;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/19
 * <p>
 * 雇佣K位工人的总代价
 * LeetCode 2462. Medium
 * <p>
 * 给你一个下标从 0 开始的整数数组 costs ，其中 costs[i] 是雇佣第 i 位工人的代价。
 * 同时给你两个整数 k 和 candidates 。我们想根据以下规则恰好雇佣 k 位工人：
 * -总共进行 k 轮雇佣，且每一轮恰好雇佣一位工人。
 * -在每一轮雇佣中，从最前面 candidates 和最后面 candidates 人中选出代价最小的一位工人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * --比方说，costs = [3,2,7,7,1,2] 且 candidates = 2 ，第一轮雇佣中，我们选择第 4 位工人，因为他的代价最小 [3,2,7,7,1,2] 。
 * --第二轮雇佣，我们选择第 1 位工人，因为他们的代价与第 4 位工人一样都是最小代价，而且下标更小，[3,2,7,7,2] 。注意每一轮雇佣后，剩余工人的下标可能会发生变化。
 * -如果剩余员工数目不足 candidates 人，那么下一轮雇佣他们中代价最小的一人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * -一位工人只能被选择一次。
 * 返回雇佣恰好 k 位工人的总代价。
 * <p>
 * <p>
 * 示例 1：
 * 输入：costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
 * 输出：11
 * 解释：我们总共雇佣 3 位工人。总代价一开始为 0 。
 * - 第一轮雇佣，我们从 [17,12,10,2,7,2,11,20,8] 中选择。最小代价是 2 ，有两位工人，我们选择下标更小的一位工人，即第 3 位工人。总代价是 0 + 2 = 2 。
 * - 第二轮雇佣，我们从 [17,12,10,7,2,11,20,8] 中选择。最小代价是 2 ，下标为 4 ，总代价是 2 + 2 = 4 。
 * - 第三轮雇佣，我们从 [17,12,10,7,11,20,8] 中选择，最小代价是 7 ，下标为 3 ，总代价是 4 + 7 = 11 。注意下标为 3 的工人同时在最前面和最后面 4 位工人中。
 * 总雇佣代价是 11 。
 * <p>
 * 示例 2：
 * 输入：costs = [1,2,4,1], k = 3, candidates = 3
 * 输出：4
 * 解释：我们总共雇佣 3 位工人。总代价一开始为 0 。
 * - 第一轮雇佣，我们从 [1,2,4,1] 中选择。最小代价为 1 ，有两位工人，我们选择下标更小的一位工人，即第 0 位工人，总代价是 0 + 1 = 1 。注意，下标为 1 和 2 的工人同时在最前面和最后面 3 位工人中。
 * - 第二轮雇佣，我们从 [2,4,1] 中选择。最小代价为 1 ，下标为 2 ，总代价是 1 + 1 = 2 。
 * - 第三轮雇佣，少于 3 位工人，我们从剩余工人 [2,4] 中选择。最小代价是 2 ，下标为 0 。总代价为 2 + 2 = 4 。
 * 总雇佣代价是 4 。
 * <p>
 * 提示：
 * 1 <= costs.length <= 10^5
 * 1 <= costs[i] <= 10^5
 * 1 <= k, candidates <= costs.length
 * <p>
 * Tag:堆
 */
public class TheTotalCostOfHiringWorkers implements Answer {

    public static void main(String[] args) {
        new TheTotalCostOfHiringWorkers().answer();
    }

    @Override
    public void answer() {
        // int[] costs = {17, 12, 10, 2, 7, 2, 11, 20, 8};
        int[] costs = {1, 5, 7, 4, 9, 2, 3, 6};
        System.out.println(computeTotalCost(costs, 3, 3));
    }

    /**
     * 前candidates个元素和后candidates个元素，作为两个堆进行处理。
     * 每次各自吐出一个最小的，然后再往里添加元素
     */
    public long computeTotalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> preHeap = new PriorityQueue<>();
        PriorityQueue<Integer> afterHeap = new PriorityQueue<>();
        for (int i = 0; i < candidates; i++) {
            preHeap.add(costs[i]);
        }
        int left = candidates - 1;
        int right = Math.max(costs.length - candidates, candidates);
        for (int i = costs.length - 1; i >= right; i--) {
            afterHeap.add(costs[i]);
        }
        long sumCost = 0;
        for (int i = 0; i < k; i++) {
            Integer preVal = preHeap.peek();
            Integer aftVal = afterHeap.peek();
            if (preVal != null && aftVal != null) {
                if (preVal <= aftVal) {
                    sumCost += preVal;
                    preHeap.poll();
                    left++;
                    if (left < right) {
                        preHeap.add(costs[left]);
                    }
                } else {
                    sumCost += aftVal;
                    afterHeap.poll();
                    right--;
                    if (left < right) {
                        afterHeap.add(costs[right]);
                    }
                }
            } else {
                if (preVal != null) {
                    sumCost += preVal;
                    preHeap.poll();
                }
                if (aftVal != null) {
                    sumCost += aftVal;
                    afterHeap.poll();
                }
            }
        }
        return sumCost;
    }

    @Override
    public Object initData() {
        return null;
    }
}
