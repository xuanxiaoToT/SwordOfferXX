package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/3
 * <p>
 * 找到所有数组中消失的数字
 * LeetCode 448. Medium
 * <p>
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * <p>
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[2]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 */
class FindAllMissingNumInArray implements Answer {

    public static void main(String[] args) {
        new FindAllMissingNumInArray().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDisappearedNumbers1(nums));
    }

    /**
     * 原地修改法
     * 可以让nums本身充当哈希表
     * <p>
     * 具体来说，遍历 nums，每遇到一个数 x，就让 nums[x−1] 增加 n。由于 nums 中所有数均在[1,n]中，
     * 增加以后，这些数必然大于 n。最后我们遍历 nums，若 nums[i]未大于 n，就说明没有遇到过数 i+1。
     * 这样我们就找到了缺失的数字。
     * <p>
     * 注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 n 取模来还原出它本来的值。
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }


    /**
     * 桶排序算法，交换
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 != i && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public void swap(int[] nums, int to, int from) {
        int temp = nums[to];
        nums[to] = nums[from];
        nums[from] = temp;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
