package com.xx.algorithm.other;

import com.xx.Answer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/12
 * <p>
 * 请实现有如下两个操作的神奇字典。
 * ● 函数buildDict，输入单词数组用来创建一个字典。
 * ● 函数search，输入一个单词，判断能否修改该单词中的一个字
 * 符，使修改之后的单词是字典中的一个单词。
 * <p>
 * 例如，输入["happy"，"new"，"year"]创建一个神奇字典。如果
 * 输入单词"now"进行查找操作，由于将其中的'o'修改成'e'就可以得到
 * 字典中的"new"，因此返回true。如果输入单词"new"，那么将其中的
 * 任意字符修改成另一个不同的字符都无法得到字典中的单词，因此返
 * 回false。
 */
public class MagicDictionary implements Answer {

    public static void main(String[] args) {
        new MagicDictionary().answer();
    }

    /**
     * something
     */
    @Override
    public void answer() {
        String[] strings = initData();
        String inputWord = "now";
        Map<Integer, List<String>> map = Arrays.stream(strings).collect(Collectors.groupingBy(String::length));

        List<String> stringList = map.get(inputWord.length());
        for (String s : stringList) {
            if (compareStr(s, inputWord, 1)){
                System.out.println(s);
                return;
            }
        }
    }

    // 使用前缀树来解决。
    public void answerTwo(){

    }

    private boolean compareStr(String s, String inputWord, int i) {
        int errorCount = 0;
        for (int j = 0; j < s.toCharArray().length; j++) {
            if (s.charAt(j) != inputWord.charAt(j)) {
                errorCount++;
            }
        }
        return errorCount == i;
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"happy", "new", "year"};
    }

}