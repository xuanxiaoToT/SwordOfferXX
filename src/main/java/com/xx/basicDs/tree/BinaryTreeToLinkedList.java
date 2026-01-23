package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/15
 * <p>
 * 二叉树展开为链表
 * LeetCode 114.
 * <p>
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，
 * 而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 */
public class BinaryTreeToLinkedList implements Answer {

    public static void main(String[] args) {
        new BinaryTreeToLinkedList().answer();
    }

    private TreeNode lastnode = null;

    /**
     * 解1:正常前序做，用栈
     * 2：用后续来做：因为按照左中右递归时，中间处理则会把右节点给丢失掉。
     */
    @Override
    public void answer() {
        TreeNode root = initData();
        //右左中,变为这种后续遍历.
        if (root == null){
            return;
        }
        myDiGui(root);
    }

    private void myDiGui(TreeNode p) {
        if (p == null) {
            return;
        }
        myDiGui(p.right);
        myDiGui(p.left);
        p.right = lastnode;
        p.left = null;
        lastnode = p;
    }

    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
