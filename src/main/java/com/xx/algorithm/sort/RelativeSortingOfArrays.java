package com.xx.algorithm.sort;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/25
 * 数组相对排序
 * <p>
 * 输入两个数组arr1和arr2，其中数组arr2中的每个数字
 * 都唯一，并且都是数组arr1中的数字。请将数组arr1中的数字按照
 * 数组arr2中的数字的相对顺序排序。如果数组arr1中的数字在数组
 * arr2中没有出现，那么将这些数字按递增的顺序排在后面。假设数
 * 组中的所有数字都在0到1000的范围内。
 * <p>
 * 例如，输入的数组arr1和
 * arr2分别是[2，3，3，7，3，9，2，1，7，2]和[3，2，1]，则数组
 * arr1排序之后为[3，3，3，2，2，2，1，7，7，9]。
 */
public class RelativeSortingOfArrays implements Answer {

    public static void main(String[] args) {
        new RelativeSortingOfArrays().answer();
    }

    /**
     * 按照arr2的规则来码放，再吧剩余的排序放回去即可。
     */
    @Override
    public void answer() {
        int[] arr1 = initData();
        int[] arr2 = new int[]{3, 2, 1};
        Map<Integer, Integer> tempMap = new HashMap<>(arr2.length);
        for (int i : arr2) {
            tempMap.put(i, 0);
        }
        List<Integer> extra = new ArrayList<>();
        for (int i : arr1) {
            if (tempMap.containsKey(i)) {
                tempMap.put(i, tempMap.get(i) + 1);
            } else {
                extra.add(i);
            }
        }

        int index = 0;
        for (int i : arr2) {
            Integer count = tempMap.get(i);
            if (count > 0) {
                while (count > 0) {
                    arr1[index] = i;
                    index++;
                    count--;
                }
            }
        }
        extra.sort(Comparator.comparingInt(o -> o));
        for (Integer num : extra) {
            arr1[index] = num;
            index++;
        }
        System.out.println(arr1);
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{2, 3, 3, 7, 3, 9, 2, 1, 7, 2};
    }
}
