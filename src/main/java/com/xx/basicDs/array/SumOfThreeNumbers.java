package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/17
 * <p>
 * 三数之和
 * LeetCode 015
 * <p>
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i],
 * nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * <p>
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0
 */
public class SumOfThreeNumbers implements Answer {

    public static void main(String[] args) {
        new SumOfThreeNumbers().answerTwo();
    }

    /**
     * 解1：用普通的双遍历试一下,
     * 问题：结果如何去重？
     * 题意理解错误：三元组不能重复，而非每个位置只能在三元组使用一次~！！！！
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            if (map.containsKey(data[i])) {
                map.get(data[i]).add(i);
            } else {
                HashSet<Integer> set = new HashSet<>();
                set.add(i);
                map.put(data[i], set);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                int temp = -(data[i] + data[j]);
                HashSet<Integer> indexSet = map.get(temp);
                if (indexSet != null) {
                    for (Integer index : indexSet) {
                        if (index != i && index != j && index > j) {
                            List<Integer> list = Arrays.asList(data[i], data[j], data[index]);
                            result.add(list);
                        }
                    }
                }
            }
        }
        //fixme：解决重复问题
        System.out.println(result);
    }


    /**
     * 排序+双指针
     * <p>
     * 遍历排序后数组：
     * 若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
     * 对于重复元素：跳过，避免出现重复解
     * 令左指针 L=i+1，右指针 R=n−1，当 L< R时，执行循环：
     * 当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。
     * 并同时将 L,R移到下一位置，寻找新的解
     * 若和大于 0，说明 nums[R]太大，R左移
     * 若和小于 0，说明 nums[L] 太小，L 右移
     */
    public void answerTwo() {
        int[] nums = initData();
        List<List<Integer>> result = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //双指针
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            if (nums[i] > 0) {
                System.out.println(result);
                return;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int curr = nums[i];
            int L = i + 1, R = len - 1;
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if (tmp == 0) {
                    result.add(Arrays.asList(curr, nums[L], nums[R]));
                    while (L < R && nums[L + 1] == nums[L]) {
                        ++L;
                    }
                    while (L < R && nums[R - 1] == nums[R]) {
                        --R;
                    }
                    ++L;
                    --R;
                } else if (tmp < 0) {
                    // 需要更大的
                    ++L;
                } else {
                    // 需要更小的
                    --R;
                }
            }
        }
        System.out.println(result);
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{-1, 0, 1, 2, -1, -4};
    }
}
