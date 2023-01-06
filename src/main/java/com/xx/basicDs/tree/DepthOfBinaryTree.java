package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author 玄霄
 * @CreateDate 2022/7/19
 * 二叉树的深度
 */
public class DepthOfBinaryTree implements Answer {

    private int maxDepth;

    public static void main(String[] args) {
        new DepthOfBinaryTree().answerOne();
    }

    @Override
    public void answerOne() {
        // 直接递归遍历
        TreeNode treeNode = initData();
        traversalTree(treeNode, 1);
        System.out.println(maxDepth);
    }


    private void answerTwo() {
        // 树的深度 等于 它的左子树的深度 与 它的右子树的深度 中的 最大值 +1 。
        TreeNode treeNode = initData();
        int depth = maxDepth(treeNode);
        System.out.println(depth);
    }

    private void answerThree() {
        // 如何用层序遍历来做?
    }

    private void traversalTree(TreeNode root, int deep) {
        if (root == null) {
            if (maxDepth < deep) {
                maxDepth = deep - 1;
            }
            return;
        }
        traversalTree(root.left, deep + 1);
        traversalTree(root.right, deep + 1);
    }

    public int maxDepth(TreeNode root) {
        // 如果 root 为空，直接返回 0
        if (root == null) {
            return 0;
        }
        // 递归调用 maxDepth，求出当前节点的左子树的最大深度
        int left = maxDepth(root.left);
        // 递归调用 maxDepth，求出当前节点的右子树的最大深度
        int right = maxDepth(root.right);
        // 求出当前节点的左右子树中较大的值
        int childMaxDepth = Math.max(left, right);
        // 二叉树的最大深度就是它的左右子树中较大的值加上 1
        return childMaxDepth + 1;
    }

    @Override
    public TreeNode initData() {
        TreeNode treeNode = DataFactory.generateTreeNode();
        return treeNode;
    }
}