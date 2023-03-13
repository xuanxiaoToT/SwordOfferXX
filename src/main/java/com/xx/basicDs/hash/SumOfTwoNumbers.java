package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/12
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出
 * 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class SumOfTwoNumbers implements Answer {

    public static void main(String[] args) {
        new SumOfTwoNumbers().answerOne();
    }

    /**
     * 解1：hash法
     */
    @Override
    public void answerOne() {
        int[] numbs = initData();
        int target = 9;
        Map<Integer, Integer> map = new HashMap<>();
        //建立哈希表，用于查找。
        for (int i = 0; i < numbs.length; i++) {
            int complement = target - numbs[i];
            if (map.containsKey(complement)) {
                int[] result = {map.get(complement), i};
                System.out.println(Arrays.toString(result));
                return;
            }
            map.put(numbs[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{2, 11, 15, 7};
    }
}
