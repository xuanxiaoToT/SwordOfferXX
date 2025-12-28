package com.xx.basicDs.number.位运算;

import com.xx.Answer;

/**
 * 插入
 * LeetCode Easy
 * <p>
 * https://leetcode.cn/problems/insert-into-bits-lcci/description/?envType=study-plan-v2&envId=cracking-the-coding-interview
 * <p>
 * 给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
 * 编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。具体插入过程如图所示。
 * 题目保证从 i 位到 j 位足以容纳 M， 例如： M = 10011，则 i～j 区域至少可容纳 5 位。
 * <p>
 * 示例 1：
 * 输入：N = 1024(10000000000), M = 19(10011), i = 2, j = 6
 * 输出：N = 1100(10001001100)
 * <p>
 * 示例 2：
 * 输入：N = 0, M = 31(11111), i = 0, j = 4
 * 输出：N = 31(11111)
 * <p>
 * Tag：位运算
 * <p>
 * 知识点：
 * 任何比特 AND 0 都等于 0
 * 任何比特 AND 1 都等于 1
 * 跟0异或，还是他本身
 * 跟1异或，直接全变为0
 * (1 << i) - 1：生成 右侧i位全为1 的数。
 * 如何清除二进制的比特位？
 */
public class InsertIntoBits implements Answer {
    public static void main(String[] args) {
        new InsertIntoBits().answerOne();
    }

    @Override
    public void answerOne() {
        // int N = 1024;
        // int M = 19;
        // int i = 2;
        // int j = 6;

        // int N = 0;
        // int M = 31;
        // int i = 0;
        // int j = 4;

        // int N = 1148;
        // int M = 5;
        // int i = 1;
        // int j = 5;

        int N = 1143207437;
        int M = 1017033;
        int i = 11;
        int j = 31;
        System.out.println(insertBits(N, M, i, j));
    }

    /**
     * 首先，把 n 的二进制从低到高第 i 到 j 位清除（置为 0），这里 i 和 j 都是从 0 开始的。
     * 如何清除二进制的比特位？
     * 比如 i=3，j=4。
     * 先构造一个二进制数 11000，然后将其取反，得到 11⋯1100111。
     * 然后计算 n AND 11⋯1100111。
     * 由于任何比特 AND 0 都等于 0，所以我们清除了第 3 到 4 位；
     * 由于任何比特 AND 1 都不变，所以其余比特不变。
     * <p>
     * 如何构造 11000？
     * 构造 100，即 1 左移 j−i+1。
     * 把 100 减一，得到 11。
     * 把 11 左移 i 位，得到 11000。
     * 然后把 m 填入 n，m 的最低位在第 i 位。
     * <p>
     * 做法是把 m 左移 i 后，与 n 计算或。
     *
     */
    public int insertBits(int N, int M, int i, int j) {
        // M左移动i位
        int mbin = M << i;
        // 需要构造一个掩码，使得 第i位到第j位的值为0，其余位为1。
        // (1 << (j + 1)) - 1：生成  低j+1位全为1 的数。
        // (1L << i) - 1：生成 低i位全为1 的数。
        // 两者异或（^）后，中间i~j位会保留为1，其余位为0。
        long tempMask = ((1L << (j + 1)) - 1) ^ ((1L << i) - 1);
        // 对临时掩码取反（~），得到最终掩码：第i~j位为0，其余位为1。
        long finalMask = ~tempMask;
        // 这样才能用and消除
        long finalN = N & finalMask;
        long res = mbin ^ finalN;
        return Math.toIntExact(res);
    }

    /**
     * i是开始，靠右
     * 蠢的要死的字符串写法
     */
    public int insertBitsOld(int N, int M, int i, int j) {
        String mBin = Integer.toBinaryString(M);
        String nBin = Integer.toBinaryString(N);
        int len = j - i + 1;
        if (mBin.length() < len) {
            StringBuilder sb = new StringBuilder();
            sb.append("0".repeat(len - mBin.length()));
            sb.append(mBin);
            mBin = sb.toString();
        }

        nBin = "0".repeat(32 - nBin.length()) + nBin;
        StringBuilder sb = new StringBuilder();
        //
        String sub1 = nBin.substring(0, nBin.length() - j - 1);
        String sub2 = nBin.substring(nBin.length() - i);
        String res = sub1 + mBin + sub2;
        return Integer.parseInt(res, 2);
    }

    @Override
    public Object initData() {
        return null;
    }
}
