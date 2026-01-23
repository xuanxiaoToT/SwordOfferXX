package com.xx.basicDs.array.区间类;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author XuanXiao
 * @CreateDate 2024/3/4
 * <p>
 * 无重叠区间
 * LeetCode  435. Medium
 * <p>
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * <p>
 * 示例 1:
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * <p>
 * 示例 2:
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * <p>
 * 示例 3:
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * <p>
 * 提示:
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 * <p>
 * Tag: 区间  贪心
 */
public class NoOverlappingInterval implements Answer {
    public static void main(String[] args) {
        new NoOverlappingInterval().answer();
    }

    @Override
    public void answer() {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3},};
        System.out.println(computeResult(intervals));
    }

    /**
     * 难点一：一看题就有感觉需要排序，但究竟怎么排序，按左边界排还是右边界排。
     * 难点二：排完序之后如何遍历，如果没有分析好遍历顺序，那么排序就没有意义了。
     * 难点三：直接求重复的区间是复杂的，转而求最大非重复区间个数。
     * 难点四：求最大非重复区间个数时，需要一个分割点来做标记。
     */
    public int computeResult(int[][] intervals) {
        if (intervals.length == 1) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int noOverCount = 1;
        int last = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] lastNode = intervals[last];
            int[] node = intervals[i];
            int thisLeft = node[0];
            //不重叠
            if (thisLeft >= lastNode[1]) {
                last = i;
                noOverCount++;
            }
        }
        return intervals.length - noOverCount;
    }

    @Override
    public Object initData() {
        return null;
    }
}