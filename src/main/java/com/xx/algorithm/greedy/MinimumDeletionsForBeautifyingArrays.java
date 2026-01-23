package com.xx.algorithm.greedy;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/21
 * <p>
 * 美化数组的最少删除数
 * LeetCode  2216. Medium
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：
 * nums.length 为偶数
 * 对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
 * 注意，空数组同样认为是美丽数组。
 * 你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。
 * 返回使 nums 变为美丽数组所需删除的 最少 元素数目。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2,3,5]
 * 输出：1
 * 解释：可以删除 nums[0] 或 nums[1] ，这样得到的 nums = [1,2,3,5] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 1 个元素。
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,2,2,3,3]
 * 输出：2
 * 解释：可以删除 nums[0] 和 nums[5] ，这样得到的 nums = [1,2,2,3] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 2 个元素。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 *
 * Tag:  数组  贪心
 */
public class MinimumDeletionsForBeautifyingArrays implements Answer {

    public static void main(String[] args) {
        new MinimumDeletionsForBeautifyingArrays().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[] nums = initData();
        System.out.println(minDeletion(nums));
    }

    /**
     * 使用变量 delCount 代表已删除的元素个数，由于每次删除元素，剩余元素都会往前移动，因此当前trueIndex下标为 i−delCount 。
     * 处理 nums 过程中，若当前下标为偶数，且与下一位置元素相同，那么当前元素需被删除，令 delCount 自增。
     * <p>
     * 最终数组长度为 n−delCount，若长度为奇数，需要再额外删除结尾元素（delCount 再加一），否则 delCount 即为答案。
     */
    public int minDeletion(int[] nums) {
        int delCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int trueIndex = Math.max(i - delCount, 0);
            int end = i;
            if (trueIndex % 2 == 0) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] == nums[j]) {
                        delCount++;
                        end = j;
                    } else {
                        end = j;
                        break;
                    }
                }
                i = end;
            }
        }
        if ((nums.length - delCount) % 2 != 0) {
            delCount++;
        }
        return delCount;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        //return new int[]{1, 1, 2, 3, 5};
        //return new int[]{1, 1, 2, 2, 3, 3};
        return new int[]{8, 8, 8, 8, 1};
    }
}
