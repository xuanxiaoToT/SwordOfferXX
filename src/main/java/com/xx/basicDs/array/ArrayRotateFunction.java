package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/2
 */
public class ArrayRotateFunction implements Answer {

    public static void main(String[] args) {
        new ArrayRotateFunction().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums = {-1,4,2,3};
        System.out.println(checkPossibility(nums));
    }

    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }
        // 最大最小要考虑吗
        int left = 0;
        int mid = 1;
        int right = 2;
        int errorCount = 0;
        while (right < nums.length) {
            if (nums[left] > nums[mid] && nums[mid] > nums[right]) {
                return false;
            } else if (nums[left] > nums[mid] || nums[mid] > nums[right]) {
                errorCount++;
            }
            left++;
            mid++;
            right++;
        }
        return errorCount <= 1;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
