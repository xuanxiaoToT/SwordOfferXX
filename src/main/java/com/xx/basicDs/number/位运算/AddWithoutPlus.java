package com.xx.basicDs.number.位运算;

import com.xx.Answer;

/**
 * 不用加号的加法
 * LeetCode Easy
 * <p>
 * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 * <p>
 * 示例：
 * 输入：a = 1, b = 1
 * 输出：2
 * <p>
 * 提示：
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 * <p>
 * Tag:位运算
 */
public class AddWithoutPlus implements Answer {
    public static void main(String[] args) {
        new AddWithoutPlus().answer();
    }

    @Override
    public void answer() {
        System.out.println(add(5, 6));
    }

    /**
     * 预备知识:
     * 有符号整数通常用补码来表示和存储，补码具有如下特征：
     * 正整数的补码与原码相同；负整数的补码为其原码除符号位外的所有位取反后加 1。
     * 可以将减法运算转化为补码的加法运算来实现。
     * 符号位与数值位可以一起参与运算。
     *
     */
    public int add(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    @Override
    public Object initData() {
        return null;
    }
}
