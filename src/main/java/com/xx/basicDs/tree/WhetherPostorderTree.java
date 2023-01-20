package com.xx.basicDs.tree;

import com.xx.Answer;

import java.util.Arrays;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/27
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同。
 */
public class WhetherPostorderTree implements Answer {

    public static void main(String[] args) {
        WhetherPostorderTree whetherPostorderTree = new WhetherPostorderTree();
        whetherPostorderTree.answerOne();
    }

    @Override
    public void answerOne() {
        List<Integer> list = initData();
        boolean validate = validate(list, 0, list.size() - 1);
        System.out.println(validate);
    }

    // 7,13,9,21,20,15
    private boolean validate(List<Integer> data, int start, int end) {
        if (start >= end) {
            return true;
        }
        Integer rootValue = data.get(end);
        Integer splitPoint = findFirstMaxIndex(start, end, data, rootValue);
        if (splitPoint == null) {
            return false;
        }
        for (int i = start; i < splitPoint; i++) {
            if (data.get(i) > rootValue) {
                return false;
            }
        }
        for (int i = splitPoint + 1; i < end; i++) {
            if (data.get(i) < rootValue) {
                return false;
            }
        }
        return validate(data, start, splitPoint - 1) && validate(data, splitPoint, end - 1);
    }

    private Integer findFirstMaxIndex(int start, int max, List<Integer> data, int target) {
        for (int i = start; i <= max; i++) {
            if (data.get(i) > target) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Integer> initData() {
        int[] temp = {1, 6, 3, 2, 5};
        // int[] temp = {1,3,2,6,5};
        return Arrays.asList(1, 3, 2, 5, 7, 6, 4);
    }
}