package com.xx.basicDs.array.区间类;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2024/4/11
 * <p>
 * 统计将重叠区间合并成组的方案数
 * LeetCode 2580. Medium
 * <p>
 * 给你一个二维整数数组 ranges ，其中 ranges[i] = [starti, endi] 表示 starti 到 endi 之间（包括二者）的所有整数都包含在第 i 个区间中。
 * 你需要将 ranges 分成 两个 组（可以为空），满足：
 * 每个区间只属于一个组。
 * 两个有 交集 的区间必须在 同一个 组内。
 * 如果两个区间有至少 一个 公共整数，那么这两个区间是 有交集 的。
 * 比方说，区间 [1, 3] 和 [2, 5] 有交集，因为 2 和 3 在两个区间中都被包含。
 * 请你返回将 ranges 划分成两个组的 总方案数 。由于答案可能很大，将它对 109 + 7 取余 后返回。
 * <p>
 * 示例 1：
 * 输入：ranges = [[6,10],[5,15]]
 * 输出：2
 * 解释：
 * 两个区间有交集，所以它们必须在同一个组内。
 * 所以有两种方案：
 * - 将两个区间都放在第 1 个组中。
 * - 将两个区间都放在第 2 个组中。
 * <p>
 * 示例 2：
 * 输入：ranges = [[1,3],[10,20],[2,5],[4,8]]
 * 输出：4
 * 解释：
 * 区间 [1,3] 和 [2,5] 有交集，所以它们必须在同一个组中。
 * 同理，区间 [2,5] 和 [4,8] 也有交集，所以它们也必须在同一个组中。
 * 所以总共有 4 种分组方案：
 * - 所有区间都在第 1 组。
 * - 所有区间都在第 2 组。
 * - 区间 [1,3] ，[2,5] 和 [4,8] 在第 1 个组中，[10,20] 在第 2 个组中。
 * - 区间 [1,3] ，[2,5] 和 [4,8] 在第 2 个组中，[10,20] 在第 1 个组中。
 * <p>
 * 提示：
 * 1 <= ranges.length <= 10^5
 * ranges[i].length == 2
 * 0 <= starti <= endi <= 10^9
 *
 * Tag:区间排序  快速幂
 */
public class TheTotalOfMergeOverlappingIntervalsIntoGroup implements Answer {

    public static void main(String[] args) {
        new TheTotalOfMergeOverlappingIntervalsIntoGroup().answer();
    }

    //分为两个问题：找汇并区间、分组
    @Override
    public void answer() {
        // int[][] ranges = new int[][]{{1, 3}, {10, 20}, {2, 5}, {4, 8}};
        //int[][] ranges = new int[][]{{6, 10}, {5, 15}};
        int[][] ranges = {{34, 56}, {28, 29}, {12, 16}, {11, 48}, {28, 54}, {22, 55}, {28, 41}, {41, 44}};
        System.out.println(computerTotal(ranges));
    }

    public int computerTotal(int[][] ranges) {
        int MOD = 1000000007;
        long result = 1;
        Stack<int[]> stack = new Stack<>();
        Arrays.sort(ranges, Comparator.comparingInt(dto -> dto[0]));
        stack.push(ranges[0]);
        for (int i = 1; i < ranges.length; i++) {
            int[] pre = stack.pop();
            int[] node = ranges[i];
            if (node[0] <= pre[1]) {
                stack.push(new int[]{pre[0], Math.max(node[1], pre[1])});
            } else {
                stack.push(pre);
                stack.push(node);
            }
        }
        //long result = ((long) Math.pow(2, stack.size())) % 1000000007;
        // 为什么不是直接计算后取模？奇怪
        for (int i = 0; i < stack.size(); i++) {
            result = (result * 2) % MOD;
        }
        return (int) result;
    }

    /**
     * 利用右端点的最大长度来做
     */
    public int countWays2(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int ans = 1;
        int maxR = -1;
        for (int[] p : ranges) {
            if (p[0] > maxR) { // 无法合并
                ans = ans * 2 % 1_000_000_007; // 新区间
            }
            maxR = Math.max(maxR, p[1]); // 合并
        }
        return ans;
    }

    @Override
    public Object initData() {
        return null;
    }
}
