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
        new ZeroAfterFactorial().answer();
    }

    /**
     * 解1：0只会出现在与5相乘的时候
     * <p>
     * 结尾每多一个0，就必须有一个10参与相乘，而想要获得10，只有和5或者和5的倍数相乘：2×5=10, 4×5=20, 6×5=30......
     * <p>
     * 阶乘公式可以转化为 n! = 1×2×3×4×(1×5)×...×(2×5)×...×(3×5) ...×(n-1)×n
     * 假设5x为5的整数倍，阶乘乘到5x时，排在它前面的元素2x、4x一定会提供足够的偶数因子（其实一个2x就足够了），所以我们不必担心乘5为奇数得不到10的情况。
     * <p>
     * 公式：n! = 1×2×3×4×(1×5)×...×(2×5)×...×(3×5) ...×(n-1)×n中，从1乘到n，每经过一次5的倍数，就可以分出一个5（n每分出一个5，n阶乘就能保证给你合成一个10）
     * <p>
     * 那么只要求乘到n时一共经过几次5的倍数就好了，其实就是在求n÷5向下取整，所以直接n /= 5就好了
     */
    @Override
    public void answer() {
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
