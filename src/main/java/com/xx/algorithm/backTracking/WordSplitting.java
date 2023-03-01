package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 玄霄
 * @CreateDate 2023/3/1
 * <p>
 * 单词拆分
 * LeetCode 139
 * <p>
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 * 注意，你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class WordSplitting implements Answer {

    public static void main(String[] args) {
        new WordSplitting().answerOne();
    }

    /**
     * 解1：利用回溯法
     * 参考《分割回文子字符串》
     * 思路是一样的
     */
    @Override
    public void answerOne() {
        Set<String> dic = initData();
        //String inputData = "applepenapple";
        String inputData = "catsandog";
        answerOneDiGui(dic, inputData, 0);
    }

    /**
     * 尝试使用动态规划来解决试试
     * todo fixme
     */
    public void answerTwo() {

    }

    private void answerOneDiGui(Set<String> dic, String inputData, int start) {
        if (start >= inputData.length()) {
            System.out.println(true);
            return;
        }
        for (int i = start; i < inputData.length(); i++) {
            if (dic.contains(inputData.substring(start, i + 1))) {
                answerOneDiGui(dic, inputData, i + 1);
            }
        }

    }

    /**
     * 输出数据
     */
    @Override
    public Set<String> initData() {
        //return new HashSet<>(Arrays.asList("apple", "pen"));
        return new HashSet<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
    }
}
