package com.xx.temp;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Random;

/**
 * 在长度 2N 的数组中找出重复 N 次的元素
 * LeetCode 961 Easy
 * <p>
 * 给你一个整数数组 nums ，该数组具有以下属性：
 * <p>
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,3]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：nums = [2,1,2,5,3,2]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：nums = [5,1,5,2,5,3,5,4]
 * 输出：5
 * <p>
 * 提示：
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 104
 * nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
 * <p>
 * Tag：众数问题
 */
public class NRepeatedElementArray implements Answer {
    public static void main(String[] args) {
        new NRepeatedElementArray().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums = new int[]{2, 1, 2, 5, 3, 2};
        System.out.println(repeatedNTimes(nums));
    }

    /**
     * 随机选择法
     * <p>
     * 在 nums 中随机选择两个下标不同的元素，如果两数相等，即找到了重复元素。
     * <p>
     * 可以证明当N增大时，概率趋近于四分之一
     * <p>
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/n-repeated-element-in-size-2n-array/solutions/3870905/si-chong-fang-fa-ha-xi-ji-he-mo-er-tou-p-f95m/
     */
    public int repeatedNTimes4(int[] nums) {
        final Random rand = new Random();
        int n = nums.length;
        while (true) {
            // 在 [0, n-1] 中随机生成两个不同下标
            int i = rand.nextInt(n);
            int j = rand.nextInt(n - 1);
            if (j >= i) {
                j++;
            }
            if (nums[i] == nums[j]) {
                return nums[i];
            }
        }
    }

    /**
     * 检查邻近元素
     * <p>
     * 设出现次数为 n 的那个数为 x。
     * 如果相邻两个 x 之间都至少有一个数，那么 nums 至少要有 2n−1 个数，这是合法的。
     * 如果相邻两个 x 之间都至少有两个数，那么 nums 至少要有 3n−2 个数。
     * <p>
     * 如果 n=2，这是合法的，例如 [3,1,2,3]。
     * 如果 n≥3，那么 3n−2>2n，不合法。这意味着，当 n≥3 时，一定存在 nums[i]=nums[i−1] 或者 nums[i]=nums[i−2]。
     * 为了兼容 n=2 的情况，我们可以判断 nums[i] 是否与下标 [i−3,i−1] 中的元素相等。
     *
     */
    public int repeatedNTimes3(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1])
                return nums[i];
            if (i + 2 < nums.length && nums[i] == nums[i + 2])
                return nums[i];
            if (i + 3 < nums.length && nums[i] == nums[i + 3])
                return nums[i];
        }
        return -1;
    }

    /**
     * 摩尔投票法
     * <p>
     * 为了让出现 n 次的那个数变成绝对众数，我们可以分类讨论：
     * <p>
     * 如果 nums[0] 在下标 [1,n−1] 中出现过，那么返回 nums[0]。
     * 否则，去掉 nums[0]，剩下 2n−1 个数，出现次数为 n 的那个数变成绝对众数，可以用 169 题的算法解决。
     * 这两件事情可以在同一个循环中完成。
     *
     */
    public int repeatedNTimes2(int[] nums) {
        int ans = 0;
        int hp = 0;
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            if (x == nums[0]) {
                return x;
            }
            // x 是初始擂主，生命值为 1
            if (hp == 0) {
                ans = x;
                hp = 1;
            } else {
                // 比武，同门加血，否则扣血
                hp += x == ans ? 1 : -1;
            }
        }
        return ans;
    }

    /**
     * 理解错了题意
     * 注意：恰有一个元素重复 n 次
     * 指的就是只有一个元素重复，其他元素不会重复~！
     */
    public int repeatedNTimes(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
                if (map.get(num) == nums.length / 2) {
                    return num;
                }
            } else {
                map.put(num, +1);
            }
        }
        return -1;
    }

    @Override
    public Object initData() {
        return null;
    }
}
