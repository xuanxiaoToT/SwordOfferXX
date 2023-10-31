package com.xx.domain;

import com.xx.basicDs.tree.AATraversalOfTree;

import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/22
 */
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode() {
    }

    @Override
    public String toString() {
        List<Integer> list = new AATraversalOfTree().levelOrderTraversal(this);
        return list.toString();
    }
}