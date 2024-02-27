package com.xx.algorithm.search;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/20
 * <p>
 * 狒狒吃香蕉
 * LeetCode 875. 爱吃香蕉的珂珂  Medium
 * <p>
 * 狒狒很喜欢吃香蕉。一天它发现了n堆香蕉，第i堆有
 * piles[i]根香蕉。门卫刚好走开，H小时后才会回来。狒狒吃香蕉喜
 * 欢细嚼慢咽，但又想在门卫回来之前吃完所有的香蕉。请问狒狒每
 * 小时至少吃多少根香蕉？如果狒狒决定每小时吃k根香蕉，而它在吃
 * 的某一堆剩余的香蕉的数目少于k，那么它只会将这一堆的香蕉吃
 * 完，下一个小时才会开始吃另一堆的香蕉。
 * <p>
 * 例如，有4堆香蕉，表示香蕉数目的数组piles为[3，6，7，11]，
 * 门卫将于8小时之后回来，那么狒狒每小时吃香蕉的最少数目为4根。
 * 如果它每小时吃4根香蕉，那么它用8小时吃完所有香蕉。如果它每小
 * 时只吃3根香蕉，则需要10小时，不能在门卫回来之前吃完。
 * <p>
 * 示例 1：
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * <p>
 * 示例 3：
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 * <p>
 * 提示：
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 */
public class BaboonsEatBananas implements Answer {

    public static void main(String[] args) {
        new BaboonsEatBananas().answerOne();
    }

    /**
     * 1.确定解的范围，容易得其最大值。故可以在[1,MAX]的范围内二分，每个值代入确定能不能吃完。
     * 解法略，复杂度O(N*LogN)
     * 缺点：每次都需要遍历piles数组。这块能省？
     */
    @Override
    public void answerOne() {
        int[] piles = {332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184};
        // 门卫回来的时间
        int targetHours = 823855818;
        System.out.println(minEatingSpeed(piles, targetHours));
    }

    public int minEatingSpeed(int[] piles, int targetHours) {
        int max = Arrays.stream(piles).max().getAsInt();
        int min = 1;
        if (targetHours == piles.length) {
            return max;
        }
        int result = max;
        while (min < max) {
            int mid = min + (max - min) / 2;
            int eatHours = computeEatHours(piles, mid);
            if (eatHours <= targetHours) {
                max = mid;
                //最后一个mid值
                result = mid;
            } else {
                min = mid + 1;
            }
        }
        return result;
    }

    private int computeEatHours(int[] piles, int mid) {
        int sum = 0;
        for (int pile : piles) {
            int temp = pile / mid;
            if (pile % mid > 0) {
                temp = temp + 1;
            }
            sum = sum + temp;
        }
        return sum;
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{3, 6, 7, 11};
    }
}