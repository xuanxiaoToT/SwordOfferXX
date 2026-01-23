package com.xx.basicDs.stack.单调栈;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * 两个最好的不重叠活动
 * LeetCode 2054 Medium
 * <p>
 * 给你一个下标从 0 开始的二维整数数组 events ，其中 events[i] = [startTimei, endTimei, valuei] 。
 * 第 i 个活动开始于 startTimei ，结束于 endTimei ，如果你参加这个活动，那么你可以得到价值 valuei 。
 * 你 最多 可以参加 两个时间不重叠 活动，使得它们的价值之和 最大 。
 * 请你返回价值之和的 最大值 。
 * 注意，活动的开始时间和结束时间是 包括 在活动时间内的，也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。
 * 更具体的，如果你参加一个活动，且结束时间为 t ，那么下一个活动必须在 t + 1 或之后的时间开始。
 * <p>
 * 示例 1:
 * 输入：events = [[1,3,2],[4,5,2],[2,4,3]]
 * 输出：4
 * 解释：选择绿色的活动 0 和 1 ，价值之和为 2 + 2 = 4 。
 * <p>
 * 示例 2：
 * Example 1 Diagram
 * 输入：events = [[1,3,2],[4,5,2],[1,5,5]]
 * 输出：5
 * 解释：选择活动 2 ，价值和为 5 。
 * <p>
 * 示例 3：
 * 输入：events = [[1,5,3],[1,5,1],[6,6,5]]
 * 输出：8
 * 解释：选择活动 0 和 2 ，价值之和为 3 + 5 = 8 。
 * <p>
 * 提示：
 * 2 <= events.length <= 10^5
 * events[i].length == 3
 * 1 <= startTimei <= endTimei <= 10^9
 * 1 <= valuei <= 10^6
 * <p>
 * Tag:二分查找  TreeSet  单调栈
 */
public class TwoBestNonOverlappingEvents implements Answer {

    public static void main(String[] args) {
        new TwoBestNonOverlappingEvents().answer();
    }

    /**
     * 最简单的，无脑遍历
     * 超时
     */
    public int maxTwoEventsOld(int[][] events) {
        int resultMax = 0;
        // 多层遍历
        for (int i = 0; i < events.length; i++) {
            int[] event = events[i];
            resultMax = Math.max(event[2], resultMax);
            for (int j = 0; j < events.length; j++) {
                if (i == j) {
                    continue;
                }
                int[] eventPre = events[j];
                if (eventPre[1] < event[0]) {
                    resultMax = Math.max(event[2] + eventPre[2], resultMax);
                }
            }
        }
        return resultMax;
    }

    /**
     * 排序 + 单调栈二分
     *
     * 在遍历 events 的过程中（注意 events 已按照结束时间排序），只在遇到更大价值的活动时，才记录该活动。
     */
    public int maxTwoEvents(int[][] events) {
        int resultMax = 0;
        Arrays.sort(events, Comparator.comparing(dto -> dto[1]));

        // (结束时间, 价值)
        // ArrayList<int[]> stack = new ArrayList<>();

        TreeSet<int[]> stackSet = new TreeSet<>(Comparator.comparing(dto -> dto[0]));

        // stackSet.add(new int[]{0, 1});

        // 栈底哨兵
        stackSet.add(new int[]{0, 0});
        for (int i = 0; i < events.length; i++) {
            int[] event = events[i];
            // 寻找结束时间比这个小，并且val最大的。
            int[] floor = stackSet.lower(new int[]{event[0], 0});
            resultMax = Math.max(floor[1] + event[2], resultMax);
            // 遇到比栈顶更大的价值，入栈
            if (event[2] > stackSet.last()[1]) {
                int[] eventTemp = {event[1], event[2]};
                if (stackSet.contains(eventTemp)) {
                    stackSet.remove(eventTemp);
                }
                stackSet.add(eventTemp);
            }
        }
        return resultMax;
    }


    /**
     * stack已经按照结束时间有序，并且价值也有序；
     * <p>
     * 排序后，对比如下两个活动：
     * <p>
     * 活动一：结束于 3 时刻，价值 999。
     * 活动二：结束于 6 时刻，价值 9。
     * 活动二的结束时间又晚，价值又小，全方面不如活动一，是垃圾数据，直接忽略。
     * <p>
     * 换句话说，在遍历 events 的过程中（注意 events 已按照结束时间排序），只在遇到更大价值的活动时，才记录该活动。
     *
     */
    private int searchTopLeft(List<int[]> stack, int[] event) {
        // 利用二分查找，返回最后一个满足 stack[i][0] < target 的 i
        int left = -1, right = stack.size();
        while (left + 1 < right) { // 开区间二分
            int mid = left + (right - left) / 2;
            if (stack.get(mid)[0] < event[0]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    @Override
    public void answer() {
        // int[][] events = new int[][]{{1,3,2},{4,5,2},{2,4,3}};
        int[][] events = new int[][]{{1, 1, 1}, {2, 2, 2}, {1, 1, 2}};
        System.out.println(maxTwoEvents(events));
    }


    @Override
    public Object initData() {
        return null;
    }
}
