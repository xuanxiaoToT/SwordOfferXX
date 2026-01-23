package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/6/20
 * <p>
 * 字符串压缩
 * LeetCode Easy
 * <p>
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。
 * 若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 * <p>
 * 示例1:
 * 输入："aabcccccaaa"
 * 输出："a2b1c5a3"
 * <p>
 * 示例2:
 * 输入："abbccd"
 * 输出："abbccd"
 * 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 * <p>
 * 提示：
 * 字符串长度在[0, 50000]范围内。
 */
public class CompressString implements Answer {

    public static void main(String[] args) {
        new CompressString().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        String S = "abbccd";
        System.out.println(compressString(S));
    }

    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        Character last = null;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (last == null) {
                count = 1;
                sb.append(c);
            } else {
                if (last == c) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(c);
                    count = 1;
                }
            }
            last = c;
        }
        if (count > 0) {
            sb.append(count);
        }
        String sbString = sb.toString();
        return sbString.length() >= S.length() ? S : sbString;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
