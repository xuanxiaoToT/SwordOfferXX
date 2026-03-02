package com.xx.algorithm.math;

import com.xx.Answer;

/**
 * 十-二进制数的最少数目
 * LeetCode 1689 Medium-
 * <p>
 * 如果一个十进制数字不含任何前导零，且每一位上的数字不是 0 就是 1 ，那么该数字就是一个 十-二进制数 。例如，101 和 1100 都是 十-二进制数，而 112 和 3001 不是。
 * 给你一个表示十进制整数的字符串 n ，返回和为 n 的 十-二进制数 的最少数目。
 * <p>
 * 示例 1：
 * 输入：n = "32"
 * 输出：3
 * 解释：10 + 11 + 11 = 32
 * <p>
 * 示例 2：
 * 输入：n = "82734"
 * 输出：8
 * <p>
 * 示例 3：
 * 输入：n = "27346209830709182346"
 * 输出：9
 * <p>
 * 提示：
 * 1 <= n.length <= 10^5
 * n 仅由数字组成
 * n 不含任何前导零并总是表示正整数
 * <p>
 * Tag:数学  建模
 */
public class PartitioningIntoMinimumNumberOfDeciBinaryNumbers implements Answer {
    public static void main(String[] args) {
        new PartitioningIntoMinimumNumberOfDeciBinaryNumbers().answer();
    }

    @Override
    public void answer() {
        String n = "27346209830709182346";
        System.out.println(minPartitions(n));
    }

    /**
     * 每个位上是几，就需要该位几个1.本质上就是最大值。最大也就是9
     */
    public int minPartitions(String n) {
        int max = 0;
        for (int i = 0; i < n.length(); i++) {
            int ch = n.charAt(i) - '0';
            max = Math.max(max, ch);
        }
        return max;
    }

    @Override
    public Object initData() {
        return null;
    }
}
