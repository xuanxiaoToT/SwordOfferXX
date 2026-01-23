package com.xx.algorithm.dynamicProgram.二维DP;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/7
 * <p>
 * 最长公共子序列
 * LeetCode 1143 Medium
 * <p>
 * 输入两个字符串，请求出它们的最长公共子序列的长度。
 * 如果从字符串s1中删除若干字符之后能得到字符串s2，
 * 那么字符串s2就是字符串s1的一个子序列。
 * <p>
 * 例如，从字符串"abcde"中删除
 * 两个字符之后能得到字符串"ace"，因此字符串"ace"是字符 串"abcde"的一个子序列。
 * 但字符串"aec"不是字符串"abcde"的子序列。
 * 如果输入字符串"abcde"和"badfe"，那么它们的最长公共子序
 * 列是"bde"，因此输出3。
 * <p>
 * 思路：如果字符s1[i]与字符s2[j]不相同，则这两个字符不可能同时出
 * 现在s1[0..i]和s2[0..j]的公共子序列中。
 * <p>
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * <p>
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * <p>
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 */
public class LongestCommonSubsequence implements Answer {

    public static void main(String[] args) {
        new LongestCommonSubsequence().answer();
    }

    /**
     * 1.无脑的，直接穷举，然后求最大值。
     * 2.函数f（i，j）表示第1个字符串中下标从0到i的子字符串（记为s1[0..i]）和
     * 第2个字符串中下标从0到j的子字符串（记为s2[0..j]）的最长公共子序列的长度。
     * <p>
     * 则状态转移方程为 [i]==[j] ? f(i-1,j-1)+1 : max(f(i-1,j),f(j-1,i))
     * <p>
     * todo:可以只用两行来存储，优化存储效率
     */
    @Override
    public void answer() {
        String[] strings = initData();
        String strA = strings[0];
        String strB = strings[1];
        int[][] dp = new int[strA.length()][strB.length()];
        int max = 0;
        for (int i = 0; i < strA.length(); i++) {
            for (int j = 0; j < strB.length(); j++) {
                if (strA.charAt(i) == strB.charAt(j)) {
                    if (i > 0 && j > 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    if (i > 0 && j > 0) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    } else {
                        if (i > 0) {
                            dp[i][j] = dp[i - 1][j];
                        }
                        if (j > 0) {
                            dp[i][j] = dp[i][j - 1];
                        }
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(Arrays.deepToString(dp));
        System.out.println(max);
    }


    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"abcde", "badfe"};
    }
}
