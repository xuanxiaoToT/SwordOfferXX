package com.xx.algorithm.greedy;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/10
 * 任务调度器
 * LeetCode 621
 * <p>
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，
 * 并且每个任务都可以在 1 个单位时间内执行完。
 * 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，
 * 或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 * 示例 1：
 * 输入：tasks = ['A','A','A','B','B','B'], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * <p>
 * 示例 2：
 * 输入：tasks = ['A','A','A','B','B','B'], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ['A','A','A','B','B','B']
 * ['A','B','A','B','A','B']
 * ['B','B','B','A','A','A']
 * ...
 * 诸如此类
 * <p>
 * 示例 3：
 * 输入：tasks = ['A','A','A','A','A','A','B','C','D','E','F','G'], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 */
public class TaskScheduler implements Answer {

    public static void main(String[] args) {
        new TaskScheduler().answer();
    }

    /**
     * 解1：任务需要的总时间总是取决于最多的那个任务.
     * 采用贪心算法来做  todo
     */
    @Override
    public void answer() {
        Character[] data = initData();

    }

    /**
     * 采用桶排序的思想
     * https://leetcode.cn/problems/task-scheduler/solutions/196302/tong-zi-by-popopop/
     */
    public void answerTwo(int n) {
        Character[] tasks = initData();
        int[] temp = new int[26];
        int countMaxTask = 0;
        int maxTask = 0;
        for (Character c : tasks) {
            temp[c - 'A']++;
            maxTask = Math.max(temp[c - 'A'], maxTask);
        }
        for (int i = 0; i < 26; i++) {
            if (temp[i] == maxTask) {
                countMaxTask++;
            }
        }
        int result = Math.max(tasks.length, (maxTask - 1) * (n + 1) + countMaxTask);
        System.out.println(result);
    }

    /**
     * 输出数据
     */
    @Override
    public Character[] initData() {
        return new Character[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    }
}
