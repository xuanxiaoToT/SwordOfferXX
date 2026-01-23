package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.HashMap;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/4
 * <p>
 * 和为k的子数组
 * LeetCode 560
 * <p>
 * 输入一个整数数组和一个整数k，请问数组中有多少个数
 * 字之和等于k的连续子数组？
 * <p>
 * 例如，输入数组[1，1，1]，k的值为2，有2个连续子数组之和等于2。
 * 注意：不一定是正数
 * <p>
 * 举一反三：
 * 输入一个只包含0和1的数组，请问如何求0和1的个数相
 * 同的最长连续子数组的长度？
 * 例如，在数组[0，1，0]中有两个子数
 * 组包含相同个数的0和1，分别是[0，1]和[1，0]，它们的长度都是
 * 2，因此输出2。
 * <p>
 * 同理：首先将0看作-1.然后就转变为了求解和为0的连续子数组。继续按
 * 上述方法即可。
 */
public class SubarrayThatSumIsK implements Answer {

    public static void main(String[] args) {
        new SubarrayThatSumIsK().answerTwo();
    }

    /**
     * 采用额外数组，存储计算结果
     * <p>
     * 不用双指针的原因：此题目没有指明数字都是正的，不能保证右移变大，左移变小。
     */
    @Override
    public void answer() {
        int[] nums = initData();
        // sumTemp[i]表示i左侧以及自己，的元素和。
        int[] sumTemp = new int[nums.length];
        int k = 4;
        int result = 0;
        sumTemp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumTemp[i] = nums[i] + sumTemp[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = sumTemp[j] - sumTemp[i];
                if (sum == k) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    /**
     * 第一次遍历可以求出0-i的所有临时和。
     * 那么i-j的连续子数组的和就相当于是sum[j]-sum[i] == k，就等同于k+sum[i] 是否等于sum[j]
     * 那么 我们只需继续遍历这个sum数组，判断其值+上k后，是否在sum中存在即可。
     * <p>
     * todo fix 和一样的时候
     */
    private void answerTwo() {
        int[] nums = initData();
        HashMap<Integer, Integer> tempMap = new HashMap<>(nums.length);
        int k = 0;
        int result = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = nums[i - 1] + sum[i - 1];
            // map重复问题？
            tempMap.put(sum[i], i);
        }
        for (int i = 0; i < sum.length; i++) {
            Integer temp = tempMap.get(k + sum[i]);
            if (temp != null && temp != i) {
                result++;
            }
        }
        System.out.println(result);
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        //return new int[]{1, 2, 2, 2, 1};
        return new int[]{1, -1, 0};
    }
}