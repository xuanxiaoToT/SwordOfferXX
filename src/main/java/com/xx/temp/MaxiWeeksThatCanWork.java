package com.xx.temp;

import com.xx.Answer;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/16
 * <p>
 * 你可以工作的最大周数
 * LeetCode 1953. Medium
 * <p>
 * 给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
 * 你可以按下面两个规则参与项目中的工作：
 * 每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。
 * 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
 * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。
 * 注意，由于这些条件的限制，你可能无法完成所有阶段任务。
 * <p>
 * 返回在不违反上面规则的情况下你 最多 能工作多少周。
 * <p>
 * 示例 1：
 * 输入：milestones = [1,2,3]
 * 输出：6
 * 解释：一种可能的情形是：
 * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * 总周数是 6 。
 * <p>
 * 示例 2：
 * 输入：milestones = [5,2,1]
 * 输出：7
 * 解释：一种可能的情形是：
 * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 7 周，你参与并完成项目 0 中的一个阶段任务。
 * 总周数是 7 。
 * 注意，你不能在第 8 周参与完成项目 0 中的最后一个阶段任务，因为这会违反规则。
 * 因此，项目 0 中会有一个阶段任务维持未完成状态。
 * <p>
 * 提示：
 * n == milestones.length
 * 1 <= n <= 10^5
 * 1 <= milestones[i] <= 10^9
 * <p>
 * Tag:数学
 */
public class MaxiWeeksThatCanWork implements Answer {

    public static void main(String[] args) {
        new MaxiWeeksThatCanWork().answerOne();
    }

    @Override
    public void answerOne() {
        //int[] miletones = new int[]{1, 2, 3};
        //int[] miletones = new int[]{5, 2, 1};
        //int[] miletones = new int[]{5, 7, 5, 7, 9, 7};
        //int[] miletones = new int[]{5, 5, 21};
        System.out.println(numberOfWeeks(miletones));
    }

    /**
     * 超时
     */
    public long numberOfWeeks2(int[] miletones) {
        long result = 0;
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(miletones.length, (o1, o2) -> {
            return o2.value - o1.value;
        });
        for (int i = 0; i < miletones.length; i++) {
            maxHeap.add(new Node(i, miletones[i]));
        }

        int last = -1;
        while (!maxHeap.isEmpty()) {
            Node poll = maxHeap.poll();
            if (poll.index == last) {
                Node second = maxHeap.poll();
                if (second == null) {
                    return result;
                } else {
                    second.value--;
                    result++;
                    last = second.index;
                    if (second.value > 0) {
                        maxHeap.add(second);
                    }
                }
                maxHeap.add(poll);
            } else {
                poll.value--;
                result++;
                last = poll.index;
                if (poll.value > 0) {
                    maxHeap.add(poll);
                }
            }
        }
        return result;
    }

    /**
     * 说白了就是按照从多到少顺序跳着放，从下标0开始，那么相同元素能相邻的充要条件就是最多的那个项目个数超过总个数的一半向上取整，
     * 否则必定不会出现相同元素相邻的情况。
     */
    public long numberOfWeeks(int[] milestones) {
        // 耗时最长工作所需周数
        int longest = milestones[0];
        long rest = 0;
        for (int count : milestones) {
            longest = Math.max(longest, count);
            rest += count;
        }
        // 其余工作共计所需周数
        rest -= longest;
        if (longest > rest + 1) {
            // 此时无法完成所耗时最长的工作
            return rest * 2 + 1;
        } else {
            //此时可以完成所有工作 fixme:why？
            return longest + rest;
        }
    }

    private class Node {
        public int index;
        public int value;

        public Node(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}
