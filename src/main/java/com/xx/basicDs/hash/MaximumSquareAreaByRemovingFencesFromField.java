package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 移除栅栏得到的正方形田地的最大面积
 * LeetCode 2975. Medium
 * <p>
 * 有一个大型的 (m - 1) x (n - 1) 矩形田地，其两个对角分别是 (1, 1) 和 (m, n) ，
 * 田地内部有一些水平栅栏和垂直栅栏，分别由数组 hFences 和 vFences 给出。
 * 水平栅栏为坐标 (hFences[i], 1) 到 (hFences[i], n)，垂直栅栏为坐标 (1, vFences[i]) 到 (m, vFences[i]) 。
 * 返回通过 移除 一些栅栏（可能不移除）所能形成的最大面积的 正方形 田地的面积，或者如果无法形成正方形田地则返回 -1。
 * 由于答案可能很大，所以请返回结果对 109 + 7 取余 后的值。
 * 注意：田地外围两个水平栅栏（坐标 (1, 1) 到 (1, n) 和坐标 (m, 1) 到 (m, n) ）以及两个垂直栅栏（坐标 (1, 1) 到 (m, 1) 和坐标 (1, n) 到 (m, n) ）所包围。这些栅栏 不能 被移除。
 * <p>
 * 示例 1：
 * 输入：m = 4, n = 3, hFences = [2,3], vFences = [2]
 * 输出：4
 * 解释：移除位于 2 的水平栅栏和位于 2 的垂直栅栏将得到一个面积为 4 的正方形田地。
 * <p>
 * 示例 2：
 * 输入：m = 6, n = 7, hFences = [2], vFences = [4]
 * 输出：-1
 * 解释：可以证明无法通过移除栅栏形成正方形田地。
 * <p>
 * 提示：
 * 3 <= m, n <= 10^9
 * 1 <= hFences.length, vFences.length <= 600
 * 1 < hFences[i] < m
 * 1 < vFences[i] < n
 * hFences 和 vFences 中的元素是唯一的。
 * <p>
 * Tag：哈希
 */
public class MaximumSquareAreaByRemovingFencesFromField implements Answer {
    public static void main(String[] args) {
        new MaximumSquareAreaByRemovingFencesFromField().answerOne();
    }

    @Override
    public void answerOne() {
        int m = 4;
        int n = 3;
        int[] hFences = new int[]{2, 3};
        int[] vFences = new int[]{2};

        // int m = 6;
        // int n = 7;
        // int[] hFences = new int[]{2};
        // int[] vFences = new int[]{4};

        // int m = 6;
        // int n = 7;
        // int[] hFences = new int[]{2, 3};
        // int[] vFences = new int[]{4};
        System.out.println(maximizeSquareArea(m, n, hFences, vFences));
    }

    /**
     * O(N2)
     * 只需要记录边长即可
     * <p>
     * 这把虽然花的时间多，但是基本没有错误提交
     * 而且思路很正确，先想出最暴力的，然后再根据最暴力的进行优化
     */
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Arrays.sort(hFences);
        Arrays.sort(vFences);
        int mask = 1_000_000_007;
        long result = -1;
        Set<Integer> hangLengthSet = new HashSet<>();
        for (int i = -1; i < hFences.length; i++) {
            int last = 1;
            if (i > -1) {
                last = hFences[i];
            }
            for (int j = i + 1; j <= hFences.length; j++) {
                int now = 0;
                if (j == hFences.length) {
                    now = m;
                } else {
                    now = hFences[j];
                }
                hangLengthSet.add(now - last);
            }
        }
        Set<Integer> lieLengthSet = new HashSet<>();
        for (int i = -1; i < vFences.length; i++) {
            int last = 1;
            if (i > -1) {
                last = vFences[i];
            }
            for (int j = i + 1; j <= vFences.length; j++) {
                int now = 0;
                if (j == vFences.length) {
                    now = n;
                } else {
                    now = vFences[j];
                }
                lieLengthSet.add(now - last);
            }
        }
        for (Integer hangLeng : hangLengthSet) {
            if (lieLengthSet.contains(hangLeng)) {
                long temp = ((long) hangLeng * hangLeng);
                result = Math.max(temp, result);
            }
        }
        return Math.toIntExact(result % mask);
    }

    /**
     * 最简单的，四重遍历，但是会超时。
     * 想一想：哪些遍历是重复的？
     * 选中一条左边，计算边的全部长度
     */
    public int maximizeSquareAreaOld(int m, int n, int[] hFences, int[] vFences) {
        Arrays.sort(hFences);
        Arrays.sort(vFences);
        int mask = 1_000_000_007;
        long result = -1;
        for (int i = -1; i < hFences.length; i++) {
            int hang = 1;
            if (i > -1) {
                hang = hFences[i];
            }
            for (int j = -1; j < vFences.length; j++) {
                int lie = 1;
                if (j > -1) {
                    lie = vFences[j];
                }
                for (int k = i + 1; k <= hFences.length; k++) {
                    int downHang = 0;
                    if (k == hFences.length) {
                        downHang = m;
                    } else {
                        downHang = hFences[k];
                    }
                    for (int l = j + 1; l <= vFences.length; l++) {
                        int rightLie = 0;
                        if (l == vFences.length) {
                            rightLie = n;
                        } else {
                            rightLie = vFences[l];
                        }
                        if (rightLie - lie == downHang - hang) {
                            long temp = ((long) (rightLie - lie) * (downHang - hang));
                            result = Math.max(temp, result);
                        }
                    }
                }
            }
        }
        return Math.toIntExact(result % mask);
    }

    @Override
    public Object initData() {
        return null;
    }
}
