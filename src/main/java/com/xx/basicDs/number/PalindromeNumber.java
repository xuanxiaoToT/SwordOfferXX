package com.xx.basicDs.number;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/1/12
 * <p>
 * 回文数
 * LeetCode 09
 * <p>
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 能不将整数转为字符串来解决这个问题吗？
 * <p>
 * 例如，121 是回文，而 123 不是。
 * <p>
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 */
public class PalindromeNumber implements Answer {

    public static void main(String[] args) {
        new PalindromeNumber().answerOne();
    }

    /**
     * 解1：不将数字转换为字符串
     * 则需要用其它方式拆分位值
     */
    @Override
    public void answerOne() {
        Integer data = initData();
        if (data < 0 || data % 10 == 0) {
            System.out.println(false);
        }
        //后半部分逆序 若等于前半部分，则表明是回文
        int right = 0;
        while (data > right) {
            int temp = data % 10;
            data = data / 10;
            right = right * 10 + temp;
        }
        if (data == right) {
            System.out.println(true);
        } else {
            System.out.println(right / 10 == data);
        }
        System.out.println(right);
    }

    /**
     * 输出数据
     */
    @Override
    public Integer initData() {
        return 123321;
    }
}
