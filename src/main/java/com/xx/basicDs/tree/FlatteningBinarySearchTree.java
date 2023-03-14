package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/27
 * <p>
 * 展平二叉搜索树
 * <p>
 * 给定一棵二叉搜索树，请调整节点的指针使每个节点都
 * 没有左子节点。调整之后的树看起来像一个链表，但仍然是二叉搜
 * 索树。
 */
public class FlatteningBinarySearchTree implements Answer {

    private TreeNode last;

    public static void main(String[] args) {
        new FlatteningBinarySearchTree().answerTwo();
    }

    /**
     * 递归法简单做
     */
    @Override
    public void answerOne() {
        TreeNode treeNode = initData();
        TreeNode head = new TreeNode(0);
        last = head;
        diGuiTree(treeNode);
        System.out.println(head.right);
    }

    private void diGuiTree(TreeNode node) {
        if (node == null) {
            return;
        }
        diGuiTree(node.left);
        System.out.println(node.value + " " + last.value);
        last.right = node;
        last.left = null;
        last = node;
        // System.out.println(node.value + " " + last.value);
        diGuiTree(node.right);
    }

    // 非递归，用栈来做，把取值的那块改为组装链表
    public void answerTwo() {
        TreeNode treeNode = initData();
        TreeNode cur = treeNode;
        TreeNode point = new TreeNode(0);

        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.value);
            cur = cur.right;
        }
    }

    /**
     * something
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}