package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/19
 * <p>
 * 编辑距离
 * LeetCode 72.  困难
 * <p>
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class EditDistance implements Answer {
    public static void main(String[] args) {
        new EditDistance().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String[] data = initData();
        String word1 = data[0];
        String word2 = data[1];
        if (word1.isEmpty() || word2.isEmpty()) {
            Math.max(word1.length(), word2.length());
            return;
        }

        // dp[i][j] 代表 word1(0-i) 变为 word(0-j)需要的最少步骤数
        int[][] dp = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            char char1 = word1.charAt(i);
            for (int j = 0; j < word2.length(); j++) {
                char char2 = word2.charAt(j);
                if (char1 == char2) {
                    if (i >= 1 && j >= 1) {
                        // 删除和新增
                        dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                        // 不动，不需要替换操作
                        dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i][j]);
                    } else if (i >= 1) {
                        // i不加1，因为最后一个已经相同，计算的是前面的距离
                        // dp[i - 1][j] + 1则表示，i串的最后一个执行删除操作。
                        dp[i][j] = Math.min(i, dp[i - 1][j] + 1);
                    } else if (j >= 1) {
                        dp[i][j] = Math.min(j, dp[i][j - 1] + 1);
                    }
                } else {
                    if (i >= 1 && j >= 1) {
                        // 删除和新增
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                        // 替换操作
                        dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, dp[i][j]);
                    } else if (i >= 1) {
                        //同上，此时i加1是因为最后一个不同，因为从0开始，计算长度要+1
                        dp[i][j] = Math.min(i, dp[i - 1][j]) + 1;
                    } else if (j >= 1) {
                        dp[i][j] = Math.min(j, dp[i][j - 1]) + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                }
            }
        }
        System.out.println(dp[word1.length() - 1][word2.length() - 1]);
        System.out.println(Arrays.deepToString(dp));
    }

    @Override
    public String[] initData() {
        //return new String[]{"horse", "ros"};
        //return new String[]{"intention", "execution"};
        //return new String[]{"sea", "eat"};
        //return new String[]{"qr", "qwer"};

    }

}
