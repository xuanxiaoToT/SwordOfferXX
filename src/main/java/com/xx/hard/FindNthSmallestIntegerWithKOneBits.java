package com.xx.hard;

import com.xx.Answer;

/**
 * 二进制中恰好K个1的第N小整数
 * LeetCode 3821. Hard
 * <p>
 * 给你两个正整数 n 和 k。
 * <p>
 * 返回一个整数，表示其二进制表示中 恰好 包含 k 个 1 的第 n 小的正整数。题目保证答案 严格小于 2^50。
 * <p>
 * 示例 1：
 * 输入： n = 4, k = 2
 * 输出： 9
 * 解释：
 * 二进制表示中恰好包含 k = 2 个 1 的前 4 个正整数分别是：
 * 3 = 112
 * 5 = 1012
 * 6 = 1102
 * 9 = 10012
 * <p>
 * 示例 2：
 * 输入： n = 3, k = 1
 * 输出： 4
 * 解释：
 * 二进制表示中恰好包含 k = 1 个 1 的前 3 个正整数分别是：
 * 1 = 12
 * 2 = 102
 * 4 = 1002
 * <p>
 * 提示：
 * 1 <= n <= 250
 * 1 <= k <= 50
 * 答案严格小于 250。
 * <p>
 * Tag：试填法   组合数学  动态规划   位运算
 */
public class FindNthSmallestIntegerWithKOneBits implements Answer {
    private static final int MX = 50;
    // 因为k最大会取50，所以j初始化为MX+1。i为什么不取51呢？因为答案严格小于2^50，所以第50位为0即可。
    private static final long[][] comb = new long[MX][MX + 1];
    private static boolean initialized = false;

    // leetCode 判断题目时，会反复创建类，但是类里的static会一直在。等于初始化一次就能一直用
    public FindNthSmallestIntegerWithKOneBits() {
        if (initialized) {
            return;
        }
        initialized = true;

        // 预处理组合数
        // comb[i][j] 表示i位为0，且i-1位有j个1的组合数
        for (int i = 0; i < MX; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                // 等于i-1位为1的 comb[i - 1][j - 1] 加上i-1位为0的 comb[i - 1][j]
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
    }

    public static void main(String[] args) {
        new FindNthSmallestIntegerWithKOneBits().answer();
    }

    @Override
    public void answer() {
        long n = 4;
        int k = 2;
        System.out.println(nthSmallest(n, k));
    }

    /**
     * 从高到低，依次考虑答案二进制的第 i 位填 0 还是填 1。
     * 在示例 1 中，我们要计算第 4 小的二进制数，恰好包含 2 个 1。
     * 如果答案是 0__，那么我们要在剩下的 3 个位置上填 2 个 1，这一共有 C (3,2)=3 种方案，说明前 3 小的二进制数都是 0__。但 n=4>3，所以第 4 小的二进制数一定是 1__。
     * 填入 1 后，问题变成：
     * 计算第 4−3=1 小的二进制数，恰好包含 2−1=1 个 1。
     * 这是一个规模更小的子问题，用同样的方法解决。最终我们得到答案 1001。
     * 一般地，枚举 i=49,48,…,0，计算在 i 个位置上填 k 个 1 的方案数 c=C (i,k)，分类讨论：
     * 如果 n ≤ c，那么答案二进制的 i 位填 0。
     * 如果 n > c，说明 n 比较大，答案二进制的 i 位填 1。然后把 n 减少 c，k 减少 1，解决这个规模更小的子问题。
     * 代码实现时，可以预处理 50 以内的组合数（题目保证答案小于 2^50）
     */
    public long nthSmallest(long n, int k) {
        // 在第i个位置上填充k个1
        long ans = 0;
        for (int i = MX - 1; k > 0; i--) {
            // 第 i 位填 0 的方案数，也就是i-1位里包含k个1的组合数
            long c = comb[i][k];
            // n 比较大，第 i 位必须填1。也就是需要的排序n比你全部的组合大
            if (n > c) {
                n -= c;
                // 该位置为1
                ans |= 1L << i;
                // 维护剩余的 1 的个数
                k--;
            }
        }
        return ans;
    }

    @Override
    public Object initData() {
        return null;
    }
}
