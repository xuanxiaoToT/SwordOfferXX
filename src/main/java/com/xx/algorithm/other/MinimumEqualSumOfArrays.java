package com.xx.algorithm.other;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/29
 * <p>
 * 数组的最小相等和
 * LeetCode 100102. Medium
 * <p>
 * 给你两个由正整数和 0 组成的数组 nums1 和 nums2 。
 * 你必须将两个数组中的 所有 0 替换为 严格 正整数，并且满足两个数组中所有元素的和 相等 。
 * 返回 最小 相等和 ，如果无法使两数组相等，则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [3,2,0,1,0], nums2 = [6,5,0]
 * 输出：12
 * 解释：可以按下述方式替换数组中的 0 ：
 * - 用 2 和 4 替换 nums1 中的两个 0 。得到 nums1 = [3,2,2,1,4] 。
 * - 用 1 替换 nums2 中的一个 0 。得到 nums2 = [6,5,1] 。
 * 两个数组的元素和相等，都等于 12 。可以证明这是可以获得的最小相等和。
 * <p>
 * 示例 2：
 * 输入：nums1 = [2,0,2,0], nums2 = [1,4]
 * 输出：-1
 * 解释：无法使两个数组的和相等。
 * <p>
 * 提示：
 * 1 <= nums1.length, nums2.length <= 10^5
 * 0 <= nums1[i], nums2[i] <= 10^6
 */
public class MinimumEqualSumOfArrays implements Answer {

    public static void main(String[] args) {
        new MinimumEqualSumOfArrays().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[] nums1 = new int[]{3, 2, 0, 1, 0}, nums2 = new int[]{6, 5, 0};
        System.out.println(minSum(nums1, nums2));
    }

    /**
     * 错误提交5次
     * 原因：
     * 1.元素的最大值为10^5，所以其sum是会超int的，这个第一时间没想到，用的int。
     * 2.所有 0 替换为 严格 正整数。主义这句话，意味着0变为的最大数只能是 Integer.MAX_VALUE，这个后边才想到。
     * 反思：对边界要敏感！
     */
    public long minSum(int[] nums1, int[] nums2) {
        int zeroCount1 = 0;
        long sum1 = 0;
        int zeroCount2 = 0;
        long sum2 = 0;
        int maxValue = Integer.MAX_VALUE;
        for (int j : nums1) {
            sum1 = sum1 + j;
            if (j == 0) {
                zeroCount1++;
            }
        }

        for (int j : nums2) {
            sum2 = sum2 + j;
            if (j == 0) {
                zeroCount2++;
            }
        }
        if (zeroCount2 == 0 && zeroCount1 == 0) {
            return sum1 == sum2 ? sum1 : -1;
        }
        if (zeroCount2 == 0) {
            if (sum1 + zeroCount1 > sum2) {
                return -1;
            } else {
                if ((long) zeroCount1 * maxValue < sum2 - sum1) {
                    return -1;
                } else {
                    return sum2;
                }
            }
        }

        if (zeroCount1 == 0) {
            if (sum2 + zeroCount2 > sum1) {
                return -1;
            } else {
                if ((long) zeroCount2 * maxValue < sum1 - sum2) {
                    return -1;
                } else {
                    return sum1;
                }
            }
        }

        if (sum2 + zeroCount2 > sum1 + zeroCount1) {
            if (sum2 + zeroCount2 > (long) zeroCount1 * maxValue + sum1) {
                return -1;
            } else {
                return sum2 + zeroCount2;
            }
        } else {
            if (sum1 + zeroCount1 > (long) zeroCount2 * maxValue + sum2) {
                return -1;
            } else {
                return sum1 + zeroCount1;
            }
        }
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
