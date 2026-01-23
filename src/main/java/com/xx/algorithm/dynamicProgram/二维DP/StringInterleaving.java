package com.xx.algorithm.dynamicProgram.二维DP;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/8
 * <p>
 * 交错字符串
 * LeetCode 97  Hard
 * <p>
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 * <p>
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 * <p>
 * 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它?
 *
 * Tag: 二维动态规划
 */
public class StringInterleaving implements Answer {

    public static void main(String[] args) {
        new StringInterleaving().answer();
    }


    /**
     * dp法
     * f（i，j） = s1[0..i] + s2[0..j]按照字符串的交织规则，
     * 字符串s3的下标为i+j+1的字符（s3[i+j+1]）既可能是来自字符串s1的下标为i的字符（s1[i]），
     * 也可能是来自字符串s2的下标为j的字符（s2[j]）。如果s3[i+j+1]和s1[i]相同，只要s1[0..i-1]和s2[0..j]能交织得到子字符串s3[i+j]，
     * 那么s1[0..i]一定能和s2[0..j]交织得到s3[0..i+j+1]。也就是说，当s3[i+j+1]和s1[i]相同时，f（i，j）的值等于f（i-1，j）的值。
     * 类似地，当s3[i+j+1]和s2[j]相同时，f（i，j）的值等于f（i，j-1）的值。如果s1[i]和s2[j]都和s3[i+j+1]相同，
     * 此时只要f（i-1，j）和f（i，j-1）有一个值为true，那么f（i，j）的值为true。
     * <p>
     * 问题fix：思考的时候就是0,0没想明白。将其当做-1处理就很容易解决了。
     */
    @Override
    public void answer() {
        System.out.println(isInterleave("aa", "ab", "abaa"));

    }

    public boolean isInterleave(String strA, String strB, String strResult) {
        if (strA.length() + strB.length() != strResult.length()) {
            return false;
        }
        if (strA.isEmpty()) {
            return strB.equals(strResult);
        }
        if (strB.isEmpty()) {
            return strA.equals(strResult);
        }

        boolean[][] dp = new boolean[strA.length() + 1][strB.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < strA.length(); i++) {
            dp[i + 1][0] = dp[i][0] && strA.charAt(i) == strResult.charAt(i);
        }
        for (int i = 0; i < strB.length(); i++) {
            dp[0][i + 1] = dp[0][i] && strB.charAt(i) == strResult.charAt(i);
        }
        for (int i = 0; i < strA.length(); i++) {
            for (int j = 0; j < strB.length(); j++) {
                char charA = strA.charAt(i);
                char charB = strB.charAt(j);
                char charResult = strResult.charAt(i + j + 1);
                dp[i + 1][j + 1] = (charA == charResult && dp[i][j + 1]) || (charB == charResult && dp[i + 1][j]);
            }
        }
        return dp[strA.length()][strB.length()];
    }


    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"aabcc", "dbbca", "aadbbcbcac"};
    }
}