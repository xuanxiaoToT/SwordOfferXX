package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/31
 * <p>
 * 包含重复元素集合的组合
 * <p>
 * 给定一个可能包含重复数字的整数集合，请找出所有元
 * 素之和等于某个给定值的所有组合。输出中不得包含重复的组合。
 * <p>
 * 例如，输入整数集合[2，2，2，4，3，3]，元素之和等于8的组合有
 * 2个，分别是[2，2，4]和[2，3，3]。
 * <p>
 * 提示：最大的不同在于输入的集合中有重复的数字但输出不得包含重复的组合
 */
public class CombinationThatContainRepeatElements implements Answer {

    public static void main(String[] args) {
        new CombinationThatContainRepeatElements().answerOne();
    }

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 1.还是用之前非重复的那种递归方式，但是承接的result改为set。 解法略
     * 2.采用剪枝，如何剪？
     * ！！！解：避免重复的组合的方法是当在某一步决定跳过某个值为m的数字时，跳过所有值为m的数字。！！！
     * 例如 不选2 那么后续的2也都不选
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        Arrays.sort(data);
        diGui(8, data, 0, new ArrayList<>(), 0, 0, true);
        System.out.println(result);
    }

    private void diGui(int target, int[] data, int index, ArrayList<Integer> temp, int tempSum, int last, boolean chooseFlag) {
        if (tempSum == target) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (tempSum > target || index >= data.length) {
            return;
        }

        //chooseFlag：是否选择当前值
        if (last == data[index] && !chooseFlag) {
            diGui(target, data, index + 1, temp, tempSum, data[index], false);
        } else {
            //选这个
            temp.add(data[index]);
            tempSum = tempSum + data[index];
            diGui(target, data, index + 1, temp, tempSum, data[index], true);

            //不选这个，以及以后的这个值都不选
            temp.remove(temp.size() - 1);
            tempSum = tempSum - data[index];
            diGui(target, data, index + 1, temp, tempSum, data[index], false);
        }

    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{2, 2, 2, 4, 3, 3};
    }
}
