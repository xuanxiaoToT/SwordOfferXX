package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/27
 * <p>
 * 二叉树的锯齿形层序遍历
 * LeetCode  103  Medium
 * <p>
 * 从上到下，从左到右  "之"字型打印二叉树
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * <p>
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 * <p>
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 */
public class ZigzagTransverseOutTree implements Answer {

    public static void main(String[] args) {
        ZigzagTransverseOutTree zigzagTransverseOutTree = new ZigzagTransverseOutTree();
        zigzagTransverseOutTree.answer();
    }

    @Override
    public void answer() {
        TreeNode root = initData();
        List<List<Integer>> res = new LinkedList<List<Integer>>();

        // 边界情况处理
        if (root == null) {
            System.out.println("null");
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        // 用来判断当前的层数是否为奇数层，初始化在第 0 层，为偶数层
        boolean isOddNumber = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 奇偶层总是交替出现的
            // 通过取反操作，判断当前的层数是否为奇偶层
            isOddNumber = !isOddNumber;
            LinkedList<Integer> temp = new LinkedList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 如果是奇数层，那么按顺序添加到双端队列的尾部
                if (isOddNumber) {
                    temp.addLast(node.val);
                } else {
                    // 如果是偶数层，那么按顺序添加到双端队列的头部
                    temp.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(temp);
        }
        System.out.println(res);
    }

    @Override
    public TreeNode initData() {
        TreeNode treeNode = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        treeNode.left = node1;
        treeNode.right = node2;
        node2.left = node3;
        return treeNode;
    }
}