package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Objects;


/**
 * 最长的平衡子串I
 * LeetCode 3713. Medium-
 * <p>
 * 给你一个由小写英文字母组成的字符串 s。
 * 如果一个 子串 中所有 不同 字符出现的次数都 相同 ，则称该子串为 平衡 子串。
 * 请返回 s 的 最长平衡子串 的 长度 。
 * 子串 是字符串中连续的、非空 的字符序列。
 * <p>
 * 示例 1：
 * 输入： s = "abbac"
 * 输出： 4
 * 解释：
 * 最长的平衡子串是 "abba"，因为不同字符 'a' 和 'b' 都恰好出现了 2 次。
 * <p>
 * 示例 2：
 * 输入： s = "zzabccy"
 * 输出： 4
 * 解释：
 * 最长的平衡子串是 "zabc"，因为不同字符 'z'、'a'、'b' 和 'c' 都恰好出现了 1 次。
 * <p>
 * 示例 3：
 * 输入： s = "aba"
 * 输出： 2
 * 解释：
 * 最长的平衡子串之一是 "ab"，因为不同字符 'a' 和 'b' 都恰好出现了 1 次。另一个最长的平衡子串是 "ba"。
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成。
 * <p>
 * Tag: 哈希
 */
public class LongestBalancedSubstringI implements Answer {
    public static void main(String[] args) {
        new LongestBalancedSubstringI().answer();
    }

    @Override
    public void answer() {
        String s = "rps";
        System.out.println(longestBalanced(s));
    }

    /**
     * 因为长度要求只有1000，所以暴力遍历。
     * 优化点：
     * 1.用int[]数组替代map，效率更高。
     * 2.isBalanced判断，可以用map.size*max 如果等于 j - i + 1，则说明都相同了，且都等于max。
     * 3.如果剩余的长度已经小于res了，则不用再继续遍历
     *
     */
    public int longestBalanced(String s) {
        // HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] map = new int[26];
            int type = 0;
            int max = 0;
            // 如果剩余的长度已经小于res了，则不用再继续遍历
            if (s.length() - i <= res) {
                break;
            }
            for (int j = i; j < s.length(); j++) {
                int index = s.charAt(j) - 'a';
                map[index]++;
                if (map[index] == 1) {
                    type++;
                }
                max = Math.max(max, map[index]);
                int length = j - i + 1;
                if (type * max == length) {
                    res = Math.max(res, length);
                }
            }
        }
        return res;
    }

    // 不用全部遍历map
    private boolean isBalanced(HashMap<Character, Integer> map, Integer base) {
        for (Integer value : map.values()) {
            if (!Objects.equals(value, base)) {
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
