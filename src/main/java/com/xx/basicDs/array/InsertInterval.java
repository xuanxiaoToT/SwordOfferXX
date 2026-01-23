package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/16
 * <p>
 * 插入区间
 * LeetCode 57. Medium
 * <p>
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 示例 3：
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * <p>
 * 示例 4：
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * <p>
 * 示例 5：
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 * <p>
 * 提示：
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 10^5
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 10^5
 */
public class InsertInterval implements Answer {

    public static void main(String[] args) {
        new InsertInterval().answer();
    }

    /**
     * 此题同合并区间
     */
    @Override
    public void answer() {
        int[] newInterval = new int[]{7, 11};
        int[][] data = initData();
        int[][] result = new int[data.length + 1][2];
        // 确认插入位置,并使用result作为临时存储
        boolean addFlag = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if (i == 0 && !addFlag) {
                result[i] = newInterval;
                break;
            }
            int[] tempNode = addFlag ? data[i] : data[i - 1];
            if (newInterval[0] >= tempNode[0]) {
                if (addFlag) {
                    result[i] = data[i];
                } else {
                    result[i] = newInterval;
                    addFlag = true;
                }
            } else {
                result[i] = data[i - 1];
            }
        }

        System.out.println(Arrays.deepToString(result));

        // 结果参考-区间合并
        int index = 0;
        for (int i = 1; i < result.length; i++) {
            int[] cur = result[i];
            int curLeft = cur[0];
            int[] last = result[index];
            int lastRight = last[1];
            if (curLeft > lastRight) {
                index++;
                result[index] = cur;
            } else {
                last[1] = Math.max(cur[1], last[1]);
                result[index] = last;
            }
        }
        int[][] result2 = new int[index + 1][2];
        System.arraycopy(result, 0, result2, 0, index + 1);
        System.out.println(Arrays.deepToString(result2));
    }


    @Override
    public int[][] initData() {
        return new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
    }
}