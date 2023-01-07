package com.xx.algorithm.other;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/10/18
 * 最大的异或
 * <p>
 * 输入一个整数数组（每个数字都大于或等于0），请计算
 * 其中任意两个数字的异或的最大值。例如，在数组[1，3，4，7]
 * 中，3和4的异或结果最大，异或结果为7。
 * <p>
 * 异或：如果a、b两个值不相同，则异或结果为1
 */
public class MaxYiHuo implements Answer {

    public static void main(String[] args) {
        new MaxYiHuo().answerOne();
    }

    /**
     * 方法1：暴力遍历
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                int temp = data[i] ^ data[j];
                max = Math.max(max, temp);
            }
        }
        System.out.println(max);
    }

    /**
     * 方法2：转成二进制 前缀树。
     * 如果想找到某个整数k和其他整数的最大异或值，那么尽量找和k的数位不
     * 同的整数。
     * 因此，这个问题可以转化为查找的问题，而且还是按照整数的二
     * 进制数位进行查找的问题。需要将整数的每个数位都保存下来。前缀
     * 树可以实现这种思路，前缀树的每个节点对应整数的一个数位，路径
     * 对应一个整数。
     * 注：整数32位
     */
    public void answerTwo() {

    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 3, 4, 7};
    }
}
