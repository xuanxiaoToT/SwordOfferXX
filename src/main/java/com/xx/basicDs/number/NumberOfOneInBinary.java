package com.xx.basicDs.number;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author 玄霄
 * @CreateDate 2022/8/2
 * <p>
 * 前n个数字二进制形式中1的个数
 * <p>
 * 输入一个非负数n，请计算0到n之间每个数字的二进制形
 * 式中1的个数，并输出一个数组。例如，输入的n为4，由于0、1、
 * 2、3、4的二进制形式中1的个数分别为0、1、1、2、1，因此输出数
 * 组[0，1，1，2，1]。
 */
public class NumberOfOneInBinary implements Answer {

    public static void main(String[] args) {
        new NumberOfOneInBinary().answerOne();
    }

    /**
     * 如果正整数i是一个偶
     * 数，那么i相当于将“i/2”左移一位的结果，因此偶数i和“i/2”的
     * 二进制形式中1的个数是相同的。如果i是奇数，那么i相当于将
     * “i/2”左移一位之后再将最右边一位设为1的结果，因此奇数i的二进
     * 制形式中1的个数比“i/2”的1的个数多1。
     */
    @Override
    public void answerOne() {
        Integer n = initData();
        int[] result = new int[n];
        result[0] = 0;
        result[1] = 1;
        for (int i = 2; i < n; i++) {
            if (i % 2 == 0) {
                result[i] = result[i / 2];
            } else {
                result[i] = result[i / 2] + 1;
            }
        }
        System.out.println(Arrays.toString(result));
    }

    /**
     * something
     */
    @Override
    public Integer initData() {
        return 5;
    }
}