package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author 玄霄
 * @CreateDate 2022/9/23
 * 根节点到叶节点的路径数字之和
 */
public class SumPathFromRootToLeaf implements Answer {

    private int sum = 0;

    public static void main(String[] args) {
        new SumPathFromRootToLeaf().answerOne();
    }

    /**
     * 在一棵二叉树中所有节点都在0～9的范围之内，从根节
     * 点到叶节点的路径表示一个数字。求二叉树中所有路径表示的数字
     * 之和。
     * <p>
     * 也是直接递归遍历就行了
     */
    @Override
    public void answerOne() {
        TreeNode treeNode = initData();
        diGuiTree(treeNode,0);
        System.out.println(sum);
    }

    private void diGuiTree(TreeNode node, int lastNum) {
        if (node.left == null && node.right == null) {
            System.out.println(lastNum * 10 + node.value);
            sum = sum + lastNum * 10 + node.value;
            return;
        }
        lastNum = lastNum * 10 + node.value;
        if (node.left != null) {
            diGuiTree(node.left, lastNum);
        }
        if (node.right != null) {
            diGuiTree(node.right, lastNum);
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