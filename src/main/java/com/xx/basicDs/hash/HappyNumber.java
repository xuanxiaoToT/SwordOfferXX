package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/12
 * <p>
 * 快乐数
 * LeetCode 202. 简单
 * <p>
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」 定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * <p>
 * 示例 1：
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * 示例 2：
 * 输入：n = 2
 * 输出：false
 * <p>
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class HappyNumber implements Answer {

    public static void main(String[] args) {
        new HappyNumber().answerOne();
    }

    @Override
    public void answerOne() {
        Integer num = initData();
        System.out.println(isHappy(num));
        System.out.println(isHappy2(num));
    }

    /**
     * 用hashSet存储过去的结果。
     * 缺点是hashSet可能会变的很大
     */
    public boolean isHappy(int n) {
        //哈希表记录数位平方和计算过程中的每个数
        HashSet<Integer> hashSet = new LinkedHashSet<>();
        while (!hashSet.contains(n)) {
            hashSet.add(n);
            int sum = 0;
            while (n > 0) {
                //计算数位平方和
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            //n 为数位平方和
            n = sum;
        }
        return n == 1;
    }

    /**
     * 历史结果就像一个链表，本质上是判断是否存在环，且环的入口节点为1。
     * 参考：https://leetcode.cn/problems/happy-number/solutions/224894/kuai-le-shu-by-leetcode-solution/?envType=study-plan-v2&envId=top-interview-150
     */
    public boolean isHappy2(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    @Override
    public Integer initData() {
        return 19;
    }
}