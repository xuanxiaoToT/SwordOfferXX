package com.xx.basicDs.number.位运算;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/6
 * <p>
 * 只出现一次的数字
 * LeetCode  136.  Easy
 * <p>
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * <p>
 * 示例 1 ：
 * 输入：nums = [2,2,1]
 * 输出：1
 * <p>
 * 示例 2 ：
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 * <p>
 * 示例 3 ：
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * 除了某个元素只出现一次以外，其余每个元素均出现两次。
 */
public class NumbersThatOnlyAppearOnce implements Answer {
    public static void main(String[] args) {
        new NumbersThatOnlyAppearOnce().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    /**
     * 利用二进制的特性
     * 一个数和它本身异或后是0，一个数与0异或后是他本身。
     */
    public int singleNumber(int[] nums) {
        //应该具有线性时间复杂度,且不得使用额外空间。
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            temp = temp ^ nums[i];
        }
        return temp;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
