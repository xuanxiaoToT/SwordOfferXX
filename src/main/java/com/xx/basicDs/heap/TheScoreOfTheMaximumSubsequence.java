package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/19
 */
public class TheScoreOfTheMaximumSubsequence implements Answer {

    public static void main(String[] args) {
        new TheScoreOfTheMaximumSubsequence().answerOne();
    }

    @Override
    public void answerOne() {
        int[] nums1 = {1, 4};
        int[] nums2 = {3, 1};
        int k = 2;
        System.out.println(computeMaxScore(nums1, nums2, k));
    }

    /**
     * 超时！
     */
    public long computeMaxScore(int[] nums1, int[] nums2, int k) {
        List<int[]> min2 = new ArrayList<>();
        List<int[]> max1 = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            max1.add(new int[]{nums1[i], i});
            min2.add(new int[]{nums2[i], i});
        }
        max1.sort(Comparator.comparingInt(dto -> dto[0]));
        min2.sort(Comparator.comparingInt(dto -> dto[0]));
        Set<Integer> hasVis = new HashSet<>();
        long maxResult = Integer.MIN_VALUE;
        for (int i = 0; i <= min2.size() - k; i++) {
            int min = min2.get(i)[0];
            int index = min2.get(i)[1];
            long sum = 0;
            int count = 0;
            for (int j = max1.size() - 1; j >= 0; j--) {
                int[] maxTemp = max1.get(j);
                if (k == 1) {
                    sum += nums1[index];
                    break;
                } else {
                    if (maxTemp[1] != index && !hasVis.contains(maxTemp[1])) {
                        count++;
                        sum += maxTemp[0];
                    }
                    if (count == k - 1) {
                        sum += nums1[index];
                        break;
                    }
                }
            }
            hasVis.add(index);
            maxResult = Math.max((long) sum * min, maxResult);
        }
        return maxResult;
    }

    @Override
    public Object initData() {
        return null;
    }
}