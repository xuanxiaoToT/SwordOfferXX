package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/6/26
 * <p>
 * 字符串轮转。
 * LeetCode Easy
 * <p>
 * 给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 * <p>
 * 示例1:
 * 输入：s1 = "waterbottle", s2 = "erbottlewat"
 * 输出：True
 * <p>
 * 示例2:
 * 输入：s1 = "aa", s2 = "aba"
 * 输出：False
 * 提示：
 * <p>
 * 字符串长度在[0, 100000]范围内。
 * 说明:
 * <p>
 * 你能只调用一次检查子串的方法吗？
 */
public class StringRotation implements Answer {

    public static void main(String[] args) {
        new StringRotation().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String s1 = "", s2 = "";
        System.out.println(isFlipedString(s1, s2));
    }

    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.isEmpty() && s2.isEmpty()) {
            return true;
        }
        if (s1.isEmpty() || s2.isEmpty()) {
            return false;
        }
        char first = s1.charAt(0);
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == first) {
                String afterS2 = s2.substring(i, s2.length());
                String preS2 = s2.substring(0, i);
                String afterS1 = s1.substring(0, s2.length() - i);
                String preS1 = s1.substring(s2.length() - i, s1.length());
                if (afterS2.equals(afterS1) && preS1.equals(preS2)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 方法二：搜索子字符串
     * 思路
     * <p>
     * 首先，如果 s1和 s2的长度不一样，那么无论怎么轮转，s1都不能得到 s2，
     * 返回 false。字符串 s2+s2 包含了所有 s1 可以通过轮转操作得到的字符串，只需要检查 s
     * 2是否为 s2+s2 的子字符串即可。具体可以参考「28. 实现 strStr() 的官方题解」的实现代码，本题解中采用直接调用库函数的方法。
     */
    public boolean isFlipedString2(String s1, String s2) {
        return s1.length() == s2.length() && (s2 + s2).contains(s1);
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
