package com.xx.basicDs.array.统计数组中的元素;

import com.xx.Answer;

/**
 * 消失的数字
 * LeetCode  Easy
 * <p>
 * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 * <p>
 * 注意：本题相对书上原题稍作改动
 * <p>
 * 示例 1：
 * 输入：[3,0,1]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：[9,6,4,2,3,5,7,0,1]
 * 输出：8
 * <p>
 * Tag:数组  哈希  数学
 */
public class MissingNumber implements Answer {
    public static void main(String[] args) {
        new MissingNumber().answer();
    }

    @Override
    public void answer() {
        int[] nums = new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(missingNumber(nums));
    }

    public int missingNumber(int[] nums) {
        int[] temp = new int[nums.length + 1];
        for (int num : nums) {
            temp[num] = 1;
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 数学法
     * 将从 0 到 n 的全部整数之和记为 total，根据高斯求和公式，有：
     * total= ∑(n,i=1) = n(n+1)/2
     * 将数组 nums 的元素之和记为 arrSum，则 arrSum 比 total 少了消失的一个数字，因此消失的数字即为 total 与 arrSum 之差。
     */
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int s = 0;
        for (int a : nums) {
            s += a;
        }
        return n * (n + 1) / 2 - s;
    }

    @Override
    public Object initData() {
        return null;
    }
}
