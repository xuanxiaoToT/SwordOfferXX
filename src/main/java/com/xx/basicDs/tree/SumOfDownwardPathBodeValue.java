package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 玄霄
 * @CreateDate 2022/9/23
 * 向下的路径节点值之和
 * <p>
 * 给定一棵二叉树和一个值sum，求二叉树中节点值之和等
 * 于sum的路径的数目。路径的定义为二叉树中顺着指向子节点的指针
 * 向下移动所经过的节点，但不一定从根节点开始，也不一定到叶节
 * 点结束。
 */
public class SumOfDownwardPathBodeValue implements Answer {

    private int targetSum = 16;
    private int result = 0;

    public static void main(String[] args) {
        new SumOfDownwardPathBodeValue().answerTwo();
    }

    /**
     * 因为不一定从根节点开始，也不一定到叶节点结束，故任何一段都有可能，所以需要一个双重循环、
     * 遍历树，然后每个树又作为一个新树去判断。
     */
    @Override
    public void answerOne() {

    }

    /**
     * 将之前的结果存起来~！ 转为列表计算
     * 每个节点都计算总和，并存储在一个列表里，到达一个节点时，现在的和与之前路径的值相减，看是否有符合的
     */
    public void answerTwo() {
        TreeNode treeNode = initData();
        diGuiTree(treeNode, new ArrayList<>());
        System.out.println(result);
    }

    private void diGuiTree(TreeNode node, List<Integer> list) {
        if (node.left == null && node.right == null) {
            list.add(node.value);
            checkList(list);
            list.remove(list.size() - 1);
            return;
        }
        list.add(node.value);
        if (node.left != null) {
            diGuiTree(node.left, list);
        }
        if (node.right != null) {
            diGuiTree(node.right, list);
        }
        list.remove(list.size() - 1);
    }

    private void checkList(List<Integer> list) {
        int tempSum = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            tempSum = tempSum + list.get(i);
            if (tempSum == targetSum) {
                result++;
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
