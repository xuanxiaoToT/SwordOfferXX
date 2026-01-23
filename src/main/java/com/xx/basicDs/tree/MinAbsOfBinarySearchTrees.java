package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/24
 * <p>
 * 二叉搜索树的最小绝对差
 * LeetCode 530 Easy
 * <p>
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * <p>
 * 示例 1：
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 * <p>
 * 提示：
 * 树中节点的数目范围是 [2, 10^4]
 * 0 <= Node.val <= 10^5
 */
public class MinAbsOfBinarySearchTrees implements Answer {

    public static void main(String[] args) {
        new MinAbsOfBinarySearchTrees().answer();
    }


    /**
     * 解1：中序遍历简单做
     */
    @Override
    public void answer() {
        TreeNode root = initData();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            System.out.println(0);
        }
        Integer last = null;
        int minAbs = Integer.MAX_VALUE;
        TreeNode cur = root;
        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (last == null) {
                last = cur.val;
            } else {
                minAbs = Math.min(Math.abs(cur.val - last), minAbs);
                last = cur.val;
            }
            cur = cur.right;
        }
        System.out.println(minAbs);
    }

    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
