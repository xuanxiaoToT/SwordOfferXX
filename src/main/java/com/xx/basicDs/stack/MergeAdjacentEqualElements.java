package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 合并相邻且相等的元素
 * LeetCode 3834. Medium
 * <p>
 * 给你一个整数数组 nums。
 * 你需要 重复 执行以下合并操作，直到无法再进行任何更改：
 * <p>
 * 如果数组中存在 两个相邻且相等的元素，选择当前数组中 最左侧 的这对相邻元素，并用它们的 和 替换它们。
 * 每次合并操作后，数组的大小 减少 1。对更新后的数组重复此过程，直到无法再进行任何操作。
 * <p>
 * 返回完成所有可能的合并操作后的最终数组。
 * <p>
 * 示例 1：
 * 输入： nums = [3,1,1,2]
 * 输出： [3,4]
 * 解释：
 * 中间的两个元素相等，将它们合并为 1 + 1 = 2，结果为 [3, 2, 2]。
 * 最后的两个元素相等，将它们合并为 2 + 2 = 4，结果为 [3, 4]。
 * 不再存在相邻且相等的元素。因此，答案为 [3, 4]。
 * <p>
 * 示例 2：
 * 输入： nums = [2,2,4]
 * 输出： [8]
 * 解释：
 * 前两个元素相等，将它们合并为 2 + 2 = 4，结果为 [4, 4]。
 * 前两个元素相等，将它们合并为 4 + 4 = 8，结果为 [8]。
 * <p>
 * 示例 3：
 * 输入： nums = [3,7,5]
 * 输出： [3,7,5]
 * 解释：
 * 数组中没有相邻且相等的元素，因此不执行任何操作。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * <p>
 * Tag:栈
 */
public class MergeAdjacentEqualElements implements Answer {
    public static void main(String[] args) {
        new MergeAdjacentEqualElements().answer();
    }

    @Override
    public void answer() {
        // int[] nums = {5, 4, 2, 1, 1};
        // int[] nums = {3,1,1,2};
        // int[] nums = {2,2,4};
        int[] nums = {1, 1, 2, 2, 4, 5, 6, 7};
        System.out.println(mergeAdjacent(nums));
    }

    /**
     * 用栈模拟即可
     */
    public List<Long> mergeAdjacent(int[] nums) {
        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            Long num = (long) nums[i];
            if (stack.isEmpty()) {
                stack.push(num);
                continue;
            }
            // 需要合并
            if (num.equals(stack.peek())) {
                while (!stack.isEmpty()) {
                    // 与栈顶元素相加
                    num = stack.pop() + num;
                    if (stack.isEmpty()) {
                        stack.push(num);
                        break;
                    }
                    if (!Objects.equals(stack.peek(), num)) {
                        stack.push(num);
                        break;
                    }
                }
            } else {
                stack.push(num);
            }
        }
        return new ArrayList<>(stack);
    }

    @Override
    public Object initData() {
        return null;
    }
}
