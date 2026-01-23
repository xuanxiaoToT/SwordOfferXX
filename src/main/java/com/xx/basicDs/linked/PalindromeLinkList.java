package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/24
 * <p>
 * 回文链表
 * LeetCode Easy
 * <p>
 * 如何判断一个链表是不是回文？要求解法的时间复杂度
 * 是O（n），并且不得使用超过O（1）的辅助空间。如果一个链表是
 * 回文，那么链表的节点序列从前往后看和从后往前看是相同的。
 */
public class PalindromeLinkList implements Answer {

    public static void main(String[] args) {
        new PalindromeLinkList().answer();
    }

    /**
     * 思路：
     * 1.将后半段(或前半段)连表逆置。然后比较。一共O(3n)也是O(n)
     * 2.存储在一个string里比较。空间复杂度o(N)
     * 3.利用栈结构来做
     */
    @Override
    public void answer() {

    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * something
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}