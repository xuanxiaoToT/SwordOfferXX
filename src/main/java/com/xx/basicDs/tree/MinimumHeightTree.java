package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

/**
 * @author XuanXiao
 * @CreateDate 2024/7/16
 */
public class MinimumHeightTree implements Answer {

    public static void main(String[] args) {
        new MinimumHeightTree().answerOne();
    }

    @Override
    public void answerOne() {
        // int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] nums = new int[]{1, 2};
        System.out.println(buildMinHeightTree(nums));
    }

    public TreeNode buildMinHeightTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int left = 0;
        int right = nums.length - 1;
        return createTreeNode(nums, left, right);
    }

    private TreeNode createTreeNode(int[] nums, int left, int right) {
        if (left > right || left < 0 || right >= nums.length) {
            return null;
        }
        int midIndex = (left + right) / 2;
        TreeNode mid = new TreeNode(nums[midIndex], null, null);
        TreeNode leftNode = createTreeNode(nums, left, midIndex - 1);
        TreeNode rightNode = createTreeNode(nums, midIndex + 1, right);
        mid.left = leftNode;
        mid.right = rightNode;
        return mid;
    }


    @Override
    public Object initData() {
        return null;
    }
}
