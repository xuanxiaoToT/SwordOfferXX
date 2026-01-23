package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/3
 * <p>
 * 组合总和
 * LeetCode 39 Medium
 * <p>
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * <p>
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * <p>
 * 提示：
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 */
public class SumOfCombinationI implements Answer {

    public static void main(String[] args) {
        new SumOfCombinationI().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[] nums = initData();
        int target = 1;
        List<List<Integer>> result = new ArrayList<>();
        answerOneDiGui(nums, target, 0, new ArrayList<>(), result);
        System.out.println(result);
    }

    private void answerOneDiGui(int[] nums, int target, int index, List<Integer> temp, List<List<Integer>> result) {
        if (index >= nums.length) {
            return;
        }
        int sum = temp.stream().mapToInt(s -> s).sum();
        if (sum == target) {
            ArrayList<Integer> arrayList = new ArrayList<>(temp);
            result.add(arrayList);
            return;
        }
        if (sum > target) {
            return;
        }
        temp.add(nums[index]);
        answerOneDiGui(nums, target, index, temp, result);
        temp.remove(temp.size() - 1);

        answerOneDiGui(nums, target, index + 1, temp, result);
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        //return new int[]{2, 3, 6, 7};
        return new int[]{2};
    }
}
