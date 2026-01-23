package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/13
 * <p>
 * 特殊数组I
 * LeetCode 3151 Easy
 * <p>
 * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
 * Aging 有一个整数数组 nums。如果 nums 是一个 特殊数组 ，返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入：nums = [1]
 * 输出：true
 * 解释：
 * 只有一个元素，所以答案为 true。
 * <p>
 * 示例 2：
 * 输入：nums = [2,1,4]
 * 输出：true
 * 解释：
 * 只有两对相邻元素： (2,1) 和 (1,4)，它们都包含了奇偶性不同的数字，因此答案为 true。
 * <p>
 * 示例 3：
 * 输入：nums = [4,3,1,6]
 * 输出：false
 * 解释：
 * nums[1] 和 nums[2] 都是奇数。因此答案为 false。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * <p>
 * Tag:奇偶性可以用位运算
 */
public class SpecialArrayI implements Answer {
    /**
     * 解1：
     */
    @Override
    public void answer() {

    }

    public boolean isArraySpecial(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        for (int left = 0, right = 1; right < nums.length; left++, right++) {
            if ((nums[left] & 1) == (nums[right] & 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
