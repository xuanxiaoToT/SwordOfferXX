package com.xx.algorithm.dynamicProgram.简单DP;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/1
 * <p>
 * 单词拆分
 * LeetCode 139 Medium
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
 * <p>
 * Tag：动态规划
 */
public class WordSplitting implements Answer {

    public static void main(String[] args) {
        new WordSplitting().answerOne();
    }

    /**
     * 解1：利用回溯法
     * 超时！
     * 参考《分割回文子字符串》
     * 思路是一样的
     */
    @Override
    public void answerOne() {
        Set<String> dic = initData();
        String inputData = "applepenapple";
        //String inputData = "catsandog";
        answerOneDiGui(dic, inputData, 0);
    }

    /**
     * 尝试使用动态规划来解决试试
     * 定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词。
     */
    public boolean wordBreak(String s, List<String> dic) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (String dicStr : dic) {
                //i为终点，计算是否为true
                if (i >= dicStr.length()) {
                    String sub = s.substring(i - dicStr.length(), i);
                    if (sub.equals(dicStr) && dp[i - dicStr.length()]) {
                        dp[i] = true;
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[s.length()];
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
        System.out.println(false);
    }

    /**
     * 输出数据
     */
    @Override
    public Set<String> initData() {
        return new HashSet<>(Arrays.asList("apple", "pen"));
        //return new HashSet<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
    }
}
