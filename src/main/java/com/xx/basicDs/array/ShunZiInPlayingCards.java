package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/7/26
 * 扑克牌中的顺子
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这 5 张牌是不是连续的。
 * <p>
 * 2 ～ 10 为数字本身，A 为 1 ，J 为 11 ，Q 为 12 ，K 为 13 ，而大、小王为 0 ，可以看成任意数字。
 * A 不能视为 14。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,4,5]
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: [0,0,1,2,5]
 * 输出: True
 */
public class ShunZiInPlayingCards implements Answer {

    public static void main(String[] args) {
        new ShunZiInPlayingCards().answerOne();
    }

    /**
     * 由此可以看出，顺子的情况下除了 0 之外，五张牌的最大牌与最小牌之差必然是小于 5 的。
     * <p>
     * 综上所述，我们可以去遍历这 5 张牌，执行如下的操作：
     * <p>
     * 1、设置一个哈希表 Set，借助它来执行判重操作，判断这 5 张牌是否存在重复牌，如果存在，那么肯定就不是顺子了。
     * 2、设置两个变量 Max、Min，用来记录这五张牌除了 0 之外的最大值和最小值。
     * 3、遇到大小王，忽略它。
     * 4、遇到非大小王的牌，检查哈希表是否存在这张牌，如果存在，那么肯定就不是顺子，直接返回 false，否则将它加入到哈希表中，同时更新最大牌、最小牌的值。
     * 5、遍历完毕之后，判断 Max、Min 是否小于 5。
     */
    @Override
    public void answerOne() {
        int[] data = initData();
        int min = Integer.MAX_VALUE;
        int max = 0;
        int n = 5;
        for (int datum : data) {
            if (datum != 0) {
                min = Math.min(min, datum);
                max = Math.max(max, datum);
            }
        }
        System.out.println((max - min) < 5);
    }

    @Override
    public int[] initData() {
//        return new int[]{1, 2, 3, 4, 5};
        return new int[]{0, 0, 1, 2, 5};
    }
}