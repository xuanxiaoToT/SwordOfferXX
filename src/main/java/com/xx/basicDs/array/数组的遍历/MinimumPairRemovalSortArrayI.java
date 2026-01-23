package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 移除最小数对使数组有序I
 * LeetCode 3507. Easy
 * <p>
 * 给你一个数组 nums，你可以执行以下操作任意次数：
 * 选择 相邻 元素对中 和最小 的一对。如果存在多个这样的对，选择最左边的一个。
 * 用它们的和替换这对元素。
 * 返回将数组变为 非递减 所需的 最小操作次数 。
 * 如果一个数组中每个元素都大于或等于它前一个元素（如果存在的话），则称该数组为非递减。
 * <p>
 * 示例 1：
 * 输入： nums = [5,2,3,1]
 * 输出： 2
 * 解释：
 * 元素对 (3,1) 的和最小，为 4。替换后 nums = [5,2,4]。
 * 元素对 (2,4) 的和为 6。替换后 nums = [5,6]。
 * 数组 nums 在两次操作后变为非递减。
 * <p>
 * 示例 2：
 * 输入： nums = [1,2,2]
 * 输出： 0
 * 解释：
 * 数组 nums 已经是非递减的。
 * <p>
 * 提示：
 * 1 <= nums.length <= 50
 * -1000 <= nums[i] <= 1000
 * <p>
 */
public class MinimumPairRemovalSortArrayI implements Answer {
    public static void main(String[] args) {
        new MinimumPairRemovalSortArrayI().answer();
    }

    @Override
    public void answer() {
        // int[] nums = new int[]{5, 2, 3, 1};
        // int[] nums = new int[]{1, 2, 2};
        int[] nums = new int[]{4, 3, 2, 1};
        System.out.println(minimumPairRemoval(nums));
    }

    /**
     * 模拟操作：暴力法
     * <p>
     * 改进：可以在原数组上模拟，用双指针完成遍历，
     */
    public int minimumPairRemoval(int[] nums) {
        boolean flag = true;
        List<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int left = -1;
        int right = -1;
        list.add(nums[0]);
        int result = 0;
        // 将nums转换为list方便操作，同时顺便做第一遍合并
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] + nums[i - 1] < min) {
                min = nums[i] + nums[i - 1];
                left = i - 1;
                right = i;
            }
            if (nums[i] < nums[i - 1]) {
                flag = false;
            }
            list.add(nums[i]);
        }
        if (flag) {
            return 0;
        }
        list.set(left, min);
        list.remove(right);
        result++;
        // 正式开始合并和去除
        while (!flag && list.size() > 1) {
            min = Integer.MAX_VALUE;
            left = -1;
            right = -1;
            flag = true;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) + list.get(i - 1) < min) {
                    min = list.get(i) + list.get(i - 1);
                    left = i - 1;
                    right = i;
                }
                if (list.get(i) < list.get(i - 1)) {
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
            result++;
            list.set(left, min);
            list.remove(right);
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
