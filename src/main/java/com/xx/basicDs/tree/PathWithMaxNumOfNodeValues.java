package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/26
 * <p>
 * 节点值之和最大的路径
 * LeetCode 124. 二叉树中的最大路径和
 * <p>
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * <p>
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 提示：
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 <= Node.val <= 1000
 */
public class PathWithMaxNumOfNodeValues implements Answer {

    private int maxResult = Integer.MIN_VALUE;

    public static void main(String[] args) {
        new PathWithMaxNumOfNodeValues().answerOne();
    }


    /**
     * 思路：如果一条路径同时经过某个节点的左右子节点，那么该路径一定不会经过它的父节点。
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
        Integer asd = diGuiTree(treeNode);
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
        if (left != null && right != null) {
            int tempMax = node.value;
            if (left > 0) {
                tempMax = tempMax + left;
            }
            if (right > 0) {
                tempMax = tempMax + right;
            }
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