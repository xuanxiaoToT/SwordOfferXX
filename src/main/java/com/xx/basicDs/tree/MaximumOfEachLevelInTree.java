package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/20
 * <p>
 * 二叉树中每层的最大值
 * <p>
 * 输入一棵二叉树，请找出二叉树中每层的最大值。
 */
public class MaximumOfEachLevelInTree implements Answer {
    public static void main(String[] args) {
        new MaximumOfEachLevelInTree().answerOne();
    }

    /**
     * 计算每层的最大值，则用双循环。
     */
    @Override
    public void answerOne() {
        TreeNode root = initData();
        Queue<TreeNode> outQueue = new LinkedList<>();
        if (root != null) {
            outQueue.add(root);
        }
        while (!outQueue.isEmpty()) {
            int size = outQueue.size();
            int max = 0;
            for (int i = 0; i < size; i++) {
                TreeNode temp = outQueue.poll();
                max = Math.max(temp.value, max);
                if (temp.left != null) {
                    outQueue.add(temp.left);
                }
                if (temp.right != null) {
                    outQueue.add(temp.right);
                }
            }
            System.out.println(max);
        }

    }

    /**
     * 用两个队列实现
     * 可以用两个不同的队列queue1和
     * queue2分别存放两层的节点，队列queue1中只放当前遍历层的节点，
     * 而队列queue2中只放下一层的节点
     */
    public void answerTwo() {

    }

    /**
     * something
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
