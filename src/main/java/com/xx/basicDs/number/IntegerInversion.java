package com.xx.basicDs.number;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/1/20
 * <p>
 * 整数反转
 * LeetCode 007
 * <p>
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 * <p>
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 * <p>
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 * <p>
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 */
public class IntegerInversion implements Answer {

    public static void main(String[] args) {
        new IntegerInversion().answer();
    }

    /**
     * 解1:解题的关键是关于溢出的判断；
     */
    @Override
    public void answer() {
        int temp;
        int newx = 0;
        int x = initData();
        while (x != 0) {
            temp = x % 10;
            x = x / 10;
            if (newx > Integer.MAX_VALUE / 10 || (newx == Integer.MAX_VALUE / 10 && temp > Integer.MAX_VALUE % 10)) {
                System.out.println(0);
            } else if (newx < Integer.MIN_VALUE / 10 || (newx == Integer.MIN_VALUE / 10 && x < Integer.MIN_VALUE % 10)) {
                System.out.println(0);
            }
            newx = newx * 10 + temp;
        }
        System.out.println(newx);
    }

    /**
     * 输出数据
     */
    @Override
    public Integer initData() {
        return -123;
    }
}
