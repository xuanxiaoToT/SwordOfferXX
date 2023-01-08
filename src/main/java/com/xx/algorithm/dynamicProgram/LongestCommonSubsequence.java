package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author 玄霄
 * @CreateDate 2022/12/7
 * 最长公共子序列
 * 输入两个字符串，请求出它们的最长公共子序列的长度。
 * 如果从字符串s1中删除若干字符之后能得到字符串s2，
 * 那么字符串s2就是字符串s1的一个子序列。
 * 例如，从字符串"abcde"中删除
 * 两个字符之后能得到字符串"ace"，因此字符串"ace"是字符
 * 串"abcde"的一个子序列。但字符串"aec"不是字符串"abcde"的子序
 * 列。如果输入字符串"abcde"和"badfe"，那么它们的最长公共子序
 * 列是"bde"，因此输出3。
 * <p>
 * 思路：如果字符s1[i]与字符s2[j]不相同，则这两个字符不可能同时出
 * 现在s1[0..i]和s2[0..j]的公共子序列中。
 */
public class LongestCommonSubsequence implements Answer {

    public static void main(String[] args) {
        String[] split = new String[]{};
        System.out.println(split.length);
        // new LongestCommonSubsequence().answerOne();
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
    public void answerOne() {
        String[] strings = initData();
        String strA = strings[0];
        String strB = strings[1];
        int[][] dp = new int[strA.length()][strB.length()];

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
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }


    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"abcde", "badfe"};
    }
}
