package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/8/3
 * 和大于或等于k的最短子数组输入一个正整数组成的数组和一个正整数k，
 * 请问数组中和大于或等于k的连续子数组的最短长度是多少？
 * 如果不存在所有数字之和大于或等于k的子数组，则返回0。
 * 例如，输入数组[5，1，4，3]，k的值为7，和大于或等于7的最短连续子数组是[4，3]，因
 * 此输出它的长度2。
 * <p>
 * 同类型题目：输入一个由正整数组成的数组和一个正整数k，请问数组
 * 中有多少个数字乘积小于k的连续子数组？例如，输入数组[10，5，2，6]，k的值为100，有8个子数组的所有数字的乘积小于100，它们
 * 分别是[10]、[5]、[2]、[6]、[10，5]、[5，2]、[2，6]和[5，2，6]。
 * <p>
 * 使用双指针法：前提是每个数是正数
 */
public class SumGreaterOrEqualOfKShortestSubarray implements Answer {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new SumGreaterOrEqualOfKShortestSubarray().answerThree();
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 以一个数为中心，左右遍历。
     * 缺点：重复了！！而且只需要一个数为准，向右遍历即可。
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        int n = 7;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int target = n - nums[i];
            int left = i - 1;
            int right = i + 1;
            int sum = 0;
            while (left >= 0 || right < nums.length) {
                if (left >= 0 && right < nums.length) {
                    if (nums[left] <= nums[right]) {
                        sum = sum + nums[right];
                        right++;
                    } else {
                        sum = sum + nums[left];
                        left--;
                    }
                } else {
                    if (left < 0) {
                        sum = sum + nums[right];
                        right++;
                    }
                    if (right >= nums.length) {
                        sum = sum + nums[left];
                        left--;
                    }
                }
                if (sum >= target) {
                    int len = right - left - 1;
                    System.out.println((left + 1) + " " + (right - 1));
                    if (len < min) {
                        min = len;
                    }
                    break;
                }
            }
        }
        System.out.println(min);
    }

    /**
     * 以一个数为中心，向右遍历。
     * 缺点：重复计算了很多
     */
    private void answerTwo() {
        int[] nums = initData();
        int n = 7;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum = sum + nums[j];
                if (sum >= n) {
                    if (min > (j - i + 1)) {
                        min = j - i + 1;
                        System.out.println(i + " " + j);
                    }
                    break;
                }
            }
        }
        System.out.println(min);
    }

    /**
     * 采用双指针，滑动窗口
     */
    private void answerThree() {
        int[] nums = initData();
        int n = 7;
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = nums[left];
        while (right < nums.length && left <= right) {
            if (sum >= n) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    System.out.println(left + " " + right);
                }
                sum = sum - nums[left];
                left++;
            } else {
                right++;
                if (right < nums.length) {
                    sum = sum + nums[right];
                }
            }
        }
        System.out.println(min);
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{5, 1, 4, 3};
    }
}