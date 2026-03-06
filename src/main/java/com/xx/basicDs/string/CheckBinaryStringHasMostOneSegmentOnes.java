package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * 检查二进制字符串字段
 * LeetCode 1784. Easy
 * <p>
 * 给你一个二进制字符串 s ，该字符串 不含前导零 。
 * 如果 s 包含 零个或一个由连续的 '1' 组成的字段 ，返回 true 。否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：s = "1001"
 * 输出：false
 * 解释：由连续若干个 '1' 组成的字段数量为 2，返回 false
 * <p>
 * 示例 2：
 * 输入：s = "110"
 * 输出：true
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s[i]为 '0' 或 '1'
 * s[0] 为 '1'
 */
public class CheckBinaryStringHasMostOneSegmentOnes implements Answer {
    public static void main(String[] args) {
        new CheckBinaryStringHasMostOneSegmentOnes().answer();
    }

    @Override
    public void answer() {
        // String s = "1001";
        // String s = "110000";
        String s = "10101010111";
        System.out.println(checkOnesSegment(s));
    }

    public boolean checkOnesSegment(String s) {
        int count = 0;
        char last = '0';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1' && last != '1') {
                count++;

            }
            last = s.charAt(i);
        }
        return count <= 1;
    }

    @Override
    public Object initData() {
        return null;
    }
}
