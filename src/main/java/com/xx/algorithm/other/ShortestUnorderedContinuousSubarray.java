package com.xx.algorithm.other;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/16
 * <p>
 * 最短无序连续子数组
 * LeetCode 581
 * <p>
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，
 * 那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 * 示例 1：
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：0
 */
public class ShortestUnorderedContinuousSubarray implements Answer {

    public static void main(String[] args) {
        new ShortestUnorderedContinuousSubarray().answerOne();
    }

    /**
     * 解1：子数组的起点：一个数要比之前所有的数都大，如果之前的数比我大，则之前的数就是不规则的起点。
     * 终点：如果之前的数有比我大，则我是终点。
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int[] assist = new int[data.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            assist[i] = Math.max(max, data[i]);
            max = Math.max(max, data[i]);
        }
        int left = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] < assist[i]) {
                if (left != -1) {
                    left = Math.min(findLeft(data, assist, i), left);
                } else {
                    left = findLeft(data, assist, i);
                }
            }
        }
        int right = -1;
        for (int i = data.length - 1; i > 0; i--) {
            if (data[i] < assist[i]) {
                right = i;
                break;
            }
        }

        if (left != -1 || right != -1) {
            System.out.println(right - left + 1);
        } else {
            System.out.println(0);
        }

    }

    private int findLeft(int[] data, int[] assist, int i) {
        for (int j = 0; j < assist.length; j++) {
            if (data[i] < assist[j]) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        //return new int[]{2, 6, 4, 8, 10, 9, 15};
        //return new int[]{2, 1, 3, 5, 4};
        //return new int[]{1, 3, 5, 4, 2};
        return new int[]{-1, -1, -1, -1};
    }
}
