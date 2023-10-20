package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/19
 * <p>
 * 从中序与后序遍历序列构造二叉树
 * LeetCode 106  Medium
 * <p>
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
 * postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * <p>
 * 示例 1:
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 * <p>
 * 示例 2:
 * 输入：inorder = [-1], postorder = [-1]
 * 输出：[-1]
 * <p>
 * 提示:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder 和 postorder 都由 不同 的值组成
 * postorder 中每一个值都在 inorder 中
 * inorder 保证是树的中序遍历
 * postorder 保证是树的后序遍历
 */
public class ConstructBinaryTreeByMidAndAfter implements Answer {

    public static void main(String[] args) {
        new ConstructBinaryTreeByMidAndAfter().answerOne();
    }

    @Override
    public void answerOne() {
        int[][] data = initData();
        int[] midOrder = data[0];
        int[] afterOrder = data[1];
        Map<Integer, Integer> afterMap = new HashMap<>(afterOrder.length);
        for (int i = 0; i < afterOrder.length; i++) {
            afterMap.put(afterOrder[i], i);
        }
        TreeNode treeNode = constructBinaryTree(0, midOrder.length - 1, midOrder, afterMap);
        System.out.println(treeNode);
    }

    private TreeNode constructBinaryTree(int start, int end, int[] midOrder, Map<Integer, Integer> afterMap) {
        if (start == end) {
            return new TreeNode(midOrder[start], null, null);
        }
        if (start > end || end >= midOrder.length) {
            return null;
        }
        int rootIndex = findRootIndexByMid(start, end, midOrder, afterMap);
        TreeNode left = constructBinaryTree(start, rootIndex - 1, midOrder, afterMap);
        TreeNode right = constructBinaryTree(rootIndex + 1, end, midOrder, afterMap);
        TreeNode root = new TreeNode(midOrder[rootIndex], left, right);
        return root;
    }

    private int findRootIndexByMid(final int start, final int end, final int[] midOrder, final Map<Integer, Integer> afterMap) {
        int resultIndex = -1;
        int tempMax = -1;
        for (int i = start; i <= end && i < midOrder.length; i++) {
            int value = midOrder[i];
            Integer afterIndex = afterMap.get(value);
            if (afterIndex > tempMax) {
                resultIndex = i;
                tempMax = afterIndex;
            }
        }
        return resultIndex;
    }


    @Override
    public int[][] initData() {
        return new int[][]{{9, 3, 15, 20, 7}, {9, 15, 7, 20, 3}};
    }
}