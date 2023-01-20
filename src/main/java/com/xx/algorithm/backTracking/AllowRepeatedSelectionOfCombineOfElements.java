package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/27
 * <p>
 * 允许重复选择元素的组合
 * <p>
 * 给定一个没有重复数字的正整数集合，请找出所有元素
 * 之和等于某个给定值的所有组合。同一个数字可以在组合中出现任
 * 意次。
 * <p>
 * 例如，输入整数集合[2，3，5]，元素之和等于8的组合有3
 * 个，分别是[2，2，2，2]、[2，3，3]和[3，5]。
 */
public class AllowRepeatedSelectionOfCombineOfElements implements Answer {

    public static void main(String[] args) {
        new AllowRepeatedSelectionOfCombineOfElements().answerOne();
    }

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * something
     */
    @Override
    public void answerOne() {
        int target = 8;
        int[] data = initData();
        diGui(target, data, 0, new ArrayList<>(), 0);
        System.out.println(result);
    }

    private void diGui(int target, int[] data, int index, ArrayList<Integer> temp, int tempSum) {
        if (tempSum == target) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (tempSum > target || index >= data.length) {
            return;
        }
        //选这个
        temp.add(data[index]);
        tempSum = tempSum + data[index];
        diGui(target, data, index, temp, tempSum);

        //不选这个
        temp.remove(temp.size() - 1);
        tempSum = tempSum - data[index];
        diGui(target, data, index + 1, temp, tempSum);
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{2, 3, 5, 8};
    }
}