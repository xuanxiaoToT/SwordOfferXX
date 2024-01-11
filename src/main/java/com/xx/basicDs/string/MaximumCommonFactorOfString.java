package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/11
 * <p>
 * 字符串的最大公因子
 * LeetCode 1071. Easy
 * <p>
 * 对于字符串 s 和 t，只有在 s = t + ... + t（t 自身连接 1 次或多次）时，我们才认定 “t 能除尽 s”。
 * 给定两个字符串 str1 和 str2 。返回 最长字符串 x，要求满足 x 能除尽 str1 且 x 能除尽 str2 。
 * <p>
 * 示例 1：
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * <p>
 * 示例 2：
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * <p>
 * 示例 3：
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 * <p>
 * 提示：
 * 1 <= str1.length, str2.length <= 1000
 * str1 和 str2 由大写英文字母组成
 * <p>
 * Tag：字符串  辗转相除法
 */
public class MaximumCommonFactorOfString implements Answer {
    public static void main(String[] args) {
        new MaximumCommonFactorOfString().answerOne();
    }

    @Override
    public void answerOne() {
        String str1 = "LEET";
        String str2 = "CODE";
        System.out.println(maximumCommonFactor(str1, str2));
    }

    private String maximumCommonFactor(String str1, String str2) {
        int gcd = gcd(str1.length(), str2.length());
        String target = str1.substring(0, gcd);
        for (int i = 0; i < Math.max(str1.length(), str2.length()); i = i + gcd) {
            int right = i + gcd;
            if (right <= str1.length()) {
                String temp1 = str1.substring(i, right);
                if (!temp1.equals(target)) {
                    return "";
                }
            }
            if (right <= str2.length()) {
                String temp2 = str2.substring(i, right);
                if (!temp2.equals(target)) {
                    return "";
                }
            }
        }
        return target;
    }

    /**
     * 辗转相除法 求最大公因数
     */
    int gcd(int a, int b) {
        while (b != 0) {
            int r = b;
            b = a % b;
            a = r;
        }
        return a;
    }


    /**
     * 递归版本 辗转相除法
     */
    int gcd2(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    @Override
    public Object initData() {
        return null;
    }
}