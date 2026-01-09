package com.xx.basicDs.array.统计数组中的元素;

import com.xx.Answer;

import java.util.Arrays;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/29
 * <p>
 * 超过一半的数字
 * LeetCode Easy
 * <p>
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1：
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：[3,2]
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * Tag：
 */
public class OverHalfOfList implements Answer {

    public static void main(String[] args) {
        OverHalfOfList overHalfOfList = new OverHalfOfList();
        overHalfOfList.answerOne();
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Integer maxNum = null;
        int count = 0;
        for (int num : nums) {
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

        return maxNum;

    }

    /**
     * 抵消法
     */
    @Override
    public void answerOne() {
        // int[] nums = new int[]{1, 2, 5, 9, 5, 9, 5, 5, 5};
        int[] nums = new int[]{5, 5, 1, 2,};
        System.out.println(majorityElement(nums));
    }

    @Override
    public List<Integer> initData() {
        return Arrays.asList(1, 2, 3, 2, 2);
    }

}
