package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.*;

/**
 * @author xuanxiao
 * @CreateDate 2023/1/4
 * <p>
 * 最长连续序列
 * LeetCode 128 Medium
 * <p>
 * 输入一个无序的整数数组，请计算最长的连续数值序列的长度。
 * 例如，输入数组[10，5，9，2，4，3]，则最长的连续数值
 * 序列是[2，3，4，5]，因此输出4。
 * <p>
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class LongestContinuousSequence implements Answer {

    public static void main(String[] args) {
        new LongestContinuousSequence().answerThree();
    }

    /**
     * 解1:使用哈希表
     * 思路参考：https://leetcode.cn/problems/longest-consecutive-sequence/solutions/276931/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int tempCount = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    tempCount += 1;
                }

                longestStreak = Math.max(longestStreak, tempCount);
            }
        }
        System.out.println(longestStreak);
    }

    /**
     * 解2:用图的广度优先遍历来解决。
     * poll之后如果存在poll+1或者poll-1则将其入队。
     * 遍历过的入had，后续不再遍历
     * 每次的值记录，然后取最大值即可。
     */
    private void answerTwo() {
        int[] data = initData();
        HashSet<Integer> dataSet = new HashSet<>(data.length);
        for (int datum : data) {
            dataSet.add(datum);
        }
        int max = 0;
        for (int datum : data) {
            int bfs = bfs(dataSet, datum);
            max = Math.max(bfs, max);
        }
        System.out.println(max);
    }

    private int bfs(HashSet<Integer> dataSet, int startNum) {
        Queue<Integer> queue = new LinkedList<>();
        int result = 0;
        if (dataSet.contains(startNum)) {
            queue.add(startNum);
            dataSet.remove(startNum);
        }
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            result++;
            if (dataSet.contains(poll - 1)) {
                queue.add(poll - 1);
                dataSet.remove(poll - 1);
            }
            if (dataSet.contains(poll + 1)) {
                queue.add(poll + 1);
                dataSet.remove(poll + 1);
            }
        }
        return result;
    }

    /**
     * 解3:用并查集来做
     * 先初始化，然后对于每个子图，如果存在其n+1或n-1则直接合并。
     * 最后计算最大的子图。
     */
    private void answerThree() {
        int[] data = initData();
        Set<Integer> setData = new HashSet<>();
        Map<Integer, Integer> fatherMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int datum : data) {
            setData.add(datum);
            fatherMap.put(datum, datum);
            countMap.put(datum, 1);
        }
        int max = 0;
        for (int datum : data) {
            unionNode(fatherMap, countMap, datum, setData);
        }
        for (Integer value : countMap.values()) {
            max = Math.max(max, value);
        }

        System.out.println(max);

    }

    private void unionNode(Map<Integer, Integer> fatherMap, Map<Integer, Integer> countMap, int datum, Set<Integer> setData) {
        setData.remove(datum);
        int root = findRoot(fatherMap, datum);
        if (setData.contains(datum - 1)) {
            int rootLeft = findRoot(fatherMap, datum - 1);
            if (root != rootLeft) {
                fatherMap.put(rootLeft, root);
                countMap.put(root, countMap.get(root) + countMap.get(rootLeft));
            }
        }
        if (setData.contains(datum + 1)) {
            int rootRight = findRoot(fatherMap, datum + 1);
            if (root != rootRight) {
                fatherMap.put(rootRight, root);
                countMap.put(root, countMap.get(root) + countMap.get(rootRight));
            }
        }
    }

    private int findRoot(Map<Integer, Integer> fatherMap, int datum) {
        if (fatherMap.get(datum) != datum) {
            return findRoot(fatherMap, fatherMap.get(datum));
        }
        return fatherMap.get(datum);
    }

    /**
     * 输入数据
     */
    @Override
    public int[] initData() {
        //return new int[]{10, 5, 9, 2, 4, 3};
        return new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        //return new int[]{100, 4, 200, 1, 3, 2};
    }
}