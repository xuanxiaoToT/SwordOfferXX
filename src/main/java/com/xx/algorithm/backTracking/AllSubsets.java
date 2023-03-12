package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/26
 * 所有子集
 * LeetCode 78. 子集
 * <p>
 * 输入一个不含重复数字的数据集合，请找出它的所有子集。
 * <p>
 * 例如，数据集合[1，2]有4个子集，分别是[]、[1]、[2]和[1，2]。
 */
public class AllSubsets implements Answer {

    private Set<List<Integer>> result = new HashSet<>();

    public static void main(String[] args) {
        new AllSubsets().answerOne();
    }

    /**
     * something
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        myDiGui(new ArrayList<>(), 0, nums);
        System.out.println(result);
    }

    public void myDiGui(List<Integer> temp, int i, int[] data) {
        if (i >= data.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        //选
        temp.add(data[i]);
        myDiGui(temp, i + 1, data);
        temp.remove(temp.size() - 1);

        //不选
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