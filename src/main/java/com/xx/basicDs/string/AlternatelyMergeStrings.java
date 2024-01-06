package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/6
 * <p>
 * 交替合并字符串
 * LeetCode 1768. Easy
 * <p>
 * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。
 * 如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
 * 返回 合并后的字符串 。
 * <p>
 * 示例 1：
 * 输入：word1 = "abc", word2 = "pqr"
 * 输出："apbqcr"
 * 解释：字符串合并情况如下所示：
 * word1：  a   b   c
 * word2：    p   q   r
 * 合并后：  a p b q c r
 * <p>
 * 示例 2：
 * 输入：word1 = "ab", word2 = "pqrs"
 * 输出："apbqrs"
 * 解释：注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
 * word1：  a   b
 * word2：    p   q   r   s
 * 合并后：  a p b q   r   s
 * <p>
 * 示例 3：
 * 输入：word1 = "abcd", word2 = "pq"
 * 输出："apbqcd"
 * 解释：注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
 * word1：  a   b   c   d
 * word2：    p   q
 * 合并后：  a p b q c   d
 * <p>
 * 提示：
 * 1 <= word1.length, word2.length <= 100
 * word1 和 word2 由小写英文字母组成
 * <p>
 * Tag: 字符串合并
 */
public class AlternatelyMergeStrings implements Answer {

    public static void main(String[] args) {
        new AlternatelyMergeStrings().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {

    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int left = 0;
        int right = 0;
        while (left < word1.length() && right < word2.length()) {
            sb.append(word1.charAt(left));
            sb.append(word2.charAt(right));
            left++;
            right++;
        }
        if (left != word1.length() || right != word2.length()) {
            if (left < word1.length()) {
                sb.append(word1.substring(left));
            } else {
                sb.append(word2.substring(right));
            }
        }
        return sb.toString();
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
