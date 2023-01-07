package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.TreeSet;

/**
 * @author 玄霄
 * @CreateDate 2022/9/29
 * <p>
 * 值和下标之差都在给定的范围内
 * <p>
 * 给定一个整数数组nums和两个正数k、t，请判断是否存
 * 在两个不同的下标i和j满足i和j之差的绝对值不大于给定的k，并且
 * 两个数值nums[i]和nums[j]的差的绝对值不大于给定的t。
 * <p>
 * 举例：
 * 如果输入数组{1，2，3，1}，k为3，t为0，由于下标0和下
 * 标3对应的数字之差的绝对值为0，因此返回true。如果输入数组{1，
 * 5，9，1，5，9}，k为2，t为3，由于不存在两个下标之差小于或等于2
 * 且它们差的绝对值小于或等于3的数字，因此此时应该返回false。
 */
public class DifferenceBetweenValueAndSubscriptInRange implements Answer {

    public static void main(String[] args) {
        new DifferenceBetweenValueAndSubscriptInRange().answerOne();
    }

    /**
     * 方法一：无脑遍历  O(nk)
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        int k = 2;
        int t = 3;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length && j <= i + k; j++) {
                if (Math.abs(nums[i] - nums[j]) <= t) {
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }
        System.out.println(false);
    }

    /**
     * 将其转换为treeSet
     * <p>
     * 需要从一个大小为k的数据容器中找出小于或等于某个数字的最大
     * 值及大于或等于某个数字的最小值，这正是TreeSet或TreeMap适用的
     * 场景。
     */
    public void answerTwo() {
        int[] nums = initData();
        TreeSet<Integer> set = new TreeSet<>();
        int k = 3;
        int t = 0;
        for (int i = 0; i <= k; i++) {
            set.add(nums[i]);
        }

        for (int i = k + 1; i < nums.length; i++) {
            // 小于或等于给定值的最大值。
            Integer floor = set.floor(nums[i]);
            if (floor != null && floor >= nums[i] - t) {
                System.out.println(true);
            }
            // 大于或等于给定值的最小值
            Integer ceiling = set.ceiling(nums[i]);
            if (ceiling != null && ceiling <= nums[i] + t) {
                System.out.println(true);
            }
            set.add(nums[i]);
            set.remove(nums[i - k]);
        }
    }

    /**
     * 利用hash思想：
     * 这个题目关心的是差的绝对值小于或等于t的数字，因此
     * 可以将数字放入若干大小为t+1的桶中。
     * 例如，将从0到t的数字放入编号为0的桶中，从t+1到2t+1的数字
     * 放入编号为1的桶中。其他数字以此类推。这样做的好处是如果两个数
     * 字被放入同一个桶中，那么它们的差的绝对值一定小于或等于t。
     */
    public void answerThree() {

    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 1};
        // return new int[]{1, 5, 9, 1, 5, 9};
    }
}