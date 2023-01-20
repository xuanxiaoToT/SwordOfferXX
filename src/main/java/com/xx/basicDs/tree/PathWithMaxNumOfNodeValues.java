package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/26
 * 节点值之和最大的路径
 * <p>
 * 怎么感觉更像图？
 * <p>
 * 在二叉树中将路径定义为顺着节点之间的连接从任意一
 * 个节点开始到达任意一个节点所经过的所有节点。路径中至少包含
 * 一个节点，不一定经过二叉树的根节点，也不一定经过叶节点。给
 * 定非空的一棵二叉树，请求出二叉树所有路径上节点值之和的最大
 * 值。
 */
public class PathWithMaxNumOfNodeValues implements Answer {

    private int maxResult = Integer.MIN_VALUE;

    public static void main(String[] args) {
        new PathWithMaxNumOfNodeValues().answerOne();
    }


    /**
     * 思路：如果一条路径同时经过某个节点的左右子节点，那么该路径一定不能经过它的父节点。
     * <p>
     * <p>
     * 由于路径可能只经过左子树或右子树而不经过根节点，为了求得
     * 二叉树的路径上节点值之和的最大值，需要先求出左右子树中路径节
     * 点值之和的最大值（左右子树中的路径不经过当前节点），再求出经
     * 过根节点的路径节点值之和的最大值，最后对三者进行比较得到最大
     * 值。
     * <p>
     * 最大值要么在左节点上，要么在右节点上，要么是自己加左右。
     */
    @Override
    public void answerOne() {
        TreeNode treeNode = initData();
        Integer asd = diGuiTree(treeNode.right);
        System.out.println(maxResult + " " + asd);
    }

    private Integer diGuiTree(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null && node.left == null) {
            // 都为null
            maxResult = Math.max(node.value, maxResult);
            return node.value;
        }
        Integer left = diGuiTree(node.left);
        Integer right = diGuiTree(node.right);
        System.out.println(maxResult);
        if (left != null && right != null) {
            int tempMax = left + right + node.value;
            maxResult = Math.max(tempMax, maxResult);
            return Math.max(Math.max(left + node.value, right + node.value), node.value);
        } else {
            if (left != null) {
                int tempMax = Math.max(left + node.value, node.value);
                maxResult = Math.max(tempMax, maxResult);
                return tempMax;
            } else {
                int tempMax = Math.max(right + node.value, node.value);
                maxResult = Math.max(tempMax, maxResult);
                return tempMax;
            }
        }
    }

    /**
     * something
     */
    @Override
    public TreeNode initData() {
        TreeNode node1 = new TreeNode(-9);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(-3);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node4.left = node6;
        return node1;
    }
}