package com.xx.basicDs.tree;

import com.xx.domain.TreeNode;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/29
 * <p>
 * 二叉搜索树迭代器
 * <p>
 * 请实现二叉搜索树的迭代器BSTIterator，它主要有如下3个函数。
 * ● 构造函数：输入二叉搜索树的根节点初始化该迭代器。
 * ● 函数next：返回二叉搜索树中下一个最小的节点的值。
 * ● 函数hasNext：返回二叉搜索树是否还有下一个节点。
 */
public class BinarySearchTreeIterator {

    private TreeNode root;
    private TreeNode cur;
    private Stack<TreeNode> stack;

    public BinarySearchTreeIterator(TreeNode node) {
        this.root = node;
        this.cur = root;
        this.stack = new Stack<>();
    }


    /**
     * 方法1：转化成列表，存储。
     * 方法2：将此搜索树转换为链表。
     * 方法3：用栈，参考遍历结构。
     * 之前解法都有，解法略。
     */
    public int next() {
        while (!stack.isEmpty() || cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int temp = cur.val;
        cur = cur.right;
        return temp;
    }

    public boolean hasNext() {
        return (!stack.isEmpty() || cur != null);
    }

}