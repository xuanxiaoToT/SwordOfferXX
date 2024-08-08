package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/8
 * <p>
 * 找出与数组相加的整数I
 * LeetCode 3131. Easy
 * <p>
 * 给你两个长度相等的数组 nums1 和 nums2。
 * <p>
 * 数组 nums1 中的每个元素都与变量 x 所表示的整数相加。如果 x 为负数，则表现为元素值的减少。
 * 在与 x 相加后，nums1 和 nums2 相等 。当两个数组中包含相同的整数，并且这些整数出现的频次相同时，两个数组 相等 。
 * <p>
 * 返回整数 x 。
 * <p>
 * 示例 1:
 * 输入：nums1 = [2,6,4], nums2 = [9,7,5]
 * 输出：3
 * 解释：
 * 与 3 相加后，nums1 和 nums2 相等。
 * <p>
 * 示例 2:
 * 输入：nums1 = [10], nums2 = [5]
 * 输出：-5
 * 解释：
 * 与 -5 相加后，nums1 和 nums2 相等。
 * <p>
 * 示例 3:
 * 输入：nums1 = [1,1,1,1], nums2 = [1,1,1,1]
 * 输出：0
 * 解释：
 * 与 0 相加后，nums1 和 nums2 相等。
 * <p>
 * 提示：
 * 1 <= nums1.length == nums2.length <= 100
 * 0 <= nums1[i], nums2[i] <= 1000
 * 测试用例以这样的方式生成：存在一个整数 x，使得 nums1 中的每个元素都与 x 相加后，nums1 与 nums2 相等。
 * <p>
 * Tag:慢慢读题  简单遍历
 */
public class FindTheIntegerAddedToArrayI implements Answer {
    public static void main(String[] args) {
        new FindTheIntegerAddedToArrayI().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums1 = {2, 6, 4};
        int[] nums2 = {9, 7, 5};
        System.out.println(addedInteger(nums1, nums2));
    }

    /**
     * 也可以直接求最小值就行
     */
    public int addedInteger(int[] nums1, int[] nums2) {
        int min1 = 1001, min2 = 1001;
        for (int i = 0; i < nums1.length; i++) {
            min1 = Math.min(nums1[i], min1);
            min2 = Math.min(nums2[i], min2);
        }
        return min2 - min1;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
