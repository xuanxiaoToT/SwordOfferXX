package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.HashMap;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/11
 * <p>
 * 单词规律
 * LeetCode 290. 简单
 * <p>
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * 输入: pattern = "abba", s = "dog cat cat dog"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:pattern = "abba", s = "dog cat cat fish"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: pattern = "aaaa", s = "dog cat cat dog"
 * 输出: false
 * <p>
 * 提示:
 * 1 <= pattern.length <= 300
 * pattern 只包含小写英文字母
 * 1 <= s.length <= 3000
 * s 只包含小写英文字母和 ' '
 * s 不包含 任何前导或尾随对空格
 * s 中每个单词都被 单个空格 分隔
 */
public class WordPatterns implements Answer {

    public static void main(String[] args) {
        new WordPatterns().answerOne();
    }

    @Override
    public void answerOne() {
        String pattern = "abba";
        String s = "dog cat dog cat";
        System.out.println(patternWord(pattern, s));
    }

    /**
     * 注意：是双向
     */
    public boolean patternWord(String pattern, String s) {
        String[] wordList = s.split(" ");
        if (pattern.length() != wordList.length) {
            return false;
        }
        HashMap<Character, String> patternMap = new HashMap<>(pattern.length());
        HashMap<String, Character> sMap = new HashMap<>(wordList.length);
        for (int i = 0; i < pattern.length(); i++) {
            Character cPattern = pattern.charAt(i);
            String str = wordList[i];
            if (patternMap.containsKey(cPattern) && sMap.containsKey(str)) {
                if (!patternMap.get(cPattern).equals(str) || sMap.get(str) != cPattern) {
                    return false;
                }
            } else if (!patternMap.containsKey(cPattern) && !sMap.containsKey(str)) {
                patternMap.put(cPattern, str);
                sMap.put(str, cPattern);
            } else {
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