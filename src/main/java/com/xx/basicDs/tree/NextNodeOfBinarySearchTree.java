package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/27
 * <p>
 * 二叉搜索树的下一个节点
 * <p>
 * 给定一棵二叉搜索树和它的一个节点p，请找出按中序遍
 * 历的顺序该节点p的下一个节点。假设二叉搜索树中节点的值都是唯
 * 一的。
 */
public class NextNodeOfBinarySearchTree implements Answer {

    public static void main(String[] args) {
        new NextNodeOfBinarySearchTree().answerOne();
    }


    @Override
    public void answerOne() {


    }

    /**
     * 利用二叉搜索树的特性。
     * 首先判断该节点有无右节点，有的话直接前往右节点的最左节点即可。
     * 否则从头遍历 如果该值大于目标，则下一个节点在右侧，否则在左侧。
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return null;
    }

    /**
     * 2.用标志位法
     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        boolean flag = false;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (flag) {
                return cur;
            }
            if (cur == p) {
                flag = true;
            }
            cur = cur.right;
        }
        return null;
    }


    /**
     * something
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
