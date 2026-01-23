package com.xx.algorithm.greedy;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/1
 * <p>
 * 心算挑战
 * LeetCode Medium
 * <p>
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，
 * 则选手成绩「有效」且得分为 cnt 张卡牌数字总和。 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 * <p>
 * 示例 1：
 * 输入：cards = [1,2,8,9], cnt = 3
 * <p>
 * 输出：18
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 * <p>
 * 示例 2：
 * 输入：cards = [3,3,1], cnt = 1
 * <p>
 * 输出：0
 * 解释：不存在获取有效得分的卡牌方案。
 * <p>
 * 提示：
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 * <p>
 * Tag:贪心+排序
 */
public class MentalArithmeticChallenge implements Answer {
    public static void main(String[] args) {
        new MentalArithmeticChallenge().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[] cards = {1, 2, 8, 9};
        int cnt = 3;
        System.out.println(maxmiumScore(cards, cnt));
    }

    /**
     * 为了选取尽量大的数，将 cards 从大到小排序，并累加前 cnt 个数，记作 s。
     * <p>
     * 分类讨论：
     * 如果 s 是偶数，这是我们能得到的最大得分，直接返回 s。
     * 如果 s 是奇数，那么可以：
     * 从前 cnt 个数中去掉一个最小的奇数，从后 n−cnt 个数中加进来一个最大的偶数，这样得分就变成偶数了。
     * 从前 cnt 个数中去掉一个最小的偶数，从后 n−cnt 个数中加进来一个最大的奇数，这样得分就变成偶数了。
     * 两种情况取最大值。
     */
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int n = cards.length;
        int s = 0;
        for (int i = n - cnt; i < n; i++) {
            s += cards[i]; // 最大的 cnt 个数之和
        }
        if (s % 2 == 0) { // s 是偶数
            return s;
        }

        int x = cards[n - cnt];
        int ans = replaceSum(cards, cnt, s, x); // 替换 x
        for (int i = n - cnt + 1; i < n; i++) {
            if (cards[i] % 2 != x % 2) { // 找到一个最小的奇偶性和 x 不同的数
                ans = Math.max(ans, replaceSum(cards, cnt, s, cards[i])); // 替换
                break;
            }
        }
        return ans;
    }

    private int replaceSum(int[] cards, int cnt, int s, int x) {
        for (int i = cards.length - cnt - 1; i >= 0; i--) {
            if (cards[i] % 2 != x % 2) { // 找到一个最大的奇偶性和 x 不同的数
                return s - x + cards[i]; // 用 cards[i] 替换 s
            }
        }
        return 0;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
