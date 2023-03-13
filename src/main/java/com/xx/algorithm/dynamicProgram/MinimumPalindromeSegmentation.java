package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/7
 * <p>
 * 最少回文分割
 * <p>
 * 输入一个字符串，请问至少需要分割几次才可以使分割
 * 出的每个子字符串都是回文？
 * <p>
 * 例如，输入字符串"aaba"，至少需要分割1次，
 * 从两个相邻字符'a'中间切一刀将字符串分割成两个回文
 * 子字符串"a"和"aba"。
 */
public class MinimumPalindromeSegmentation implements Answer {

    public static void main(String[] args) {
        new MinimumPalindromeSegmentation().answerOne();
    }

    /**
     * 即一个字符串最少可以分割出几个回文
     * f(i)表示s[0..i]的字符串，最少几个回文。如果s[i..j]是一个回文，那么f(j)=f(i-1)+1
     */
    @Override
    public void answerOne() {
        String data = initData();
        int[] dp = new int[data.length()];
        Arrays.parallelSetAll(dp, i -> i + 1);
        for (int i = 0; i < data.length(); i++) {
            for (int j = i; j < data.length(); j++) {
                if (isHuiWen(data, i, j)) {
                    dp[j] = i >= 1 ? Math.min(dp[i - 1] + 1, dp[j]) : 1;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    private boolean isHuiWen(String data, int start, int end) {
        while (start < end) {
            if (data.charAt(start) != data.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     * something
     */
    @Override
    public String initData() {
        return "legoog";
    }
}