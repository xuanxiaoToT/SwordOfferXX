package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/30
 * <p>
 * 元素和最小的山形三元组I
 * LeetCode 2908. Easy
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 如果下标三元组 (i, j, k) 满足下述全部条件，则认为它是一个 山形三元组 ：
 * i < j < k
 * nums[i] < nums[j] 且 nums[k] < nums[j]
 * 请你找出 nums 中 元素和最小 的山形三元组，并返回其 元素和 。如果不存在满足条件的三元组，返回 -1 。
 * <p>
 * 示例 1：
 * 输入：nums = [8,6,1,5,3]
 * 输出：9
 * 解释：三元组 (2, 3, 4) 是一个元素和等于 9 的山形三元组，因为：
 * - 2 < 3 < 4
 * - nums[2] < nums[3] 且 nums[4] < nums[3]
 * 这个三元组的元素和等于 nums[2] + nums[3] + nums[4] = 9 。可以证明不存在元素和小于 9 的山形三元组。
 * <p>
 * 示例 2：
 * 输入：nums = [5,4,8,7,10,2]
 * 输出：13
 * 解释：三元组 (1, 3, 5) 是一个元素和等于 13 的山形三元组，因为：
 * - 1 < 3 < 5
 * - nums[1] < nums[3] 且 nums[5] < nums[3]
 * 这个三元组的元素和等于 nums[1] + nums[3] + nums[5] = 13 。可以证明不存在元素和小于 13 的山形三元组。
 * <p>
 * 示例 3：
 * 输入：nums = [6,5,4,3,4,5]
 * 输出：-1
 * 解释：可以证明 nums 中不存在山形三元组。
 * <p>
 * 提示：
 * 3 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 */
public class ElementsAndTheSmallestMountainTripleI implements Answer {

    public static void main(String[] args) {
        new ElementsAndTheSmallestMountainTripleI().answerOne();
    }

    /**
     * 解1：
     * 思路：对于每个数num[i]，最左求比自己小的最小值，最右求比自己小的最小值。然后求解即可。
     * 那么，最右求最小值有很多重复遍历，是否可以保存下来？
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        System.out.println(minimumSum(nums));
    }

    /**
     * 遇到这种三元组的题目，一个通常的做法是枚举中间的数。
     * 知道了 nums[j]，只需要知道 j 左边的最小值和右边的最小值，就知道了三元组的和的最小值。
     * <p>
     * 两个辅助数组：
     * 1.用来存储左边最小的。
     * 2.用来存储自己右侧最小的。
     */
    public int minimumSum(int[] nums) {
        if (nums.length < 3) {
            return -1;
        }
        int minL = nums[0];
        int[] minLeft = new int[nums.length];
        int minR = nums[nums.length - 1];
        int[] minRight = new int[nums.length];
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            minLeft[i] = minL;
            minL = Math.min(nums[i], minL);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            minRight[i] = minR;
            minR = Math.min(minR, nums[i]);
        }
        //fixme:由于这里还要遍历1遍，因此这里可以顺便计算最小左侧，而不用minLeft数组
        for (int i = 1; i < nums.length - 1; i++) {
            if (minLeft[i] < nums[i] && nums[i] > minRight[i]) {
                int tempSum = minLeft[i] + nums[i] + minRight[i];
                result = Math.min(tempSum, result);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        //return new int[]{8, 6, 1, 5, 3};
        //return new int[]{5, 4, 8, 7, 10, 2};
        return new int[]{6, 5, 4, 3, 4, 5};
    }
}
