package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/25
 * <p>
 * H 指数
 * LeetCode 274. Medium
 * <p>
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，
 * 并且每篇论文 至少 被引用 h 次。如果 h 有多种可能的值，h 指数 是其中最大的那个。
 * <p>
 * 示例 1：
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 * <p>
 * 示例 2：
 * 输入：citations = [1,3,1]
 * 输出：1
 * <p>
 * 提示：
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
public class H_Index implements Answer {

    public static void main(String[] args) {
        new H_Index().answerTwo();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[] citations = initData();
        Arrays.sort(citations);
        int count = 1;
        int result = 0;
        for (int i = citations.length - 1; i >= 0; i--, count++) {
            if (citations[i] >= count) {
                result = count;
            } else {
                System.out.println(result);
                return;
            }
        }
        System.out.println(result);
    }

    /**
     * 方法二：计数排序
     * <p>
     * 根据定义，我们可以发现 H 指数不可能大于总的论文发表数，所以对于引用次数超过论文发表数的情况，
     * 我们可以将其按照总的论文发表数来计算即可。这样我们可以限制参与排序的数的大小为 [0,n]（其中n为总的论文发表数），
     * 使得计数排序的时间复杂度降低到 O(n)。
     * <p>
     * 最后我们可以从后向前遍历数组 counter，对于每个 0≤i≤n，在数组 counter 中得到大于或等于当前引用次数 i 的总论文数。
     * 当我们找到一个 H指数时跳出循环，并返回结果。
     */
    public int answerTwo() {
        int[] citations = initData();
        int n = citations.length, tot = 0;
        int[] counter = new int[n + 1];
        for (int citation : citations) {
            if (citation >= n) {
                counter[n]++;
            } else {
                counter[citation]++;
            }
        }
        //倒序便利
        for (int i = n; i >= 0; i--) {
            tot += counter[i];
            if (tot >= i) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 我们需要找到一个值 h，它是满足「有 h 篇论文的引用次数至少为 h」的最大值。小于等于 h 的所有值 x 都满足这个性质，
     * 而大于 h 的值都不满足这个性质。同时因为我们可以用较短时间（扫描一遍数组的时间复杂度为 O(n)，其中 n 为数组 citations 的长度）
     * 来判断 x 是否满足这个性质，所以这个问题可以用二分搜索来解决。
     * <p>
     * 设查找范围的初始左边界 left 为 0，初始右边界 right 为 n。每次在查找范围内取中点 mid，同时扫描整个数组，
     * 判断是否至少有 mid 个数大于 mid。如果有，说明要寻找的 h 在搜索区间的右边，反之则在左边。
     */
    public int answerThree() {
        int[] citations = initData();
        int left = 0, right = citations.length;
        int mid = 0, cnt = 0;
        while (left < right) {
            // +1 防止死循环
            mid = (left + right + 1) >> 1;
            cnt = 0;
            for (int citation : citations) {
                if (citation >= mid) {
                    cnt++;
                }
            }
            if (cnt >= mid) {
                // 要找的答案在 [mid,right] 区间内
                left = mid;
            } else {
                // 要找的答案在 [0,mid) 区间内
                right = mid - 1;
            }
        }
        return left;

    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{3, 0, 6, 1, 5};
        //return new int[]{1, 3, 1};
    }
}
