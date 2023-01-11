package com.xx.basicDs.graph.topology;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/12/27
 * 外星文字典
 * <p>
 * 一种外星语言的字母都是英文字母，但字母的顺序未知。
 * 给定该语言排序的单词列表，请推测可能的字母顺序。如果有
 * 多个可能的顺序，则返回任意一个。如果没有满足条件的字母顺
 * 序，则返回空字符串。
 * 例如，如果输入排序的单词列表为
 * ["ac"，"ab"，"bc"，"zc"，"zb"]，那么一个可能的字母顺序
 * 是"acbz"。
 * <p>
 * 使用拓扑排序
 */
public class AlienWordDictionary implements Answer {

    public static void main(String[] args) {
        new AlienWordDictionary().answerOne();
    }

    /**
     * 解:此题与《课程顺序》题目类似
     * 唯一的问题是如何将字母表转换为graph
     */
    @Override
    public void answerOne() {

        String[] data = initData();
        // 字母表的顺序：即与上一个字符进行对比
        for (int i = 1; i < data.length; i++) {
            String last = data[i - 1];
            String it = data[i];
            for (int j = 0; j < last.length() && j < it.length(); j++) {
                char charLast = last.charAt(j);
                char charIt = it.charAt(j);
                if (charLast != charIt) {
                    System.out.println(charLast + "->" + charIt);
                    break;
                }
            }
        }
        // 后面的解法与《课程顺序》题目类似 此处略

    }

    /**
     * 输入数据
     */
    @Override
    public String[] initData() {
        return new String[]{"ac", "ab", "bc", "zc", "zb"};
    }
}