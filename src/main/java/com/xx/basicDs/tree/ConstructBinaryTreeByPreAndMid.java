package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

import java.util.HashMap;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/14
 * <p>
 * 从前序与中序遍历序列构造二叉树
 * LeetCode 105.
 * <p>
 * 给定两个整数数组 preorder 和 inorder ，
 * 其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * <p>
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 */
public class ConstructBinaryTreeByPreAndMid implements Answer {

    public static void main(String[] args) {
        new ConstructBinaryTreeByPreAndMid().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[][] data = initData();
        int[] preOrder = data[0];
        int[] midOrder = data[1];
        HashMap<Integer, Integer> preMap = new HashMap<>();
        for (int i = 0; i < preOrder.length; i++) {
            preMap.put(preOrder[i], i);
        }
        //根据  midOrder来构造
        TreeNode treeNode = constructBinaryTree(0, midOrder.length - 1, midOrder, preMap);
        System.out.println(123);
    }

    private TreeNode constructBinaryTree(int start, int end, int[] midOrder, HashMap<Integer, Integer> preMap) {
        if (start == end) {
            return new TreeNode(midOrder[start], null, null);
        }
        if (start > end || end >= midOrder.length) {
            return null;
        }
        int minByPre = findMinByPre(start, end, midOrder, preMap);
        TreeNode left = constructBinaryTree(start, minByPre - 1, midOrder, preMap);
        TreeNode right = constructBinaryTree(minByPre + 1, end, midOrder, preMap);
        return new TreeNode(midOrder[minByPre], left, right);
    }


    private int findMinByPre(int start, int end, int[] midOrder, HashMap<Integer, Integer> preMap) {
        int minIndex = midOrder.length;
        int minValue = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (preMap.get(midOrder[i]) < minValue) {
                minValue = preMap.get(midOrder[i]);
                minIndex = i;
            }
        }
        return minIndex;
    }


    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{3, 9, 20, 15, 7}, {9, 3, 15, 20, 7}};
    }
}
