package com.xx.basicDs.tree;

import com.xx.Answer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/19
 * <p>
 * 填充每个节点的下一个右侧节点指针II
 * LeetCode 117. Medium
 * <p>
 * 给定一个二叉树：
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 提示：
 * 树中的节点数在范围 [0, 6000] 内
 * -100 <= Node.val <= 100
 * <p>
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序的隐式栈空间不计入额外空间复杂度。
 */
public class FillInNextRightNodeForEachNodeII implements Answer {

    public static void main(String[] args) {
        new FillInNextRightNodeForEachNodeII().answer();
    }

    @Override
    public void answer() {
        Node root = initData();
        Node pre = null;
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if (i == 0) {
                    pre = poll;
                } else {
                    pre.next = poll;
                    pre = poll;
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        System.out.println(root);
    }

    @Override
    public Node initData() {
        Node treeNode = new Node(15);
        Node node1 = new Node(9);
        Node node2 = new Node(20);
        Node node3 = new Node(7);
        Node node4 = new Node(13);
        Node node5 = new Node(21);
        treeNode.left = node1;
        treeNode.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        return treeNode;
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}