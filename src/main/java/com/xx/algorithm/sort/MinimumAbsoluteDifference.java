package com.xx.algorithm.sort;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最小绝对差
 * LeetCode 1200. Easy
 * <p>
 * 给你个整数数组 arr，其中每个元素都 不相同。
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 * 每对元素对 [a,b] 如下：
 * a , b 均为数组 arr 中的元素
 * a < b
 * b - a 等于 arr 中任意两个元素的最小绝对差
 * <p>
 * 示例 1：
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 * <p>
 * 示例 2：
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 * <p>
 * 示例 3：
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 * <p>
 * 提示：
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 * <p>
 * Tag:排序  数组遍历
 */
public class MinimumAbsoluteDifference implements Answer {
    public static void main(String[] args) {
        new MinimumAbsoluteDifference().answer();
    }

    @Override
    public void answer() {
        int[] arr = new int[]{4, 2, 1, 3};
        System.out.println(minimumAbsDifference(arr));
    }

    /**
     * 遇到更小的就清空
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);

        int best = Integer.MAX_VALUE;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < n - 1; ++i) {
            int delta = arr[i + 1] - arr[i];
            if (delta < best) {
                best = delta;
                ans.clear();
                List<Integer> pair = new ArrayList<Integer>();
                pair.add(arr[i]);
                pair.add(arr[i + 1]);
                ans.add(pair);
            } else if (delta == best) {
                List<Integer> pair = new ArrayList<Integer>();
                pair.add(arr[i]);
                pair.add(arr[i + 1]);
                ans.add(pair);
            }
        }

        return ans;
    }


    /**
     * 简单做法：遍历两次
     */
    public List<List<Integer>> minimumAbsDifferenceOld(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i] - arr[i - 1]);
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == min) {
                result.add(new ArrayList<>(Arrays.asList(arr[i - 1], arr[i])));
            }
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
