package com.xx.algorithm.math;

import com.xx.Answer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/12
 * <p>
 * 直线上最多的点数
 * LeetCode 149. Hard
 * <p>
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * <p>
 * 示例 1：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 * <p>
 * 提示：
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * points 中的所有点 互不相同
 * <p>
 * Tag : 哈希  数学知识
 */
public class MaxPointsOnLine implements Answer {

    public static void main(String[] args) {
        new MaxPointsOnLine().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[][] points = new int[][]{{0, 1}, {0, 0}, {0, 4}, {0, -2}, {0, -1}, {0, 3}, {0, -4}};
        System.out.println(maxPoints(points));
    }

    /**
     * 按照斜率归档即可
     * 注意:Double由于自动拆箱和装箱，会有-0.0 和 -INFINITY的问题
     */
    public int maxPoints(int[][] points) {
        if (points.length == 1) {
            return 1;
        }
        int max = 0;
        Map<Double, Set<int[]>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            for (int j = i + 1; j < points.length; j++) {
                int[] point1 = points[i];
                int[] point2 = points[j];
                Double slope = ((point2[1] - point1[1]) * 1.0 / (point2[0] - point1[0])) + 0.0;
                if (slope == Double.NEGATIVE_INFINITY) {
                    slope = Double.POSITIVE_INFINITY;
                }
                if (map.containsKey(slope)) {
                    map.get(slope).add(point2);
                } else {
                    Set<int[]> set = new HashSet<>();
                    set.add(point1);
                    set.add(point2);
                    map.put(slope, set);
                }
                max = Math.max(map.get(slope).size(), max);
            }
        }
        return max;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
