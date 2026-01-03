package com.xx.algorithm.search.二分查找;

import com.xx.Answer;

/**
 * 稀疏数组搜索
 * LeetCode Easy
 * <p>
 * 稀疏数组搜索。有个 排好序 的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 * <p>
 * 示例 1：
 * 输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 * 输出：-1
 * 说明：不存在返回-1。
 * <p>
 * 示例 2：
 * 输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 * 输出：4
 * <p>
 * 提示:
 * words的长度在[1, 1000000]之间
 * <p>
 * Tag: 二分查找
 */
public class SparseArraySearch implements Answer {
    public static void main(String[] args) {
        new SparseArraySearch().answerOne();
    }

    @Override
    public void answerOne() {
        // String[] words = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        // String s = "ta";

        String[] words = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String s = "ball";
        System.out.println(findString(words, s));
    }

    /**
     * 因为明确了有序，所以还是用二分效率更高
     * 那么遇到空串怎么做：
     * 左右都可以，找到一个不是空串的，然后判断完就可以确定再哪半部分了
     */
    public int findString(String[] words, String s) {
        int left = 0;
        int right = words.length - 1;
        while (left <= right && left < words.length && left >= 0) {
            int mid = (left + right) / 2;
            int tempMid = mid;
            // 这里选择向右一直找
            while (tempMid < words.length && words[tempMid].isEmpty()) {
                tempMid++;
            }
            // 找到右边界了，还是没有值，则直接返回左半区。
            if (tempMid >= words.length) {
                right = mid - 1;
                continue;
            }
            if (words[tempMid].equals(s)) {
                return tempMid;
            }
            if (words[tempMid].compareTo(s) > 0) {
                right = mid - 1;
            } else {
                left = tempMid + 1;
            }
        }
        return -1;
    }

    @Override
    public Object initData() {
        return null;
    }
}
