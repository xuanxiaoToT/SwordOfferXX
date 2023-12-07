package com.xx.algorithm.math;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/7
 * <p>
 * 阶乘后的零
 * LeetCode 172.  Medium
 * <p>
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * <p>
 * 提示: n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * <p>
 * 示例 2：
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 * <p>
 * 示例 3：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 提示：
 * 0 <= n <= 10^4
 * <p>
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 */
public class ZeroAfterFactorial implements Answer {

    public static void main(String[] args) {
        new ZeroAfterFactorial().answerOne();
    }

    /**
     * 解1：0只会出现在与5相乘的时候
     * <p>
     * n! 尾零的数量即为 n! 中因子 10 的个数，而 10=2×5，因此转换成求 n! 中质因子 2 的个数和质因子 5 的个数的较小值。
     * <p>
     * 实际上就是计算1-n之中有多少个5的因数。以130为例：
     * 1.第一次除以5时得到26，表明存在26个包含 [一] 个因数5的数；
     * 2.第二次除以5得到5，表明存在5个包含 [二] 个因数5的数(这些数字的一个因数5已经在第一次运算的时候统计了)；
     * 3.第三次除以5得到1，表明存在1个包含 [三] 个因数5的数(这些数字的两个因数5已经在前两次运算的时候统计了)；
     * 4.得到从1-n中所有5的因数的个数
     */
    @Override
    public void answerOne() {
        System.out.println(trailingZeroes(10));
    }

    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
