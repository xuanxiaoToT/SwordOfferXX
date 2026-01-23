package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/7
 * <p>
 * 判断子序列
 * LeetCode 392. Easy
 * <p>
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 */
public class DetermineSubsequences implements Answer {

    public static void main(String[] args) {
        new DetermineSubsequences().answer();
    }

    @Override
    public void answer() {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(determineStrSub(s, t));
    }

    /**
     * 进阶版：给t改造为一个map，key为字母，value为出现的下标列表。
     * 然后扫描s，扫描每一个字母，保证后面出现的index比他大即可。
     */
    public void answerTwo() {
        //解法略
    }

    // 仅有一个s的时候，直接扫就行。
    private boolean determineStrSub(String s, String t) {
        int index = 0;
        for (char c : s.toCharArray()) {
            boolean flag = false;
            for (int i = index; i < t.length(); i++) {
                char tChar = t.charAt(i);
                if (c == tChar) {
                    index = i + 1;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object initData() {
        return null;
    }
}