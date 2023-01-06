package com.xx.util;

import com.xx.domain.BiLinkNode;
import com.xx.domain.ListNode;
import com.xx.domain.TreeNode;

/**
 * @author 玄霄
 * @CreateDate 2022/6/27
 */
public class DataFactory {


    /**
     * 15
     * /  \
     * 9   20
     * / \   \
     * 7  13  21
     */
    public static TreeNode generateTreeNode() {
        TreeNode treeNode = new TreeNode(15);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(21);
        treeNode.left = node1;
        treeNode.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        return treeNode;
    }

    public static ListNode generateLinkedList() {
        ListNode root = new ListNode(3);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(6);
        root.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        return root;
    }

    // 带环的连表
    public static ListNode generateRingLinkedList() {
        ListNode root = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        root.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        return root;
    }

    public static BiLinkNode generateBiLinkList() {
        BiLinkNode node1 = new BiLinkNode(1);
        BiLinkNode node2 = new BiLinkNode(2);
        BiLinkNode node3 = new BiLinkNode(3);
        BiLinkNode node4 = new BiLinkNode(4);
        BiLinkNode node5 = new BiLinkNode(5);
        BiLinkNode node6 = new BiLinkNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        node6.previous = node5;
        node5.previous = node4;
        node4.previous = node3;
        node3.previous = node2;
        node2.previous = node1;
        return node1;
    }

    public static int[][] generate2DArray() {
        int[][] testDate = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        return testDate;
    }

}
