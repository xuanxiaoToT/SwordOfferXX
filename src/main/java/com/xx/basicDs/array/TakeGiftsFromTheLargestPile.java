package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/28
 * <p>
 * 从数量最多的堆取走礼物
 * LeetCode 2558 Easy
 * <p>
 * 给你一个整数数组 gifts ，表示各堆礼物的数量。每一秒，你需要执行以下操作：
 * -选择礼物数量最多的那一堆。
 * -如果不止一堆都符合礼物数量最多，从中选择任一堆即可。
 * -选中的那一堆留下平方根数量的礼物（向下取整），取走其他的礼物。
 * 返回在 k 秒后剩下的礼物数量。
 * <p>
 * 示例 1：
 * 输入：gifts = [25,64,9,4,100], k = 4
 * 输出：29
 * 解释：
 * 按下述方式取走礼物：
 * - 在第一秒，选中最后一堆，剩下 10 个礼物。
 * - 接着第二秒选中第二堆礼物，剩下 8 个礼物。
 * - 然后选中第一堆礼物，剩下 5 个礼物。
 * - 最后，再次选中最后一堆礼物，剩下 3 个礼物。
 * 最后剩下的礼物数量分别是 [5,8,9,4,3] ，所以，剩下礼物的总数量是 29 。
 * <p>
 * 示例 2：
 * 输入：gifts = [1,1,1,1], k = 4
 * 输出：4
 * 解释：
 * 在本例中，不管选中哪一堆礼物，都必须剩下 1 个礼物。
 * 也就是说，你无法获取任一堆中的礼物。
 * 所以，剩下礼物的总数量是 4 。
 * <p>
 * 提示：
 * 1 <= gifts.length <= 10^3
 * 1 <= gifts[i] <= 10^9
 * 1 <= k <= 10^3
 */
public class TakeGiftsFromTheLargestPile implements Answer {

    public static void main(String[] args) {
        new TakeGiftsFromTheLargestPile().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] gifts = initData();
        int k = 4;
        System.out.println(pickGifts(gifts, k));
    }

    public long pickGifts(int[] gifts, int k) {
        for (int i = 0; i < k; i++) {
            int maxIndex = 0;
            int maxValue = 0;
            for (int j = 0; j < gifts.length; j++) {
                if (gifts[j] > maxValue) {
                    maxIndex = j;
                    maxValue = gifts[j];
                }
            }
            gifts[maxIndex] = (int) Math.floor(Math.sqrt(gifts[maxIndex]));
            System.out.println(Arrays.toString(gifts));
        }

        long result = 0;
        for (int gift : gifts) {
            result += gift;
        }
        return result;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{25, 64, 9, 4, 100};
    }
}
