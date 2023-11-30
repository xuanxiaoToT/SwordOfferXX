package com.xx.basicDs.string;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/30
 * <p>
 * 确定两个字符串是否接近
 * LeetCode 1657. Medium
 * <p>
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 * <p>
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：word1 = "abc", word2 = "bca"
 * 输出：true
 * 解释：2 次操作从 word1 获得 word2 。
 * 执行操作 1："abc" -> "acb"
 * 执行操作 1："acb" -> "bca"
 * <p>
 * 示例 2：
 * 输入：word1 = "a", word2 = "aa"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 * <p>
 * 示例 3：
 * 输入：word1 = "cabbba", word2 = "abbccc"
 * 输出：true
 * 解释：3 次操作从 word1 获得 word2 。
 * 执行操作 1："cabbba" -> "caabbb"
 * 执行操作 2："caabbb" -> "baaccc"
 * 执行操作 2："baaccc" -> "abbccc"
 * <p>
 * 示例 4：
 * 输入：word1 = "cabbba", word2 = "aabbss"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 * <p>
 * 提示：
 * 1 <= word1.length, word2.length <= 10^5
 * word1 和 word2 仅包含小写英文字母
 *
 * Tag：排序  哈希  字符串
 */
public class DetermineWhetherTwoStringsAreClose implements Answer {

    public static void main(String[] args) {
        new DetermineWhetherTwoStringsAreClose().answerOne();
    }

    @Override
    public void answerOne() {
        String str1 = "cabbba";
        String str2 = "abbccc";
        System.out.println(whetherTwoStringsAreClose(str1, str2));
    }

    /**
     * 关键就是看清楚两个操作的本质！
     * 操作 1 的本质：字符可以任意排列
     * 操作 2 的本质：出现次数是可以交换
     */
    public boolean whetherTwoStringsAreClose(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        int[] mapStr1 = new int[26];
        int[] mapStr2 = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            mapStr1[c1 - 'a']++;
            mapStr2[c2 - 'a']++;
        }
        return whetherClose(mapStr1, mapStr2);
    }

    private boolean whetherClose(int[] mapStr1, int[] mapStr2) {
        int notCommon = 0;

        for (int i = 0; i < mapStr1.length; i++) {
            // 字母种类要一样
            if (mapStr1[i] != 0 && mapStr2[i] == 0 || mapStr1[i] == 0 && mapStr2[i] != 0) {
                return false;
            }
            if (mapStr1[i] != mapStr2[i]) {
                notCommon++;
            }
        }
        if (notCommon == 0) {
            return true;
        }
        Arrays.sort(mapStr1);
        Arrays.sort(mapStr2);
        for (int i = 0; i < mapStr1.length; i++) {
            if (mapStr1[i] != mapStr2[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object initData() {
        return null;
    }
}