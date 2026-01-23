package com.xx.basicDs.number.位运算;

import com.xx.Answer;

import java.util.Arrays;
import java.util.List;

/**
 * 构造最小位运算数组I
 * LeetCode 3314. Easy
 * <p>
 * 给你一个长度为 n 的质数数组 nums 。你的任务是返回一个长度为 n 的数组 ans ，对于每个下标 i ，以下 条件 均成立：
 * ans[i] OR (ans[i] + 1) == nums[i]
 * 除此以外，你需要 最小化 结果数组里每一个 ans[i] 。
 * 如果没法找到符合 条件 的 ans[i] ，那么 ans[i] = -1 。
 * 质数 指的是一个大于 1 的自然数，且它只有 1 和自己两个因数。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,5,7]
 * 输出：[-1,1,4,3]
 * 解释：
 * 对于 i = 0 ，不存在 ans[0] 满足 ans[0] OR (ans[0] + 1) = 2 ，所以 ans[0] = -1 。
 * 对于 i = 1 ，满足 ans[1] OR (ans[1] + 1) = 3 的最小 ans[1] 为 1 ，因为 1 OR (1 + 1) = 3 。
 * 对于 i = 2 ，满足 ans[2] OR (ans[2] + 1) = 5 的最小 ans[2] 为 4 ，因为 4 OR (4 + 1) = 5 。
 * 对于 i = 3 ，满足 ans[3] OR (ans[3] + 1) = 7 的最小 ans[3] 为 3 ，因为 3 OR (3 + 1) = 7 。
 * <p>
 * 示例 2：
 * 输入：nums = [11,13,31]
 * 输出：[9,12,15]
 * 解释：
 * 对于 i = 0 ，满足 ans[0] OR (ans[0] + 1) = 11 的最小 ans[0] 为 9 ，因为 9 OR (9 + 1) = 11 。
 * 对于 i = 1 ，满足 ans[1] OR (ans[1] + 1) = 13 的最小 ans[1] 为 12 ，因为 12 OR (12 + 1) = 13 。
 * 对于 i = 2 ，满足 ans[2] OR (ans[2] + 1) = 31 的最小 ans[2] 为 15 ，因为 15 OR (15 + 1) = 31 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 2 <= nums[i] <= 1000
 * nums[i] 是一个质数。
 * <p>
 * Tag: 位运算
 */
public class ConstructTheMinimumBitwiseArrayI implements Answer {
    public static void main(String[] args) {
        new ConstructTheMinimumBitwiseArrayI().answer();
    }

    @Override
    public void answer() {
        List<Integer> nums = Arrays.asList(2, 3, 5, 7);
        System.out.println(Arrays.toString(minBitwiseArray(nums)));
    }

    /**
     * 根据题目要求：为 nums 中的每一个 x 找到一个最小的 ans 使得 ans∣(ans+1)=x。
     * 观察 ans+1，易知 ans+1 的作用是将 ans 中从低位到高位第一个 0 变成 1 并且使该 0 之前的全部低位 1 变为 0，
     * 那么 ans∣(ans+1) 的作用即是将ans 中从低位到高位第一个 0 变成 1。
     * <p>
     * 由此可知，对于 x 二进制位从低位到高位的第一个 0 之前的所有 1，任取一个 1 变为 0 都可以求得一个 ans，使得 ans∣(ans+1)=x。
     * 题目要求找到最小的 ans，因此我们只需要找到每个 x 中第一位 0 的位置 pos 并将 pos−1 处的 1 变为 0 即可，
     * 具体代码逻辑如下：使用 d 来判断当前二进制位是否为 0，假设当前二进制位为 1，
     * 那么将 res 更新为 x−d，也就是使当前二进制位变为 0，然后将 d 左移一位，继续判断下一位二进制位是否为 0。如果当前二进制位为 0，那么说明已经没有更小的 ans 了。
     *
     */
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            int res = -1;
            int mask = 1;
            while ((x & mask) != 0) {
                // 使当前二进制位变为 0
                res = x - mask;
                mask <<= 1;
            }
            result[i] = res;
        }
        return result;
    }

    /**
     * 大概分析了一下，发现这个数一定是位于 (num - 1) / 2 和 num - 1之间
     * 所以直接遍历一下
     */
    public int[] minBitwiseArrayOld(List<Integer> nums) {
        int[] ans = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            Integer num = nums.get(i);
            ans[i] = -1;
            for (int j = (num - 1) / 2; j <= num - 1; j++) {
                if ((j | (j + 1)) == num) {
                    ans[i] = j;
                    break;
                }
            }
        }
        return ans;
    }

    @Override
    public Object initData() {
        return null;
    }
}
