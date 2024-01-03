package com.xx.basicDs.array.数组的移动翻转;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/2
 * <p>
 * 旋转函数
 * LeetCode 396. Medium
 * <p>
 * 给定一个长度为 n 的整数数组 nums 。
 * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
 * <p>
 * 生成的测试用例让答案符合 32 位 整数。
 * <p>
 * 示例 1:
 * 输入: nums = [4,3,2,6]
 * 输出: 26
 * 解释:
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
 * <p>
 * 示例 2:
 * 输入: nums = [100]
 * 输出: 0
 * <p>
 * 提示:
 * n == nums.length
 * 1 <= n <= 10^5
 * -100 <= nums[i] <= 100
 * <p>
 * Tag：数学规律  归纳法  数组的旋转
 */
public class ArrayRotateFunction implements Answer {

    public static void main(String[] args) {
        new ArrayRotateFunction().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums = new int[]{-8, 5, -10, -5, -7};
        System.out.println(maxRotateFunction(nums));
    }

    /**
     * 记数组 nums 的元素之和为 numSum。根据公式，可以得到：
     * <p>
     * F(0)=0×nums[0]+1×nums[1]+…+(n−1)×nums[n−1]
     * F(1)=1×nums[0]+2×nums[1]+…+0×nums[n−1]
     * ...
     * 更一般地，当 1≤k < n 时，F(k)=F(k−1)+numSum−n×nums[n−k]
     * 我们可以不停迭代计算出不同的 F(k)，并求出最大值。
     */
    public int maxRotateFunction(int[] nums) {
        int f = 0;
        int n = nums.length;
        int numSum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
        }
        int res = f;
        for (int i = n - 1; i > 0; i--) {
            f += numSum - n * nums[i];
            res = Math.max(res, f);
        }
        return res;
    }

    /**
     * 超时！！
     */
    private int maxRotateFunctionError(int[] nums) {
        int[] maxDp = new int[nums.length];
        int max = Integer.MIN_VALUE;
        if (nums.length == 1) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < maxDp.length; j++) {
                int index = i - j >= 0 ? i - j : nums.length - (j - i);
                maxDp[j] = maxDp[j] + i * nums[index];
                if (i == nums.length - 1) {
                    max = Math.max(maxDp[j], max);
                }
            }
        }
        return max;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
