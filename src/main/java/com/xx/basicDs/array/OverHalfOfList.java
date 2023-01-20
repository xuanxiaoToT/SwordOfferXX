package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/29
 * 超过一半的数字
 * <p>
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class OverHalfOfList implements Answer {

    public static void main(String[] args) {
        OverHalfOfList overHalfOfList = new OverHalfOfList();
        overHalfOfList.answerOne();
    }

    /**
     * 抵消法
     */
    @Override
    public void answerOne() {
        Integer maxNum = null;
        int count = 0;
        List<Integer> list = initData();
        for (Integer num : list) {
            if (maxNum == null || count <= 0) {
                maxNum = num;
                count = 1;
            } else {
                if (maxNum.equals(num)) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        System.out.println(maxNum);

    }

    @Override
    public List<Integer> initData() {
        return Arrays.asList(1, 2, 3, 2, 2);
    }


}
