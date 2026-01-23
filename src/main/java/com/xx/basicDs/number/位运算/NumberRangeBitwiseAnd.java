package com.xx.basicDs.number.位运算;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/7
 * <p>
 * 数字范围按位与
 * LeetCode 201. Medium
 * <p>
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，
 * 返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 * <p>
 * 示例 1：
 * 输入：left = 5, right = 7
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：left = 0, right = 0
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：left = 1, right = 2147483647
 * 输出：0
 * <p>
 * 提示：
 * 0 <= left <= right <= 2^31 - 1
 */
public class NumberRangeBitwiseAnd implements Answer {

    public static void main(String[] args) {
        new NumberRangeBitwiseAnd().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        System.out.println(rangeBitwiseAnd(5, 7));
    }

    /**
     * 对于一系列的位，例如 [1,1,0,1,1]，
     * 只要有一个零值的位，那么这一系列位的按位与运算结果都将为零。
     * <p>
     * 可以将问题重新表述为：给定两个整数，我们要找到它们对应的二进制字符串的公共前缀。
     */
    public int rangeBitwiseAnd(int left, int right) {
        if (left == right) {
            return left;
        }
        int count = 0;
        while (left < right) {
            left = left >> 1;
            right = right >> 1;
            count++;
        }
        for (int i = 0; i < count; i++) {
            left = left << 1;
        }
        return left;
    }

    /**
     * Brian Kernighan 算法
     * <p>
     * 「Brian Kernighan 算法」，它用于清除二进制串中最右边的 1。
     * <p>
     * Brian Kernighan 算法的关键在于我们每次对 number 和 number−1 之间进行按位与运算后，
     * number 中最右边的 1 会被抹去变成 0。
     */
    public int rangeBitwiseAnd2(int left, int right) {
        while (left < right) {
            // 抹去最右边的 1
            right = right & (right - 1);
        }
        return right;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
