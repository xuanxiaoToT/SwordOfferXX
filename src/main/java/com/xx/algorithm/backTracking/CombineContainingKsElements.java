package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/27
 * <p>
 * 包含k个元素的组合
 * <p>
 * 输入n和k，请输出从1到n中选取k个数字组成的所有组
 * 合。
 * <p>
 * 例如，如果n等于3，k等于2，将组成3个组合，分别是[1，2]、
 * [1，3]和[2，3]。
 */
public class CombineContainingKsElements implements Answer {

    public static void main(String[] args) {
        new CombineContainingKsElements().answerOne();
    }

    List<List<Integer>> result = new ArrayList<>();

    /**
     * something
     */
    @Override
    public void answerOne() {
        int k = 2;
        int[] nums = initData();
        diGui(new ArrayList<>(), 0, nums, k);
        System.out.println(result);
    }

    private void diGui(List<Integer> temp, int index, int[] data, int k) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (index >= data.length) {
            return;
        }
        // 选择此元素
        temp.add(data[index]);
        diGui(temp, index + 1, data, k);

        // 不选择此元素
        temp.remove(temp.size() - 1);
        diGui(temp, index + 1, data, k);
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 4};
    }
}