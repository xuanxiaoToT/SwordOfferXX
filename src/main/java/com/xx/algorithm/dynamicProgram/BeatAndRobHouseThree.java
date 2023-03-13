package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/7
 * <p>
 * 打家劫舍 III
 * LeetCode 337
 * <p>
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
 * 聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * <p>
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * <p>
 * 示例 2:
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 */
public class BeatAndRobHouseThree implements Answer {

    public static void main(String[] args) {
        new BeatAndRobHouseThree().answerOne();
    }

    /**
     * 解1：采用动态规划？
     * 关键是要考虑两种情况：选了自己，没选自己。
     */
    @Override
    public void answerOne() {
        TreeNode root = initData();
        int[] maxNode = getMaxNode(root);
        System.out.println(Arrays.toString(maxNode));
    }

    private int[] getMaxNode(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] leftMax = getMaxNode(node.left);
        int[] rightMax = getMaxNode(node.right);

        // [选了自己，没选自己]
        return new int[]{node.value + leftMax[1] + rightMax[1],
                Math.max(leftMax[0], leftMax[1]) + Math.max(rightMax[0], rightMax[1])};
    }

    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
