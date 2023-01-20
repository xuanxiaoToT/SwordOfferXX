package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/8
 * 字符串交织
 * 输入3个字符串s1、s2和s3，请判断字符串s3能不能由字
 * 符串s1和s2交织而成，即字符串s3的所有字符都是字符串s1或s2中
 * 的字符，字符串s1和s2中的字符都将出现在字符串s3中且相对位置
 * 不变。例如，字符串"aadbbcbcac"可以由字符
 * 串"aabcc"和"dbbca"交织而成。
 */
public class StringInterleaving implements Answer {

    public static void main(String[] args) {
        new StringInterleaving().answerOne();
    }

    /**
     * 双指针法，仅相等时需要考虑
     */
    @Override
    public void answerOne() {
        String[] initData = initData();
        String strA = initData[0];
        String strB = initData[1];
        String strResult = initData[2];
        int i = 0, j = 0, k = 0;
        while (k < strResult.length()) {
            char charA = strA.charAt(i);
            char charB = strB.charAt(j);
            char charResult = strResult.charAt(k);
            if (charA != charB) {
                if (charA == charResult) {
                    i++;
                }
                if (charB == charResult) {
                    j++;
                }
                if (charA != charResult && charB != charResult) {
                    System.out.println("false");
                    return;
                }
            } else {
                String temp = charA + String.valueOf(charA);
                if (strResult.substring(k, k + 2).equals(temp)) {
                    //fixme:
                    i++;
                    j++;
                    k++;
                } else {
                    char tempA = strA.charAt(i + 1);
                    char tempB = strA.charAt(j + 1);
                    char tempR = strResult.charAt(k + 1);
                    if (tempA != tempR && tempB != tempR) {
                        System.out.println("false");
                        return;
                    }
                    if (tempA == tempR) {
                        i++;
                    }
                    if (tempB == tempR) {
                        j++;
                    }
                }
            }
            k++;
        }
        System.out.println("true");
    }

    /**
     * dp法
     * f（i，j） = s1[0..i] + s2[0..j]
     * <p>
     * 按照字符串的交织规则，字符串s3的下标为i+j+1的字符
     * （s3[i+j+1]）既可能是来自字符串s1的下标为i的字符（s1[i]），也
     * 可能是来自字符串s2的下标为j的字符（s2[j]）。如果s3[i+j+1]和
     * s1[i]相同，只要s1[0..i-1]和s2[0..j]能交织得到子字符串
     * s3[i+j]，那么s1[0..i]一定能和s2[0..j]交织得到s3[0..i+j+1]。也
     * 就是说，当s3[i+j+1]和s1[i]相同时，f（i，j）的值等于f（i-1，
     * j）的值。类似地，当s3[i+j+1]和s2[j]相同时，f（i，j）的值等于
     * f（i，j-1）的值。如果s1[i]和s2[j]都和s3[i+j+1]相同，此时只要
     * f（i-1，j）和f（i，j-1）有一个值为true，那么f（i，j）的值为
     * true。
     * <p>
     * 问题fix：思考的时候就是0,0没想明白。将其当做-1处理就很容易解决了。
     */
    private void answerTwo() {
        String[] initData = initData();
        String strA = initData[0];
        String strB = initData[1];
        String strResult = initData[2];
        boolean[][] dp = new boolean[strA.length() + 1][strB.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < strA.length(); i++) {
            dp[i + 1][0] = dp[i][0] && strA.charAt(i) == strResult.charAt(i);
        }
        for (int i = 0; i < strA.length(); i++) {
            dp[0][i + 1] = dp[0][i + 1] && strB.charAt(i) == strResult.charAt(i);
        }
        for (int i = 0; i < strA.length(); i++) {
            for (int j = 0; j < strB.length(); j++) {
                char charA = strA.charAt(i);
                char charB = strB.charAt(j);
                char charResult = strResult.charAt(i + j + 1);
                dp[i + 1][j + 1] = (charA == charResult && dp[i][j + 1]) || (charB == charResult && dp[i + 1][j]);
            }
        }
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"aabcc", "dbbca", "aadbbcbcac"};
    }
}