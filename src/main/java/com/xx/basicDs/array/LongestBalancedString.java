package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/8
 * <p>
 * 最长平衡子字符串
 * LeetCode 2609  Easy
 * <p>
 * 给你一个仅由 0 和 1 组成的二进制字符串 s 。
 * 如果子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量，则认为 s 的这个子字符串是平衡子字符串。
 * 请注意，空子字符串也视作平衡子字符串。
 * 返回  s 中最长的平衡子字符串长度。
 * 子字符串是字符串中的一个连续字符序列。
 * <p>
 * 示例 1：
 * 输入：s = "01000111"
 * 输出：6
 * 解释：最长的平衡子字符串是 "000111" ，长度为 6 。
 * <p>
 * 示例 2：
 * 输入：s = "00111"
 * 输出：4
 * 解释：最长的平衡子字符串是 "0011" ，长度为  4 。
 * <p>
 * 示例 3：
 * 输入：s = "111"
 * 输出：0
 * 解释：除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。
 * <p>
 * 提示：
 * 1 <= s.length <= 50
 * '0' <= s[i] <= '1'
 * <p>
 * Tag: 计数法、双指针、字符串
 */
public class LongestBalancedString implements Answer {

    public static void main(String[] args) {
        new LongestBalancedString().answerOne();
    }

    @Override
    public void answerOne() {
        String s = initData();
        System.out.println(findTheLongestBalancedSubstring2(s));
    }

    /**
     * 通过计数法来实现
     */
    public int findTheLongestBalancedSubstring2(String s) {
        int result = 0;
        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                //如果前面是1，表明0是刚开始
                if (i > 0 && s.charAt(i - 1) == '1') {
                    zeroCount = 0;
                }
                oneCount = 0;
                zeroCount++;
            }
            if (s.charAt(i) == '1') {
                oneCount++;
                result = Math.max(Math.min(zeroCount, oneCount) * 2, result);
            }
        }
        return result;
    }

    /**
     * 第一把就想到的，检查01分界处，然后进行左右遍历
     */
    public int findTheLongestBalancedSubstring(String s) {
        int result = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0' && s.charAt(i + 1) == '1') {
                int subLength = computeSubLength(i, i + 1, s);
                result = Math.max(result, subLength);
                i = i + subLength / 2;
            }
        }
        return result;
    }

    public int computeSubLength(int left, int right, String s) {
        int result = 2;
        while (left >= 0 && right < s.length()) {
            left--;
            right++;
            if (left >= 0 && right < s.length() && s.charAt(left) == '0' && s.charAt(right) == '1') {
                result += 2;
            } else {
                return result;
            }
        }
        return result;
    }

    @Override
    public String initData() {
        return "01000111";
        // return "00111111111";
        // return "11111";
    }
}
