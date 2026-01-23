package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/25
 * <p>
 * 加油站
 * LeetCode 134. Medium
 * <p>
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * <p>
 * 示例 1:
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * <p>
 * 示例 2:
 * 输入: gas = [2,3,4], cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * <p>
 * 提示:
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 105
 * 0 <= gas[i], cost[i] <= 104
 */
public class GasStation implements Answer {
    public static void main(String[] args) {
        new GasStation().answer();
    }

    @Override
    public void answer() {
        int[][] data = initData();
        int[] gas = data[0];
        int[] cost = data[1];
        // =0还有解的唯一情况，就是仅有一个的情况
        if (gas.length == 1 && cost.length == 1 && gas[0] - cost[0] >= 0) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < gas.length; i++) {
            int temp = gas[i] - cost[i];
            //如果存在解，则一定是唯一的。则只用考虑>0的情况，0的情况则直接略过。这表明因为0对前进没有任何影响。
            if (temp > 0) {
                if (valid(gas, cost, i)) {
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private boolean valid(int[] gas, int[] cost, int start) {
        int index = start;
        int sum = gas[index] - cost[index];
        do {
            index++;
            if (index >= gas.length) {
                index = 0;
            }
            if (index == start) {
                return true;
            }
            sum = sum + gas[index] - cost[index];
            if (sum < 0) {
                return false;
            }
        } while (true);
    }

    /**
     * https://leetcode.cn/problems/gas-station/solutions/488357/jia-you-zhan-by-leetcode-solution/?envType=study-plan-v2&envId=top-interview-150
     * <p>
     * 从 x,y 之间的任何一个加油站出发，都无法到达加油站 y 的下一个加油站。
     * 在发现了这一个性质后，算法就很清楚了：我们首先检查第 0 个加油站，并试图判断能否环绕一周；如果不能，就从第一个无法到达的加油站开始继续检查。
     * <p>
     * 直观理解，不用公式推导。可以这样想：假设从x加油站出发经过z加油站最远能到达y加油站，
     * 那么从z加油站直接出发，不可能到达y下一个加油站。因为从x出发到z加油站时肯定还有存储的油，这都到不了y的下一站，而直接从z出发刚开始是没有存储的油的，
     * 所以更不可能到达y的下一站。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    /**
     * 利用贪心算法
     * 因为如果存在解，则保证它是唯一的，所以一定是差值的最小前缀和的下一站。
     * 如果总差值小于零，说明总油量不够行驶一周无解。
     */
    public int answerTwo(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        int min = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            if (sum < min) {
                min = sum;
                idx = i + 1;
            }
        }
        return sum < 0 ? -1 : idx;
    }


    @Override
    public int[][] initData() {
        // return new int[][]{{1, 2, 3, 4, 5}, {3, 4, 5, 1, 2}};
        //return new int[][]{{2, 3, 4}, {3, 4, 3}};
        return new int[][]{{0, 0, 1}, {0, 0, 1}};
    }
}