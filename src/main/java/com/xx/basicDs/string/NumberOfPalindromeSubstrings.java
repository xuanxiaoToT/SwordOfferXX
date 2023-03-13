package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/10
 * <p>
 * 回文子字符串的个数
 * LeetCode 647. 回文子串
 * <p>
 * 给定一个字符串，请问该字符串中有多少个回文连续子字符串？
 * <p>
 * 例如，字符串"abc"有3个回文子字符串，分别
 * 为"a"、"b"和"c"；
 * 而字符串"aaa"有6个回文子字符串，分别
 * 为"a"、"a"、"a"、"aa"、"aa"和"aaa"。
 * <p>
 * 举一反三：
 * LeetCode 005 《最长回文子串》
 * 同样可以用这个办法
 */
public class NumberOfPalindromeSubstrings implements Answer {

    private int count = 0;

    public static void main(String[] args) {
        new NumberOfPalindromeSubstrings().answerTwo();
    }

    /**
     * 笨方法：直接双层遍历，
     * 复杂度N3
     */
    @Override
    public void answerOne() {

        String oriStr = initData();
        for (int i = 0; i < oriStr.length(); i++) {
            for (int j = i + 1; j < oriStr.length(); j++) {
                // 判断i和j是否为回文，解法略。
            }
        }
    }

    /**
     * 遍历每个字母，以每个字母为中心点向左右遍历。这样就将找回文和遍历结合在一起了。
     * 双数位的情况，则以其+1位的为主。
     * 也是O(N2)
     */
    public void answerTwo() {
        String oriStr = initData();
        for (int i = 0; i < oriStr.length(); i++) {
            this.count++;
            // 奇数
            countPalindrome(oriStr, i - 1, i + 1);
            // 偶数
            countPalindrome(oriStr, i, i + 1);
        }
        System.out.println(count);
    }

    private void countPalindrome(String str, int left, int right) {
        while (left >= 0 && right < str.length()) {
            if (str.charAt(left) == str.charAt(right)) {
                this.count++;
            } else {
                return;
            }
            left--;
            right++;
        }
    }

    /**
     * something
     */
    @Override
    public String initData() {
        return "aabaa";
    }
}