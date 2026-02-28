package com.xx.basicDs.number;

import com.xx.Answer;

/**
 * 连接连续二进制数字
 * LeetCode 1680. Medium
 * <p>
 * 给你一个整数 n ，请你将 1 到 n 的二进制表示连接起来，并返回连接结果对应的 十进制 数字对 10^9 + 7 取余的结果。
 * <p>
 * 示例 1：
 * 输入：n = 1
 * 输出：1
 * 解释：二进制的 "1" 对应着十进制的 1 。
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：27
 * 解释：二进制下，1，2 和 3 分别对应 "1" ，"10" 和 "11" 。
 * 将它们依次连接，我们得到 "11011" ，对应着十进制的 27 。
 * <p>
 * 示例 3：
 * 输入：n = 12
 * 输出：505379714
 * 解释：连接结果为 "1101110010111011110001001101010111100" 。
 * 对应的十进制数字为 118505380540 。
 * 对 10^9 + 7 取余后，结果为 505379714 。
 * <p>
 * 提示：
 * 1 <= n <= 10^5
 * <p>
 * Tag：二进制迭代法  模运算定律
 */
public class ConcatenationOfConsecutiveBinaryNumbers implements Answer {
    public static void main(String[] args) {
        new ConcatenationOfConsecutiveBinaryNumbers().answer();
    }

    @Override
    public void answer() {
        int n = 50;
        System.out.println(concatenatedBinary(50));
    }

    /**
     * 模运算的分配律和结合律：
     * (a × b + c) % MOD = [(a % MOD) × (b % MOD) + c % MOD] % MOD
     * (A + B) % MOD = ( (A % MOD) + (B % MOD) ) % MOD
     * <p>
     * 二进制的递推法求十进制：
     * 假设前k位的十进制的值为 f(K)
     * 等同于前k位左移1位，加上k+1位，也就是 f(K)*2
     * 则f(k+1) = f(K)*2+b(k+1)
     * 在用这个结合摸运算的分配率可得：
     * remainder = (remainder * 2 + currentBit) % MOD;
     */
    public int concatenatedBinary(int n) {
        int MOD = 1000_000_007;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(Integer.toBinaryString(i));
        }
        String binaryStr = sb.toString();
        long remainder = 0;
        for (int i = 0; i < binaryStr.length(); i++) {
            int currentBit = binaryStr.charAt(i) - '0';
            // 核心方法是逐位迭代 + 模运算分配律
            remainder = (remainder * 2 + currentBit) % MOD;
        }
        return Math.toIntExact(remainder);
    }

    @Override
    public Object initData() {
        return null;
    }
}
