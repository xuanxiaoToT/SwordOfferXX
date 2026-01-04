package com.xx.algorithm.math;

import com.xx.Answer;

/**
 *
 * 四因数
 * LeetCode 1390. Medium
 * <p>
 * 给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。如果数组中不存在满足题意的整数，则返回 0 。
 * <p>
 * 示例 1：
 * 输入：nums = [21,4,7]
 * 输出：32
 * 解释：
 * 21 有 4 个因数：1, 3, 7, 21
 * 4 有 3 个因数：1, 2, 4
 * 7 有 2 个因数：1, 7
 * 答案仅为 21 的所有因数的和。
 * <p>
 * 示例 2:
 * 输入: nums = [21,21]
 * 输出: 64
 * <p>
 * 示例 3:
 * 输入: nums = [1,2,3,4,5]
 * 输出: 0
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 * <p>
 * Tag: 因数分解
 */
public class FourDivisors implements Answer {
    public static void main(String[] args) {
        new FourDivisors().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // int[] nums = new int[]{21,21};
        // int[] nums = new int[]{1,2,3,4,5};
        System.out.println(sumFourDivisors(nums));
    }

    /**
     * 利用因数成对出现的特性，只需要遍历到 Math.sqrt(num) 处即可
     * 又因为1和他本身一定是因数，所以2到Math.sqrt(num)中间且仅有一个即可
     */
    public int sumFourDivisors(int[] nums) {
        int result = 0;
        for (int num : nums) {
            int temp = 1 + num;
            int count = 2;
            double sqrt = Math.sqrt(num);
            for (int j = 2; j < sqrt; j++) {
                // 如果可以开方，说明因数个数一定是奇数，不满足4个
                if (num % sqrt == 0) {
                    break;
                }
                if (num % j == 0) {
                    count += 2;
                    temp = temp + j + num / j;
                    if (count > 4) {
                        break;
                    }
                }
            }
            if (count == 4) {
                result += temp;
            }
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
