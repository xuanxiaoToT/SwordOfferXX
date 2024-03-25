package com.xx.basicDs.stack.单调栈;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/3/25
 * <p>
 * 股票价格跨度
 * LeetCode 901. Medium
 * <p>
 * 设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。
 * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * 例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。
 * 实现 StockSpanner 类：
 * StockSpanner() 初始化类对象。
 * int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
 * <p>
 * 示例：
 * 输入：
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * 输出：
 * [null, 1, 1, 1, 2, 1, 4, 6]
 * <p>
 * 解释：
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // 返回 1
 * stockSpanner.next(80);  // 返回 1
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(70);  // 返回 2
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(75);  // 返回 4 ，因为截至今天的最后 4 个股价 (包括今天的股价 75) 都小于或等于今天的股价。
 * stockSpanner.next(85);  // 返回 6
 * <p>
 * 提示：
 * 1 <= price <= 10^5
 * 最多调用 next 方法 10^4 次
 */
public class StockSpanner {

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        //List<Integer> list = Arrays.asList(100, 80, 60, 70, 60, 75, 85);
        List<Integer> list = Arrays.asList(31, 41, 48, 59, 79);
        for (Integer i : list) {
            System.out.println(stockSpanner.next(i));
        }
    }

    Stack<Integer> stack;
    Stack<Integer> minHasKnow;

    public StockSpanner() {
        stack = new Stack<>();
        minHasKnow = new Stack<>();
    }

    /**
     * stack:单调栈，单调递减。pop时，表明此栈后方的值都是大于price的，不用再管。
     * minHasKnow:结果保存栈。表明这个值之前还有多少连续小于我的。
     * <p>
     * 思路：构建单调栈时，如果连续小于，则直接累加result。
     * 如果遇到需要退后pop时，则从中间结果表里取值。
     */
    public int next(int price) {
        int result = 0;
        if (stack.isEmpty()) {
            stack.push(price);
            minHasKnow.push(1);
            return 1;
        } else {
            while (!stack.isEmpty()) {
                Integer peek = stack.peek();
                if (price < peek) {
                    stack.add(price);
                    result++;
                    minHasKnow.push(result);
                    break;
                } else {
                    stack.pop();
                    result += minHasKnow.pop();
                }
            }
            if (stack.isEmpty()) {
                stack.push(price);
                minHasKnow.push(result + 1);
                return result + 1;
            }
        }
        return result;
    }

    /**
     * 方法2：参考每日温度的解法
     * 如果把每日的 price 当成数组不同下标的值，即需要求出每个值与上一个更大元素之间的下标之差，这种题目可以用单调栈求解。
     * <p>
     * 此题的具体解法上，栈的元素可以是股票价格的下标（即天数）和股票价格的二元数对，
     * 并且在栈中先插入一个最大值作为天数为 −1 天的价格，来保证栈不会为空。调用 next 时，
     * 先将栈中价格小于等于此时 price 的元素都弹出，直到遇到一个大于 price 的值，并将 price 入栈，计算下标差返回。
     */
    public static class StockSpanner2 {
        Deque<int[]> stack;
        int idx;

        public StockSpanner2() {
            stack = new ArrayDeque<int[]>();
            stack.push(new int[]{-1, Integer.MAX_VALUE});
            idx = -1;
        }

        public int next(int price) {
            idx++;
            while (price >= stack.peek()[1]) {
                stack.pop();
            }
            int ret = idx - stack.peek()[0];
            stack.push(new int[]{idx, price});
            return ret;
        }
    }

}
