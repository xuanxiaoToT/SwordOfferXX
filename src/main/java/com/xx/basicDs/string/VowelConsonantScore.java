package com.xx.basicDs.string;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 元音辅音得分
 * LeetCode 3813. Easy
 * <p>
 * 给你一个字符串 s，由小写英文字母、空格和数字组成。
 * 令 v 表示 s 中元音字母的数量，c 表示辅音字母的数量。
 * 元音字母是 'a'、'e'、'i'、'o' 和 'u'，而英文字母表中除元音外的其他字母均视为辅音字母。
 * 字符串 s 的 得分 定义如下：
 * 如果 c > 0，则 score = floor(v / c)，其中 floor 表示 向下取整 到最接近的整数。
 * 否则，如果 c = 0，则 score = 0。
 * 返回一个整数，表示字符串的得分。
 * <p>
 * 示例 1：
 * 输入: s = "cooear"
 * 输出: 2
 * 解释:
 * 字符串 s = "cooear" 包含 v = 4 个元音字母 ('o', 'o', 'e', 'a') 和 c = 2 个辅音字母 ('c', 'r')。
 * 得分为 floor(v / c) = floor(4 / 2) = 2。
 * <p>
 * 示例 2：
 * 输入: s = "axeyizou"
 * 输出: 1
 * 解释:
 * 字符串 s = "axeyizou" 包含 v = 5 个元音字母 ('a', 'e', 'i', 'o', 'u') 和 c = 3 个辅音字母 ('x', 'y', 'z')。
 * 得分为 floor(v / c) = floor(5 / 3) = 1。
 * <p>
 * 示例 3：
 * 输入: s = "au 123"
 * 输出: 0
 * 解释:
 * 字符串 s = "au 123" 不包含辅音字母 (c = 0)，因此得分为 0。
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s 仅由小写英文字母、空格和数字组成。
 * <p>
 * Tag：字符串遍历  哈希
 */
public class VowelConsonantScore implements Answer {
    public static void main(String[] args) {
        new VowelConsonantScore().answerOne();
    }

    @Override
    public void answerOne() {
        String s = "au 123";
        System.out.println(vowelConsonantScore(s));
    }

    /**
     * 仔细读题，不要着急。
     * 反正也是三题选手，急什么
     */
    public int vowelConsonantScore(String s) {
        Set<Character> yuanYin = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int vCount = 0;
        int cCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                continue;
            }
            if (c == ' ') {
                continue;
            }
            if (yuanYin.contains(c)) {
                vCount++;
            } else {
                cCount++;
            }
        }
        return cCount == 0 ? 0 : Math.floorDiv(vCount, cCount);
    }

    @Override
    public Object initData() {
        return null;
    }
}
