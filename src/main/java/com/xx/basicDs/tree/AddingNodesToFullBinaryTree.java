package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/20
 * 在完全二叉树中添加节点
 * <p>
 * 在完全二叉树中，除最后一层之外其他层的节点都是满
 * 的（第n层有2n-1个节点）。最后一层的节点可能不满，该层所有的
 * 节点尽可能向左边靠拢。
 * <p>
 * 在完全二叉树中添加节点时需要按照广度
 * 优先搜索的顺序找出第1个缺少子节点的节点。
 */
public class AddingNodesToFullBinaryTree implements Answer {

    private TreeNode lastNode;

    public static void main(String[] args) {
        new AddingNodesToFullBinaryTree().answer();
    }

    /**
     * 直接层序遍历到最后一层，然后添加即可。
     */
    @Override
    public void answer() {
        //初始化完全二叉树中
        TreeNode treeNode = initData();
        insert(treeNode, new TreeNode(123));
        insert(treeNode, new TreeNode(456));
        System.out.println(treeNode);
    }


    /**
     * 优化：不用每次都从根节点开始
     */
    private void insert(TreeNode root, TreeNode node) {
        // 层序遍历，第一个遇到的子节点为null的即可进行添加。
        Queue<TreeNode> outQueue = new LinkedList<TreeNode>();
        if (lastNode != null) {
            outQueue.add(lastNode);
        } else {
            outQueue.add(root);
        }

        while (!outQueue.isEmpty()) {
            TreeNode temp = outQueue.poll();
            if (temp.left != null) {
                outQueue.add(temp.left);
            } else {
                temp.left = node;
                lastNode = node;
                return;
            }
            if (temp.right != null) {
                outQueue.add(temp.right);
            } else {
                temp.right = node;
                lastNode = node;
                return;
            }
        }
    }

    /**
     * something
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
