package com.xx.basicDs.array.统计数组中的元素;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/4
 * <p>
 * 错误的集合
 * LeetCode 645.  Easy
 * <p>
 * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，
 * 导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[1,2]
 * <p>
 * 提示：
 * 2 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^4
 * <p>
 * Tag: 哈希  数组
 */
public class WrongCollectionArray implements Answer {

    public static void main(String[] args) {
        new WrongCollectionArray().answerOne();
    }


    @Override
    public void answerOne() {
        int[] nums = {1, 2, 2, 4};
        System.out.println(Arrays.toString(findErrorNums(nums)));
    }

    /**
     * 思路：
     * 参考{@link FindAllMissingNumInArray} 的原地修改法
     * 将传入的nums改造为hash表。
     */
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        int n = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] % n - 1;
            nums[index] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < n) {
                result[1] = i + 1;
            }
            if (nums[i] > 2 * n) {
                result[0] = i + 1;
            }
            // 恢复入参
            nums[i] = nums[i] % n;
        }
        return result;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
