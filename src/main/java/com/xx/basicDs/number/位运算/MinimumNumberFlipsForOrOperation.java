package com.xx.basicDs.number.位运算;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/29
 * <p>
 * 或运算的最小翻转次数
 * LeetCode  1318. Easy
 * <p>
 * 给你三个正整数 a、b 和 c。
 * 你可以对 a 和 b 的二进制表示进行位翻转操作，返回能够使按位或运算   a OR b == c  成立的最小翻转次数。
 * 「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。
 * <p>
 * 示例 1：
 * 输入：a = 2, b = 6, c = 5
 * 输出：3
 * 解释：翻转后 a = 1 , b = 4 , c = 5 使得 a OR b == c
 * <p>
 * 示例 2：
 * 输入：a = 4, b = 2, c = 7
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：a = 1, b = 2, c = 3
 * 输出：0
 * <p>
 * 提示：
 * 1 <= a <= 10^9
 * 1 <= b <= 10^9
 * 1 <= c <= 10^9
 * <p>
 * Tag:位运算
 */
public class MinimumNumberFlipsForOrOperation implements Answer {

    public static void main(String[] args) {
        new MinimumNumberFlipsForOrOperation().answerOne();
    }

    @Override
    public void answerOne() {
        System.out.println(computeResult(1, 2, Integer.MAX_VALUE));
    }

    /**
     * 或 操作，有一个1即为1
     */
    public int computeResult(int a, int b, int c) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int aDit = a & 1;
            int bDit = b & 1;
            int cDit = c & 1;
            if ((aDit | bDit) != cDit) {
                if (aDit == 1 && bDit == 1 && cDit == 0) {
                    result += 2;
                } else {
                    result++;
                }
            }
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}