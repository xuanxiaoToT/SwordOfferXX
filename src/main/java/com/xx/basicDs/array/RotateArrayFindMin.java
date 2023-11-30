package com.xx.basicDs.array;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/16
 * <p>
 * 寻找旋转排序数组中的最小值
 * LeetCode 153.  Medium
 * <p>
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为 1。
 * <p>
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * <p>
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 * <p>
 * 示例 3：
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * <p>
 * Tag: 二分查找
 */
public class RotateArrayFindMin {

    public static void main(String[] args) {
        RotateArrayFindMin rotateArrayFindMin = new RotateArrayFindMin();
        int result = rotateArrayFindMin.answerOne();
        System.out.println(result);
    }

    /**
     * 二分法
     */
    private int answerOne() {
        int[] dataArray = getTestData();
        int left = 0;
        int right = dataArray.length - 1;
        if (dataArray[left] <= dataArray[right]) {
            return dataArray[left];
        }
        while (left < right - 1) {
            int mid = (left + right) / 2;
            // 交叉点在右半部分
            if (dataArray[mid] > dataArray[right]) {
                // 舍弃
                left = mid;
                continue;
            }
            if (dataArray[left] > dataArray[mid]) {
                // 交叉点在左半部分
                right = mid;
                continue;
            }
            // 此题用不到，因为互不相同。但有相同情况时，可以用到。
            if (dataArray[right] == dataArray[mid] && dataArray[left] == dataArray[mid]) {
                return findMin(dataArray, left, right);
            }
        }
        System.out.println(right + " " + dataArray[right]);
        return dataArray[right];
    }

    private int findMin(int[] dataArray, int left, int right) {
        int result = dataArray[left];
        // 从头到尾遍历 dataArray
        for (int i = left; i <= right; i++) {
            // 当发现此时遍历的元素值小于 result
            if (dataArray[i] < result) {
                // 那么更新 result
                result = dataArray[i];
            }
        }
        // 返回 dataArray 中的最小值
        return result;
    }

    private int[] getTestData() {
        //int[] dataArray = {2, 2, 2, 1, 2};
        //int[] dataArray = {3, 4, 5, 1, 2};
        //int[] dataArray = {4,5,6,7,0,1,2};
        int[] dataArray = {11, 13, 15, 17};
        return dataArray;
    }

}