package com.xx.algorithm.search;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/18
 * 经典二分查找
 */
public class ClassicBinarySearch {

    public static void main(String[] args) {
        ClassicBinarySearch classicBinarySearch = new ClassicBinarySearch();
        int[] nums = new int[]{1, 3, 4, 5, 8, 10, 15};
        System.out.println(classicBinarySearch.binarySearch(nums, 15));
    }

    public int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        // 此处是小于等于
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                // 此处是等于-1，而不是等于mid
                end = mid - 1;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            }
        }

        return -1;
    }

}