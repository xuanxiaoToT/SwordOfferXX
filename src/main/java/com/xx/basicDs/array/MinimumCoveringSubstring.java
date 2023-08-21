package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/21
 * <p>
 * 最小覆盖子串
 * LeetCode 76.
 * <p>
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * <p>
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 */
public class MinimumCoveringSubstring implements Answer {
    public static void main(String[] args) {
        new MinimumCoveringSubstring().answerOne();
    }

    /**
     * 解1：使用滑动窗口算法
     */
    @Override
    public void answerOne() {
        String data = initData();
        String target = getTarget();
        HashMap<Character, Integer> targetMap = new HashMap<>();
        HashMap<Character, Integer> dataMap = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            Character c = target.charAt(i);
            targetMap.put(c, targetMap.computeIfAbsent(c, key -> 0) + 1);
        }
        int left = 0;
        int right = 0;
        int resultMin = Integer.MAX_VALUE;
        String result = "";
        dataMap.put(data.charAt(0), dataMap.computeIfAbsent(data.charAt(0), key -> 0) + 1);
        while (right < data.length() && left <= right) {
            if (isEligible(targetMap, dataMap)) {
                if (right - left + 1 < resultMin) {
                    resultMin = right - left + 1;
                    result = data.substring(left, right + 1);
                    System.out.println(result);
                }
                dataMap.put(data.charAt(left), dataMap.computeIfAbsent(data.charAt(left), key -> 0) - 1);
                left++;
            } else {
                right++;
                if (right >= data.length()) {
                    break;
                }
                dataMap.put(data.charAt(right), dataMap.computeIfAbsent(data.charAt(right), key -> 0) + 1);
            }
        }
        System.out.println(result);
    }

    public boolean isEligible(HashMap<Character, Integer> targetMap, HashMap<Character, Integer> dataMap) {
        for (Map.Entry<Character, Integer> entry : targetMap.entrySet()) {
            Character key = entry.getKey();
            Integer count = entry.getValue();
            if (!dataMap.containsKey(key) || dataMap.get(key) < count) {
                return false;
            }
        }
        return true;
    }

    private String getTarget() {
        return "ABC";
        //return "cae";
    }

    /**
     * 输出数据
     */
    @Override
    public String initData() {
        return "ADOBECODEBANC";
        //return "cabwefgewcwaefgcf";
    }
}
