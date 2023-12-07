package com.xx.algorithm.math;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/7
 * <p>
 * x的平方根
 * LeetCode 69. Easy
 * <p>
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 提示：
 * 0 <= x <= 2^31 - 1
 *
 * Tag: 数学  二分
 */
public class SolvingSquareRoots implements Answer {

    public static void main(String[] args) {
        new SolvingSquareRoots().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        System.out.println((int) Math.sqrt(56));
    }

    public int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

    /**
     * 由于 x 平方根的整数部分 ans 是满足 k^2 <= x 最大 k 值，因此我们可以对 k 进行二分查找，从而得到答案。
     * <p>
     * 二分查找的下界为 0，上界可以粗略地设定为 x。在二分查找的每一步中，我们只需要比较中间元素 mid 的平方与 x 的大小关系，
     * 并通过比较的结果调整上下界的范围。由于我们所有的运算都是整数运算，不会存在误差，因此在得到最终的答案 ansa 后，
     * 也就不需要再去尝试 ans+1 了。
     */
    public int mySqrt2(int x) {
        int left = 0, right = x, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
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
