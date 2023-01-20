package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/23
 * 横向遍历二叉树
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