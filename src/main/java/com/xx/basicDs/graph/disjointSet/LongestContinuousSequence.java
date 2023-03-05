package com.xx.basicDs.graph.disjointSet;

import com.xx.Answer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xuanxiao
 * @CreateDate 2023/1/4
 * <p>
 * 最长连续序列
 * <p>
 * 输入一个无序的整数数组，请计算最长的连续数值序列的长度。
 * 例如，输入数组[10，5，9，2，4，3]，则最长的连续数值
 * 序列是[2，3，4，5]，因此输出4。
 */
public class LongestContinuousSequence implements Answer {

    public static void main(String[] args) {
        new LongestContinuousSequence().answerThree();
    }

    /**
     * 解1:无脑排序即可。
     * 缺点：最差O(NlogN)
     */
    @Override
    public void answerOne() {
        // 快排略
    }

    /**
     * 解2:用图的广度优先遍历来解决。
     * poll之后如果存在poll+1或者poll-1则将其入队。
     * 遍历过的入had，后续不再遍历
     * 每次的值记录，然后取最大值即可。
     */
    private void answerTwo() {
        // 解法略。ps：感觉不如直接排序
        // 优点：O(N)
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
        Set<Integer> had = new HashSet<>();
        for (int datum : data) {
            setData.add(datum);
            fatherMap.put(datum, datum);
        }
        for (int datum : data) {
            had.add(datum);
            unionNode(fatherMap, datum, setData, had);
        }
        System.out.println(fatherMap);
    }

    private void unionNode(Map<Integer, Integer> fatherMap, int datum, Set<Integer> setData, Set<Integer> had) {
        int root = findRoot(fatherMap, datum);
        if (setData.contains(datum - 1)) {
            int rootLeft = findRoot(fatherMap, datum - 1);
            if (root != rootLeft) {
                fatherMap.put(root, rootLeft);
            }
        }
        if (setData.contains(datum + 1)) {
            int rootRight = findRoot(fatherMap, datum + 1);
            if (root != rootRight) {
                fatherMap.put(root, rootRight);
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
        return new int[]{10, 5, 9, 2, 4, 3};
    }
}