package com.xx.basicDs.string;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/15
 *
 * 反转字符串中的元音字母
 * LeetCode 345.  Easy
 *
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现不止一次。
 *
 * 示例 1：
 * 输入：s = "hello"
 * 输出："holle"
 *
 * 示例 2：
 * 输入：s = "leetcode"
 * 输出："leotcede"
 *
 * 提示：
 * 1 <= s.length <= 3 * 10^5
 * s 由 可打印的 ASCII 字符组成
 *
 * Tag:字符串遍历
 */
public class InvertVowelsInString implements Answer {
    public static void main(String[] args) {
        new InvertVowelsInString().answerOne();
    }

    @Override
    public void answerOne() {
        System.out.println(invertString("leetcode"));
    }

    /**
     * 用一个队列暂存，然后再替换
     */
    private String invertString(String str) {
        StringBuilder result = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (vowels.contains(Character.toUpperCase(c))) {
                queue.add(c);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (vowels.contains(Character.toUpperCase(c))) {
                result.append(queue.poll());
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * 交换法
     * 类似快排思路
     */
    public String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] s_array = s.toCharArray();
        while (left < right) {
            char tempsave;
            if (isyuanyin(s_array[left]) && !isyuanyin(s_array[right])) {
                right--;
            } else if (!isyuanyin(s_array[left]) && isyuanyin(s_array[right])) {
                left++;
            } else if (isyuanyin(s_array[left]) && isyuanyin(s_array[right])) {
                tempsave = s_array[left];
                s_array[left] = s_array[right];
                s_array[right] = tempsave;
                left++;
                right--;
            } else {
                left++;
                right--;
            }
        }
        return new String(s_array);
    }

    public boolean isyuanyin(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') return true;
        return false;
    }

    @Override
    public Object initData() {
        return null;
    }
}