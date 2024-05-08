package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/8
 * <p>
 * 给植物浇水
 * LeetCode 2079. Medium
 * <p>
 * 你打算用一个水罐给花园里的 n 株植物浇水。植物排成一行，从左到右进行标记，编号从 0 到 n - 1 。其中，第 i 株植物的位置是 x = i 。
 * x = -1 处有一条河，你可以在那里重新灌满你的水罐。
 * <p>
 * 每一株植物都需要浇特定量的水。你将会按下面描述的方式完成浇水：
 * 按从左到右的顺序给植物浇水。
 * 在给当前植物浇完水之后，如果你没有足够的水 完全 浇灌下一株植物，那么你就需要返回河边重新装满水罐。
 * 你 不能 提前重新灌满水罐。
 * 最初，你在河边（也就是，x = -1），在 x 轴上每移动 一个单位 都需要 一步 。
 * <p>
 * 给你一个下标从 0 开始的整数数组 plants ，数组由 n 个整数组成。其中，plants[i] 为第 i 株植物需要的水量。
 * 另有一个整数 capacity 表示水罐的容量，返回浇灌所有植物需要的 步数 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：plants = [2,2,3,3], capacity = 5
 * 输出：14
 * 解释：从河边开始，此时水罐是装满的：
 * - 走到植物 0 (1 步) ，浇水。水罐中还有 3 单位的水。
 * - 走到植物 1 (1 步) ，浇水。水罐中还有 1 单位的水。
 * - 由于不能完全浇灌植物 2 ，回到河边取水 (2 步)。
 * - 走到植物 2 (3 步) ，浇水。水罐中还有 2 单位的水。
 * - 由于不能完全浇灌植物 3 ，回到河边取水 (3 步)。
 * - 走到植物 3 (4 步) ，浇水。
 * 需要的步数是 = 1 + 1 + 2 + 3 + 3 + 4 = 14 。
 * <p>
 * 示例 2：
 * 输入：plants = [1,1,1,4,2,3], capacity = 4
 * 输出：30
 * 解释：从河边开始，此时水罐是装满的：
 * - 走到植物 0，1，2 (3 步) ，浇水。回到河边取水 (3 步)。
 * - 走到植物 3 (4 步) ，浇水。回到河边取水 (4 步)。
 * - 走到植物 4 (5 步) ，浇水。回到河边取水 (5 步)。
 * - 走到植物 5 (6 步) ，浇水。
 * 需要的步数是 = 3 + 3 + 4 + 4 + 5 + 5 + 6 = 30 。
 * <p>
 * 示例 3：
 * 输入：plants = [7,7,7,7,7,7,7], capacity = 8
 * 输出：49
 * 解释：每次浇水都需要重新灌满水罐。
 * 需要的步数是 = 1 + 1 + 2 + 2 + 3 + 3 + 4 + 4 + 5 + 5 + 6 + 6 + 7 = 49 。
 * <p>
 * 提示：
 * n == plants.length
 * 1 <= n <= 1000
 * 1 <= plants[i] <= 10^6
 * max(plants[i]) <= capacity <= 10^9
 * <p>
 * Tag:理解题以后很简单   数学
 */
public class WateringPlants implements Answer {
    public static void main(String[] args) {
        new WateringPlants().answerOne();
    }

    @Override
    public void answerOne() {
        // int[] plants = new int[]{2, 2, 3, 3};
        // int capacity = 5;
        // int[] plants = new int[]{1,1,1,4,2,3};
        // int capacity = 4;
        // int[] plants = new int[]{7,7,7,7,7,7,7};
        // int capacity = 8;
        int[] plants = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        int capacity = 8;
        System.out.println(stepsWateringPlants(plants, capacity));
    }

    /**
     * 理解题以后很简单
     */
    public int stepsWateringPlants(int[] plants, int capacity) {
        int result = 0;
        int remainder = capacity;
        for (int i = 0; i < plants.length; i++) {
            int plant = plants[i];
            result++;
            if (remainder >= plant) {
                remainder = remainder - plant;
            } else {
                // 回去取水，来回需要2*i到，再下一步已经包含在result++里，所以这里不需要
                result = result + (i + i);
                remainder = capacity - plant;
            }
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
