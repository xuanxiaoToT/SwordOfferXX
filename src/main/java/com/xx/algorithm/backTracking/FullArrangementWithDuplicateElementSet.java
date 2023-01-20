package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/11/22
 * <p>
 * 有重复元素集合的全排列
 * <p>
 * 给定一个包含重复数字的集合，请找出它的所有全排列。
 * <p>
 * 例如，集合[1，1，2]有3个全排列，分别是[1，1，2]、[1，2，1]和[2，1，1]。
 */
public class FullArrangementWithDuplicateElementSet implements Answer {

    public static void main(String[] args) {
        new FullArrangementWithDuplicateElementSet().answerOne();
    }

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * something
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        // 排序后的全排列不变
        Arrays.sort(data);
        diGui(data, new int[data.length], new ArrayList<>(), 0);
        System.out.println(result);

    }

    private void diGui(int[] data, int[] flag, List<Integer> temp, int index) {
        if (temp.size() == data.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (index > data.length) {
            return;
        }
        // 遇到相同的  则后面的该值都不选
        for (int i = 0; i < data.length; i++) {
            if (flag[i] == 0) {
                // 相同的值 只能从左往右。否则不选
                if (i > 0 && flag[i - 1] == 0 && data[i] == data[i - 1]) {
                    continue;
                }
                //选这个,并且后续的可以继续选
                temp.add(data[i]);
                flag[i] = 1;
                diGui(data, flag, temp, index + 1);

                //不选这个,并且后续的这个值都不选
                temp.remove(temp.size() - 1);
                flag[i] = 0;
                // diGui(data, flag, temp, index + 1);
            }
        }
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 1, 3};
    }
}