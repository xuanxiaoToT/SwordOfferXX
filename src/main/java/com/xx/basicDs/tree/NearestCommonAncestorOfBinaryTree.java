package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/11
 * <p>
 * 二叉树的最近公共祖先
 * LeetCode 236 Medium
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 示例 1：
 * 3
 * 5        1
 * 6   2    0    8
 * 7   4
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * <p>
 * 示例 2：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 示例 3：
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 */
public class NearestCommonAncestorOfBinaryTree implements Answer {

    private TreeNode result = null;

    public static void main(String[] args) {
        new NearestCommonAncestorOfBinaryTree().answerTwo();
    }

    /**
     * 解1：如果两个节点分布在X节点的左右，那么X节点一定是他的最近公共祖先。
     * 如果两个节点都在左，则向左遍历。如果都在右，则向右遍历。
     * <p>
     * 公共祖先节点可以为节点本身!!!。
     */
    @Override
    public void answerOne() {
        TreeNode root = initData();
        TreeNode targetOne = new TreeNode(13);
        TreeNode targetTwo = new TreeNode(7);
        myTransverse(root, targetOne, targetTwo);
        System.out.println(result.value);
    }

    /**
     * fixme：没有考虑节点本身为祖先的情况
     */
    private void myTransverse(TreeNode node, TreeNode targetOne, TreeNode targetTwo) {
        if (node != null && node.left != null && node.right != null) {
            boolean left = whetherExit(node.left, targetOne, targetTwo);
            boolean right = whetherExit(node.right, targetOne, targetTwo);
            if (left && right) {
                result = node;
                return;
            } else {
                if (left) {
                    myTransverse(node.left, targetOne, targetTwo);
                }
                if (right) {
                    myTransverse(node.right, targetOne, targetTwo);
                }
            }
        }
    }


    private boolean whetherExit(TreeNode node, TreeNode targetOne, TreeNode targetTwo) {
        if (node == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = node;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                if (cur.value == targetOne.value || cur.value == targetTwo.value) {
                    return true;
                }
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return false;
    }

    /**
     * 官解，在遍历递归的同时即可对比。而不是单开一个递归去对比。
     */
    public void answerTwo() {
        TreeNode root = initData();
        TreeNode targetOne = new TreeNode(13);
        TreeNode targetTwo = new TreeNode(7);
        lca(root, targetOne, targetTwo);
        System.out.println(result.value);
    }


    /**
     *
     */
    private boolean lca(TreeNode node, TreeNode targetOne, TreeNode targetTwo) {
        if (node == null) {
            return false;
        }
        boolean left = lca(node.left, targetOne, targetTwo);
        boolean right = lca(node.right, targetOne, targetTwo);
        boolean self = node.value == targetOne.value || node.value == targetTwo.value;

        if (self && left || self && right || left && right) {
            result = node;
        }
        return self || left || right;
    }


    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
