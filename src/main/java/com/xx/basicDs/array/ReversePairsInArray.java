package com.xx.basicDs.array;


import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/14
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 * <p>
 * 限制：
 * 0 <= 数组长度 <= 50000
 */
public class ReversePairsInArray implements Answer {

    public static void main(String[] args) {
        new ReversePairsInArray().answerOne();
    }

    /**
     * 时间复杂度O(N2) 实现简单，不会破坏原有数组，不需要额外空间
     */
    @Override
    public void answerOne() {
        int result = 0;
        int[] oriData = initData();

        for (int i = 0; i < oriData.length; i++) {
            for (int j = i + 1; j < oriData.length; j++) {
                if (oriData[j] < oriData[i]) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    /**
     * 时间复杂度O(N*LogN) 利用归并排序，在排序过程中记录逆序数组的数目。因为在排序前，左右的顺序是确定的
     * 缺点：会破坏原有数组，需要额外的O(N)空间
     */
    public void answerTwo() {

    }

    @Override
    public int[] initData() {
        return new int[]{7, 5, 6, 4};
    }
}