package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/2
 * <p>
 * 非递减数列
 * LeetCode 665. Medium
 * <p>
 * 给你一个长度为 n 的整数数组 nums ，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个 4 变成 1 来使得它成为一个非递减数列。
 * <p>
 * 示例 2:
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^4
 * -10^5 <= nums[i] <= 10^5
 *
 * Tag: 数组
 */
public class NonDecreasingArray implements Answer {

    public static void main(String[] args) {
        new NonDecreasingArray().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        //int[] nums = { 4, 2, 3};
        //int[] nums = {-1, 4, 2, 3};
        int[] nums = {5, 7, 1, 8};
        System.out.println(checkPossibility(nums));
    }

    /**
     * 滑动窗口，三个三个考虑
     * 三个点分为两个情况：即凹型和凸型
     * 每个又分为左高右低和左低右高两种。
     * 分别修改不同的值，是的其非递减即可。
     */
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
            } else {
                // 凹型
                if (nums[left] > nums[mid] && nums[mid] <= nums[right]) {
                    nums[left] = nums[mid];
                    errorCount++;
                    if (nums[left] > nums[right]) {
                        nums[left] = nums[mid];
                    } else {
                        nums[mid] = nums[left];
                    }
                }
                //凸型
                if (nums[left] <= nums[mid] && nums[mid] > nums[right]) {
                    errorCount++;
                    if (nums[left] > nums[right]) {
                        nums[right] = nums[mid];
                    } else {
                        nums[mid] = nums[left];
                    }
                }
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
