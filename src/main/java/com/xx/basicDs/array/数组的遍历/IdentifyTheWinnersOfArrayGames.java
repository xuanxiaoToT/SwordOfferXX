package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/19
 * <p>
 * 找出数组游戏的赢家
 * LeetCode 1535.  Medium
 * <p>
 * 给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。
 * 每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。比较 arr[0] 与 arr[1] 的大小，
 * 较大的整数将会取得这一回合的胜利并保留在位置 0 ，较小的整数移至数组的末尾。当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家 。
 * 返回赢得比赛的整数。
 * 题目数据 保证 游戏存在赢家。
 * <p>
 * 示例 1：
 * 输入：arr = [2,1,3,5,4,6,7], k = 2
 * 输出：5
 * 解释：一起看一下本场游戏每回合的情况：
 * <p>
 * 因此将进行 4 回合比赛，其中 5 是赢家，因为它连胜 2 回合。
 * <p>
 * 示例 2：
 * 输入：arr = [3,2,1], k = 10
 * 输出：3
 * 解释：3 将会在前 10 个回合中连续获胜。
 * <p>
 * 示例 3：
 * 输入：arr = [1,9,8,2,3,7,6,4,5], k = 7
 * 输出：9
 * <p>
 * 示例 4：
 * 输入：arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000
 * 输出：99
 * <p>
 * 提示：
 * 2 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^6
 * arr 所含的整数 各不相同 。
 * 1 <= k <= 10^9
 * <p>
 * Tag:仔细读题   数组遍历   逻辑
 */
public class IdentifyTheWinnersOfArrayGames implements Answer {
    public static void main(String[] args) {
        new IdentifyTheWinnersOfArrayGames().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        //int[] arr = new int[]{2, 1, 3, 5, 4, 6, 7};
        //int k = 2;
        int[] arr = new int[]{1, 9, 8, 2, 3, 7, 6, 4, 5};
        int k = 7;
        System.out.println(getWinner(arr, k));
    }

    /**
     * 空间浪费。
     * 注意是连续获胜k回合！
     */
    public int getWinner(int[] arr, int k) {
        //求最大值
        if (k >= arr.length) {
            int max = Arrays.stream(arr).max().getAsInt();
            return max;
        }
        Map<Integer, Integer> winCountMap = new HashMap<>();
        Deque<Integer> queue = new LinkedList<>();
        for (int num : arr) {
            queue.addLast(num);
        }
        while (!queue.isEmpty()) {
            int first = queue.pollFirst();
            int second = queue.pollFirst();
            if (first > second) {
                winCountMap.put(first, winCountMap.getOrDefault(first, 0) + 1);
                if (winCountMap.get(first) == k) {
                    return first;
                }
                queue.addFirst(first);
                queue.addLast(second);
            } else {
                winCountMap.put(second, winCountMap.getOrDefault(second, 0) + 1);
                if (winCountMap.get(second) == k) {
                    return second;
                }
                queue.addFirst(second);
                queue.addLast(first);
            }
        }
        return -1;
    }

    /**
     * 更好的解法
     * 遍历的时候，只需要关注前面的胜者即可
     * <p>
     * 注意条件：连续获胜k回合。因此，之前胜利的回合数不需要保存！
     * 因此，我的解法1是空间浪费的
     */
    public int getWinner2(int[] arr, int k) {
        int max = Math.max(arr[0], arr[1]), winSum = 1;
        for (int i = 2; i < arr.length; i++) {
            if (winSum == k) {
                return max;
            }
            if (max > arr[i]) {
                winSum++;
            } else {
                max = arr[i];
                winSum = 1;
            }
        }
        return max;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
