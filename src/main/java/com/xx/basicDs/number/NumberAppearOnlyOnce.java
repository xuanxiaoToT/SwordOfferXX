package com.xx.basicDs.number;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/2
 * <p>
 * 只出现一次的数字
 * <p>
 * 输入一个整数数组，数组中只有一个数字出现了一次，
 * 而其他数字都出现了3次。请找出那个只出现一次的数字。
 * 例如，
 * 输入的数组为[0，1，0，1，0，1，100]，则只出现一次的数字是
 * 100。
 * <p>
 * 一个整数是由32个0或1组成的。我们可以将数组中所有数字的同
 * 一位置的数位相加。如果将出现3次的数字单独拿出来，那么这些出现
 * 了3次的数字的任意第i个数位之和都能被3整除。因此，如果数组中所
 * 有数字的第i个数位相加之和能被3整除，那么只出现一次的数字的第i
 * 个数位一定是0；如果数组中所有数字的第i个数位相加之和被3除余
 * 1，那么只出现一次的数字的第i个数位一定是1。这样只出现一次的任
 * 意第i个数位可以由数组中所有数字的第i个数位之和推算出来。当我
 * 们知道一个整数任意一位是0还是1之后，就可以知道它的数值。
 */
public class NumberAppearOnlyOnce implements Answer {

    public static void main(String[] args) {
        new NumberAppearOnlyOnce().answerOne();
    }

    /**
     * 1.最简单的，用set来接
     * 2.重点：任何一个数字异或它自己的结果都是0,相同的3个数字异或的结果是数字本身
     */
    @Override
    public void answerOne() {
        int[] bitSums = new int[32];
        int[] nums = initData();
        for (int num : nums) {
            for (int i = 0; i < bitSums.length; i++) {
                //代码“（num>>（31-i））&1”用来得到整数num的二进制形式中从左数起第i个数位。
                bitSums[i] += (num >> (31 - i)) & 1;
            }
        }
        int result = 0;
        for (int bitSum : bitSums) {
            result = (result << 1) + bitSum % 3;
        }
        System.out.println(result);
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{0, 1, 0, 1, 0, 1, 100};
    }
}
