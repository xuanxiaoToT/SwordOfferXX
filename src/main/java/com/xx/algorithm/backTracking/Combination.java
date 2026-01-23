package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/1
 * <p>
 * 组合
 * LeetCode 77  Medium
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combination implements Answer {

    public static void main(String[] args) {
        new Combination().answer();
    }

    /**
     * 解1：经典回溯法+剪枝
     */
    @Override
    public void answer() {
        int n = 4;
        int k = 2;
        System.out.println(combine(n, k));
    }

    /**
     * 两个递归写法都可以用
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        //myDiGui(result, new ArrayList<>(k), 1, n, k);
        myDiGui2(result, new ArrayList<>(k), 1, n, k);
        return result;
    }

    // 非循环写法
    public void myDiGui(List<List<Integer>> result, List<Integer> temp, int num, int n, int k) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (num > n) {
            return;
        }
        // 选num
        temp.add(num);
        myDiGui(result, temp, num + 1, n, k);
        temp.remove(temp.size() - 1);

        //不选num
        myDiGui(result, temp, num + 1, n, k);
    }

    // 循环写法
    public void myDiGui2(List<List<Integer>> result, List<Integer> temp, int num, int n, int k) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (num > n) {
            return;
        }

        for (int i = num; i <= n; i++) {
            // 选num
            temp.add(i);
            myDiGui(result, temp, i + 1, n, k);
            temp.remove(temp.size() - 1);
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
