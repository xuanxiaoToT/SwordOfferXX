package com.xx.basicDs.array.前缀和;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * 连续数组
 * LeetCode 525. Medium+
 * <p>
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * 示例 1：
 * 输入：nums = [0,1]
 * 输出：2
 * 说明：[0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0]
 * 输出：2
 * 说明：[0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。
 * <p>
 * 示例 3：
 * 输入：nums = [0,1,1,1,1,1,0,0,0]
 * 输出：6
 * 解释：[1,1,1,0,0,0] 是具有相同数量 0 和 1 的最长连续子数组。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 * <p>
 * Tag: 哈希  前缀和
 */
public class ContiguousArray implements Answer {

    public static void main(String[] args) {
        new ContiguousArray().answer();
    }

    @Override
    public void answer() {
        // int[] nums = {0, 1, 1, 1, 1, 1, 0, 0, 0};
        // int[] nums = {0, 1, 1, 1, 1, 1, 0, 0, 0, 0};
        // int[] nums = { 1, 1, 1, 1, 1, 0, 0, 0, 0};
        int[] nums = {0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0};
        System.out.println(findMaxLength(nums));
    }

    /**
     * 将0视为-1，也就是求和为0的最大子数组
     * 用前缀和来记录出现过的和的情况。由于数组越长越好，所以只保留最左前缀
     * 然后枚举右侧，求出和，如果和为1，则需要前缀为1的(因为1+0=1)表明中间那段就是0了
     * 同理，当sum为0时，则本身就是最长结果。sum为-1时，则-1+0=0，找-1的最左前缀。
     */
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        // 这里求前缀和的时候，可以共用一次遍历
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum--;
            } else {
                sum += nums[i];
            }
            if (sum == 0) {
                res = Math.max(res, i + 1);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                int need = sum;
                if (map.get(need) < i) {
                    res = Math.max(res, i - map.get(need));
                }
            }
        }
        return res;
    }

    public Object initData() {
        return null;
    }
}

