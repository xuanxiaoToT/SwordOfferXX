package com.xx.basicDs.number.位运算;

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

    // 此题的关键是判断两个字符串是否拥有相同字母。
    private boolean isCommon(String str1, String str2) {
        //方法1，直接用hashMap实现

        //方法2：用长度26的数组来作为map

        //方法3：二进制表示：可以用一个int型整数记录某个字符串中
        // 出现的字符。如果字符串中包含'a'，那么整数最右边的数位为1；如
        // 果字符串中包含'b'，那么整数的倒数第2位为1，其余以此类推。这样
        // 做的好处是能更快地判断两个字符串是否包含相同的字符。如果两个
        // 字符串中包含相同的字符，那么它们对应的整数相同的某个数位都为
        // 1，两个整数的与运算将不会等于0。如果两个字符串没有相同的字
        // 符，那么它们对应的整数的与运算的结果等于0。
        int bitFlag1 = 0;
        int bitFlag2 = 0;
        for (char c : str1.toCharArray()) {
            bitFlag1 = NumberUtil.setBit(bitFlag1, c - 'a');
        }
        for (char c : str2.toCharArray()) {
            bitFlag2 = NumberUtil.setBit(bitFlag2, c - 'a');
        }
        return (bitFlag1 & bitFlag2) == 0;
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
