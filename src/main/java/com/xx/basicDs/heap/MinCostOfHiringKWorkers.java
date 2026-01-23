package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/2
 * <p>
 * 雇佣K名工人的最低成本
 * LeetCode 857. Hard
 * <p>
 * 有 n 名工人。 给定两个数组 quality 和 wage ，其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为 wage[i] 。
 * 现在我们想雇佣 k 名工人组成一个工资组。在雇佣 一组 k 名工人时，我们必须按照下述规则向他们支付工资：
 * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
 * 工资组中的每名工人至少应当得到他们的最低期望工资。
 * 给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额 。在实际答案的 10-5 以内的答案将被接受。。
 * <p>
 * 示例 1：
 * 输入： quality = [10,20,5], wage = [70,50,30], k = 2
 * 输出： 105.00000
 * 解释： 我们向 0 号工人支付 70，向 2 号工人支付 35。
 * <p>
 * 示例 2：
 * 输入： quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * 输出： 30.66667
 * 解释： 我们向 0 号工人支付 4，向 2 号和 3 号分别支付 13.33333。
 * <p>
 * 提示：
 * n == quality.length == wage.length
 * 1 <= k <= n <= 10^4
 * 1 <= quality[i], wage[i] <= 10^4
 */
public class MinCostOfHiringKWorkers implements Answer {

    public static void main(String[] args) {
        new MinCostOfHiringKWorkers().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[] quality = {10, 20, 5};
        int[] wage = {70, 50, 30};
        int k = 2;
        System.out.println(mincostToHireWorkers(quality, wage, k));
    }

    /**
     * 最简单的：O(N2)
     * 即：在最优发工资方案下，至少有一名工人，发给他的工资恰好等于他的最低期望工资。
     * <p>
     * 先假设一组必会被选，且其作为基数(即单位占比的工资数)。
     * 然后按照工作质量占比排序，符合k-1个的一定是本组最小的。
     * 然后把每个组的都算出来，就得到全部的了。
     * todo：如何优化，可以节省遍历？
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double result = Double.MAX_VALUE;
        int[][] qw = new int[quality.length][2];
        for (int i = 0; i < quality.length; i++) {
            qw[i] = new int[]{quality[i], wage[i]};
        }
        Arrays.sort(qw, Comparator.comparing(o -> o[0]));

        for (int i = 0; i < qw.length; i++) {
            double sum = qw[i][1];
            double per = qw[i][1] * 1.0 / qw[i][0];
            int count = k - 1;
            for (int j = 0; j < qw.length && count > 0; j++) {
                if (j != i && per * qw[j][0] >= qw[j][1]) {
                    sum += per * qw[j][0];
                    count--;
                }
            }
            if (count == 0) {
                result = Math.min(sum, result);
            }
        }

        return result;
    }

    /**
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/solutions/1815856/yi-bu-bu-ti-shi-ru-he-si-kao-ci-ti-by-en-1p00/
     * <p>
     * 思路:
     * 若以某人的 ri为基准发工资，那么对于 r 值不超过 ri的工人，发给他们的工资是不低于其最低期望工资的，
     * 因此这些工人是可以随意选择（雇佣）的。
     * 按照 ri从小到大排序，我们便可以快速地求出这些工人。
     * <p>
     * 设这 k名工人的 quality 之和为 sumQ，若以 ri为基准发工资，那么发的工资总额为 sumQ⋅ri，因此 sumQ越小发的工资总额就越小。
     * 因此我们需要在从小到大枚举 ri时，维护当前最小的 k 个 quality值。
     * <p>
     * 用一个最大堆来维护。
     */
    public double mincostToHireWorkers2(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] id = new Integer[n];
        Arrays.setAll(id, i -> i);
        // 按照 r 值排序
        Arrays.sort(id, (i, j) -> wage[i] * quality[j] - wage[j] * quality[i]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int sumQ = 0;
        for (int i = 0; i < k; i++) {
            pq.offer(quality[id[i]]);
            sumQ += quality[id[i]];
        }

        // 选 r 值最小的 k 名工人
        double ans = sumQ * ((double) wage[id[k - 1]] / quality[id[k - 1]]);

        // 后面的工人 r 值更大
        // 但是 sumQ 可以变小，从而可能得到更优的答案
        for (int i = k; i < n; i++) {
            int q = quality[id[i]];
            if (q < pq.peek()) {
                sumQ -= pq.poll() - q;
                pq.offer(q);
                ans = Math.min(ans, sumQ * ((double) wage[id[i]] / q));
            }
        }
        return ans;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
