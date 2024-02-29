package com.xx.basicDs.number.位运算;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/8
 * <p>
 * 二进制求和
 * LeetCode 67. Easy
 * <p>
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 * <p>
 * 示例 1：
 * 输入:a = "11", b = "1"
 * 输出："100"
 * <p>
 * 示例 2：
 * 输入：a = "1010", b = "1011"
 * 输出："10101"
 * <p>
 * 提示：
 * 1 <= a.length, b.length <= 10^4
 * a 和 b 仅由字符 '0' 或 '1' 组成
 * 字符串如果不是 "0" ，就不含前导零
 * <p>
 * Tag：字符串求和  二进制求和  位运算
 */
public class BinarySummation implements Answer {

    public static void main(String[] args) {
        new BinarySummation().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        System.out.println(addBinary("1111", "1111"));
    }

    /**
     * 知识点：
     * 字符转数字，可以用
     */
    public String addBinary(String a, String b) {
        int startA = a.length() - 1;
        int startB = b.length() - 1;
        int jin = 0;
        StringBuilder sb = new StringBuilder();
        while (startB >= 0 || startA >= 0) {
            int aNum = startA >= 0 ? a.charAt(startA) - '0' : 0;
            int bNum = startB >= 0 ? b.charAt(startB) - '0' : 0;
            int sum = aNum + bNum + jin;
            if (sum > 1) {
                jin = 1;
                sb.append(sum % 2);
            } else {
                sb.append(sum);
                jin = 0;
            }
            startA--;
            startB--;
        }
        if (jin == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
