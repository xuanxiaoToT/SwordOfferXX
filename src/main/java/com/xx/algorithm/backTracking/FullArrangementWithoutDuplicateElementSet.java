package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/31
 * <p>
 * 没有重复元素集合的全排列
 * LeetCode 46. 全排列  Medium
 * <p>
 * 给定一个没有重复数字的集合，请找出它的所有全排列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class FullArrangementWithoutDuplicateElementSet implements Answer {
    public static void main(String[] args) {
        new FullArrangementWithoutDuplicateElementSet().answerOne();
    }

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * something
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        diGui(data, new int[data.length], new ArrayList<>(), 1);
        // diGui2(data,0);
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
        for (int i = 0; i < data.length; i++) {
            if (flag[i] == 0) {
                //选这个
                temp.add(data[i]);
                flag[i] = 1;
                diGui(data, flag, temp, index + 1);

                //不选这个
                temp.remove(temp.size() - 1);
                flag[i] = 0;
            }
        }
    }

    private void diGui2(int[] data, int index) {
        if (index == data.length) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int datum : data) {
                temp.add(datum);
            }
            result.add(temp);
            return;
        }
        if (index > data.length) {
            return;
        }
        for (int i = index; i < data.length; i++) {
            //此处是交换，而非选择，调换位置进行全排列
            swap(data, i, index);
            diGui2(data, i + 1);
            swap(data, index, i);
        }
    }

    private void swap(int[] data, int i, int index) {
        //略
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3};
    }
}
