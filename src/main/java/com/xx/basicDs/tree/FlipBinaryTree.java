package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/20
 * <p>
 * 翻转二叉树
 * LeetCode 226. Easy
 * <p>
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * <p>
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * <p>
 * 示例 2：
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * <p>
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 提示：
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class FlipBinaryTree implements Answer {

    public static void main(String[] args) {
        new FlipBinaryTree().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        TreeNode treeNode = initData();
        TreeNode newRoot = invertTree(treeNode);
        System.out.println(newRoot);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        return root;
    }

    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
