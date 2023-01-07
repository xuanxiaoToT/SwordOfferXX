package com.xx.algorithm.search;

/**
 * @author 玄霄
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

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            }
        }

        return -1;
    }

}