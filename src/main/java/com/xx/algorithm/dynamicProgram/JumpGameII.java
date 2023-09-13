package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/13
 * <p>
 * 跳跃游戏 II
 * LeetCode 045  中等
 * <p>
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * <p>
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * 提示:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达 nums[n-1]
 */
public class JumpGameII implements Answer {

    public static void main(String[] args) {
        new JumpGameII().answerOne();
    }

    /**
     * dp[i][j] 表示j结尾，上一个位置是i的最小跳跃数。
     * fixme:寄，leetcode执行时，超出内存限制。
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int[][] dp = new int[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            //以i结尾的最小跳跃数。
            int min = findMin(dp, i);
            for (int j = i + 1; j <= i + data[i] && j < data.length; j++) {
                //从i开始到j
                dp[i][j] = min + 1;
            }
        }
        int result = findMin(dp, data.length - 1);
        System.out.println(result);
        System.out.println(Arrays.deepToString(dp));
    }

    private int findMin(int[][] dp, int i) {
        int min = -1;
        for (int j = 0; j < i; j++) {
            if (dp[j][i] != 0) {
                min = min > 0 ? Math.min(dp[j][i], min) : dp[j][i];
            }
        }
        return min == -1 ? 0 : min;
    }

    @Override
    public int[] initData() {
        return new int[]{3, 3, 2, 1, 0};
        // return new int[]{2, 3, 1, 1, 4};
        // return new int[]{2, 3, 0, 1, 4};
        // return new int[]{1, 1, 1, 1, 1};
        // return new int[]{2, 2, 2, 1, 1};
    }
}