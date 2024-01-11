package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/11
 * <p>
 * 种花问题
 * LeetCode 605. Easy
 * <p>
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false 。
 * <p>
 * 示例 1：
 * 输入：flowerbed = [1,0,0,0,1], n = 1
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：flowerbed = [1,0,0,0,1], n = 2
 * 输出：false
 * <p>
 * 提示：
 * 1 <= flowerbed.length <= 2 * 10^4
 * flowerbed[i] 为 0 或 1
 * flowerbed 中不存在相邻的两朵花
 * 0 <= n <= flowerbed.length
 * <p>
 * Tag: 数组的遍历  贪心  注意边界情况
 */
public class FlowerPlantingProblem implements Answer {

    public static void main(String[] args) {
        new FlowerPlantingProblem().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] flowerbed = {1, 0, 1, 0, 1, 0, 1};
        System.out.println(canPlaceFlowers(flowerbed, 1));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int m = flowerbed.length;
        for (int i = 0; i < flowerbed.length; i++) {
            if ((i == 0 || flowerbed[i - 1] == 0) && flowerbed[i] == 0 && (i == m - 1 || flowerbed[i + 1] == 0)) {
                count++;
                flowerbed[i] = 1;
            }
        }
        return count >= n;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
