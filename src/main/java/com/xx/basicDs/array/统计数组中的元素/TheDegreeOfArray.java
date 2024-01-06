package com.xx.basicDs.array.统计数组中的元素;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/4
 * <p>
 * 数组的度
 * LeetCode 697. Easy
 * <p>
 * 给定一个非空且只包含非负数的整数数组 nums，数组的 度 的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2,3,1]
 * 输出：2
 * 解释：
 * 输入数组的度是 2 ，因为元素 1 和 2 的出现频数最大，均为 2 。
 * 连续子数组里面拥有相同度的有如下所示：
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组 [2, 2] 的长度为 2 ，所以返回 2 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,2,3,1,4,2]
 * 输出：6
 * 解释：
 * 数组的度是 3 ，因为元素 2 重复出现 3 次。
 * 所以 [2,2,3,1,4,2] 是最短子数组，因此返回 6 。
 * <p>
 * 提示：
 * nums.length 在 1 到 50,000 范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 * <p>
 * Tag: 哈希表  数组
 */
public class TheDegreeOfArray implements Answer {

    public static void main(String[] args) {
        new TheDegreeOfArray().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums = {1, 2, 2, 3, 1};
        System.out.println(findShortestSubArray(nums));
    }

    /**
     * 解1：记原数组中出现次数最多的数为 x，那么和原数组的度相同的最短连续子数组，必然包含了原数组中的全部 x，
     * 且两端恰为 x 第一次出现和最后一次出现的位置。
     * <p>
     * 在实际代码中，我们使用哈希表实现该功能，每一个数映射到一个长度为 3 的数组，
     * 数组中的三个元素分别代表这个数出现的次数、这个数在原数组中第一次出现的位置和这个数在原数组中最后一次出现的位置。
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int maxDegree = 1;
        int minLength = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                map.put(nums[i], temp);
            }
            List<Integer> list = map.get(nums[i]);
            if (list.size() >= maxDegree) {
                maxDegree = list.size();
            }
        }
        for (List<Integer> value : map.values()) {
            if (value.size() == maxDegree) {
                minLength = Math.min(minLength, value.get(value.size() - 1) - value.get(0) + 1);
            }
        }
        return minLength;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
