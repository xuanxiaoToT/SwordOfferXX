package com.xx.basicDs.array.统计数组中的元素;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/6
 * <p>
 * 数组中重复的数据
 * LeetCode 442. Medium
 * <p>
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。
 * 请你找出所有出现 两次 的整数，并以数组形式返回。
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[2,3]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,2]
 * 输出：[1]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * nums 中的每个元素出现 一次 或 两次
 * <p>
 * Tag: 统计数组中的元素  数组
 */
public class DuplicateDataInArray implements Answer {

    public static void main(String[] args) {
        new DuplicateDataInArray().answerOne();
    }


    @Override
    public void answerOne() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDuplicates(nums));
    }

    /**
     * 原地哈希算法
     * 同：{@link WrongCollectionArray}
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] % n - 1;
            nums[index] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 2 * n) {
                result.add(i + 1);
            }
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
