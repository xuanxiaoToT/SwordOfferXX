package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/31
 * <p>
 * 合并 K 个升序链表
 * LeetCode 023
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 */
public class MergeKSortedLinkedLists implements Answer {

    public static void main(String[] args) {
        new MergeKSortedLinkedLists().answerTwo();
    }

    /**
     * 思路1：类似广度遍历，每次取一个最小的。
     * 每次扫描K遍，最高扫描k*max(nodeLeigh)
     */
    @Override
    public void answerOne() {
        ListNode[] listNodes = initData();
        ListNode newHead = null;
        ListNode newPoint = null;
        while (true) {
            int minIndex = -1;
            ListNode minPoint = null;
            for (int i = 0; i < listNodes.length; i++) {
                ListNode temp = listNodes[i];
                if (temp == null) {
                    continue;
                }
                if (minPoint == null) {
                    minIndex = i;
                    minPoint = temp;
                } else {
                    if (minPoint.val > temp.val) {
                        minPoint = temp;
                        minIndex = i;
                    }
                }
            }
            if (minIndex == -1) {
                break;
            }
            if (newHead == null) {
                newHead = minPoint;
                newPoint = newHead;
            } else {
                newPoint.next = minPoint;
                newPoint = newPoint.next;
            }
            listNodes[minIndex] = minPoint.next;
        }
        System.out.println(newHead);
    }

    /**
     * 思路2：两两合并，递归完成合并
     * 参考归并排序
     */
    public void answerTwo() {
        ListNode[] listNodes = initData();

        mergeSort(listNodes, 0, listNodes.length - 1);
        System.out.println(listNodes[0]);
    }

    public void mergeSort(ListNode[] listNodes, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            //对first--last的前一半的元素进行排序
            mergeSort(listNodes, left, mid);
            //对first--last的后一半的元素进行排序
            mergeSort(listNodes, mid + 1, right);
            //对first--last左右两边拍好的两个序列进行归并
            mergeLinkedList(listNodes, left, mid + 1);
        }
    }

    private void mergeLinkedList(ListNode[] listNodes, int left, int right) {
        ListNode one = listNodes[left];
        ListNode two = listNodes[right];
        if (two == null) {
            return;
        }
        if (one == null) {
            listNodes[left] = two;
            listNodes[right] = null;
            return;
        }
        ListNode newHead = null;
        ListNode newPoint = null;
        ListNode point1 = one;
        ListNode point2 = two;
        while (point1 != null && point2 != null) {
            if (point1.val > point2.val) {
                if (newHead == null) {
                    newHead = point2;
                    newPoint = newHead;
                    point2 = point2.next;
                } else {
                    newPoint.next = point2;
                    newPoint = newPoint.next;
                    point2 = point2.next;
                }
            } else {
                if (newHead == null) {
                    newHead = point1;
                    newPoint = newHead;
                    point1 = point1.next;
                } else {
                    newPoint.next = point1;
                    newPoint = newPoint.next;
                    point1 = point1.next;
                }
            }
        }
        if (point1 != null) {
            newPoint.next = point1;
        }
        if (point2 != null) {
            newPoint.next = point2;
        }
        listNodes[left] = newHead;
        listNodes[right] = null;
    }

    /**
     * 输出数据
     */
    @Override
    public ListNode[] initData() {
        //return new ListNode[]{};

        //145  134 26
        //ListNode node11 = new ListNode(1);
        //ListNode node12 = new ListNode(4);
        //ListNode node13 = new ListNode(5);
        //node11.next = node12;
        //node12.next = node13;
        //
        //ListNode node21 = new ListNode(1);
        //ListNode node22 = new ListNode(3);
        //ListNode node23 = new ListNode(4);
        //node21.next = node22;
        //node22.next = node23;
        //
        //ListNode node31 = new ListNode(2);
        //ListNode node32 = new ListNode(6);
        //node31.next = node32;
        //return new ListNode[]{node11,node12,node31};

        //return new ListNode[]{null, new ListNode(1)};

        // -1 5  11    6 10
        ListNode node11 = new ListNode(-1);
        ListNode node12 = new ListNode(5);
        ListNode node13 = new ListNode(11);
        node11.next = node12;
        node12.next = node13;
        ListNode node31 = new ListNode(6);
        ListNode node32 = new ListNode(10);
        node31.next = node32;
        return new ListNode[]{null, node11, null, node31};
    }
}
