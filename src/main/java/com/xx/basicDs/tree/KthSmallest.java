package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/17
 * <p>
 * 二叉搜索树中第K小的元素
 * LeetCode 230.
 * <p>
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，
 * 请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * 示例 1:
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * <p>
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */
public class KthSmallest implements Answer {

    public static void main(String[] args) {
        new KthSmallest().answerOne();
    }

    /**
     * 解1：因为是二叉搜索树，所以正常中序遍历即可。
     */
    @Override
    public void answerOne() {
        TreeNode root = initData();
        int k = 3;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        List<TreeNode> tempList = new ArrayList<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            tempList.add(cur);
            // 可以不用list来接，只用个count来计数即可。
            if (tempList.size() == k) {
                System.out.println(cur.val);
                return;
            }
            cur = cur.right;
        }
    }

    /**
     * 进阶：
     * 可以用map存储每个节点的子节点数量，这样如果左子树没变，请求的K值在左子树，则可以直接返回结果。
     * 具体规则如下：
     * 对当前结点 node  进行如下操作：
     * <p>
     * 如果 node 的左子树的结点数 left 小于 k−1k-1k−1，则第 k 小的元素一定在 node  的右子树中，令 node 等于其的右子结点，k 等于 k−left−1，并继续搜索；
     * 如果 node 的左子树的结点数 left 等于 k−1k-1k−1，则第 k 小的元素即为 node ，结束搜索并返回 node 即可；
     * 如果 node 的左子树的结点数 left 大于 k−1k-1k−1，则第 k 小的元素一定在 node  的左子树中，令 node 等于其左子结点，并继续搜索。
     */
    public void answerThree() {

    }

    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
