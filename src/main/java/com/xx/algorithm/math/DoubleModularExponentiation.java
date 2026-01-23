package com.xx.algorithm.math;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/1
 * <p>
 * 双模幂运算
 * LeetCode 2961  Medium
 * <p>
 * 给你一个下标从 0 开始的二维数组 variables ，
 * 其中 variables[i] = [ai, bi, ci, mi]，以及一个整数 target 。
 * <p>
 * 如果满足以下公式，则下标 i 是 好下标：
 * 0 <= i < variables.length
 * ((aibi % 10)ci) % mi == target
 * 返回一个由 好下标 组成的数组，顺序不限 。
 * <p>
 * 示例 1：
 * 输入：variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
 * 输出：[0,2]
 * 解释：对于 variables 数组中的每个下标 i ：
 * 1) 对于下标 0 ，variables[0] = [2,3,3,10] ，(23 % 10)3 % 10 = 2 。
 * 2) 对于下标 1 ，variables[1] = [3,3,3,1] ，(33 % 10)3 % 1 = 0 。
 * 3) 对于下标 2 ，variables[2] = [6,1,1,4] ，(61 % 10)1 % 4 = 2 。
 * 因此，返回 [0,2] 作为答案。
 * <p>
 * 示例 2：
 * 输入：variables = [[39,3,1000,1000]], target = 17
 * 输出：[]
 * 解释：对于 variables 数组中的每个下标 i ：
 * 1) 对于下标 0 ，variables[0] = [39,3,1000,1000] ，(393 % 10)1000 % 1000 = 1 。
 * 因此，返回 [] 作为答案。
 * <p>
 * 提示：
 * 1 <= variables.length <= 100
 * variables[i] == [ai, bi, ci, mi]
 * 1 <= ai, bi, ci, mi <= 10^3
 * 0 <= target <= 10^3
 * <p>
 * Tag: 快速幂
 */
public class DoubleModularExponentiation implements Answer {

    public static void main(String[] args) {
        new DoubleModularExponentiation().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[][] variables = {{2, 3, 3, 10}, {3, 3, 3, 1}, {6, 1, 1, 4}};
        int target = 2;
        //int[][] variables = {{30, 5, 43, 2}, {15, 50, 35, 41}, {45, 34, 41, 32}, {14, 37, 33, 13}, {6, 8, 1, 53}, {37, 1, 12, 52}, {42, 37, 2, 52}, {9, 2, 15, 3}, {31, 12, 21, 24}, {52, 24, 6, 12}, {51, 35, 21, 52}, {30, 18, 10, 2}, {27, 31, 50, 27}, {29, 25, 26, 32}, {15, 38, 43, 17}, {22, 12, 16, 43}, {48, 9, 15, 6}, {41, 26, 22, 21}, {41, 49, 52, 26}, {53, 38, 9, 33}};
        //int target = 1;
        System.out.println(getGoodIndices(variables, target));
    }

    /**
     * 注意越界问题！
     * 对于幂函数pow，是用数值逼近的方法给出的，并不保证严格精确，执行效率也不高
     * <p>
     * 重点其实在于每次计算后都可以通过取模来减少数值，不取模便会溢出。
     * 能取模的原因是因为我们只需要%模数的部分进行计算，得到的结果是一样的，就像以10为模数，就只需要考虑个位数即可。
     */
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int[] variable = variables[i];
            int a = variable[0];
            int b = variable[1];
            int c = variable[2];
            int m = variable[3];
            if (target < m) {
                long v = getPow(getPow(a, b, 10), c, m);
                System.out.println(v);
                if ((int) v == target) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    //采用快速幂算法
    public int getPow(int a, int b, int mod) {
        if (b == 0) {
            return 1 % mod;
        }
        if (b == 1) {
            return a % mod;
        }
        if (b == 2) {
            return (a * a) % mod;
        }
        if (b == 3) {
            return (a * a * a) % mod;
        }
        int mid = b / 2;
        return (getPow(a, mid, mod) * getPow(a, b - mid, mod)) % mod;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
