package com.xx.basicDs.string;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/5
 * <p>
 * 字符串中的变位词
 * <p>
 * 输入字符串s1和s2，如何判断字符串s2中是否包含字符串s1的某个变位词？
 * 如果字符串s2中包含字符串s1的某个变位词，
 * 则字符串s1至少有一个变位词是字符串s2的子字符串。假设两个字
 * 符串中只包含英文小写字母。
 * <p>
 * 例如，字符串s1为"ac"，字符串s2为"dgcaf"，由于字符串s2中包含字符串s1的变位词"ca"，
 * 因此输出为true。如果字符串s1为"ab"，字符串s2为"dgcaf"，则输出为false。
 * <p>
 * 变位词是与字符串相关的面试题中经常出现的一个概念。
 * 所谓的变位词是指组成各个单词的字母及每个字母出现的次数完全相
 * 同，只是字母排列的顺序不同。
 * <p>
 * 举一反三,同类型：
 * LeetCode 438 找到字符串中所有字母异位词  Medium
 * 输入字符串s1和s2，如何找出字符串s2的所有变位词在
 * 字符串s1中的起始下标？假设两个字符串中只包含英文小写字母。
 * 例如，字符串s1为"cbadabacg"，字符串s2为"abc"，字符串s2的两
 * 个变位词"cba"和"bac"是字符串s1中的子字符串，输出它们在字符
 * 串s1中的起始下标0和5。
 *
 * Tag：滑动窗口.md
 */
public class AnagramsInStrings implements Answer {

    public static void main(String[] args) {
        new AnagramsInStrings().answerOne();
    }

    /**
     * 采用滑动窗口法，因为S1若包含S2的变位词，
     * 则采用一个S2.length长度的窗口进行滑动，然后判断。
     * 复杂度：O(N)
     */
    @Override
    public void answerOne() {
        String str1 = "ac";
        String str2 = initData();
        int left = 0;
        int right = left + str1.length() - 1;

        HashMap<Character, Integer> map = new HashMap<>(26);
        for (Character c : str1.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (Character c : str2.substring(left, right + 1).toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
            } else {
                map.put(c, -1);
            }
        }
        while (right < str2.length()) {
            System.out.println(map);
            /**
             * 判断两个词是否是变位词，调用此方法时，上层已经保证两者长度相同。
             * str1将所有字母放入map，以及数目。
             * str2则遍历，减去对应的字母数。
             * 如果最后map都为0，则表明其是变位词。
             * 此法的关键难点在于，返回前如何将map恢复到原来的样子。
             * 解决：不用恢复，而是上层在滑动窗口的时候做加减。
             */
            if (whetherAnagrams(map)) {
                System.out.println(true);
            }

            //  滑动窗口开始移动，将涉及到的-1
            map.put(str2.charAt(left), map.get(str2.charAt(left)) + 1);
            map.put(str2.charAt(right), map.get(str2.charAt(right)) + 1);
            left++;
            right++;
            if (right < str2.length()) {
                if (map.containsKey(str2.charAt(left))) {
                    map.put(str2.charAt(left), map.get(str2.charAt(left)) - 1);
                } else {
                    map.put(str2.charAt(left), -1);
                }
                if (map.containsKey(str2.charAt(right))) {
                    map.put(str2.charAt(right), map.get(str2.charAt(right)) - 1);
                } else {
                    map.put(str2.charAt(right), -1);
                }
            }
        }

    }

    private boolean whetherAnagrams(HashMap<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * something
     */
    @Override
    public String initData() {
        return "dgcafac";
    }
}
