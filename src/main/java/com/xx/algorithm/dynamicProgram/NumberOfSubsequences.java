package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author 玄霄
 * @CreateDate 2022/12/12
 * 子序列的数目
 * <p>
 * 输入字符串S和T，请计算字符串S中有多少个子序列等于
 * 字符串T。例如，在字符串"appplep"中，有3个子序列等于字符
 * 串"apple".
 */
public class NumberOfSubsequences implements Answer {
    public static void main(String[] args) {
        new NumberOfSubsequences().answerOne();
    }

    /**
     * 同样的，空间效率可以优化
     * 解法：f(i,j) = 表示字符串S(j)中有n个子序列等于T(i);
     */
    @Override
    public void answerOne() {
        String[] strings = initData();
        String target = strings[0];
        String material = strings[1];
        int[][] dp = new int[target.length()][material.length()];
        for (int i = 0; i < target.length(); i++) {
            for (int j = 0; j < material.length(); j++) {
                char charTar = target.charAt(i);
                char charMat = material.charAt(j);
                if (charTar == charMat) {
                    if (i > 0 && j > 0) {
                        //状态转移方程
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1] + 1);
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    if (j > 0) {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"aple", "appplep"};
    }
}