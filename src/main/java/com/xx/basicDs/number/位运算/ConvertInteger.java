package com.xx.basicDs.number.位运算;

import com.xx.Answer;

/**
 * 整数转换
 * LeetCode Easy
 * <p>
 * 整数转换。编写一个函数，确定需要改变几个位才能将整数 A 转成整数 B。
 * <p>
 * 示例 1：
 * 输入：A = 29 （或者 0b11101）, B = 15（或者 0b01111）
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：A = 1，B = 2
 * 输出：2
 * <p>
 * 提示:
 * A，B范围在[-2147483648, 2147483647]之间
 * <p>
 * Tag:位运算 逻辑位移和算数位移的区别
 */
public class ConvertInteger implements Answer {
    public static void main(String[] args) {
        new ConvertInteger().answer();
    }

    @Override
    public void answer() {
        // int A = 29;
        // int B = 15;

        int A = 826966453;
        int B = -729934991;
        System.out.println(convertInteger(A, B));
    }

    public int convertInteger(int A, int B) {
        int result = 0;
        while (B != 0 || A != 0) {
            int maxRightA = A & 1;
            A = A >> 1;
            int maxRightB = B & 1;
            B = B >> 1;
            if (maxRightA != maxRightB) {
                result++;
            }
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
