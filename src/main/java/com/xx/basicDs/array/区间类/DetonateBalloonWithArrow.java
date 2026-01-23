package com.xx.basicDs.array.区间类;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/16
 * <p>
 * 用最少数量的箭引爆气球
 * LeetCode 452. Medium
 * <p>
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平
 * 直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 * <p>
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
 * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
 * <p>
 * 示例 2：
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * 解释：每个气球需要射出一支箭，总共需要4支箭。
 * <p>
 * 示例 3：
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * - 在x = 2处发射箭，击破气球[1,2]和[2,3]。
 * - 在x = 4处射出箭，击破气球[3,4]和[4,5]。
 * <p>
 * 提示:
 * 1 <= points.length <= 10^5
 * points[i].length == 2
 * -2^31 <= xstart < xend <= 2^31 - 1
 */
public class DetonateBalloonWithArrow implements Answer {

    public static void main(String[] args) {
        new DetonateBalloonWithArrow().answer();
    }

    @Override
    public void answer() {
        int[][] data = initData();
        Arrays.sort(data, Comparator.comparingInt(o -> o[0]));
        //System.out.println(Arrays.deepToString(data));
        List<int[]> result = new ArrayList<>();
        for (int[] node : data) {
            if (result.isEmpty()) {
                result.add(node);
            } else {
                int[] last = result.get(result.size() - 1);
                if (node[0] > last[1]) {
                    result.add(node);
                } else {
                    last[0] = Math.max(node[0], last[0]);
                    last[1] = Math.min(node[1], last[1]);
                    result.set(result.size() - 1, last);
                }
            }
        }
        System.out.println(result);
    }

    @Override
    public int[][] initData() {
        // return new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        // return new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        // return new int[][]{{1, 2}, {2, 3}, {3, 6}, {6, 8}};
        //return new int[][]{{1, 5}, {2, 3}, {3, 6}, {6, 8}};
        return new int[][]{{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}};
    }
}