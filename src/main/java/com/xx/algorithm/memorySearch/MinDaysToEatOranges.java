package com.xx.algorithm.memorySearch;

import com.xx.Answer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/12
 * <p>
 * 吃掉N个橘子的最少天数
 * LeetCode 1553. Hard
 * <p>
 * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
 * 吃掉一个橘子。
 * 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
 * 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
 * 每天你只能从以上 3 种方案中选择一种方案。
 * 请你返回吃掉所有 n 个橘子的最少天数。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：你总共有 10 个橘子。
 * 第 1 天：吃 1 个橘子，剩余橘子数 10 - 1 = 9。
 * 第 2 天：吃 6 个橘子，剩余橘子数 9 - 2*(9/3) = 9 - 6 = 3。（9 可以被 3 整除）
 * 第 3 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。
 * 第 4 天：吃掉最后 1 个橘子，剩余橘子数 1 - 1 = 0。
 * 你需要至少 4 天吃掉 10 个橘子。
 * <p>
 * 示例 2：
 * 输入：n = 6
 * 输出：3
 * 解释：你总共有 6 个橘子。
 * 第 1 天：吃 3 个橘子，剩余橘子数 6 - 6/2 = 6 - 3 = 3。（6 可以被 2 整除）
 * 第 2 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。（3 可以被 3 整除）
 * 第 3 天：吃掉剩余 1 个橘子，剩余橘子数 1 - 1 = 0。
 * 你至少需要 3 天吃掉 6 个橘子。
 * <p>
 * 示例 3：
 * 输入：n = 1
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：n = 56
 * 输出：6
 * <p>
 * 提示：
 * 1 <= n <= 2*10^9
 * <p>
 * Tag:记忆话搜索  贪心  数学
 */
public class MinDaysToEatOranges implements Answer {
    public static void main(String[] args) {
        new MinDaysToEatOranges().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        System.out.println(minDays3(5126970));
    }

    /**
     * 递归加减枝
     * 理所当然，超时。因为不需要明细
     */
    public int minDays(int n) {
        result = n;
        myDiGui(n, 0);
        return result;
    }

    /**
     * 分析后发现其类似于一个三叉树，本质上是求最大深度，可以直接用树的遍历来做
     * 还是超时。
     * 因为size后续会膨胀的很大.
     * <p>
     * 前期是不是没必要-1，因为肯定很少？
     */
    public int minDays2(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                if (poll <= 0) {
                    return depth;
                }
                queue.add(poll - 1);
                if (poll % 2 == 0) {
                    queue.add(poll - poll / 2);
                }
                if (poll % 3 == 0) {
                    queue.add(poll - 2 * (poll / 3));
                }
            }
            depth++;
        }
        return depth;
    }


    private final Map<Integer, Integer> memo = new HashMap<>();

    /**
     * 记忆化搜索，起关键思想就是把之前的结果存起来
     * <p>
     * 推理可得：在最优操作序列中，如果第一个不是「减 1」的操作，而是「除以 2」，
     * 那么当 n 是偶数时，第一个操作一定是「除以 2」，当 n 是奇数时，前两个操作一定是「减 1，除以 2」。
     * <p>
     * 同理可证明，在最优操作序列中，如果第一个不是「减 1」的操作是「除以 3」，
     * 那么一定会先执行 n mod 3 次「减 1」操作，把 n 变成 3 的倍数，然后再执行「除以 3」。
     * <p>
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/minimum-number-of-days-to-eat-n-oranges/solutions/2773476/liang-chong-fang-fa-ji-yi-hua-sou-suo-zu-18jv/
     * 来源：力扣（LeetCode）
     */
    public int minDays3(int n) {
        if (n <= 1) {
            return n;
        }
        Integer res = memo.get(n);
        if (res != null) { // 之前计算过
            return res;
        }
        res = Math.min(minDays3(n / 2) + n % 2, minDays3(n / 3) + n % 3) + 1;
        memo.put(n, res); // 记忆化
        return res;
    }


    int result;

    private void myDiGui(int remainder, int index) {
        if (remainder <= 0) {
            result = Math.min(result, index);
            return;
        }
        //选第一种
        myDiGui(remainder - 1, index + 1);
        if (remainder % 2 == 0) {
            myDiGui(remainder - remainder / 2, index + 1);
        }
        if (remainder % 3 == 0) {
            myDiGui(remainder - 2 * (remainder / 3), index + 1);
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
