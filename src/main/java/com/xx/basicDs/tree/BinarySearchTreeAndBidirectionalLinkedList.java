package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 玄霄
 * @CreateDate 2022/6/29
 * 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 其中右指针表示向下，左指针表示向后
 */
public class BinarySearchTreeAndBidirectionalLinkedList implements Answer {

    public static void main(String[] args) {
        BinarySearchTreeAndBidirectionalLinkedList aaa = new BinarySearchTreeAndBidirectionalLinkedList();
        aaa.answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode root = initData();
        TreeNode listHead = null;
        TreeNode listPre = null;

        TreeNode cur = root;

        List<TreeNode> nodeList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 进行双端链表的组装
            cur = stack.pop();
            if (listHead == null) {
                listHead = cur;
                listPre = listHead;
            } else {
                listPre.right = cur;
                cur.left = listPre;
                listPre = cur;
            }
            nodeList.add(cur);
            cur = cur.right;
        }
        listPre.right = listHead;
        listHead.left = listPre;
        // TreeNode result = transTreeToList(nodeList);
        System.out.println("Done");
    }

    public void answerTwo() {
        //  也可以用递归的方式来做
    }

    //  将其转换为双端循环列表
    private TreeNode transTreeToList(List<TreeNode> treeNodeList) {
        if (treeNodeList.isEmpty()) {
            return null;
        }
        TreeNode root = null;
        TreeNode pre = null;
        for (TreeNode node : treeNodeList) {
            if (root == null) {
                root = node;
                pre = root;
            } else {
                pre.right = node;
                node.left = pre;
                pre = node;
            }
        }
        root.left = pre;
        pre.right = root;
        return root;
    }

    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
