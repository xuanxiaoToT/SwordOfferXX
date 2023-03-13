package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/23
 * <p>
 * 横向遍历二叉树
 * LeetCode 102. 二叉树的层序遍历
 * <p>
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * <p>
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 * <p>
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 */
public class TransverseOutTree implements Answer {

    public static void main(String[] args) {
        TransverseOutTree transverseOutTree = new TransverseOutTree();
        transverseOutTree.answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode treeNode = initData();
        Queue<TreeNode> outQueue = new LinkedList<TreeNode>();
        if (treeNode != null) {
            outQueue.add(treeNode);
        }
        while (!outQueue.isEmpty()) {
            // System.out.println(outQueue.size());
            TreeNode temp = outQueue.poll();
            System.out.println(temp.value);
            if (temp.left != null) {
                outQueue.add(temp.left);
            }
            if (temp.right != null) {
                outQueue.add(temp.right);
            }
        }
    }

    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}