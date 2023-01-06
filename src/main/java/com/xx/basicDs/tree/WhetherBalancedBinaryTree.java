package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author 玄霄
 * @CreateDate 2022/7/19
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class WhetherBalancedBinaryTree implements Answer {

    public static void main(String[] args) {
        new WhetherBalancedBinaryTree().answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode treeNode = initData();
        System.out.println(maxDepth(treeNode) != -1);
    }

    private int maxDepth(TreeNode root) {
        // 如果 root 为空，直接返回 0
        if (root == null) {
            return 0;
        }
        // 递归调用 maxDepth，求出当前节点的左子树的最大深度
        int left = maxDepth(root.left);
        // 递归调用 maxDepth，求出当前节点的右子树的最大深度
        int right = maxDepth(root.right);
        if (left == -1 || right == -1) {
            return -1;
        }
        // 求出当前节点的左右子树中较大的值
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        // 二叉树的最大深度就是它的左右子树中较大的值加上 1
        return Math.max(left, right) + 1;
    }

    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}