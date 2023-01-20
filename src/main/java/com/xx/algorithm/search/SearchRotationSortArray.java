package com.xx.algorithm.search;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/1/9
 * LeetCode 33
 * <p>
 * 搜索旋转排序数组
 * <p>
 * 整数数组 nums 按升序排列，数组中的值互不相同 。
 * 在传递给函数之前，nums在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * <p>
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你旋转后的数组nums和一个整数target，如果nums中存在这个目标值target，
 * 则返回它的下标，否则返回 -1 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class SearchRotationSortArray implements Answer {

    public static void main(String[] args) {
        new SearchRotationSortArray().answerOne();
    }

    /**
     * 解1：使用经典二分法来实现
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        int target = 7;
        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;

        while (left <= right) {
            if (nums[mid] == target) {
                System.out.println(mid);
                return;
            }

            //左边升序
            if (nums[left] <= nums[mid]) {
                //在左边范围内
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    //只能从右边找
                    left = mid + 1;
                }
            } else {
                //右边升序
                if (target >= nums[mid] && target <= nums[right]) {
                    //在右边范围内
                    left = mid + 1;
                } else {
                    //只能从左边找
                    right = mid - 1;
                }

            }
            mid = left + (right - left) / 2;
        }

        //没找到
        System.out.println(-1);
    }

    @Override
    public int[] initData() {
        return new int[]{4, 5, 6, 7, 0, 1, 2};
    }
}
