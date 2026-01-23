package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/6
 * <p>
 * 叶子相似的树
 * LeetCode 872. Easy+
 * <p>
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 * <p>
 * 示例 1：
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 * <p>
 * 提示：
 * 给定的两棵树结点数在 [1, 200] 范围内
 * 给定的两棵树上的值在 [0, 200] 范围内
 * <p>
 * Tag:树的遍历
 */
public class TreesWithSimilarLeaf implements Answer {

    public static void main(String[] args) {
        new TreesWithSimilarLeaf().answer();
    }

    @Override
    public void answer() {
        TreeNode root1 = DataFactory.generateTreeNode();
        System.out.println(whetherSimilar(root1, root1));
    }

    // 叶子节点相似的树
    public boolean whetherSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        midTransverse(root1, list1);
        midTransverse(root2, list2);
        return list1.equals(list2);
    }

    private void midTransverse(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
        }
        midTransverse(root.left, list);
        midTransverse(root.right, list);
    }


    @Override
    public Object initData() {
        return null;
    }
}
