package com.xx.basicDs.number;

import com.xx.Answer;

/**
 * 数字的补数
 * LeetCode 476. Easy
 * LeetCode 1009. Easy
 * <p>
 * 对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。
 * 例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。
 * 给你一个整数 num ，输出它的补数。
 * <p>
 * 示例 1：
 * 输入：num = 5
 * 输出：2
 * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 * <p>
 * 示例 2：
 * 输入：num = 1
 * 输出：0
 * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 * <p>
 * 提示：
 * 1 <= num < 2^31
 * <p>
 * Tag: 二进制
 *
 */
public class ComplementOfBaseTenInteger implements Answer {
    public static void main(String[] args) {
        new ComplementOfBaseTenInteger().answer();
    }

    @Override
    public void answer() {
        int n = 1000_000_000;
        System.out.println(bitwiseComplement(n));
    }

    public int bitwiseComplement(int n) {
        String binaryString = Integer.toBinaryString(n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i++) {
            char c = binaryString.charAt(i);
            if (c == '1') {
                sb.append('0');
            } else {
                sb.append('1');
            }
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    @Override
    public Object initData() {
        return null;
    }
}
