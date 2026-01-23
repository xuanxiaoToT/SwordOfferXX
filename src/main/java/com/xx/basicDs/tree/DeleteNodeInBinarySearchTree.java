package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/8
 * <p>
 * 删除二叉搜索树中的节点
 * LeetCode 450. Medium
 * <p>
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * <p>
 * 示例 1:
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * <p>
 * 示例 3:
 * 输入: root = [], key = 0
 * 输出: []
 * <p>
 * 提示:
 * 节点数的范围 [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -10^5 <= key <= 10^5
 * <p>
 * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * <p>
 * Tag:树的遍历  树的节点删除
 */
public class DeleteNodeInBinarySearchTree implements Answer {
    public static void main(String[] args) {
        new DeleteNodeInBinarySearchTree().answer();
    }

    @Override
    public void answer() {
        TreeNode treeNode = DataFactory.generateTreeNode();
        System.out.println(deleteNode(treeNode, 13));
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            // root 有左右子树，这时可以将 root 的后继节点（比 root 大的最小节点，即它的右子树中的最小节点，记为 successor）作为新的根节点替代 root，
            // 并将 successor 从 root 的右子树中删除，使得在保持有序性的情况下合并左右子树。
            // 简单证明，successor位于 root 的右子树中，因此大于 root 的所有左子节点；successor 是 root 的右子树中的最小节点，因此小于 root 的右子树中的其他节点。
            // 以上两点保持了新子树的有序性。
            // 在代码实现上，我们可以先寻找 successor，再删除它。successor 是 root 的右子树中的最小节点，可以先找到 root 的右子节点，再不停地往左子节点寻找，
            // 直到找到一个不存在左子节点的节点，这个节点即为 successor。然后递归地在 root.right 调用 deleteNode 来删除 successor。
            // 因为 successor 没有左子节点，因此这一步递归调用不会再次步入这一种情况。然后将 successor 更新为新的 root 并返回。
            //
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            root.right = deleteNode(root.right, successor.val);
            successor.right = root.right;
            successor.left = root.left;
            return successor;
        }
        return root;
    }

    public TreeNode deleteNodeMy(TreeNode node, int target) {
        if (node == null) {
            return null;
        }
        if (node.val == target && (node.left == null || node.right == null)) {
            if (node.left == null) {
                return node.right;
            } else {
                return node.left;
            }
        }
        deleteBSTNode(node, null, 0, target);
        return node;
    }

    /**
     * （3）被删除的节点左右子树都有
     * 这种情况是比较复杂的，为了不破坏二叉查找书的结构，我们可以按照以下操作进行：
     * -找出左子树中最大或者右子树中最小的值val
     * -将当前节点的值替换为val
     * -在左子树或者右子树中找到val删除
     */
    public void deleteBSTNode(TreeNode node, TreeNode lastNode, int last, int target) {
        if (node == null) {
            return;
        }
        if (node.val == target) {
            // 要删除的是叶子节点
            if (node.left == null && node.right == null) {
                if (last == -1) {
                    lastNode.left = null;
                } else {
                    lastNode.right = null;
                }
            } else if (node.left == null || node.right == null) {
                if (last == -1) {
                    lastNode.left = node.right != null ? node.right : node.left;
                }
                if (last == 1) {
                    lastNode.right = node.left != null ? node.left : node.right;
                }
            } else {
                if (last == -1) {
                    // 左子树中找最大
                    node.val = findMaxNode(node.left, node);
                } else {
                    node.val = findMinNode(node.right, node);
                }
            }
        }
        if (node.val > target) {
            deleteBSTNode(node.left, node, -1, target);
        } else {
            deleteBSTNode(node.right, node, 1, target);
        }
    }

    /**
     * 右子树中找最小
     */
    private int findMinNode(TreeNode node, TreeNode lastNode) {
        int result = 0;
        TreeNode last = lastNode;
        if (node.left == null && node.right == null) {
            lastNode.right = null;
            return node.val;
        }
        while (node.left != null) {
            last = node;
            node = node.left;
        }
        last.left = node.right;
        result = node.val;
        return result;
    }

    /**
     * 左子树中找最大
     */
    private int findMaxNode(TreeNode node, TreeNode lastNode) {
        TreeNode last = lastNode;
        int result = 0;
        if (node.left == null && node.right == null) {
            lastNode.left = null;
            return node.val;
        }
        while (node.right != null) {
            last = node;
            node = node.right;
        }
        last.right = node.left;
        result = node.val;
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}