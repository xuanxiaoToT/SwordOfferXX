package com.xx.basicDs.tree;


import com.xx.Answer;
import com.xx.domain.TreeNode;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/22
 * 二叉树剪枝
 * 一棵二叉树的所有节点的值要么是0要么是1，请剪除该
 * 二叉树中所有节点的值全都是0的子树
 */
public class BinaryTreePruning implements Answer {

    public static void main(String[] args) {
        BinaryTreePruning binaryTreePruning = new BinaryTreePruning();
        TreeNode treeNode = binaryTreePruning.initData();
        TreeNode treeNode1 = binaryTreePruning.answerZero(treeNode);
        System.out.println(treeNode1);
    }

    /**
     * 递归
     * 更简单
     */
    public TreeNode answerZero(TreeNode node) {
        if (node == null) {
            return null;
        }
        node.left = answerZero(node.left);
        node.right = answerZero(node.right);
        if (node.left == null && node.right == null && node.value == 0) {
            return null;
        }
        return node;
    }

    /**
     * 方式一，使用递归来解决。
     * 自己第一遍的时候想的
     */
    @Override
    public void answerOne() {
        TreeNode treeNode = initData();
        TreeNode newTree = null;
        checkTree(treeNode, newTree, "left");
        System.out.println(treeNode.toString());
    }

    /**
     * 方式一，采用 后续遍历。
     */
    public void answerTwo() {
        TreeNode root = initData();
        HashSet<TreeNode> set = new HashSet<>();
        // 后续遍历
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        TreeNode last = null;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == last) {
                // 子树判断
                if (cur.value == 0) {
                    checkZeroNode(set, cur);
                } else {
                    if (whetherZero(set, cur.left)) {
                        cur.left = null;
                    }
                    if (whetherZero(set, cur.right)) {
                        cur.right = null;
                    }
                }
                last = cur;
                cur = null;
                stack.pop();
            } else {
                cur = cur.right;
            }
        }

        System.out.println(root);
    }

    private void checkZeroNode(HashSet<TreeNode> set, TreeNode node) {
        if (whetherZero(set, node.left) && whetherZero(set, node.right)) {
            set.add(node);
        }
    }

    private boolean whetherZero(HashSet<TreeNode> set, TreeNode node) {
        if (node == null || set.contains(node)) {
            return true;
        }
        return false;
    }

    /**
     * 递归体
     */
    private void checkTree(TreeNode root, TreeNode last, String direction) {
        if (root == null) {
            return;
        }
        if (root.value == 1) {
            checkTree(root.left, root, "left");
            checkTree(root.right, root, "right");
        } else {
            if (whetherZeroTree(root)) {
                if ("left".equals(direction)) {
                    last.left = null;
                } else {
                    last.right = null;
                }
            } else {
                checkTree(root.left, root, "left");
                checkTree(root.right, root, "right");
            }
        }
    }

    private boolean whetherZeroTree(TreeNode node) {
        if (node == null) {
            return true;
        }
        return node.value == 0 && whetherZeroTree(node.left) && whetherZeroTree(node.right);
    }

    /**
     * 1
     * /  \
     * 1   0
     * / \   \
     * 0  0  1
     */
    @Override
    public TreeNode initData() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(1);
        treeNode.left = node1;
        treeNode.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        return treeNode;
    }
}
