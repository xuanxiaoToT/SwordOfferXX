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
 * @CreateDate 2023/10/24
 * <p>
 * 二叉树的层平均值
 * LeetCode 637 Easy
 * <p>
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。
 * 与实际答案相差 10^-5 以内的答案可以被接受。
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
 * 因此返回 [3, 14.5, 11] 。
 * <p>
 * 示例 2:
 * 输入：root = [3,9,20,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * <p>
 * 提示：
 * 树中节点数量在 [1, 10^4] 范围内
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class TheLayerAverageOfBinaryTree implements Answer {

    public static void main(String[] args) {
        new TheLayerAverageOfBinaryTree().answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode root = initData();
        Queue<TreeNode> outQueue = new LinkedList<TreeNode>();
        List<Double> avgList = new ArrayList<>();
        if (root != null) {
            outQueue.add(root);
        }
        while (!outQueue.isEmpty()) {
            int size = outQueue.size();
            long sumTemp = 0;
            for (int i = 0; i < size; i++) {
                TreeNode temp = outQueue.poll();
                sumTemp += temp.val;
                if (temp.left != null) {
                    outQueue.add(temp.left);
                }
                if (temp.right != null) {
                    outQueue.add(temp.right);
                }
            }
            avgList.add(sumTemp * 1.0 / size);
        }
        System.out.println(avgList);
    }

    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}