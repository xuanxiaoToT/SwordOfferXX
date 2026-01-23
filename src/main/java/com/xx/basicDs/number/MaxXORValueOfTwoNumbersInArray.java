package com.xx.basicDs.number;

import com.xx.Answer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/4
 * <p>
 * 数组中两个数的最大异或值
 * LeetCide 421. Medium
 * <p>
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * <p>
 * 示例 1：
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * <p>
 * 示例 2：
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 10^5
 * 0 <= nums[i] <= 2^31 - 1
 */
public class MaxXORValueOfTwoNumbersInArray implements Answer {

    public static void main(String[] args) {
        new MaxXORValueOfTwoNumbersInArray().answer();
    }

    /**
     * 解1：
     * https://cloud.tencent.com/developer/article/1521719
     */
    @Override
    public void answer() {
        int[] nums = initData();
        System.out.println(findMaximumXOR(nums));
    }

    // 先确定高位，再确定低位（有点贪心算法的意思），才能保证这道题的最大性质
    // 一位接着一位去确定这个数位的大小
    // 利用性质：a ^ b = c ，则 a ^ c = b，且 b ^ c = a
    public int findMaximumXOR(int[] nums) {
        int res = 0;
        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            // 注意点1：注意保留前缀的方法，mask 是这样得来的
            // 用异或也是可以的 mask = mask ^ (1 << i),作用是使得前i位为1
            mask = mask | (1 << i);

             //System.out.println(Integer.toBinaryString(mask));
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                // 注意点2：这里使用 & ，保留前缀的意思（从高位到低位）
                set.add(num & mask);
            }

            // 这里先假定第 n 位为 1 ，前 n-1 位 res 为之前迭代求得
            int temp = res | (1 << i);
            for (Integer prefix : set) {
                if (set.contains(prefix ^ temp)) {
                    res = temp;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{3, 10, 5, 25, 2, 8};
    }
}
