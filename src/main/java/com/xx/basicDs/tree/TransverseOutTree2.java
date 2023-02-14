package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/27
 * 从上到下，从左到右  "之"字型打印二叉树
 */
public class TransverseOutTree2 implements Answer {

    public static void main(String[] args) {
        TransverseOutTree2 transverseOutTree2 = new TransverseOutTree2();
        transverseOutTree2.answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode root = initData();
        // 设置 res 用来保存输出结果
        List<List<Integer>> res = new LinkedList<List<Integer>>();

        // 边界情况处理
        if (root == null) {
            System.out.println("null");
        }
        //设置一个队列，用来存储二叉树中的元素
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // 队列添加二叉树的根节点
        queue.add(root);
        // 用来判断当前的层数是否为奇数层，初始化在第 0 层，为偶数层
        boolean isOddNumber = false;
        // 遍历队列，直到队列为空，说明访问了二叉树中所有的节点
        while (!queue.isEmpty()) {
            // 用来记录 queue 的长度，即每层节点的个数
            int size = queue.size();
            // 奇偶层总是交替出现的
            // 通过取反操作，判断当前的层数是否为奇偶层
            // 由于 isOddNumber 初始化为 false，所以第一次进来这个 while 循环取反后为 true，符合第一层是奇数层的定义
            isOddNumber = !isOddNumber;
            // 生成一个双端队列 temp，用来保存每一层节点，保存成功后添加到 res 中
            LinkedList<Integer> temp = new LinkedList<Integer>();
            // 使用 for 循环，将 queue 中的元素按照给定的规则添加的 temp 中
            for (int i = 0; i < size; i++) {
                // 从 queue 中取出一个节点
                TreeNode node = queue.poll();
                // 如果是奇数层，那么按顺序添加到双端队列的尾部
                if (isOddNumber) {
                    temp.addLast(node.value);
                } else {
                    // 如果是偶数层，那么按顺序添加到双端队列的头部
                    temp.addFirst(node.value);
                }
                // 判断当前节点的左子节点是否有值，如果有，则添加到 queue 中
                if (node.left != null) {
                    queue.add(node.left);
                }
                // 判断当前节点的右子节点是否有值，如果有，则添加到 queue 中
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 把存放了每一层元素的数组 temp 添加到 res 中
            res.add(temp);
        }
        // 返回 res
        System.out.println(res);
    }

    @Override
    public TreeNode initData() {
        TreeNode treeNode = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        treeNode.left = node1;
        treeNode.right = node2;
        node2.left = node3;
        return treeNode;
    }
}