package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/16
 * <p>
 * 字符串转换整数
 * LeetCode 008
 * <p>
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。确定最终结果是负数还是正数。
 * 如果两者都不存在，则假定结果为正。读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。
 * 字符串的其余部分将被忽略。将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。
 * 必要时更改符号（从步骤 2 开始）。 如果整数数超过 32 位有符号整数范围 [−2 31, 2 31 − 1] ，
 * 需要截断这个整数，使其保持在这个范围内。
 * 具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * <p>
 * 示例 1：
 * 输入：s = "42"
 * 输出：42
 * <p>
 * 示例 2：
 * 输入：s = "   -42"
 * <p>
 * 示例 3：
 * 输入：s = "4193 with words"
 * 输出：4193
 */
public class StringConversionInteger implements Answer {

    public static void main(String[] args) {
        new StringConversionInteger().answerOne();
    }

    /**
     * 解1： 此题的关键是各类边界条件要考虑到位
     */
    @Override
    public void answerOne() {
        int data = myAtoi(initData());
        System.out.println(data);
    }

    private int myAtoi(String str) {
        int res = 0, k = 0;
        int length = str.length();
        if (length == 0)
            return 0;
        for (k = 0; k < length; k++) {
            if (str.charAt(k) != ' ') {
                break;
            }
        }

        if ((k >= length) || (str.charAt(k) != '+' && str.charAt(k) != '-') && (str.charAt(k) < '0' || str.charAt(k) > '9'))
            return 0;
        boolean b = true;
        if (str.charAt(k) == '+' || str.charAt(k) == '-') {
            b = str.charAt(k) == '+';
            k += 1;
        }
        for (int i = k; i < length; i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') break;
            if (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && str.charAt(i) > '7')
                return (b) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return (b) ? res : -res;
    }

    /**
     * 输出数据
     */
    @Override
    public String initData() {
        return "1024567";
    }
}
