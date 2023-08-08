package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/27
 * <p>
 * 最接近的三数之和
 * LeetCode 016
 * <p>
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 */
public class ThreeSumClosest implements Answer {

    public static void main(String[] args) {
        new ThreeSumClosest().answerOne();
    }

    /**
     * 解1：最简单的，直接三次遍历。
     * 解：略
     * 解2：利用双指针法。
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int targetNum = initTargetNum();
        Arrays.sort(data);
        int cha = Integer.MAX_VALUE;
        for (int i = 0; i < data.length; i++) {

        }
    }

    public int initTargetNum() {
        return 1;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{-1, 2, 1, -4};
    }
}
