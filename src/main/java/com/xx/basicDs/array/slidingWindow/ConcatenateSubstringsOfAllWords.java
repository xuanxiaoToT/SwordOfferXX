package com.xx.basicDs.array.slidingWindow;

import com.xx.Answer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/7
 *
 * 串联所有单词的子串
 *
 */
public class ConcatenateSubstringsOfAllWords implements Answer {
    public static void main(String[] args) {
        new ConcatenateSubstringsOfAllWords().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        //String s = "barfoothefoobarman";
        //String[] words = new String[]{"foo", "bar"};
        //String s = "wordgoodgoodgoodbestword";
        //String[] words = new String[]{"word","good","best","word"};
        //String s = "barfoofoobarthefoobarman";
        //String[] words = new String[]{"bar", "foo", "the"};
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word", "good", "best", "good"};
        System.out.println(findSubstringMy(s, words));
    }


    /**
     * 官解：采用滑动窗口，每次滑动长度n
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        return result;
    }


    public List<Integer> findSubstringMy(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        Set<String> set = Arrays.stream(words).collect(Collectors.toSet());
        int n = words[0].length();
        int maxLen = words[0].length() * words.length;
        int left = 0;
        int right = maxLen - 1;
        while (right < s.length()) {
            String firstWord = s.substring(left, right + 1);
            if (preliminaryJudgment(firstWord, set, n)) {
                if (isValid(s.substring(left, right + 1), words)) {
                    result.add(left);
                }
            }
            left++;
            right++;
        }
        return result;
    }

    private boolean preliminaryJudgment(String subStr, Set<String> set, int n) {
        for (int i = 0; i < subStr.length(); i += n) {
            if (!set.contains(subStr.substring(i, i + n))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 超出时间限制~!
     */
    private boolean isValid(String subStr, String[] words) {
        Map<String, Integer> map = Arrays.stream(words).collect(Collectors.toMap(Function.identity(), str -> 1, (o, n) -> o + 1));
        int start = 0;
        int n = words[0].length();
        do {
            String temp = subStr.substring(start, start + n);
            if (map.containsKey(temp)) {
                start = start + n;
                if (map.get(temp) == 1) {
                    map.remove(temp);
                } else {
                    map.put(temp, map.get(temp) - 1);
                }
            } else {
                return false;
            }
        } while (!map.isEmpty() && start < subStr.length());
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
