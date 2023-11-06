package com.xx.algorithm.stateCompression;

import com.xx.Answer;
import com.xx.util.NumberUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/6
 * <p>
 * 最大单词长度乘积
 * LeetCode 318. Medium
 * <p>
 * 给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，并且这两个单词不含有公共字母。
 * 如果不存在这样的两个单词，返回 0 。
 * <p>
 * 示例 1：
 * 输入：words = ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出：16
 * 解释：这两个单词为 "abcw", "xtfn"。
 * <p>
 * 示例 2：
 * 输入：words = ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出：4
 * 解释：这两个单词为 "ab", "cd"。
 * <p>
 * 示例 3：
 * 输入：words = ["a","aa","aaa","aaaa"]
 * 输出：0
 * 解释：不存在这样的两个单词。
 * <p>
 * 提示：
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 */
public class ProductOfMaximumWordLength implements Answer {

    public static void main(String[] args) {
        new ProductOfMaximumWordLength().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String[] words = initData();
        System.out.println(maxProduct(words));
    }

    /**
     * 如果直接用{isValid} 的方式来进行验证，会在O(N2)的基础上再加重，所以会直接超时。
     * 考虑到题目明确仅会输入小写字母，则我们可以用一个32位的整数去进行表示。
     * 具体来说，某个单词出现了字母a，那么相应的那个int值的最低位置为1。
     * 这样判断某个字母是否存在便转换成了判断该位是否为1。
     * 判断两个单词是否有相同字母，则成了判断其 与  值是否为0.
     */
    public int maxProduct(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask = NumberUtil.setBit(mask, c - 'a' );
            }
            map.put(word, mask);
        }
        int maxResult = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                int valI = map.get(words[i]);
                int valJ = map.get(words[j]);
                if ((valI & valJ) == 0) {
                    maxResult = Math.max(maxResult, words[i].length() * words[j].length());
                }
            }
        }
        return maxResult;
    }

    /**
     * 最开始想到的方法。
     * 此方法没有用到题目的条件：即全部都是小写字母
     * 直接超时！
     */
    private boolean isValid(String word1, String word2) {
        Set<Character> set = new HashSet<>();
        for (char c : word1.toCharArray()) {
            set.add(c);
        }
        for (char c : word2.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输出数据
     */
    @Override
    public String[] initData() {
        return new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
    }
}
