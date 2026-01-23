package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/12
 * <p>
 * 存在重复元素 II
 * LeetCode 219. Easy
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。
 * 如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 */
public class ExistDuplicateElementsII implements Answer {
    public static void main(String[] args) {
        ExistDuplicateElementsII elementsII = new ExistDuplicateElementsII();
        // elementsII.answerOne();
        System.out.println(elementsII.answerTwo(elementsII.initData(), 2));
    }

    /**
     * 不用额外内存的
     */
    @Override
    public void answer() {
        int k = 2;
        int[] data = initData();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; Math.abs(i - j) <= k && j < data.length; j++) {
                if (i == j) {
                    continue;
                }
                if (data[i] == data[j]) {
                    System.out.println(true);
                    return;
                }
            }
        }
        System.out.println(false);
    }

    private boolean answerTwo(int[] data, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            if (map.containsKey(data[i])) {
                Integer index = map.get(data[i]);
                if (Math.abs(index - i) <= k) {
                    return true;
                }
            }
            map.put(data[i], i);
        }
        return false;
    }

    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 1, 2, 3};
    }
}