package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/25
 * <p>
 * 相等行列对
 * LeetCode 2352. Medium
 * <p>
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 * <p>
 * 示例 1：
 * 输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
 * 输出：1
 * 解释：存在一对相等行列对：
 * - (第 2 行，第 1 列)：[2,7,7]
 * <p>
 * 示例 2：
 * 输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * 输出：3
 * 解释：存在三对相等行列对：
 * - (第 0 行，第 0 列)：[3,1,2,2]
 * - (第 2 行, 第 2 列)：[2,4,2,2]
 * - (第 3 行, 第 2 列)：[2,4,2,2]
 * <p>
 * 提示：
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * 1 <= grid[i][j] <= 10^5
 * <p>
 * Tag: 哈希  行列比较
 */
public class EqualRowAndColumnPairs implements Answer {
    public static void main(String[] args) {
        new EqualRowAndColumnPairs().answer();
    }

    @Override
    public void answer() {
        int[][] grid = {{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}};
        System.out.println(computePairs(grid));
    }

    /**
     * Tip: List是可以作为key来进行比较的，元素相同即为相同
     */
    public int computePairs(int[][] grid) {
        int result = 0;
        int n = grid.length;
        Map<List<Integer>, Integer> map = new HashMap<>(n);
        for (final int[] value : grid) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                temp.add(value[j]);
            }
            map.compute(temp, (k, v) -> v == null ? 1 : v + 1);
        }
        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>();
            for (final int[] ints : grid) {
                temp.add(ints[i]);
            }
            if (map.containsKey(temp)) {
                result += map.get(temp);
            }
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
