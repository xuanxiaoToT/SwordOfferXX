package com.xx.algorithm.recursion;

import com.xx.Answer;

public class FindKthBitInNthBinaryString implements Answer {
    public static void main(String[] args) {
        new FindKthBitInNthBinaryString().answer();
    }

    @Override
    public void answer() {
        int n = 5;
        int k = 30;

        // int n = 4;
        // int k = 11;

        // int n = 2;
        // int k = 3;
        System.out.println(findKthBit(n, k));
    }

    /**
     * 方法1：模拟
     */
    public char findKthBitOld(int n, int k) {
        if (k == 1) {
            return '0';
        }
        StringBuilder sb = new StringBuilder();
        sb.append("0");
        for (int i = 0; i < n; i++) {
            String last = sb.toString();
            sb.append("1");
            if (sb.length() == k) {
                return sb.charAt(sb.length() - 1);
            }
            for (int j = last.length() - 1; j >= 0; j--) {
                if (last.charAt(j) == '1') {
                    sb.append("0");
                } else {
                    sb.append("1");
                }
                if (sb.length() == k) {
                    return sb.charAt(sb.length() - 1);
                }
            }
        }
        return '-';
    }

    /**
     *
     * 递归法：
     * <p>
     * 观察二进制字符串 S_n，可以发现，当 n > 1 时，S_n 是在 S_{n-1} 的基础上形成的。
     * 用 len_n 表示 S_n 的长度，则 S_n 的前 len_{n-1} 个字符与 S_{n-1} 相同。
     * 还可以发现，当 n > 1 时，len_n = len_{n-1} * 2 + 1，根据 len_1 = 1 可知 len_n = 2^n - 1。
     * <p>
     * 由于 S_1 = "0"，且对于任意 n >= 1，S_n 的第 1 位字符也一定是 '0'，因此当 k = 1 时，直接返回字符 '0'。
     * <p>
     * 当 n > 1 时，S_n 的长度是 2^n - 1。S_n 可以分成三个部分：
     * <ul>
     *   <li>左边 2^(n-1) - 1 个字符是 S_{n-1}</li>
     *   <li>中间 1 个字符是 '1'（是 S_n 的第 2^(n-1) 位字符）</li>
     *   <li>右边 2^(n-1) - 1 个字符是 S_{n-1} 翻转与反转之后的结果</li>
     * </ul>
     * 因此如果 k = 2^(n-1)，直接返回字符 '1'。
     * <p>
     * 当 k != 2^(n-1) 时，考虑以下两种情况：
     * <ul>
     *   <li>如果 k < 2^(n-1)，则第 k 位字符在 S_n 的前半部分，即第 k 位字符在 S_{n-1} 中，因此在 S_{n-1} 中寻找第 k 位字符；</li>
     *   <li>如果 k > 2^(n-1)，则第 k 位字符在 S_n 的后半部分，由于后半部分为前半部分进行翻转与反转之后的结果，
     *       因此在前半部分寻找第 2^n - k 位字符，将其反转之后即为 S_n 的第 k 位字符。</li>
     * </ul>
     */
    public char findKthBit(int n, int k) {
        if (k == 1) {
            return '0';
        }
        int mid = (int) Math.pow(2, n - 1);
        if (k == mid) {
            return '1';
        }
        if (k < mid) {
            // 在前半部分
            return findKthBit(n - 1, k);
        }
        // 右半部分，等于n-1的前半部分，但是需要反转。并且0 1互换
        if (findKthBit(n - 1, mid * 2 - k) == '0') {
            return '1';
        } else {
            return '0';
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}
