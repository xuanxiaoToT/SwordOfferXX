package com.xx.algorithm.sort;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/3
 * 正方形中的最多点数
 * LeetCode 3143
 * <p>
 * 给你一个二维数组 points 和一个字符串 s ，其中 points[i] 表示第 i 个点的坐标，s[i] 表示第 i 个点的 标签 。
 * 如果一个正方形的中心在 (0, 0) ，所有边都平行于坐标轴，且正方形内 不 存在标签相同的两个点，
 * 那么我们称这个正方形是 合法 的。
 * 请你返回 合法 正方形中可以包含的 最多 点数。
 * <p>
 * 注意：
 * 如果一个点位于正方形的边上或者在边以内，则认为该点位于正方形内。
 * 正方形的边长可以为零。
 * <p>
 * 示例 1：
 * 输入：points = [[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]], s = "abdca"
 * 输出：2
 * 解释：
 * 边长为 4 的正方形包含两个点 points[0] 和 points[1] 。
 * <p>
 * <p>
 * 示例 2：
 * 输入：points = [[1,1],[-2,-2],[-2,2]], s = "abb"
 * 输出：1
 * <p>
 * 解释：
 * 边长为 2 的正方形包含 1 个点 points[0] 。
 * <p>
 * 示例 3：
 * 输入：points = [[1,1],[-1,-1],[2,-2]], s = "ccd"
 * 输出：0
 * 解释：
 * 任何正方形都无法只包含 points[0] 和 points[1] 中的一个点，所以合法正方形中都不包含任何点。
 * <p>
 * 提示：
 * 1 <= s.length, points.length <= 10^5
 * points[i].length == 2
 * -10^9 <= points[i][0], points[i][1] <= 10^9
 * s.length == points.length
 * points 中的点坐标互不相同。
 * s 只包含小写英文字母。
 * <p>
 * Tag:排序  数组
 */
public class MaximumPointsInsideTheSquare implements Answer {

    public static void main(String[] args) {
        new MaximumPointsInsideTheSquare().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[][] points = {{2, 2}, {-1, -2}, {-4, 4}, {-3, 1}, {3, -3}};
        String s = "abdca";
        //int[][] points = {{-1, -4}, {16, -8}, {13, -3}, {-12, 0}};
        //String s = "abda";

        //int[][] points = {{1, 1}, {-2, -2}, {-2, 2}};
        //String s = "abb";
        System.out.println(maxPointsInsideSquare(points, s));
    }

    /**
     * 关键点，对边长排序
     * indexList.sort(Comparator.comparing(index -> Math.max(Math.abs(points[index][0]), Math.abs(points[index][1]))));
     */
    public int maxPointsInsideSquare(int[][] points, String s) {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            indexList.add(i);
        }
        //如果包含这个点，最大的边长，按照这个排序
        indexList.sort(Comparator.comparing(index -> Math.max(Math.abs(points[index][0]), Math.abs(points[index][1]))));
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        int maxIndex = 0;
        //求最大边长
        for (int i = 0; i < indexList.size(); i++) {
            int index = indexList.get(i);
            char c = s.charAt(index);
            if (set.contains(c)) {
                //这个点不能要，意味着这个边长的其他点都不能要
                int tempLen = Math.max(Math.abs(points[index][0]), Math.abs(points[index][1]));
                if (maxLen == tempLen) {
                    maxLen = maxLen - 1;
                }
                break;
            }
            maxLen = Math.max(Math.abs(points[index][0]), Math.abs(points[index][1]));
            set.add(c);
            maxIndex = i;
        }
        int result = 0;
        //因为maxLen减了1，所以重新计算之前的点数。降序更快
        for (int i = 0; i <= maxIndex; i++) {
            int index = indexList.get(i);
            if (Math.max(Math.abs(points[index][0]), Math.abs(points[index][1])) <= maxLen) {
                result++;
            }
        }
        return result;
    }


    //public static class Node {
    //    public int[] point;
    //    public char c;
    //
    //    public Node(int[] point, char c) {
    //        this.point = point;
    //        this.c = c;
    //    }
    //
    //    public int[] getPoint() {
    //        return point;
    //    }
    //
    //    public char getC() {
    //        return c;
    //    }
    //}

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
