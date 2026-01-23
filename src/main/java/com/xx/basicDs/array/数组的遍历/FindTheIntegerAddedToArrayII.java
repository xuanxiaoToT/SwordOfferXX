package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/9
 * <p>
 * 找出与数组相加的整数II
 * LeetCode  3132. Medium
 * <p>
 * 给你两个整数数组 nums1 和 nums2。
 * 从 nums1 中移除两个元素，并且所有其他元素都与变量 x 所表示的整数相加。如果 x 为负数，则表现为元素值的减少。
 * 执行上述操作后，nums1 和 nums2 相等 。当两个数组中包含相同的整数，并且这些整数出现的频次相同时，两个数组 相等 。
 * 返回能够实现数组相等的 最小 整数 x 。
 * <p>
 * 示例 1:
 * 输入：nums1 = [4,20,16,12,8], nums2 = [14,18,10]
 * 输出：-2
 * 解释：
 * 移除 nums1 中下标为 [0,4] 的两个元素，并且每个元素与 -2 相加后，nums1 变为 [18,14,10] ，与 nums2 相等。
 * <p>
 * 示例 2:
 * 输入：nums1 = [3,5,5,3], nums2 = [7,7]
 * 输出：2
 * 解释：
 * 移除 nums1 中下标为 [0,3] 的两个元素，并且每个元素与 2 相加后，nums1 变为 [7,7] ，与 nums2 相等。
 * <p>
 * 提示：
 * 3 <= nums1.length <= 200
 * nums2.length == nums1.length - 2
 * 0 <= nums1[i], nums2[i] <= 1000
 * 测试用例以这样的方式生成：存在一个整数 x，nums1 中的每个元素都与 x 相加后，再移除两个元素，nums1 可以与 nums2 相等。
 */
public class FindTheIntegerAddedToArrayII implements Answer {

    public static void main(String[] args) {
        new FindTheIntegerAddedToArrayII().answer();
    }

    @Override
    public void answer() {
        int[] nums1 = {4, 20, 16, 12, 8};
        int[] num2 = {14, 18, 10};
        //int[] nums1 = {3, 5, 10, 9, 9, 4};
        //int[] num2 = {1, 6, 6, 0};
        System.out.println(minimumAddedInteger(nums1, num2));
    }

    public int minimumAddedInteger(int[] nums1, int[] nums2) {

        int result = Integer.MAX_VALUE;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // nums1.length = nums2.length + 2。倒着算是因为要算最小的。
        // 因为已经排好序了，前三个里面必有正确的x
        for (int i = 2; i >= 0; i--) {
            int x = nums2[0] - nums1[i];
            if (checkResult(x, nums1, nums2)) {
                return x;
            }
        }
        return result;
    }

    /**
     * 校验x是否正确
     * 已经排好序了，所以挨个校验nums2的是否正确
     */
    public boolean checkResult(int x, int[] nums1, int[] nums2) {
        int j = 0;
        for (int i = 0; i < nums2.length; i++) {
            while (j < nums1.length && nums1[j] + x != nums2[i]) {
                j++;
            }
            if (j >= nums1.length) {
                return false;
            }
            j++;
        }
        return true;
    }

    @Override
    public Object initData() {
        return null;
    }
}
