package com.xx.basicDs.array.统计数组中的元素;

import com.xx.Answer;

/**
 * 主要元素
 * LeetCode Easy
 * <p>
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
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
 * Tag: 投票算法
 *
 */
public class FindMajorityElement implements Answer {
    public static void main(String[] args) {
        new FindMajorityElement().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums = new int[]{1, 2, 5, 9, 5, 9, 5, 5, 5};
        System.out.println(majorityElement(nums));
    }

    /**
     * 注意：跟{@link OverHalfOfList}的区别是，这个需要返回-1，可能不存在过半的。所以最后需要再检查一下
     */
    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int maxNum = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count <= 0) {
                maxNum = nums[i];
                count = 1;
                continue;
            }
            if (nums[i] == maxNum) {
                count++;
            } else {
                count--;
            }
        }
        int checkCount = 0;
        for (int num : nums) {
            if (num == maxNum) {
                checkCount++;
            }
        }
        return checkCount > nums.length / 2 ? maxNum : -1;
    }

    @Override
    public Object initData() {
        return null;
    }
}
