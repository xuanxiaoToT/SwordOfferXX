package com.xx.basicDs.graph.topology;

import com.xx.Answer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/5
 * <p>
 * 找到冠军I
 * LeetCode 100115 Easy
 * <p>
 * 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。
 * 给你一个下标从 0 开始、大小为 n * n 的二维布尔矩阵 grid 。
 * 对于满足 0 <= i, j <= n - 1 且 i != j 的所有 i, j ：如果 grid[i][j] == 1，那么 i 队比 j 队 强 ；否则，j 队比 i 队 强 。
 * 在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。
 * 返回这场比赛中将会成为冠军的队伍。
 * <p>
 * 示例 1：
 * 输入：grid = [[0,1],[0,0]]
 * 输出：0
 * 解释：比赛中有两支队伍。
 * grid[0][1] == 1 表示 0 队比 1 队强。所以 0 队是冠军。
 * <p>
 * 示例 2：
 * 输入：grid = [[0,0,1],[1,0,1],[0,0,0]]
 * 输出：1
 * 解释：比赛中有三支队伍。
 * grid[1][0] == 1 表示 1 队比 0 队强。
 * grid[1][2] == 1 表示 1 队比 2 队强。
 * 所以 1 队是冠军。
 * <p>
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] 的值为 0 或 1
 * 对于满足 i != j 的所有 i, j ，grid[i][j] != grid[j][i] 均成立
 * 生成的输出满足：如果 a 队比 b 队强，b 队比 c 队强，那么 a 队比 c 队强
 */
public class FindTheChampionI implements Answer {

    public static void main(String[] args) {
        new FindTheChampionI().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[][] grid = initData();
        System.out.println(answerTwo(grid));
    }

    /**
     * 比赛的时候想到的方法，如果value里面不包含key，则其必为开始点，也就是冠军
     * 内存太浪费了
     */
    public int findChampion(int[][] grid) {
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (i != j && grid[i][j] == 1) {
                    if (map.containsKey(i)) {
                        map.get(i).add(j);
                    } else {
                        HashSet<Integer> set = new HashSet<>();
                        set.add(j);
                        map.put(i, set);
                    }
                }
            }
        }
        Set<Integer> valueSet = new HashSet<>();
        for (HashSet<Integer> value : map.values()) {
            valueSet.addAll(value);
        }
        int result = 0;
        for (Map.Entry<Integer, HashSet<Integer>> entry : map.entrySet()) {
            if (!valueSet.contains(entry.getKey())) {
                result = entry.getKey();
            }
        }
        return result;
    }

    /**
     * 比赛完了想起来了
     * 实际上，只需要保证该入度为0即可
     */
    public int answerTwo(int[][] grid) {
        int[] inDegree = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (i != j && grid[i][j] == 1) {
                    inDegree[j]++;
                }
            }
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{0, 0, 1}, {1, 0, 1}, {0, 0, 0}};
    }
}
