package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * BiNode
 * LeetCode Easy
 * <p>
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，
 * 也就是在原始的二叉搜索树上直接修改。
 * <p>
 * 返回转换后的单向链表的头节点。
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例：
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * 提示：
 * 节点数量不会超过 100000。
 * <p>
 * Tag:树的递归遍历
 */
public class BiNode implements Answer {

    TreeNode startP = null;
    TreeNode lastP = null;

    public static void main(String[] args) {
        new BiNode().answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode treeNode = DataFactory.generateTreeNode();
        TreeNode res = convertBiNode(treeNode);
        System.out.println(res.val);
    }

    /**
     * 中序遍历即可
     * 然后维持一个last指针
     */
    public TreeNode convertBiNode(TreeNode root) {
        bfs(root);
        return startP;
    }

    private void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        bfs(root.left);
        if (startP == null) {
            startP = root;
        } else {
            lastP.right = root;
            // 左侧已经遍历过了，所以这里置空没有影响
            root.left = null;
        }
        lastP = root;
        bfs(root.right);
    }


    /**
     * 使用了额外的辅助空间，不符合题意。
     * 需要原地修改
     */
    public TreeNode convertBiNodeOld(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<TreeNode> list = new ArrayList<>();
        myDiGui(root, list);
        TreeNode start = null;
        TreeNode last = null;
        for (TreeNode treeNode : list) {
            if (start == null) {
                start = treeNode;
            } else {
                last.right = treeNode;
                treeNode.left = null;
            }
            last = treeNode;
        }
        return start;
    }

    private void myDiGui(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        myDiGui(root.left, list);
        list.add(root);
        myDiGui(root.right, list);
    }

    @Override
    public Object initData() {
        return null;
    }
}
