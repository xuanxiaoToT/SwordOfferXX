package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/8
 * <p>
 * 移动零
 * LeetCode 283.
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 */
public class MoveZero implements Answer {

    public static void main(String[] args) {
        new MoveZero().answerOne();
    }

    /**
     * 解1：计算0的数，然后补零就行。大于0的数不变即可
     */
    @Override
    public void answerOne() {
        int i = 0;
        int[] nums = initData();
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
        }
        //将后面的位置补0
        for (int p = i; p < nums.length; p++) {
            nums[p] = 0;
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{0, 1, 0, 3, 12};
    }
}
