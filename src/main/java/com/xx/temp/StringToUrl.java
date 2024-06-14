package com.xx.temp;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/6/13
 */
public class StringToUrl implements Answer {

    public static void main(String[] args) {
        new StringToUrl().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
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
