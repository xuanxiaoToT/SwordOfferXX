package com.xx.basicDs.hash;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/11
 * <p>
 * 有效的字母异位词
 * LeetCode 242. Easy
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * <p>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * 提示:
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 * <p>
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class EffectiveLetterEctopicWords implements Answer {

    public static void main(String[] args) {
        new EffectiveLetterEctopicWords().answer();
    }

    @Override
    public void answer() {
        System.out.println(isAnagram("rat", "car"));
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] wordMap = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            int sIndex = sChar - 'a';
            int tIndex = tChar - 'a';
            wordMap[sIndex]++;
            wordMap[tIndex]--;
        }
        for (int count : wordMap) {
            if (count != 0) {
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