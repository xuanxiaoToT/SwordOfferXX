package com.xx.algorithm.search;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/10/20
 * 计算平方根
 * <p>
 * 输入一个非负整数，请计算它的平方根。正数的平方根
 * 有两个，只输出其中的正数平方根。如果平方根不是整数，那么只
 * 需要输出它的整数部分。
 * <p>
 * 例如，如果输入4则输出2；如果输入18则输出4。
 */
public class TakeSquareRoot implements Answer {

    public static void main(String[] args) {
        new TakeSquareRoot().answerOne();
    }

    /**
     * 二分法
     */
    @Override
    public void answerOne() {
        Integer data = initData();

        int left = 1;
        int right = data;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (mid * mid <= data) {
                if ((mid + 1) * (mid + 1) > data) {
                    System.out.println(mid);
                    return;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
    }

    /**
     * something
     */
    @Override
    public Integer initData() {
        return 81;
    }
}
