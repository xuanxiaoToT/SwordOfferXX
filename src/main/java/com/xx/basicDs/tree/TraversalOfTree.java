package com.xx.basicDs.tree;

import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.*;

/**
 * @author 玄霄
 * @CreateDate 2022/6/28
 * 树的常见遍历方法
 */
public class TraversalOfTree {

    public static void main(String[] args) {
        TraversalOfTree traversalOfTree = new TraversalOfTree();
        TreeNode treeNode = DataFactory.generateTreeNode();
        System.out.println(traversalOfTree.preOrderTraversal(treeNode));
        System.out.println(traversalOfTree.midOrderTraversal(treeNode));
        System.out.println(traversalOfTree.postOrderTraversal(treeNode));
        System.out.println(traversalOfTree.levelOrderTraversal(treeNode));
    }

    /**
     * 前序遍历
     * 根--左--右
     */
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                list.add(cur.value);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return list;
    }

    /**
     * 中序遍历
     */
    public List<Integer> midOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root;
        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.value);
            cur = cur.right;
        }
        return list;
    }

    /**
     * 后序遍历
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root;
        TreeNode pre = null;
        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == pre) {
                list.add(cur.value);
                stack.pop();
                pre = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * 层序遍历
     */
    public List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> outQueue = new LinkedList<TreeNode>();
        if (root != null) {
            outQueue.add(root);
        }
        while (!outQueue.isEmpty()) {
            TreeNode temp = outQueue.poll();
            list.add(temp.value);
            if (temp.left != null) {
                outQueue.add(temp.left);
            }
            if (temp.right != null) {
                outQueue.add(temp.right);
            }
        }
        return list;
    }

}