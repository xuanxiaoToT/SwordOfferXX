package com.xx.basicDs.number;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/8/1
 * <p>
 * 输入两个表示二进制的字符串，请计算它们的和，并以
 * 二进制字符串的形式输出。例如，输入的二进制字符串分别
 * 是"11"和"10"，则输出"101"。
 */
public class BinaryAddition implements Answer {
    public static void main(String[] args) {
        new BinaryAddition().answerOne();
    }

    /**
     * 按正常进制算
     */
    @Override
    public void answerOne() {
        String num1 = "10";
        String num2 = "11";
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (index1 >= 0 || index2 >= 0) {
            int n1 = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int n2 = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            int newNum = n1 + n2 + carry;
            if (newNum >= 2) {
                carry = 1;
                sb.append(0);
            } else {
                carry = 0;
                sb.append(newNum);
            }
            index1--;
            index2--;
        }
        if (carry > 0) {
            sb.append(1);
        }
        sb.reverse().toString();
        System.out.println(sb);
    }

    /**
     * something
     */
    @Override
    public Object initData() {
        return null;
    }
}