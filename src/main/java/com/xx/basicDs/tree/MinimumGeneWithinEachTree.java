package com.xx.basicDs.tree;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/31
 * <p>
 * 每棵子树内缺失的最小基因值
 * LeetCode 2003 Hard
 * <p>
 * 有一棵根节点为 0 的 家族树 ，总共包含 n 个节点，节点编号为 0 到 n - 1 。给你一个下标从 0 开始的整数数组 parents ，
 * 其中 parents[i] 是节点 i 的父节点。由于节点 0 是 根 ，所以 parents[0] == -1 。
 * 总共有 105 个基因值，每个基因值都用 闭区间 [1, 105] 中的一个整数表示。给你一个下标从 0 开始的整数数组 nums ，
 * 其中 nums[i] 是节点 i 的基因值，且基因值 互不相同 。
 * 请你返回一个数组 ans ，长度为 n ，其中 ans[i] 是以节点 i 为根的子树内 缺失 的 最小 基因值。
 * 节点 x 为根的 子树 包含节点 x 和它所有的 后代 节点。
 * <p>
 * 示例 1：
 * 输入：parents = [-1,0,0,2], nums = [1,2,3,4]
 * 输出：[5,1,1,1]
 * 解释：每个子树答案计算结果如下：
 * - 0：子树包含节点 [0,1,2,3] ，基因值分别为 [1,2,3,4] 。5 是缺失的最小基因值。
 * - 1：子树只包含节点 1 ，基因值为 2 。1 是缺失的最小基因值。
 * - 2：子树包含节点 [2,3] ，基因值分别为 [3,4] 。1 是缺失的最小基因值。
 * - 3：子树只包含节点 3 ，基因值为 4 。1是缺失的最小基因值。
 * <p>
 * 示例 2：
 * 输入：parents = [-1,0,1,0,3,3], nums = [5,4,6,2,1,3]
 * 输出：[7,1,1,4,2,1]
 * 解释：每个子树答案计算结果如下：
 * - 0：子树内包含节点 [0,1,2,3,4,5] ，基因值分别为 [5,4,6,2,1,3] 。7 是缺失的最小基因值。
 * - 1：子树内包含节点 [1,2] ，基因值分别为 [4,6] 。 1 是缺失的最小基因值。
 * - 2：子树内只包含节点 2 ，基因值为 6 。1 是缺失的最小基因值。
 * - 3：子树内包含节点 [3,4,5] ，基因值分别为 [2,1,3] 。4 是缺失的最小基因值。
 * - 4：子树内只包含节点 4 ，基因值为 1 。2 是缺失的最小基因值。
 * - 5：子树内只包含节点 5 ，基因值为 3 。1 是缺失的最小基因值。
 * <p>
 * 示例 3：
 * 输入：parents = [-1,2,3,0,2,4,1], nums = [2,3,4,5,6,7,8]
 * 输出：[1,1,1,1,1,1,1]
 * 解释：所有子树都缺失基因值 1 。
 * <p>
 * 提示：
 * n == parents.length == nums.length
 * 2 <= n <= 10^5
 * 对于 i != 0 ，满足 0 <= parents[i] <= n - 1
 * parents[0] == -1
 * parents 表示一棵合法的树。
 * 1 <= nums[i] <= 10^5
 * nums[i] 互不相同。
 */
public class MinimumGeneWithinEachTree implements Answer {

    public static void main(String[] args) {
        new MinimumGeneWithinEachTree().answerOne();
    }

    @Override
    public void answerOne() {
        //int[] parents = new int[]{-1, 0, 0, 2};
        //int[] geneNums = new int[]{1, 2, 3, 4};
        //int[] parents = new int[]{-1, 0, 1, 0, 3, 3};
        //int[] geneNums = new int[]{5, 4, 6, 2, 1, 3};

        //int[] parents = new int[]{-1, 2, 3, 0, 2, 4, 1};
        //int[] geneNums = new int[]{2, 3, 4, 5, 6, 7, 8};

        int[] parents = new int[]{-1, 0, 0, 0, 2};
        int[] geneNums = new int[]{6, 4, 3, 2, 1};
        System.out.println(Arrays.toString(smallestMissingValueSubtree(parents, geneNums)));
    }


    /**
     * 题目都没说是二叉树,不能用TreeNode来建树!!
     * <p>
     * 对每一个子树单独遍历的解法：直接超时~！
     * fixme：超时
     * 正确解法参考{@link MinimumGeneWithinEachTreeByOfficialAnswer}
     */
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int[] result = new int[nums.length];
        if (Arrays.stream(nums).min().getAsInt() != 1) {
            Arrays.parallelSetAll(result, i -> 1);
            return result;
        }

        List<Integer>[] tree = new ArrayList[parents.length];
        Arrays.setAll(tree, e -> new ArrayList<>());
        for (int i = 1; i < parents.length; i++) {
            tree[parents[i]].add(i);
        }

        int max = Arrays.stream(nums).max().getAsInt();
        for (int i = 0; i < tree.length; i++) {
            List<Integer> sons = tree[i];
            HashSet<Integer> hasGene = new HashSet<>();
            myTreeTraversal(i, tree, hasGene, nums);
            int min = max + 1;
            for (int j = 1; j <= max; j++) {
                if (!hasGene.contains(j)) {
                    min = Math.min(min, j);
                }

            }
            result[i] = min;
        }
        return result;
    }

    private void myTreeTraversal(int i, List<Integer>[] tree, HashSet<Integer> hasGene, int[] nums) {
        hasGene.add(nums[i]);
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(tree[i]);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                Integer poll = queue.poll();
                hasGene.add(nums[poll]);
                if (!tree[poll].isEmpty()) {
                    queue.addAll(tree[poll]);
                }
            }
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}