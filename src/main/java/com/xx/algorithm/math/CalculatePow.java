package com.xx.algorithm.math;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/9
 * <p>
 * Pow(x, n)
 * LeetCode 50. Medium
 * <p>
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -10^4 <= xn <= 10^4
 * <p>
 * Tag：二分法  数学运算
 */
public class CalculatePow implements Answer {
    public static void main(String[] args) {
        new CalculatePow().answer();
    }

    /**
     * 解1：直接用
     */
    @Override
    public void answer() {
        System.out.println(myPow2(1.0, -2147483648));
    }

    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }


    /**
     * 堆栈2分
     * 注意:当输入为 -2147483648时，乘以-1会溢出，导致一直为负数
     */
    public double myPow2(double x, long n) {
        if (n < 0) {
            return 1.0 / myPow2(x, -n);
        }
        if (n == 2) {
            return x * x;
        }
        if (n == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        double temp = myPow2(x, n / 2);
        if (n % 2 == 0) {
            return temp * temp;
        } else {
            return temp * temp * x;
        }
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
