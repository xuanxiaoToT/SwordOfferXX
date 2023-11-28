package com.xx.basicDs.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/28
 * <p>
 * 设计前中后队列
 * LeetCode 1670. Medium
 * <p>
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 * 请你完成 FrontMiddleBack 类：
 * <p>
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 * <p>
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 * <p>
 * 示例 1：
 * 输入：
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * 输出：
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 * <p>
 * 解释：
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // 返回 1 -> [4, 3, 2]
 * q.popMiddle();    // 返回 3 -> [4, 2]
 * q.popMiddle();    // 返回 4 -> [2]
 * q.popBack();      // 返回 2 -> []
 * q.popFront();     // 返回 -1 -> [] （队列为空）
 * <p>
 * <p>
 * 提示：
 * 1 <= val <= 10^9
 * 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。
 * <p>
 * Tag: 双端队列  双端链表
 */
public class FrontMiddleBackQueue {

    private Deque<Integer> pre = null;
    private Deque<Integer> back = null;

    /**
     * 也可以用 ArrayDeque
     */
    public FrontMiddleBackQueue() {
        pre = new LinkedList<>();
        back = new LinkedList<>();
    }

    /**
     * 最前面
     */
    public void pushFront(int val) {
        pre.addFirst(val);
        while (Math.abs(pre.size() - back.size()) > 1) {
            Integer pollLast = pre.pollLast();
            if (pollLast != null) {
                back.addFirst(pollLast);
            }
        }
    }

    /**
     * 选择靠前面的操作
     */
    public void pushMiddle(int val) {
        if (pre.size() > back.size()) {
            Integer preLast = pre.pollLast();
            back.addFirst(preLast);
        }
        pre.addLast(val);
    }

    /**
     * 最后面
     */
    public void pushBack(int val) {
        back.addLast(val);
        while (Math.abs(pre.size() - back.size()) > 1) {
            Integer backFirst = back.pollFirst();
            if (backFirst != null) {
                pre.addLast(backFirst);
            }
        }
    }

    public int popFront() {
        Integer result = -1;
        if (pre.isEmpty()) {
            result = back.pollFirst();
        } else {
            result = pre.pollFirst();
        }
        while (Math.abs(pre.size() - back.size()) > 1) {
            Integer backFirst = back.pollFirst();
            pre.addLast(backFirst);
        }
        return result == null ? -1 : result;
    }

    public int popMiddle() {
        if (pre.size() >= back.size()) {
            Integer result = pre.pollLast();
            return result == null ? -1 : result;
        } else {
            Integer result = back.pollFirst();
            return result == null ? -1 : result;
        }
    }

    public int popBack() {
        Integer result = -1;
        if (back.isEmpty()) {
            result = pre.pollLast();
        } else {
            result = back.pollLast();
        }
        while (Math.abs(pre.size() - back.size()) > 1) {
            Integer preLast = pre.pollLast();
            if (preLast != null) {
                back.addFirst(preLast);
            }
        }
        return result == null ? -1 : result;
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue FMBqueue = new FrontMiddleBackQueue();
        FMBqueue.pushBack(1);
        FMBqueue.pushBack(2);
        FMBqueue.pushBack(3);
        FMBqueue.pushBack(4);
        System.out.println(FMBqueue.popBack());
        System.out.println(FMBqueue.popBack());
        System.out.println(FMBqueue.popBack());
        System.out.println(FMBqueue.popBack());
        //System.out.println(FMBqueue.popFront());
    }
}