package com.xx.algorithm.other;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author 玄霄
 * @CreateDate 2022/7/26
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
 * 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
 * <p>
 * 不能使用除法。
 * <p>
 * 示例:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 */
public class BuildProductArray implements Answer {
    public static void main(String[] args) {
        new BuildProductArray().answerOne();
    }

    @Override
    public void answerOne() {
        int[] oriA = initData();
        int[] resultB = new int[oriA.length];
        int[] tempLeft = new int[oriA.length];
        int[] tempRight = new int[oriA.length];
        tempLeft[0] = oriA[0];
        for (int i = 1; i < oriA.length; i++) {
            tempLeft[i] = oriA[i] * tempLeft[i - 1];
        }
        tempRight[oriA.length - 1] = oriA[oriA.length - 1];
        for (int j = oriA.length - 2; j >= 0; j--) {
            tempRight[j] = oriA[j] * tempRight[j + 1];
        }

        for (int i = 0; i < resultB.length; i++) {
            if (i > 0 && i < resultB.length - 1) {
                resultB[i] = tempLeft[i - 1] * tempRight[i + 1];
            } else {
                resultB[0] = tempRight[1];
                resultB[resultB.length - 1] = tempLeft[resultB.length - 2];
            }
        }
        System.out.println(Arrays.toString(resultB));

    }

    @Override
    public int[] initData() {
        return new int[]{2, 3, 4, 5, 6};
    }
}
