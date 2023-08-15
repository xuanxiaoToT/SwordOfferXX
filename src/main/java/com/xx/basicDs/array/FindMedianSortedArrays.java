package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/15
 * <p>
 * 寻找两个正序数组的中位数
 * LeetCode 004
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
public class FindMedianSortedArrays implements Answer {

    public static void main(String[] args) {
        new FindMedianSortedArrays().answerOne();
    }

    /**
     * 解1：双指针遍历
     * 时间复杂度O(m+n)
     */
    @Override
    public void answerOne() {
        int[] numA = new int[]{2, 3};
        int[] numB = new int[]{1};
        System.out.println(findMedianSortedArrays(numA, numB));
    }

    /**
     * 很明显解法1没有达到题目要求的log(m+n)
     * 所以必须要使用二分法来做
     */
    public void answerTwo() {
        //todo
    }


    public double findMedianSortedArrays(int[] numA, int[] numB) {
        int i = 0;
        int j = 0;
        int mid;
        double result = 0;
        boolean evenFlag = false;
        if ((numA.length + numB.length) % 2 == 0) {
            evenFlag = true;
            mid = (numA.length + numB.length) / 2;
        } else {
            mid = (numA.length + numB.length + 1) / 2;
        }
        int preTemp = 0;
        while (i < numA.length && j < numB.length) {
            if (i + j == mid) {
                if (evenFlag) {
                    result = (Math.min(numA[i], numB[j]) + preTemp) * 1.0 / 2;
                } else {
                    result = preTemp;
                }
                return result;
            }
            if (numA[i] >= numB[j]) {
                preTemp = numB[j];
                j++;
            } else {
                preTemp = numA[i];
                i++;
            }
        }

        if (i >= numA.length) {
            int remainder = mid - numA.length - j;
            for (int k = 0; k < remainder; k++) {
                preTemp = numB[j];
                j++;
            }
            if (evenFlag) {
                result = (numB[j] + preTemp) * 1.0 / 2;
                return result;
            } else {
                return preTemp;
            }
        }
        if (j >= numB.length) {
            int remainder = mid - numB.length - i;
            for (int k = 0; k < remainder; k++) {
                preTemp = numA[i];
                i++;
            }
            if (evenFlag) {
                result = (numA[i] + preTemp) * 1.0 / 2;
                return result;
            } else {
                return preTemp;
            }
        }
        return result;
    }

    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{}, {}};
    }
}
