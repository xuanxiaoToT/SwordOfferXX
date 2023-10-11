package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.HashMap;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/11
 * <p>
 * 同构字符串
 * LeetCode 205. 简单
 * <p>
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
 * 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * <p>
 * 示例 1:
 * 输入：s = "egg", t = "add"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "foo", t = "bar"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：s = "paper", t = "title"
 * 输出：true
 * <p>
 * 提示：
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s 和 t 由任意有效的 ASCII 字符组成
 */
public class IsomorphicStrings implements Answer {

    public static void main(String[] args) {
        new IsomorphicStrings().answerOne();
    }

    @Override
    public void answerOne() {
        String s = "badc";
        String t = "baba";
        boolean result = validIsomorphicStrings(s, t);
        System.out.println(result);
    }

    /**
     * 优化，改一下复杂度，内存太多
     */
    public boolean validIsomorphicStrings(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> sMap = new HashMap<>(s.length());
        HashMap<Character, Character> tMap = new HashMap<>(t.length());
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sMap.containsKey(sChar) && tMap.containsKey(tChar)) {
                if (sMap.get(sChar) != tChar || tMap.get(tChar) != sChar) {
                    return false;
                }
            } else if (!sMap.containsKey(sChar) && !tMap.containsKey(tChar)) {
                sMap.put(sChar, tChar);
                tMap.put(tChar, sChar);
            } else {
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