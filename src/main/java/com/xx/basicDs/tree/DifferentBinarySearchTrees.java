package com.xx.basicDs.tree;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/14
 * <p>
 * 不同的二叉搜索树
 * LeetCode 96.
 * <p>
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 */
public class DifferentBinarySearchTrees implements Answer {

    public static void main(String[] args) {
        new DifferentBinarySearchTrees().answerOne();
    }

    /**
     * 解1：采用动态规划(抄的答案)
     * <p>
     * 假设n个节点存在二叉排序树的个数是G(n)，令f(i)为以i为根的二叉搜索树的个数，则
     * G(n) = f(1) + f(2) + f(3) + f(4) + ... + f(n)G(n)=f(1)+f(2)+f(3)+f(4)+...+f(n)
     * 当i为根节点时，其左子树节点个数为i-1个，右子树节点为n-i，则
     * f(i) = G(i-1)*G(n-i)f(i)=G(i−1)∗G(n−i)
     * 综合两个公式可以得到 卡特兰数 公式
     * <p>
     * PS：公式很难想到，数学不太行~！
     */
    @Override
    public void answerOne() {
        Integer n = initData();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        System.out.println(dp[n]);
    }

    /**
     * 输出数据
     */
    @Override
    public Integer initData() {
        return 2;
    }
}
