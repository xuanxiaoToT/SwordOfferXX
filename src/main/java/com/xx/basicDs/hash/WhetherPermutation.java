package com.xx.basicDs.hash;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/4/16
 * <p>
 * 判定是否互为字符重排
 * <p>
 * 给定两个由小写字母组成的字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * <p>
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * <p>
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * <p>
 * 说明：
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 */
public class WhetherPermutation implements Answer {
    public static void main(String[] args) {
        new WhetherPermutation().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        System.out.println(CheckPermutation("abc", "bca"));
    }

    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] flag1 = new int[64];
        int[] flag2 = new int[64];
        for (char c : s1.toCharArray()) {
            flag1[c - 'A']++;
        }
        for (char c : s2.toCharArray()) {
            flag2[c - 'A']++;
        }
        for (int i = 0; i < flag1.length; i++) {
            if (flag1[i] != flag2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
