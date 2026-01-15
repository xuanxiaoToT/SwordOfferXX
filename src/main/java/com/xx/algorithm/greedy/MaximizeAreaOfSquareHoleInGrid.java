package com.xx.algorithm.greedy;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 最大化网格图中正方形空洞的面积
 * LeetCode 2943. Medium
 * <p>
 * 给你两个整数 n 和 m，以及两个整数数组 hBars 和 vBars。网格由 n + 2 条水平线和 m + 2 条竖直线组成，形成 1x1 的单元格。网格中的线条从 1 开始编号。
 * 你可以从 hBars 中 删除 一些水平线条，并从 vBars 中删除一些竖直线条。注意，其他线条是固定的，无法删除。
 * 返回一个整数表示移除一些线条（可以不移除任何线条）后，网格中 正方形空洞的最大面积 。
 * <p>
 * 示例 1：
 * 输入: n = 2, m = 1, hBars = [2,3], vBars = [2]
 * 输出: 4
 * 解释:
 * 左侧图片展示了网格的初始状态。水平线是 [1,2,3,4]，竖直线是 [1,2,3]。
 * 构造最大正方形空洞的一种方法是移除水平线 2 和竖直线 2。
 * <p>
 * 示例 2：
 * 输入: n = 1, m = 1, hBars = [2], vBars = [2]
 * 输出: 4
 * 解释:
 * 移除水平线 2 和竖直线 2，可以得到最大正方形空洞。
 * <p>
 * 示例 3：
 * 输入: n = 2, m = 3, hBars = [2,3], vBars = [2,4]
 * 输出: 4
 * 解释:
 * 构造最大正方形空洞的一种方法是移除水平线 3 和竖直线 4。
 * <p>
 * 提示：
 * 1 <= n <= 10^9
 * 1 <= m <= 10^9
 * 1 <= hBars.length <= 100
 * 2 <= hBars[i] <= n + 1
 * 1 <= vBars.length <= 100
 * 2 <= vBars[i] <= m + 1
 * hBars 中所有值互不相同。
 * vBars 中所有值互不相同。
 * <p>
 * Tag:贪心  哈希
 */
public class MaximizeAreaOfSquareHoleInGrid implements Answer {
    public static void main(String[] args) {
        new MaximizeAreaOfSquareHoleInGrid().answerOne();
    }

    @Override
    public void answerOne() {
        // int n = 2;
        // int m = 1;
        // int[] hBars = new int[]{2, 3};
        // int[] vBars = new int[]{2};

        // int n = 1;
        // int m = 1;
        // int[] hBars = new int[]{2};
        // int[] vBars = new int[]{2};

        // int n = 2;
        // int m = 3;
        // int[] hBars = new int[]{2, 3};
        // int[] vBars = new int[]{2, 4};

        // int n = 2;
        // int m = 3;
        // int[] hBars = new int[]{2, 3};
        // int[] vBars = new int[]{3, 4};

        int n = 4;
        int m = 40;
        int[] hBars = new int[]{5, 3, 2, 4};
        int[] vBars = new int[]{36, 41, 6, 34, 33};
        System.out.println(maximizeSquareHoleArea(n, m, hBars, vBars));
    }

    /**
     * 本质是求 128. 最长连续序列，可以做到 O(h+v) 的时间复杂度。
     * <p>
     * 贪心地，删的线段越多，面积越大，那就先把所有能删的线段都删掉，计算最大的矩形，长宽分别是多少。
     * 取长宽的最小值，即为正方形的边长（多删的线段撤销删除）。
     */
    public int maximizeSquareHoleArea2(int n, int m, int[] hBars, int[] vBars) {
        int side = Math.min(longestConsecutive(hBars), longestConsecutive(vBars)) + 1;
        return side * side;
    }

    // 128. 最长连续序列
    private int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int num : nums) {
            st.add(num); // 把 nums 转成哈希集合
        }

        int ans = 0;
        for (int x : st) { // 遍历哈希集合
            if (st.contains(x - 1)) { // 如果 x 不是序列的起点，直接跳过
                continue;
            }
            // x 是序列的起点
            int y = x + 1;
            while (st.contains(y)) { // 不断查找下一个数是否在哈希集合中
                y++;
            }
            // 循环结束后，y-1 是最后一个在哈希集合中的数
            ans = Math.max(ans, y - x); // 从 x 到 y-1 一共 y-x 个数
        }
        return ans;
    }


    /**
     * n，hBars:行
     * m，vBars:列
     * <p>
     * 删的越多越好，因此我们直接假设全部删除，再删除的过程中一定有最大值
     * 可以发现，正方形空洞的边长取决于移除的横向和纵向最大连续线段数目
     */
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int result = 0;
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        // 上一个行号
        int lastHang = 0;
        for (int i = 0; i < hBars.length; i++) {
            // 不连续时，则重置行号
            if (lastHang == 0 || hBars[i - 1] != hBars[i] - 1) {
                lastHang = hBars[i] - 1;
            }
            int hang = hBars[i] - lastHang + 1;
            int lastLie = 0;
            for (int j = 0; j < vBars.length; j++) {
                if (lastLie == 0 || (j > 0 && vBars[j - 1] != vBars[j] - 1)) {
                    lastLie = vBars[j] - 1;
                }
                int lie = vBars[j] - lastLie + 1;
                int length = Math.min(hang, lie);
                result = Math.max(length * length, result);
            }
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
