package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/5
 * 外星语言是否排序
 * <p>
 * 有一门外星语言，它的字母表刚好包含所有的英文小写
 * 字母，只是字母表的顺序不同。给定一组单词和字母表顺序，请判
 * 断这些单词是否按照字母表的顺序排序。
 * <p>
 * 例如，输入一组单词
 * ["offer"，"is"，"coming"]，以及字母表顺
 * 序"zyxwvutsrqponmlkjihgfedcba"，由于字母'o'在字母表中位
 * 于'i'的前面，因此单词"offer"排在"is"的前面；同样，由于字
 * 母'i'在字母表中位于'c'的前面，因此单词"is"排在"coming"的前
 * 面。因此，这一组单词是按照字母表顺序排序的，应该输出true。
 */
public class AlienLanguageWhetherOrdered implements Answer {

    private String alphabet = "zyxwvutsrqponmlkjihgfedcba";

    public static void main(String[] args) {
        new AlienLanguageWhetherOrdered().answerOne();
    }

    /**
     * 就正常按照字母序列验证即可。
     */
    @Override
    public void answerOne() {
        String[] strings = initData();
        Map<Character, Integer> dict = generateDict(alphabet);
        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                if (compare(strings[i], strings[j], dict) > 0) {
                    System.out.println(false);
                }
            }
        }
        System.out.println(true);
    }

    private int compare(String str1, String str2, Map<Character, Integer> dictMap) {
        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return dictMap.get(str1.charAt(i)) - dictMap.get(str2.charAt(i));
            }
        }
        if (str1.length() != str2.length()) {
            return str1.length() - str2.length();
        }
        return 0;
    }

    private Map<Character, Integer> generateDict(String alphabet) {
        Map<Character, Integer> dictMap = new HashMap<>();
        for (int i = 0; i < alphabet.length(); i++) {
            dictMap.put(alphabet.charAt(i), i);
        }
        return dictMap;
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        //单词顺序
        return new String[]{"offer", "is", "coming"};
    }
}