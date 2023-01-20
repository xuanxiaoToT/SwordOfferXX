package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/21
 * <p>
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * <p>
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * <p>
 * 示例 2：
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 */
public class RotateStringLeft implements Answer {

    public static void main(String[] args) {
        new RotateStringLeft().answerOne();
    }

    @Override
    public void answerOne() {
        String s = initData();
        int k = 6;
        // 如果k大于一半了  则需要特殊处理
        char[] chars = s.toCharArray();
        char[] temp = s.substring(0, 6).toCharArray();
        for (int i = k; i < chars.length; i++) {
            chars[i - k] = chars[i];
        }
        for (int i = chars.length - k; i < chars.length; i++) {
            chars[i] = temp[i - (chars.length - k)];
        }
        System.out.println(chars);
    }

    @Override
    public String initData() {
        return "lrloseumgh";
    }

}
