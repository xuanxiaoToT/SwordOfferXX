package com.xx.basicDs.array.前缀和;

import com.xx.Answer;

/**
 * 使字符串平衡的最少删除次数
 * LeetCode 1653. Medium
 * <p>
 * 给你一个字符串 s ，它仅包含字符 'a' 和 'b'。
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
 * 请你返回使 s 平衡 的 最少 删除次数。
 * <p>
 * 示例 1：
 * 输入：s = "aababbab"
 * 输出：2
 * 解释：你可以选择以下任意一种方案：
 * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
 * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
 * <p>
 * 示例 2：
 * 输入：s = "bbaaaaabb"
 * 输出：2
 * 解释：唯一的最优解是删除最前面两个字符。
 * <p>
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 要么是 'a' 要么是 'b'
 * <p>
 * Tag: 前缀和   动态规划
 */
public class MinimumDeletionsToMakeStringBalanced implements Answer {
    public static void main(String[] args) {
        new MinimumDeletionsToMakeStringBalanced().answer();
    }

    @Override
    public void answer() {
        String s = "aababbab";
        // String s = "bbaaaaabb";
        // String s = "baaaaa";
        System.out.println(minimumDeletions(s));


    }

    /**
     * 动态规划
     * 考虑字符串 s 的最后一个字母：
     * <p>
     * 如果它是 'b'，则无需删除，问题规模缩小，变成「使 s 的前 n-1 个字母平衡的最少删除次数」。</li>
     * <li>如果它是 'a'：*
     * <li>删除它，则答案为「使 s 的前 n-1 个字母平衡的最少删除次数」加上 1。</li>
     * <li>保留它，那么前面的所有 'b' 都要删除。</li>
     * <p>
     * 设 cntBᵢ 为前 i 个字母中 'b' 的个数。定义 fᵢ 表示使 s 的前 i 个字母平衡的最少删除次数：
     *
     * <li>如果第 i 个字母是 'b'，则 fᵢ = fᵢ₋₁；</li>
     * <li>如果第 i 个字母是 'a'，则 fᵢ = min(fᵢ₋₁ + 1, cntBᵢ)。</li>
     *
     */
    public int minimumDeletions(String s) {
        int res = 0;
        int cntB = 0;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                // res 值不变
                cntB++;
            } else {
                // 删除这个a和全部删除b
                res = Math.min(res + 1, cntB);
            }
        }
        return res;
    }

    /**
     * 前缀和，记录左侧b的个数和右侧a的个数
     * 然后遍历：如果当前点的左侧全为a，右侧全为b应该删除多少。
     *
     */
    public int minimumDeletionsOld(String s) {
        int[] right = new int[s.length()];
        int sum = 0;
        // 右侧a的个数
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a') {
                sum++;
            }
            right[i] = sum;
        }
        int res = s.length();
        sum = 0;
        for (int i = 0; i < s.length(); i++) {
            // 左侧a的个数
            if (s.charAt(i) == 'b') {
                sum++;
            }
            // 这个点为分界线
            // 左侧的b和右侧的a
            int temp = sum + right[i] - 1;
            res = Math.min(res, temp);
        }
        return res;
    }

    @Override
    public Object initData() {
        return null;
    }
}
