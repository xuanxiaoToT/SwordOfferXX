package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/29
 * <p>
 * 找出字符串中第一个匹配项的下标
 * LeetCode 28. 简单
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 * <p>
 * 示例 1：
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * <p>
 * 示例 2：
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 * <p>
 * 提示：
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 */
public class FindSubscriptTheFirstMatchInString implements Answer {

    public static void main(String[] args) {
        new FindSubscriptTheFirstMatchInString().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String[] data = initData();
        String haystack = data[0];
        String needle = data[1];
        System.out.println(strStr(haystack, needle));
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) {
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < needle.length(); j++) {
                if (i + j >= haystack.length() || haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }

        return -1;
    }


    /**
     * 输出数据
     */
    @Override
    public String[] initData() {
        //return new String[]{"sadbutsad", "sad"};
        return new String[]{"mississippi", "issipi"};
        //return new String[]{"aaa", "aaaa"};
    }
}
