package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/21
 * <p>
 * 定长子串中元音的最大数目
 * LeetCode 1456. Medium
 * <p>
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * 英文中的 元音字母 为（a, e, i, o, u）。
 * <p>
 * 示例 1：
 * 输入：s = "abciiidef", k = 3
 * 输出：3
 * 解释：子字符串 "iii" 包含 3 个元音字母。
 * <p>
 * 示例 2：
 * 输入：s = "aeiou", k = 2
 * 输出：2
 * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
 * <p>
 * 示例 3：
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
 * <p>
 * 示例 4：
 * 输入：s = "rhythms", k = 4
 * 输出：0
 * 解释：字符串 s 中不含任何元音字母。
 * <p>
 * 示例 5：
 * 输入：s = "tryhard", k = 4
 * 输出：1
 * <p>
 * 提示：
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 1 <= k <= s.length
 * <p>
 * Tag：滑动窗口
 */
public class MaximumOfVowelsInFixedLenSubStr implements Answer {
    public static void main(String[] args) {
        new MaximumOfVowelsInFixedLenSubStr().answerOne();
    }

    @Override
    public void answerOne() {
        String str = "leetcode";
        int k = 3;
        System.out.println(computeMaxVowels(str, k));
    }

    public int computeMaxVowels(String str, int k) {
        int left = 0;
        int right = k - 1;
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int max = 0;
        int tempCount = 0;
        for (int i = 0; i < k; i++) {
            char c = str.charAt(i);
            tempCount = vowels.contains(c) ? tempCount + 1 : tempCount;
        }
        max = Math.max(tempCount, max);
        while (right < str.length()) {
            right++;
            if (right < str.length()) {
                char rightChar = str.charAt(right);
                tempCount = vowels.contains(rightChar) ? tempCount + 1 : tempCount;
                char leftChar = str.charAt(left);
                tempCount = vowels.contains(leftChar) ? tempCount - 1 : tempCount;
                left++;
                max = Math.max(tempCount, max);
            }
        }
        return max;
    }

    /**
     * 最快的方法
     */
    public int maxVowels(String s, int k) {
        int[] chars = new int[26];
        chars['a' - 0x61] = 1;
        chars['e' - 0x61] = 1;
        chars['i' - 0x61] = 1;
        chars['o' - 0x61] = 1;
        chars['u' - 0x61] = 1;
        char[] array = s.toCharArray();
        int temp = 0;
        for (int i = 0; i < k; i++) {
            temp += chars[array[i] - 0x61];
        }
        int max = temp;

        for (int i = k; i < array.length; i++) {
            temp -= chars[array[i - k] - 0x61];
            temp += chars[array[i] - 0x61];
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    @Override
    public Object initData() {
        return null;
    }
}