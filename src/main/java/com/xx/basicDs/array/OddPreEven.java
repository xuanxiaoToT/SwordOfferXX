package com.xx.basicDs.array;

import java.util.Collections;

/**
 * @author 玄霄
 * @CreateDate 2022/6/22
 * 调整数组顺序使奇数位于偶数前面
 */
public class OddPreEven {

    public static void main(String[] args) {
        OddPreEven oddPreEven = new OddPreEven();
        oddPreEven.answerOne();
    }

    /**
     * 快排的变种
     */
    private void answerOne() {
        int[] dataArray = initTestData();
        int left = 0;
        int right = dataArray.length - 1;
        while (left < right) {
            if (dataArray[left] % 2 == 0 && dataArray[right] % 2 == 1) {
                int temp = dataArray[left];
                dataArray[left] = dataArray[right];
                dataArray[right] = temp;
            }
            if (dataArray[left] % 2 != 0) {
                left++;
            }
            if (dataArray[right] % 2 == 0) {
                right--;
            }
        }
        System.out.println(Collections.singletonList(dataArray).toString());
    }

    private int[] initTestData() {
        int[] data = {1, 2, 4, 5, 6, 7};
        return data;
    }

}
