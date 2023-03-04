package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.Arrays;


/**
 * @author XuanXiao
 * @CreateDate 2023/03/04
 * <p>
 * 跳跃游戏
 * LeetCode 55
 * <p>
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class JumpGame implements Answer {

    public static void main(String[] args) {
        new JumpGame().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int[] dp = new int[data.length];
        dp[0] = 1;
        for (int i = 0; i < data.length; i++) {
            fillJumpPoints(dp, i, data[i]);
            if (dp[i] != 1) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    private void fillJumpPoints(int[] dp, int start, int maxDis) {
        for (int i = start + 1; i <= start + maxDis && i < dp.length; i++) {
            dp[i] = 1;
        }
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{4, 3, 2, 1, 0};
    }
}
