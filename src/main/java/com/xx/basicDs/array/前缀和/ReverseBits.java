package com.xx.basicDs.array.前缀和;

import com.xx.Answer;

/**
 * 翻转数位
 * LeetCode Easy
 * <p>
 * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 * <p>
 * 示例 1：
 * 输入: num = 1775(110111011112)
 * 输出: 8
 * <p>
 * 示例 2：
 * 输入: num = 7(01112)
 * 输出: 4
 * <p>
 * Tag：前缀和  动态规划
 */
public class ReverseBits implements Answer {
    public static void main(String[] args) {
        new ReverseBits().answerOne();
    }

    @Override
    public void answerOne() {
        // int num = 1775;
        // int num = 7;
        // int num = 1;
        int num = 0;
        System.out.println(reverseBits(num));
    }

    /**
     * 动态规划
     * 使用用两个动态规划数组 current[] 和 reverse[]
     * <p>
     * current[i] 表示包含第i位的从num二进制低位至第i位连续1的最长长度
     * reverse[i] 表示包含第i位的从低位到第i位最多翻转1个0->1 的连续1的最长长度
     * <p>
     * num[i]=0时，连续中断，current[i]=0,而reverse[i]允许翻转1次，但是reverse[i]又必须包含第i位，
     * 也就是说只能翻转第i位，所以前面不能出现翻转，必须全是1，这个长度恰好就是current[i-1]，
     * 所以reverse[i] = current[i-1]+1
     *
     */
    public int reverseBits(int num) {
        int max = 0;
        int reverse = 0;
        int current = 0;
        for (int i = 0; i < 32; i++) {
            int temp = num & 1;
            if (temp == 0) {
                reverse = current + 1;
                current = 0;
            } else {
                current++;
                reverse++;
            }
            max = Math.max(max, reverse);
            num = num >> 1;
        }
        return max;
    }

    /**
     * 字符串遍历，前缀和。
     * 超过1%不到
     */
    public int reverseBitsOld(int num) {
        String binaryString = Integer.toBinaryString(num);
        if (binaryString.charAt(0) == '1') {
            binaryString = "0" + binaryString;
        }
        // 包括自己，以及自己左边的1的数
        int[] leftCount = new int[binaryString.length()];
        int tempCount = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            char c = binaryString.charAt(i);
            if (c == '1') {
                tempCount++;
                leftCount[i] = tempCount;
            } else {
                leftCount[i] = tempCount;
                tempCount = 0;
            }
        }
        tempCount = 0;
        int max = 0;
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            char c = binaryString.charAt(i);
            if (c == '0') {
                max = Math.max(max, tempCount + leftCount[i] + 1);
                tempCount = 0;
            } else {
                // 因为left已经包含了自身
                max = Math.max(max, tempCount + leftCount[i]);
                tempCount++;
            }
        }
        return max;
    }

    @Override
    public Object initData() {
        return null;
    }
}
