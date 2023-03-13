package com.xx.algorithm.sort;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/6
 * <p>
 * 颜色分类
 * LeetCode 75
 * <p>
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 * 必须仅使用常数空间的一趟扫描算法
 * <p>
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 */
public class ColorClassification implements Answer {

    public static void main(String[] args) {
        new ColorClassification().answerOne();
    }

    /**
     * 解1：最容易想到的
     * 两趟扫描
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int left = 0;
        int right = data.length - 1;
        int zeroCount = 0;
        int twoCount = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) {
                zeroCount++;
            }
            if (data[i] == 2) {
                twoCount++;
            }
        }
        while (left <= right) {
            if (zeroCount > 0) {
                data[left] = 0;
                left++;
                zeroCount--;
            } else {
                if (twoCount > 0) {
                    data[right] = 2;
                    right--;
                    twoCount--;
                } else {
                    if (zeroCount == 0 && twoCount == 0) {
                        data[left] = 1;
                        left++;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(data));
    }

    /**
     * 只用一趟扫描 todo
     * 我们可以额外使用一个指针，即使用两个指针分别用来交换 0 和 1。
     */
    public void answerTwo() {
        int[] nums = initData();
        int left = 0;
        int right = nums.length - 1;

    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        //return new int[]{2, 0, 2, 1, 1, 0, 1, 0, 2, 1, 0};
        return new int[]{0};
    }
}
