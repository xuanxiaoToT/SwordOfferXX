package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 玄霄
 * @CreateDate 2023/3/12
 * <p>
 * 电话号码的字母组合
 * LeetCode 17
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 */
public class CombinationOfTelephoneNumber implements Answer {

    public static void main(String[] args) {
        new CombinationOfTelephoneNumber().answerOne();
    }

    /**
     * 解1：回溯法简单做
     */
    @Override
    public void answerOne() {
        String inputData = initData();
        Map<Character, String> phoneMap = initNumMap();
        ArrayList<String> result = new ArrayList<>();
        myDiGui(new StringBuilder(), result, 0, inputData, phoneMap);
        System.out.println(result);
    }

    private void myDiGui(StringBuilder temp, List<String> result, int index, String inputData, Map<Character, String> phoneMap) {
        if (index >= inputData.length()) {
            result.add(temp.toString());
            return;
        }
        String s = phoneMap.get(inputData.charAt(index));
        for (int i = 0; i < s.length(); i++) {
            temp.append(s.charAt(i));
            myDiGui(temp, result, index + 1, inputData, phoneMap);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    private Map<Character, String> initNumMap() {
        Map<Character, String> phoneMap = new HashMap<Character, String>(8) {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        return phoneMap;
    }

    /**
     * 输出数据
     */
    @Override
    public String initData() {
        return "23";
    }
}
