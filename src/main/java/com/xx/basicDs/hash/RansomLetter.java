package com.xx.basicDs.hash;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/8
 * <p>
 * 赎金信
 * LeetCode 383. Easy
 * <p>
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * <p>
 * 示例 1：
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * <p>
 * 示例 2：
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 * <p>
 * 提示：
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote 和 magazine 由小写英文字母组成
 */
public class RansomLetter implements Answer {
    public static void main(String[] args) {
        new RansomLetter().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String ransomNote = "aa";
        String magazine = "aavb";
        boolean result = canConstruct(ransomNote, magazine);
        System.out.println(result);
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if (cnt[c - 'a'] < 0) {
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
