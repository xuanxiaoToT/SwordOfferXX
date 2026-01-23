package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/12
 * <p>
 * 最长有效括号
 * LeetCode 32 Hard
 * <p>
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * <p>
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * <p>
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 */
public class LongestValidParentheses implements Answer {

    public static void main(String[] args) {
        new LongestValidParentheses().answer();
    }

    /**
     * 解1：暴力法。略
     * 解2：动态规划。
     * dp[i]表示以i元素结尾的最长子串长度.
     * （1）当s[i]== ')'，s[i-1] =='('时，例如："……()"，符合条件，我们只需要更新dt[i] = dt[i-2] +2.
     * （2）当s[i] == ')'，s[i-1] == ')'时，例如："……))"，如果s[i-dt[i-1]-1] = '('，
     * 那么  dp[i]=dp[i−1]+dp[i−dp[i−1]−2]+2.
     * 如果最后一个元素为' ) '是有效子串的一部分，那么他一定有一个对应的'(' 在倒数第二个')'所在的有效子字符串的前面.
     */
    @Override
    public void answer() {
        String data = initData();
        int[] dp = new int[data.length()];
        int max = 0;
        for (int i = 1; i < data.length(); i++) {
            char self = data.charAt(i);
            char pre = data.charAt(i - 1);
            if (self == ')') {
                if (pre == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else {
                    //前一个括号不是左括号。即(()())的情况
                    int len = dp[i - 1];
                    int left = i - len - 1;
                    if (left >= 0 && data.charAt(left) == '(') {
                        dp[i] = left >= 1 ? dp[i - 1] + 2 + dp[left-1] : dp[i - 1] + 2;
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(max);
    }


    /**
     * 输出数据
     */
    @Override
    public String initData() {
        //return "(()())()";
        //return "(()())(";
        return "()(())";
    }
}
