package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/19
 * <p>
 * 第 k 大的节点
 * <p>
 * 给定一棵 二叉搜索树，请找出其中第 k 大的节点。
 */
public class KthNodeInBinaryTree implements Answer {

    private int k;

    public static void main(String[] args) {
        new KthNodeInBinaryTree().answerOne();
    }

    /**
     * 第K大，即 右中左 遍历，第K个便是.
     */
    @Override
    public void answerOne() {
        k = 3;
        TreeNode root = initData();
        traversalTree(root);
    }

    private void traversalTree(TreeNode node) {
        if (node == null) {
            return;
        }
        traversalTree(node.right);
        k--;
        if (k == 0) {
            System.out.println(node.val);
        }
        traversalTree(node.left);
    }

    @Override
    public TreeNode initData() {
        TreeNode treeNode = DataFactory.generateTreeNode();
        return treeNode;
    }
}