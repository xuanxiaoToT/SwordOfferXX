package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/22
 * <p>
 * 找出输掉零场或一场比赛的玩家
 * LeetCode 2225.  Medium
 * <p>
 * 给你一个整数数组 matches 其中 matches[i] = [winneri, loseri] 表示在一场比赛中 winneri 击败了 loseri 。
 * 返回一个长度为 2 的列表 answer ：
 * answer[0] 是所有 没有 输掉任何比赛的玩家列表。
 * answer[1] 是所有恰好输掉 一场 比赛的玩家列表。
 * 两个列表中的值都应该按 递增 顺序返回。
 * <p>
 * 注意：
 * 只考虑那些参与 至少一场 比赛的玩家。
 * 生成的测试用例保证 不存在 两场比赛结果 相同 。
 * <p>
 * 示例 1：
 * 输入：matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
 * 输出：[[1,2,10],[4,5,7,8]]
 * 解释：
 * 玩家 1、2 和 10 都没有输掉任何比赛。
 * 玩家 4、5、7 和 8 每个都输掉一场比赛。
 * 玩家 3、6 和 9 每个都输掉两场比赛。
 * 因此，answer[0] = [1,2,10] 和 answer[1] = [4,5,7,8] 。
 * <p>
 * 示例 2：
 * 输入：matches = [[2,3],[1,3],[5,4],[6,4]]
 * 输出：[[1,2,5,6],[]]
 * 解释：
 * 玩家 1、2、5 和 6 都没有输掉任何比赛。
 * 玩家 3 和 4 每个都输掉两场比赛。
 * 因此，answer[0] = [1,2,5,6] 和 answer[1] = [] 。
 * <p>
 * 提示：
 * 1 <= matches.length <= 10^5
 * matches[i].length == 2
 * 1 <= winneri, loseri <= 10^5
 * winneri != loseri
 * 所有 matches[i] 互不相同
 * <p>
 * Tag：Hash的运用  数组可以转换为hash
 */
public class FindPersonWhoLostTheGame implements Answer {

    public static void main(String[] args) {
        new FindPersonWhoLostTheGame().answer();
    }

    @Override
    public void answer() {
        // int[][] matches = new int[][]{{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {4, 9}, {10, 4}, {10, 9}};
        int[][] matches = new int[][]{{2, 3}, {1, 3}, {5, 4}, {6, 4}};
        System.out.println(findWinners(matches));
    }

    /**
     * 0是没有输掉一场比赛的
     * 1是恰好输掉一场的
     * <p>
     * 用一个Map来记录，每个人输了多少次。胜者相当于输了0次
     *
     * @param matches{win，lose} fixme:TreeMap比较耗性能。1 <= winneri, loseri <= 10^5，所以可以用数组替代map
     */
    public List<List<Integer>> findWinners(int[][] matches) {
        List<Integer> loseOne = new ArrayList<>();
        List<Integer> loseZero = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, Integer> loseCountMap = new TreeMap<>();
        for (int[] match : matches) {
            int lose = match[1];
            int win = match[0];
            loseCountMap.put(lose, loseCountMap.getOrDefault(lose, 0) + 1);
            if (!loseCountMap.containsKey(win)) {
                loseCountMap.put(win, 0);
            }
        }
        for (Map.Entry<Integer, Integer> entry : loseCountMap.entrySet()) {
            Integer key = entry.getKey();
            Integer val = entry.getValue();
            if (val == 0) {
                loseZero.add(key);
            }
            if (val == 1) {
                loseOne.add(key);
            }
        }
        result.add(loseZero);
        result.add(loseOne);
        return result;
    }

    /**
     * 优化，用数组替代map，而且数组天生有序
     */
    public List<List<Integer>> findWinners2(int[][] matches) {
        int[] player = new int[100001];
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            if (player[winner] >= 0) {
                player[winner] = 1;
            }
            // 如果败者第一次输，那么记录输的次数为-1;如果不是，那么输的次数加1，这里用负数的相反数代表输的次数;
            if (player[loser] >= 0) {
                player[loser] = -1;
            } else {
                player[loser]--;
            }
        }
        List<Integer> list0 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < player.length; i++) {
            if (player[i] == 1) {
                list0.add(i);
            } else if (player[i] == -1) {
                list1.add(i);
            }
        }
        list.add(list0);
        list.add(list1);
        return list;
    }

    @Override
    public Object initData() {
        return null;
    }
}
