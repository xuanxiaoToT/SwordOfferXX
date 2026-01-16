package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/26
 * <p>
 * 所有子集
 * 子集
 * 幂集
 * LeetCode 78. Medium
 * <p>
 * 输入一个不含重复数字的数据集合，请找出它的所有子集。
 * <p>
 * 例如，数据集合[1，2]有4个子集，分别是[]、[1]、[2]和[1，2]。
 * <p>
 * Tag：回溯
 */
public class AllSubsets implements Answer {

    private List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        new AllSubsets().answerOne();
    }

    /**
     * something
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        subsets(nums);
        System.out.println(result);
    }

    public List<List<Integer>> subsets(int[] nums) {
        myDiGui(new ArrayList<>(), 0, nums);
        return result;
    }

    public void myDiGui(List<Integer> temp, int i, int[] data) {
        if (i >= data.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        // 选
        temp.add(data[i]);
        myDiGui(temp, i + 1, data);
        temp.remove(temp.size() - 1);

        // 不选
        myDiGui(temp, i + 1, data);
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3};
    }
}