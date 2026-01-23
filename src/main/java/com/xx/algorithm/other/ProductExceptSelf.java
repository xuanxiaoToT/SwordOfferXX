package com.xx.algorithm.other;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/12
 * <p>
 * 除自身以外数组的乘积
 * LeetCode 238
 * <p>
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外
 * 其余各元素的乘积。题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的
 * 乘积都在 32 位 整数范围内。请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？
 * （ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * <p>
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 */
public class ProductExceptSelf implements Answer {

    public static void main(String[] args) {
        new ProductExceptSelf().answer();
    }

    /**
     * 解1：用两个数组来存储临时值。
     * 其中left(i)表示，左侧包括i在内的全部乘积。right(i)同理。
     * 那么除i外的乘积值，就等于i左右两侧相乘。
     */
    @Override
    public void answer() {
        int[] inputData = initData();
        int[] left = new int[inputData.length];
        int[] right = new int[inputData.length];
        int[] result = new int[inputData.length];
        int leftTemp = 1;
        for (int i = 0; i < inputData.length; i++) {
            left[i] = leftTemp * inputData[i];
            leftTemp = leftTemp * inputData[i];
        }
        int rightTemp = 1;
        for (int i = inputData.length - 1; i >= 0; i--) {
            right[i] = rightTemp * inputData[i];
            rightTemp = rightTemp * inputData[i];
        }

        for (int i = 0; i < inputData.length; i++) {
            if (i == 0) {
                result[i] = right[i + 1];
            } else {
                if (i == inputData.length - 1) {
                    result[i] = left[i - 1];
                } else {
                    result[i] = left[i - 1] * right[i + 1];
                }
            }
        }
        System.out.println(Arrays.toString(result));
    }

    /**
     * 官解：想要O(1)复杂度，则需要省略掉right的使用。
     * 即在计算right的时候，同时计算result。
     * 再简化一点：就是left可以用result来临时替代。
     */
    public void answerTwo() {
        int[] inputData = initData();
        int[] result = new int[inputData.length];
        int left = 1;
        for (int i = 0; i < inputData.length; i++) {
            if (i >= 1) {
                left = left * inputData[i - 1];
            }
            result[i] = left;
        }
        int right = 1;
        for (int j = inputData.length - 1; j >= 0; j--) {
            if (j < inputData.length - 1) {
                right = right * inputData[j + 1];
            }
            result[j] = right * result[j];
        }

        System.out.println(Arrays.toString(result));
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 4};
    }
}
