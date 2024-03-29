package com.xx.basicDs.hash;

import java.util.TreeSet;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/29
 * <p>
 * 无限集中的最小数字
 * LeetCode  2336  Medium
 * <p>
 * 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。
 * 实现 SmallestInfiniteSet 类：
 * SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含 所有 正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。
 * void addBack(int num) 如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集最后。
 * <p>
 * 示例：
 * 输入
 * ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
 * [[], [2], [], [], [], [1], [], [], []]
 * 输出
 * [null, null, 1, 2, 3, null, 1, 4, 5]
 * 解释
 * SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
 * smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
 * smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
 * // 且 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。
 * <p>
 * 提示：
 * 1 <= num <= 1000
 * 最多调用 popSmallest 和 addBack 方法 共计 1000 次
 * <p>
 * Tag: 哈希表  设计
 */
class SmallestInfiniteSet {

    private int thres;
    private TreeSet<Integer> set;

    public SmallestInfiniteSet() {
        thres = 1;
        set = new TreeSet<Integer>();
    }

    /**
     * 思路2：采用有序集合
     * 使用一个有序集合 set 维护所有小于 thres 的正整数，并用 thres 表示所有大于等于 thres 的正整数。对于题目描述中的两种操作：
     * 1.如果要删除最小的正整数，那么当 s 不为空时，我们删除 s 中最小的正整数，否则删除 thres 并将 thres 的值增加 1；
     * 2.如果要添加一个正整数，如果它大于等于 thres，则不进行任何操作，否则将其加入 sss 中。
     */
    public int popSmallest() {
        if (set.isEmpty()) {
            int ans = thres;
            thres++;
            return ans;
        }
        int ans = set.pollFirst();
        return ans;
    }

    public void addBack(int num) {
        if (num < thres) {
            set.add(num);
        }
    }


    //HashSet<Integer> set = null;
    //public SmallestInfiniteSet() {
    //    set = new HashSet<>();
    //}
    //
    ///**
    // * 最简单的:直接用set来记录不在集合中的，由于最多调用1000次，所以集合不会很大。 时间较慢
    // */
    //public int popSmallest() {
    //    for (int i = 1; i < Integer.MAX_VALUE; i++) {
    //        if (!set.contains(i)) {
    //            set.add(i);
    //            return i;
    //        }
    //    }
    //    return 0;
    //}
    //
    //public void addBack(int num) {
    //    set.remove(num);
    //}
}

