package com.xx.domain;

import com.xx.basicDs.tree.TraversalOfTree;
import lombok.Data;

import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/22
 */
@Data
public class TreeNode {

    public int value;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode() {
    }

    @Override
    public String toString() {
        List<Integer> list = new TraversalOfTree().levelOrderTraversal(this);
        return list.toString();
    }
}