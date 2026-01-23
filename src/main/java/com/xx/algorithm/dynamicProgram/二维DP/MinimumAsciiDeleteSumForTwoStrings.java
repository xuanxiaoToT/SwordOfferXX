package com.xx.algorithm.dynamicProgram.二维DP;

import com.xx.Answer;

/**
 * 两个字符串的最小ASCII删除和
 * LeetCode 712. Medium
 * <p>
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 * <p>
 * 示例 1:
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 * <p>
 * 示例 2:
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 * <p>
 * 提示:
 * 0 <= s1.length, s2.length <= 1000
 * s1 和 s2 由小写英文字母组成
 * <p>
 * Tag：二维动态规划
 * <p>
 * 注意：求解ASCII 值为：(int) char 即可。
 */
public class MinimumAsciiDeleteSumForTwoStrings implements Answer {
    public static void main(String[] args) {
        new MinimumAsciiDeleteSumForTwoStrings().answer();
    }

    @Override
    public void answer() {
        // String s1 = "delete";
        // String s2 = "leet";

        // String s1 = "sea";
        // String s2 = "eat";

        // String s1 = "delete";
        // String s2 = "delete";

        String s1 = "at";
        String s2 = "a";
        System.out.println(minimumDeleteSum(s1, s2));
    }

    /**
     * 官解：
     * 但是采用了哨兵机制后。
     * 思路一样
     * <p>
     * 还有一个思路：
     * {@link LongestCommonSubsequence}
     * 质上是经典的最长公共子序列（LCS）问题，不同之处在于 LCS 问题关注的是子序列的长度，而本题关注的是子序列的 ASCII 之和，
     * 可以先求出最大的公共子序列 ASCII 之和，答案为 s1的 ASCII 之和加上s2的 ASCII 之和减去两倍的最大公共子序列 ASCII 之和。
     */
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.codePointAt(i - 1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.codePointAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            int code1 = s1.codePointAt(i - 1);
            for (int j = 1; j <= n; j++) {
                int code2 = s2.codePointAt(j - 1);
                if (code1 == code2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + code1, dp[i][j - 1] + code2);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 经典dp问题
     * 2次过，在只有一个字符初始化的时候，也就是前两个遍历到的时候，没有考虑到，他可以仅删除一个，而不是2个。
     * 应该考虑用哨兵，创建+1的长度，这样更好操作，可以省去那个判空
     * <p>
     * dp[i][j]表示S1的前i个和s2的前j个字符的最小和。
     * 当ij相同时，可以选择不删除，也可以选择删除一个(这表明前面已经有跟他相同的了，选前面的更好)
     */
    public int minimumDeleteSumOld(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        int tempSum = 0;
        for (int i = 0; i < s1.length(); i++) {
            int tempAsc = (int) s1.charAt(i);
            if (s1.charAt(i) == s2.charAt(0)) {
                dp[i][0] = i > 0 ? Math.min(tempSum, dp[i - 1][0] + tempAsc) : tempSum;
            } else {
                int temp = tempSum + tempAsc + (int) s2.charAt(0);
                dp[i][0] = i > 0 ? Math.min(temp, dp[i - 1][0] + tempAsc) : temp;
            }
            tempSum += tempAsc;
        }
        tempSum = 0;
        for (int i = 0; i < s2.length(); i++) {
            int tempAsc = (int) s2.charAt(i);
            if (s2.charAt(i) == s1.charAt(0)) {
                dp[0][i] = i > 0 ? Math.min(dp[0][i - 1] + tempAsc, tempSum) : tempSum;
            } else {
                int temp = tempSum + tempAsc + (int) s1.charAt(0);
                dp[0][i] = i > 0 ? Math.min(dp[0][i - 1] + tempAsc, temp) : temp;
            }
            tempSum += tempAsc;
        }

        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = computeMin(dp[i - 1][j - 1], dp[i - 1][j] + (int) s1.charAt(i),
                            dp[i][j - 1] + (int) s2.charAt(j));
                } else {
                    dp[i][j] = computeMin(dp[i - 1][j - 1] + (int) s1.charAt(i) + (int) s2.charAt(j),
                            dp[i - 1][j] + (int) s1.charAt(i), dp[i][j - 1] + (int) s2.charAt(j));
                }
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
    }

    private int computeMin(int val1, int... val2) {
        int min = val1;
        for (int num : val2) {
            min = Math.min(num, min);
        }
        return min;
    }

    @Override
    public Object initData() {
        return null;
    }
}
