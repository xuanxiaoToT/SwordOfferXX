package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/7/22
 * <p>
 * 特定深度节点链表
 * LeetCode Medium
 * <p>
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 * <p>
 * 示例：
 * 输入：[1,2,3,4,5,null,7,8]
 * <p>
 * 1
 * /  \
 * 2    3
 * / \    \
 * 4   5    7
 * /
 * 8
 * 输出：[[1],[2,3],[4,5,7],[8]]
 * <p>
 * Tag：树的层序遍历   链表
 */
public class ListOfDepth implements Answer {

    public static void main(String[] args) {
        new ListOfDepth().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        TreeNode treeNode = DataFactory.generateTreeNode();
        System.out.println(Arrays.toString(listOfDepth(treeNode)));
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode point = null;
            ListNode first = null;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (point == null) {
                    point = new ListNode(poll.val, null);
                    first = point;
                } else {
                    ListNode listNode = new ListNode(poll.val, null);
                    point.next = listNode;
                    point = point.next;
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            point.next = null;
            result.add(first);
        }
        return result.toArray(new ListNode[]{});
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
