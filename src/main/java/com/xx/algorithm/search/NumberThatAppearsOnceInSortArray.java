package com.xx.algorithm.search;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/20
 * <p>
 * 排序数组中只出现一次的数字
 * <p>
 * 在一个排序的数组中，除一个数字只出现一次之外，其
 * 他数字都出现了两次，请找出这个唯一只出现一次的数字。
 * <p>
 * 例如，在数组[1，1，2，2，3，4，4，5，5]中，数字3只出现了一次
 */
public class NumberThatAppearsOnceInSortArray implements Answer {

    public static void main(String[] args) {
        new NumberThatAppearsOnceInSortArray().answer();
    }

    /**
     * 1.利用异或。解题略
     * 2.利用二分
     */
    @Override
    public void answer() {
        int[] nums = initData();
        int maxRight = nums.length - 1;
        int left = 0;
        int right = maxRight;
        while (left <= right && left < nums.length && right >= 0) {
            int mid = (left + right) / 2;
            if (mid == 0 || mid == nums.length - 1) {
                System.out.println(mid + " " + nums[mid]);
                return;
            } else {
                if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                    System.out.println(mid + " " + nums[mid]);
                    return;
                } else {
                    if (nums[mid - 1] == nums[mid] && (mid) % 2 == 1) {
                        left = mid + 1;
                    }
                    if (nums[mid - 1] == nums[mid] && (mid) % 2 == 0) {
                        right = mid - 1;
                    }
                    if (nums[mid] == nums[mid + 1] && (maxRight - mid) % 2 == 1) {
                        right = mid - 1;
                    }
                    if (nums[mid] == nums[mid + 1] && (maxRight - mid) % 2 == 0) {
                        left = mid + 1;
                    }
                }
            }
        }
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        int[] temp = new int[]{1, 1, 2, 2, 3, 4, 4};
        return temp;
    }
}
