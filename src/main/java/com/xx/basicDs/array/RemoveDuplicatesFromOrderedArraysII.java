package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/25
 * <p>
 * 删除有序数组中的重复项 II
 * LeetCode 80.  Medium
 * <p>
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 说明：
 * 为什么返回数值是整数，但输出的答案是数组呢？
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3。不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 */
public class RemoveDuplicatesFromOrderedArraysII implements Answer {

    public static void main(String[] args) {
        new RemoveDuplicatesFromOrderedArraysII().answer();
    }

    @Override
    public void answer() {
        int[] data = initData();
        int left = 1;
        int right = 1;
        int count = 1;
        while (right < data.length) {
            if (data[right] == data[right - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count <= 2) {
                data[left] = data[right];
                left++;
            }
            right++;
        }
        System.out.println(left);
        System.out.println(Arrays.toString(data));
    }

    @Override
    public int[] initData() {
        // return new int[]{1, 1, 2, 2, 2, 3};
        // return new int[]{1, 1, 1, 2, 2, 2, 3, 3};
        // return new int[]{1, 2, 3, 3};
        // return new int[]{1, 2, 2, 2, 2, 2, 2};
        return new int[]{1, 2, 2, 2, 2, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6};
    }
}