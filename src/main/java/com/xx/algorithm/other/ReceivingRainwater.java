package com.xx.algorithm.other;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/5
 * <p>
 * 接雨水
 * LeetCode 42.
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <img width="640" height="320" src="https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/legend.png" alt="">
 * <p>
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * 示例 3：
 * 输入：height = [4,2,3]
 * 输出：1
 */
public class ReceivingRainwater implements Answer {

    public static void main(String[] args) {
        new ReceivingRainwater().answerOne();
    }

    /**
     * 解1：自己AC的
     */
    @Override
    public void answerOne() {
        int[] height = initData();
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > 0) {
                // 右侧相对最大的
                int next = findNextGreaterIndex(height, i);
                if (next != i) {
                    int volume = computeRainwaterVolume(height, i, next);
                    i = next - 1;
                    result = result + volume;
                }
                System.out.println(result);
            }
        }
    }

    /**
     * 结算start和end之间的雨水体积。
     * todo：此处可以优化，即在findNextGreaterIndex方法中，同时计算雨水体积，可以节省遍历次数。
     */
    private int computeRainwaterVolume(int[] height, int start, int end) {
        if (end == start + 1 || end == start) {
            return 0;
        }
        int base = Math.min(height[start], height[end]);
        int result = 0;
        for (int i = start + 1; i < end; i++) {
            result = result + (base - height[i]);
        }
        return result;
    }

    /**
     * 1.如果右侧存在比本节点大的，则立即返回。(即找出第一个比本节点大的)
     * 2.如果右侧不存在比本节点大的，则找出右节点中最大的。
     */
    private int findNextGreaterIndex(int[] height, int start) {
        int maxHeight = 0;
        int result = start;
        for (int i = start + 1; i < height.length; i++) {
            if (height[i] >= height[start]) {
                return i;
            }
            if (height[i] > maxHeight) {
                maxHeight = height[i];
                result = i;
            }
        }
        return result;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        //return new int[]{4, 2, 0, 3, 2, 5};
        //return new int[]{4, 2, 3};
    }
}
