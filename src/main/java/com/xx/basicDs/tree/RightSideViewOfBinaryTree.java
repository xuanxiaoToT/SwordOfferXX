package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/21
 * 树的右视图
 * <p>
 * 给定一棵二叉树，如果站在该二叉树的右侧，那么从上
 * 到下看到的节点构成二叉树的右侧视图。
 */
public class RightSideViewOfBinaryTree implements Answer {

    public static void main(String[] args) {
        new RightSideViewOfBinaryTree().answerOne();
    }

    /**
     * 其实就是每层的最右侧节点
     */
    @Override
    public void answerOne() {
        TreeNode root = initData();
        Queue<TreeNode> outQueue = new LinkedList<>();
        List<Integer> resultList = new ArrayList<>();
        if (root != null) {
            outQueue.add(root);
        }
        while (!outQueue.isEmpty()) {
            int size = outQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = outQueue.poll();
                if (i == size - 1) {
                    resultList.add(temp.value);
                }
                if (temp.left != null) {
                    outQueue.add(temp.left);
                }
                if (temp.right != null) {
                    outQueue.add(temp.right);
                }
            }
        }
        System.out.println(resultList);
    }

    /**
     * something
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}