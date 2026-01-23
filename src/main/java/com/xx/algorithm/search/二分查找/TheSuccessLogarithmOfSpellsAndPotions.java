package com.xx.algorithm.search.二分查找;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/10
 * <p>
 * 咒语和药水的成功对数
 * LeetCode 2300.  Medium
 * <p>
 * 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，
 * potions[j] 表示第 j 瓶药水的能量强度。
 * 同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
 * 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
 * <p>
 * 示例 1：
 * 输入：spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * 输出：[4,0,3]
 * 解释：
 * - 第 0 个咒语：5 * [1,2,3,4,5] = [5,10,15,20,25] 。总共 4 个成功组合。
 * - 第 1 个咒语：1 * [1,2,3,4,5] = [1,2,3,4,5] 。总共 0 个成功组合。
 * - 第 2 个咒语：3 * [1,2,3,4,5] = [3,6,9,12,15] 。总共 3 个成功组合。
 * 所以返回 [4,0,3] 。
 * <p>
 * 示例 2：
 * 输入：spells = [3,1,2], potions = [8,5,8], success = 16
 * 输出：[2,0,2]
 * 解释：
 * - 第 0 个咒语：3 * [8,5,8] = [24,15,24] 。总共 2 个成功组合。
 * - 第 1 个咒语：1 * [8,5,8] = [8,5,8] 。总共 0 个成功组合。
 * - 第 2 个咒语：2 * [8,5,8] = [16,10,16] 。总共 2 个成功组合。
 * 所以返回 [2,0,2] 。
 * <p>
 * 提示：
 * n == spells.length
 * m == potions.length
 * 1 <= n, m <= 10^5
 * 1 <= spells[i], potions[i] <= 10^5
 * 1 <= success <= 10^10
 * <p>
 * Tag：二分查找  排序  双指针
 */
public class TheSuccessLogarithmOfSpellsAndPotions implements Answer {

    public static void main(String[] args) {
        new TheSuccessLogarithmOfSpellsAndPotions().answer();
    }

    @Override
    public void answer() {
        // int[] spells = new int[]{5, 1, 3};
        // int[] potions = new int[]{1, 2, 3, 4, 5};
        // int success = 7;
        int[] spells = new int[]{14, 31, 7};
        int[] potions = new int[]{25, 19, 30, 37, 14, 30, 38, 22, 38, 38, 26, 33, 34, 23, 40, 28, 15, 29, 36, 39, 39, 37, 32, 38, 8, 17, 39, 20, 4, 39, 39, 7, 30, 35, 29, 23};
        int success = 317;
        System.out.println(Arrays.toString(successfulPairs(spells, potions, success)));
    }


    /**
     * 采用双指针，即拍完序之后，寻找target的时候采用双指针寻找
     */
    private void answerTwo() {
        // 略
    }

    /**
     * 采用二分查找，要查乘积大于每个数，转换为除法后，查找target的模型
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] result = new int[spells.length];
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            int count = computeSucCountByBS(spells[i], potions, success);
            result[i] = count;
        }
        return result;
    }

    /**
     * 二分的时候，注意相同数的情况
     */
    private int computeSucCountByBS(int spell, int[] potions, long success) {
        int target = (int) Math.ceil(success * 1.0 / spell);
        if (potions[0] >= target) {
            return potions.length;
        }
        int end = potions.length - 1;
        int start = 0;
        // 此处是小于等于
        while (start <= end) {
            int mid = (start + end) / 2;
            if (potions[mid] >= target && mid >= 1 && potions[mid - 1] < target) {
                return potions.length - mid;
            } else {
                if (potions[mid] < target) {
                    start = mid + 1;
                }
                if (potions[mid] >= target) {
                    end = mid - 1;
                }
            }
        }
        return 0;
    }

    /**
     * 最容易想到的，但是会直接超时
     * 此方法的时间复杂度 O(M*N)
     */
    public int[] successfulPairsMy(int[] spells, int[] potions, long success) {
        int[] result = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            int count = 0;
            for (int j = 0; j < potions.length; j++) {
                if (spells[i] * potions[j] >= success) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}