package com.xx.temp;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/10
 */
public class FindBuildingWhereAliceAndBobCanMeet implements Answer {

    public static void main(String[] args) {
        new FindBuildingWhereAliceAndBobCanMeet().answerOne();
    }

    /**
     * 解1：待解决  todo:
     */
    @Override
    public void answerOne() {
        //int[] heights = {5, 3, 8, 2, 6, 1, 4, 6};
        //int[][] queries = {{0, 7}};
        int[] heights = {6, 4, 8, 5, 2, 7};
        int[][] queries = {{0, 1}, {0, 3}, {2, 4}, {3, 4}, {2, 2}};
        System.out.println(Arrays.toString(leftmostBuildingQueries(heights, queries)));
    }

    /**
     * 最简单的，双循环便利，寻找第一个最大值
     * 超时！
     */
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[] result = new int[queries.length];
        for (int j = 0; j < queries.length; j++) {
            int[] query = queries[j];
            int rightIndex = Math.max(query[0], query[1]);
            int leftIndex = Math.min(query[0], query[1]);
            result[j] = -1;
            if (query[0] == query[1]) {
                result[j] = query[1];
                continue;
            }
            if (heights[rightIndex] > heights[leftIndex]) {
                result[j] = rightIndex;
                continue;
            }
            for (int i = rightIndex; i < heights.length; i++) {
                if (heights[i] > heights[query[0]] && heights[i] > heights[query[1]]) {
                    result[j] = i;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
