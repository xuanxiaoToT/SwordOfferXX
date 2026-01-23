package com.xx.algorithm.recursion;

import com.xx.Answer;

/**
 * 递归乘法
 * 快速幂
 * LeetCode Medium
 * <p>
 * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
 * <p>
 * 示例 1：
 * 输入：A = 1, B = 10
 * 输出：10
 * <p>
 * 示例 2：
 * 输入：A = 3, B = 4
 * 输出：12
 * <p>
 * 提示：
 * 保证乘法范围不会溢出
 * <p>
 * Tag: 递归 快速幂
 */
public class RecursiveMultiply implements Answer {
    public static void main(String[] args) {
        new RecursiveMultiply().answer();
    }

    @Override
    public void answer() {
        int A = 5;
        int B = 10;
        System.out.println(multiply(A, B));
    }

    public int multiply(int A, int B) {
        if (A > B) {
            return multiplyDigui(B, A);
        } else {
            return multiplyDigui(A, B);
        }
    }

    public int multiplyDigui(int A, int B) {
        if (A == 1) {
            return B;
        }
        if (A % 2 == 0) {
            return multiply(A / 2, B) + multiply(A / 2, B);
        } else {
            int temp = (A - 1) / 2;
            return multiply(temp, B) + multiply(temp, B) + B;
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}
