package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;
import com.xx.basicDs.string.NumberOfPalindromeSubstrings;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/17
 * <p>
 * 最长回文子串
 * LeetCode 005
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 */
public class LongestPalindromicSubstring implements Answer {

    public static void main(String[] args) {
        new LongestPalindromicSubstring().answerOne();
    }

    /**
     * 解1：中心扩散法，解法略
     * 参考-> {@link NumberOfPalindromeSubstrings}
     * <p>
     * 解2：动态规划
     * 注意：是按照长度来转移的
     * 也就是：在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的，因此一定要注意动态规划的循环顺序。
     * <p>
     * 动态规划的状态转移方程：
     * <p>
     * P(i,j)=P(i+1,j−1)∧(Si==Sj)
     * 也就是说，只有 s[i+1:j−1]是回文串，并且 sss 的第 i 和 j 个字母相同时，s[i:j] 才会是回文串。
     */
    @Override
    public void answerOne() {
        String data = initData();
        int len = data.length();
        if (len < 2) {
            System.out.println(data);
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = data.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        String result = data.substring(begin, begin + maxLen);

        System.out.println(result);
    }

    /**
     * 输出数据
     */
    @Override
    public String initData() {
        return "aaaa";
    }
}
