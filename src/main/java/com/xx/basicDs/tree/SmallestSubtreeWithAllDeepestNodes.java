package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 具有所有最深节点的最小子树
 * LeetCode 865. Medium
 * <p>
 * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
 * 返回包含原始树中所有 最深节点 的 最小子树 。
 * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
 * 一个节点的 子树 是该节点加上它的所有后代的集合。
 * <p>
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：
 * 我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
 * <p>
 * 示例 2：
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点。
 * <p>
 * 示例 3：
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。
 * <p>
 * 提示：
 * 树中节点的数量在 [1, 500] 范围内。
 * 0 <= Node.val <= 500
 * 每个节点的值都是 独一无二 的。
 * <p>
 * Tag：树的深度  树的最近公共祖先
 */
public class SmallestSubtreeWithAllDeepestNodes implements Answer {
    TreeNode result = null;
    int maxDepth = 0;

    public static void main(String[] args) {
        new SmallestSubtreeWithAllDeepestNodes().answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(4);
        root.left = node1;
        node1.right = node4;
        node4.left = node5;
        node4.right = node6;
        root.right = node2;
        node2.left = node3;
        subtreeWithAllDeepest(root);
    }

    /**
     * 左右子树深度相同且是最大深度即 ans 为当前节点，对于叶子节点也适用。
     * 刚开始想到了，但是一直让result提前返回，会导致如果左右都有的时候，左边提前返回。
     * 实际上就是让他往上跑就得了，如果上面还有左右都相等，并且是最大深度的，那就应该是他
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    int dfs(TreeNode root, int curr) {
        if (root == null) {
            return curr - 1;
        }
        int leftDepth = dfs(root.left, curr + 1);
        int rightDepth = dfs(root.right, curr + 1);
        if (leftDepth == rightDepth && leftDepth >= maxDepth) {
            result = root;
            maxDepth = leftDepth;
        }
        return Math.max(leftDepth, rightDepth);
    }


    /**
     * 第一版思路：
     * 1.先求最大深度，然后记录最大深度的节点。
     * 2.用记录的最大深度进行遍历，遇到最大深度的节点就删除。如果此节点之前全的，左右删完后就没了，就说明他的左右包含的全部的最深节点，就是答案。
     */
    public TreeNode subtreeWithAllDeepestOld(TreeNode root) {
        Map<TreeNode, Integer> deepestMap = new HashMap<>();
        computeDeepestValue(root, 1, deepestMap);
        deepestMap.entrySet().removeIf(entry -> entry.getValue() != maxDepth);
        int size = deepestMap.size();
        if (size == 1) {
            return deepestMap.keySet().iterator().next();
        }
        traversalTree(root, deepestMap, size);
        return result == null ? root : result;
    }

    private void traversalTree(TreeNode root, Map<TreeNode, Integer> deepestMap, int size) {
        if (root == null) {
            return;
        }
        boolean flag = size == deepestMap.size();
        traversalTree(root.left, deepestMap, size);
        traversalTree(root.right, deepestMap, size);
        if (flag && deepestMap.isEmpty() && result == null) {
            result = root;
        }
        deepestMap.remove(root);
    }

    private void computeDeepestValue(TreeNode root, int deep, Map<TreeNode, Integer> deepestMap) {
        if (root == null) {
            maxDepth = Math.max(maxDepth, deep - 1);
            return;
        }
        computeDeepestValue(root.left, deep + 1, deepestMap);
        computeDeepestValue(root.right, deep + 1, deepestMap);
        deepestMap.put(root, deep);
    }

    @Override
    public Object initData() {
        return null;
    }
}
