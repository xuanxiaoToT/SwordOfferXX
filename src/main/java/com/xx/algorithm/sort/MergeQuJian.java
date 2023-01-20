package com.xx.algorithm.sort;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/24
 * 合并区间
 * 输入一个区间的集合，请将重叠的区间合并。每个区间
 * 用两个数字比较，分别表示区间的起始位置和结束位置。例如，输
 * 入区间[[1，3]，[4，5]，[8，10]，[2，6]，[9，12]，[15，
 * 18]]，合并重叠的区间之后得到[[1，6]，[8，12]，[15，18]]。
 */
public class MergeQuJian implements Answer {
    public static void main(String[] args) {
        new MergeQuJian().answerOne();
    }

    /**
     * 1.先对第一个值排序,然后两两比较
     */
    @Override
    public void answerOne() {
        int[][] ints = initData();
        //排序
        Arrays.sort(ints, Comparator.comparingInt(o -> o[0]));
        //两两比较
        List<int[]> newList = new ArrayList<>();

        int[] last = ints[0];
        int[] point = ints[1];
        int i = 1;
        boolean newFlag = false;

        while (i < ints.length) {
            if (last[1] >= point[0]) {
                if (point[1] <= last[1]) {
                    if (!newFlag) {
                        newList.add(last);
                        newFlag = true;
                    }
                } else {
                    newFlag = true;
                    int[] tempNew = {last[0], Math.max(point[1], last[1])};
                    last = tempNew;
                    newList.add(tempNew);
                }
            } else {
                if (!newFlag) {
                    newList.add(last);
                }
                last = point;
                newFlag = false;
            }
            if (i + 1 >= ints.length) {
                if (!newFlag) {
                    newList.add(last);
                }
                break;
            } else {
                i = i + 1;
            }
            point = ints[i];
        }

        System.out.println(newList);

    }

    /**
     * something
     */
    @Override
    public int[][] initData() {
        int[][] temp = {{1, 3}, {4, 5}, {8, 10}, {2, 6}, {9, 12}, {15, 18}};
        return temp;
    }
}