package com.xx.basicDs.array.双指针;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/17
 * <p>
 * 安排工作以达到最大收益
 * LeetCode  826.  Medium
 * <p>
 * 你有 n 个工作和 m 个工人。给定三个数组： difficulty, profit 和 worker ，其中:
 * difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
 * worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
 * 每个工人 最多 只能安排 一个 工作，但是一个工作可以 完成多次 。
 * <p>
 * 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3 。如果一个工人不能完成任何工作，他的收益为 $0 。
 * 返回 在把工人分配到工作岗位后，我们所能获得的最大利润 。
 * <p>
 * 示例 1：
 * 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * 输出: 100
 * 解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
 * <p>
 * 示例 2:
 * 输入: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
 * 输出: 0
 * <p>
 * 提示:
 * n == difficulty.length
 * n == profit.length
 * m == worker.length
 * 1 <= n, m <= 10^4
 * 1 <= difficulty[i], profit[i], worker[i] <= 10^5
 * <p>
 * Tag: 排序  双指针
 */
public class ArrangeWorkToAchieveMaximumBenefits implements Answer {

    public static void main(String[] args) {
        new ArrangeWorkToAchieveMaximumBenefits().answer();
    }

    @Override
    public void answer() {
        int[] difficult = new int[]{2, 4, 6, 8, 10};
        int[] profit = new int[]{10, 20, 30, 40, 50};
        int[] worker = new int[]{4, 5, 6, 7};
        System.out.println(maxProfitAssignment(difficult, profit, worker));
    }

    /**
     * 双排序
     * 双指针
     * <p>
     * 时间中游
     */
    public int maxProfitAssignment(int[] difficult, int[] profit, int[] workers) {
        int result = 0;
        //首先对difficult去重
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < difficult.length; i++) {
            int diff = difficult[i];
            map.put(diff, Math.max(profit[i], map.getOrDefault(diff, 0)));
        }
        Arrays.sort(workers);
        int index = 0;
        int max = 0;
        Integer[] diffArray = map.keySet().toArray(new Integer[0]);
        for (int worker : workers) {
            while (index < diffArray.length && diffArray[index] <= worker) {
                max = Math.max(max, map.get(diffArray[index]));
                index++;
            }
            result += max;
        }
        return result;
    }

    /**
     * 最简单的，双循环
     * 超时
     */
    public int maxProfitAssignment2(int[] difficult, int[] profit, int[] worker) {
        int result = 0;
        //首先对difficult去重
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < difficult.length; i++) {
            int diff = difficult[i];
            map.put(diff, Math.max(profit[i], map.getOrDefault(diff, 0)));
        }
        for (int j : worker) {
            int max = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (j >= entry.getKey()) {
                    max = Math.max(max, entry.getValue());
                }
            }
            result += max;
        }
        return result;
    }

    /**
     * 牺牲空间换时间的、不用排序（其实本质上还是桶排序）的解法；
     * 先对difficulty去重，得到每个difficulty数值的最大profit
     * 再对每个difficulty，记录<= difficulty的最大profit；
     * <p>
     * 因为difficulty最大10^5
     */
    public int maxProfitAssignment3(int[] difficulty, int[] profit, int[] worker) {
        int[] maxProfit = new int[100001];
        // 对 difficulty 去重，得到每个 difficulty 数值的最大 profit
        for (int i = 0; i < difficulty.length; i++) {
            if (maxProfit[difficulty[i]] < profit[i]) maxProfit[difficulty[i]] = profit[i];
        }
        int max = 0;
        // 对每个 difficulty，记录 <= difficulty 的最大 profit
        for (int i = 0; i < maxProfit.length; i++) {
            if (maxProfit[i] < max) maxProfit[i] = max;
            else max = maxProfit[i];
        }
        int ans = 0;
        for (int i : worker) {
            ans += maxProfit[i];
        }
        return ans;
    }

    @Override
    public Object initData() {
        return null;
    }
}