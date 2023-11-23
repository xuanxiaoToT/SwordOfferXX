package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/23
 * <p>
 * 丑数II
 * LeetCode 264. Medium
 * <p>
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是质因子只包含 2、3 和 5 的正整数。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 * <p>
 * 提示：
 * 1 <= n <= 1690
 * <p>
 * Tag: 多路汇并  最小堆  丑数
 */
public class UglyNumberII implements Answer {
    public static void main(String[] args) {

    }

    /**
     * 解1：
     * https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247490029&idx=1&sn=bba9ddff88d247db310406ee418d5a15&chksm=fd9cb2f2caeb3be4b1f84962677337dcb5884374e5b6b80340834eaff79298d11151da2dd5f7&token=252055586&lang=zh_CN#rd
     */
    @Override
    public void answerOne() {
        int n = 10;
        System.out.println(nthUglyNumber(n));
    }

    /**
     * 有了基本的分析思路，一个简单的解法是使用优先队列：
     * <p>
     * 起始先将最小丑数 1 放入队列
     * 每次从队列取出最小值 x，然后将 x 所对应的丑数 2x、3x 和 5x 进行入队。
     * 对步骤 2 循环多次，第 n 次出队的值即是答案。
     * 为了防止同一丑数多次进队，我们需要使用数据结构 Set 来记录入过队列的丑数
     * <p>
     * 时间：O(nlog*n)
     * 空间: O(n)
     */
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>();
        int[] nums = new int[]{2, 3, 5};
        Queue<Long> minHeap = new PriorityQueue<>();
        set.add(1L);
        minHeap.add(1L);
        for (int i = 1; i <= n; i++) {
            long x = minHeap.poll();
            if (i == n) return (int) x;
            for (int num : nums) {
                long t = num * x;
                if (!set.contains(t)) {
                    set.add(t);
                    minHeap.add(t);
                }
            }
        }
        return -1;
    }

    /**
     * 因此我们可以使用三个指针来指向目标序列 arr 的某个下标（下标 0 作为哨兵不使用，起始都为1），
     * 使用arr[下标]*质因数 代表当前使用到三个有序序列中的哪一位，
     * 同时使用 idx 表示当前生成到 arr 哪一位丑数。
     */
    public int nthUglyNumber2(int n) {
        // ans 用作存储已有丑数（从下标 1 开始存储，第一个丑数为 1）
        int[] ans = new int[n + 1];
        ans[1] = 1;
        // 由于三个有序序列都是由「已有丑数」*「质因数」而来
        // i2、i3 和 i5 分别代表三个有序序列当前使用到哪一位「已有丑数」下标（起始都指向 1）
        for (int i2 = 1, i3 = 1, i5 = 1, idx = 2; idx <= n; idx++) {
            // 由 ans[iX] * X 可得当前有序序列指向哪一位
            int a = ans[i2] * 2, b = ans[i3] * 3, c = ans[i5] * 5;
            // 将三个有序序列中的最小一位存入「已有丑数」序列，并将其下标后移
            int min = Math.min(a, Math.min(b, c));
            // 由于可能不同有序序列之间产生相同丑数，因此只要一样的丑数就跳过（不能使用 else if ）
            if (min == a) i2++;
            if (min == b) i3++;
            if (min == c) i5++;
            ans[idx] = min;
        }
        return ans[n];
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
