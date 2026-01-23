package com.xx.basicDs.number.位运算;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/6
 * <p>
 * 位1的个数
 * LeetCode 191. Easy
 * <p>
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * <p>
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 3 中，输入表示有符号整数 -3。
 * <p>
 * 示例 1：
 * 输入：n = 00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * <p>
 * 示例 2：
 * 输入：n = 00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * <p>
 * 示例 3：
 * 输入：n = 11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 * <p>
 * 提示：
 * 输入必须是长度为 32 的 二进制串 。
 * <p>
 * 进阶：
 * 如果多次调用这个函数，你将如何优化你的算法？
 */
public class NumberOfBitsOne implements Answer {

    public static void main(String[] args) {
        new NumberOfBitsOne().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {

    }

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        int flag = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & flag) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    /**
     * 观察这个运算：n&(n−1)，其运算结果恰为把 n 的二进制位中的最低位的 1 变为 0 之后的结果。
     * <p>
     * 这样我们可以利用这个位运算的性质加速我们的检查过程，在实际代码中，
     * 我们不断让当前的 n 与 n−1 做与运算，直到 n 变为 0 即可。因为每次运算会使得 n 的最低位的 1 被翻转，
     * 因此运算次数就等于 n 的二进制位中 1 的个数。
     */
    public int hammingWeight2(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

    /**
     * 利用jdk自带的
     */
    public int hammingWeight3(int n) {
        return Integer.bitCount(1);
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
