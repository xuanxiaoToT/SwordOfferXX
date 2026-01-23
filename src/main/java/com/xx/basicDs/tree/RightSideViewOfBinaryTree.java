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
 * <p>
 * 二叉树的右视图
 * LeetCode 199 Medium
 * <p>
 * 给定一棵二叉树，如果站在该二叉树的右侧，那么从上
 * 到下看到的节点构成二叉树的右侧视图。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * <p>
 * 示例 2:
 * 输入: [1,null,3]
 * 输出: [1,3]
 * <p>
 * 示例 3:
 * 输入: []
 * 输出: []
 * <p>
 * 提示:
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
public class RightSideViewOfBinaryTree implements Answer {

    public static void main(String[] args) {
        new RightSideViewOfBinaryTree().answer();
    }

    /**
     * 其实就是每层的最右侧节点
     */
    @Override
    public void answer() {
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
                    resultList.add(temp.val);
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