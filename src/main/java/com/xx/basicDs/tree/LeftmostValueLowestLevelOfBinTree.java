package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/21
 * <p>
 * 二叉树最低层最左边的值
 * <p>
 * 如何在一棵二叉树中找出它最低层最左边节点的值？假
 * 设二叉树中最少有一个节点。
 */
public class LeftmostValueLowestLevelOfBinTree implements Answer {

    public static void main(String[] args) {
        new LeftmostValueLowestLevelOfBinTree().answerOne();
    }

    /**
     * something
     */
    @Override
    public void answerOne() {
        TreeNode root = initData();
        Queue<TreeNode> outQueue = new LinkedList<>();
        if (root != null) {
            outQueue.add(root);
        }
        int result = 0;
        while (!outQueue.isEmpty()) {
            // 求每层的XX时，此处多增一层for循环。
            int size = outQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = outQueue.poll();
                if (i == 0) {
                    result = temp.val;
                }
                if (temp.left != null) {
                    outQueue.add(temp.left);
                }
                if (temp.right != null) {
                    outQueue.add(temp.right);
                }
            }
        }
        System.out.println(result);
    }

    /**
     * something
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
