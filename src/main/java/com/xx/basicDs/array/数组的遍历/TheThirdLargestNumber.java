package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

import java.util.TreeSet;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/8
 * <p>
 * 第三大的数
 * LeetCode 414. Easy
 * <p>
 * 示例 1：
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * <p>
 * 示例 2：
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * <p>
 * 示例 3：
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 104
 * -2^31 <= nums[i] <= 2^31 - 1
 * <p>
 * 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
 * <p>
 * Tag: 有序集合  数组遍历
 */
public class TheThirdLargestNumber implements Answer {

    public static void main(String[] args) {
        new TheThirdLargestNumber().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {

    }

    public int thirdMax(int[] nums) {
        Integer first = null;
        Integer second = null;
        Integer third = null;
        for (int num : nums) {
            if (first == null || num > first) {
                third = second;
                second = first;
                first = num;
            } else if (first > num && (second == null || num > second)) {
                third = second;
                second = num;
            } else if (second != null && second > num && (third == null || num > third)) {
                third = num;
            }
        }
        return third == null ? first : third;
    }

    /**
     * 利用有序集合
     */
    public int thirdMax2(int[] nums) {
        TreeSet<Integer> s = new TreeSet<Integer>();
        for (int num : nums) {
            s.add(num);
            if (s.size() > 3) {
                s.remove(s.first());
            }
        }
        return s.size() == 3 ? s.first() : s.last();
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
