package com.xx.algorithm.search;

import com.xx.Answer;

import java.util.Random;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/20
 * 按权重生成随机数
 * <p>
 * 输入一个正整数数组w，数组中的每个数字w[i]表示下标
 * i的权重，请实现一个函数pickIndex根据权重比例随机选择一个下
 * 标。
 * <p>
 * 例如，如果权重数组w为[1，2，3，4]，那么函数pickIndex将
 * 有10%的概率选择0、20%的概率选择1、30%的概率选择2、40%的概率
 * 选择3。
 */
public class GenerateRandomNumberByWeight implements Answer {

    public static void main(String[] args) {
        new GenerateRandomNumberByWeight().answer();
    }

    /**
     * 题目的关键是如何实现按照概率选择数字。
     * 产生一个100内的随机整数：需要10%的概率，就判断此数字是否在1-10期间。
     */
    @Override
    public void answer() {
        int[] nums = initData();
        // 现根据权重计算全部的比例
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum == 0) {
            return;
        }
        int[] probability = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            probability[i] = nums[i] / sum;
        }

        int randomNum = new Random().nextInt(100) + 1;

        // 查找 probability里面第一个比他大的数，然后返回其下标即可。

        //  二分查找，略。注意二分条件

    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 4};
    }
}