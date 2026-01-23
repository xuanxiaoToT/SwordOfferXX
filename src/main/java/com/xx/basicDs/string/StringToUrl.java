package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/6/13
 * <p>
 * URL化
 * LeetCode Easy
 * <p>
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
 * （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 * <p>
 * 示例 1：
 * 输入："Mr John Smith    ", 13
 * 输出："Mr%20John%20Smith"
 * <p>
 * 示例 2：
 * 输入："               ", 5
 * 输出："%20%20%20%20%20"
 * <p>
 * 提示：
 * 字符串长度在 [0, 500000] 范围内。
 */
public class StringToUrl implements Answer {

    public static void main(String[] args) {
        new StringToUrl().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        String s = "Mr John Smith    ";
        System.out.println(replaceSpaces(s, 13));
    }

    public String replaceSpaces(String S, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
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
