package com.xx.algorithm.divideAndConquer;

import com.xx.Answer;
import com.xx.domain.TreeNode;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/1
 * <p>
 * 将有序数组转换为二叉搜索树
 * LeetCode 108  Easy
 * <p>
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * 示例 1：
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 * <p>
 * 示例 2：
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 按 严格递增 顺序排列
 */
public class ConvertAnOrderedArrayIntoBinarySearchTree implements Answer {

    public static void main(String[] args) {
        new ConvertAnOrderedArrayIntoBinarySearchTree().answerOne();
    }

    /**
     * 解1:简单递归
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        TreeNode result = generateBinarySearchTree(nums, 0, nums.length - 1);
        System.out.println(result);
    }

    public TreeNode generateBinarySearchTree(int[] nums, int left, int right) {
        if (left <= right) {
            int mid = (left + right) / 2;
            TreeNode midNode = new TreeNode(nums[mid]);
            midNode.left = generateBinarySearchTree(nums, left, mid - 1);
            midNode.right = generateBinarySearchTree(nums, mid + 1, right);
            return midNode;
        }
        return null;
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{-10, -3, 0, 5, 9};
    }
}
