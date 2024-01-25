package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/25
 * <p>
 * 独一无二的出现次数
 * LeetCode 1207. Easy
 * <p>
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * <p>
 * 示例 1：
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * <p>
 * 示例 2：
 * 输入：arr = [1,2]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 * <p>
 * 提示：
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 * <p>
 * Tag: 哈希
 */
public class UniqueOccurrences implements Answer {
    public static void main(String[] args) {
        new UniqueOccurrences().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums = {1, 2, 2, 1, 1, 3, 3};
        System.out.println(whetherUnique(nums));
    }

    public boolean whetherUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (Integer value : map.values()) {
            if (set.contains(value)) {
                return false;
            }
            set.add(value);
        }
        return true;
    }

    @Override
    public Object initData() {
        return null;
    }
}