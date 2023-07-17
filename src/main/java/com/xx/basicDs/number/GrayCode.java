package com.xx.basicDs.number;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/17
 * <p>
 * 格雷编码
 * LeetCode 89
 * <p>
 * n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
 * 每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
 * 第一个整数是 0 一个整数在序列中出现 不超过一次
 * 每对 相邻 整数的二进制表示 恰好一位不同 ，且
 * 第一个 和 最后一个 整数的二进制表示 恰好一位不同
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：[0,1,3,2]
 * 解释：
 * [0,1,3,2] 的二进制表示是 [00,01,11,10]
 * - 00 和 01 有一位不同
 * - 01 和 11 有一位不同
 * - 11 和 10 有一位不同
 * - 10 和 00 有一位不同
 * [0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01]
 * - 00 和 10 有一位不同
 * - 10 和 11 有一位不同
 * - 11 和 01 有一位不同
 * - 01 和 00 有一位不同
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[0,1]
 */
public class GrayCode implements Answer {

    public static void main(String[] args) {
        new GrayCode().answerOne();
    }

    /**
     * 解1：归纳法
     * 00  01
     * 0   1
     * 00  01   11  10
     * 0   1    3   2
     * 000  001  011  010  110 111  101  100
     * 0    1    3    2    6   7    5    4
     * 下一个数组的后一半：对称反转+2的n-1次方
     * 公式推导详见：
     * https://leetcode.cn/problems/gray-code/solutions/1196538/ge-lei-bian-ma-by-leetcode-solution-cqi7/
     */
    @Override
    public void answerOne() {
        int n = initData();
        List<Integer> ret = new ArrayList<Integer>();
        ret.add(0);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            for (int j = m - 1; j >= 0; j--) {
                ret.add(ret.get(j) | (1 << (i - 1)));
            }
        }
        System.out.println(ret);
    }

    /**
     * 公式法：直接牢记公式
     * 格雷码也可以使用公式直接求出。第 i(i≥0)个格雷码即为：
     * g(i) = i(+)[i/2]
     * 其中(+)指按位异或。
     */
    public void answerTwo() {

    }

    /**
     * 采用回溯法
     * 速度很慢
     */
    public void answerThree() {

    }

    /**
     * 输出数据
     */
    @Override
    public Integer initData() {
        return 3;
    }
}
