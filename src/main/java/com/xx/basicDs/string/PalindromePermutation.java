package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/6/13
 * <p>
 * 回文排列
 * LeetCode  Easy
 * <p>
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * 回文串不一定是字典当中的单词。
 * <p>
 * 示例1：
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 */
public class PalindromePermutation implements Answer {
    public static void main(String[] args) {
        new PalindromePermutation().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        String s = "AaBb//a";
        System.out.println(canPermutePalindrome(s));
    }

    /**
     * ascii码最小的是空格，为0
     * 最大的是128
     */
    public boolean canPermutePalindrome(String s) {
        int[] flag = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            flag[c - ' ']++;
        }
        int notCommon = 0;
        for (int j : flag) {
            if (j % 2 != 0) {
                notCommon++;
            }
        }
        return notCommon <= 1;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
