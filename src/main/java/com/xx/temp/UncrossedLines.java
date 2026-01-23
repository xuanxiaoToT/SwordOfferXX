package com.xx.temp;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/11
 * <p>
 * 不相交的线
 * LeetCode 1035. Medium
 * <p>
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：
 * nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
 * 输出：2
 * 解释：可以画出两条不交叉的线，如上图所示。
 * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
 * <p>
 * 示例 2：
 * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums1.length, nums2.length <= 500
 * 1 <= nums1[i], nums2[j] <= 2000
 * <p>
 * Tag: 动态规划
 */
public class UncrossedLines implements Answer {

    public static void main(String[] args) {
        new UncrossedLines().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        //int[] nums1 = {1, 4, 2};
        //int[] nums2 = {1, 2, 4};
        //int[] nums1 = {2, 5, 1, 2, 5};
        //int[] nums2 = {10, 5, 2, 1, 5, 2};
        int[] nums1 = {1, 3, 7, 1, 7, 5};
        int[] nums2 = {1, 9, 2, 5, 1};
        System.out.println(maxUncrossedLines2(nums1, nums2));
    }

    // 回溯法，超时~！
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            int num = nums2[i];
            if (map.containsKey(num)) {
                map.get(num).add(i);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                map.put(num, temp);
            }
        }
        myDiGui(nums1, map, 0, -1, 0);
        return result;
    }

    private int result = 0;

    private void myDiGui(int[] nums1, Map<Integer, List<Integer>> map, int index, int right, int count) {
        if (index >= nums1.length) {
            result = Math.max(count, result);
            return;
        }
        int num = nums1[index];
        List<Integer> list = map.get(num);
        if (list == null) {
            myDiGui(nums1, map, index + 1, right, count);
        } else {
            for (int i = 0; i < list.size(); i++) {
                int nextRight = list.get(i);
                // 本次不划线
                myDiGui(nums1, map, index + 1, right, count);
                // 本次划线
                if (nextRight > right) {
                    myDiGui(nums1, map, index + 1, nextRight, count + 1);
                }
            }
        }
    }

    // 记住：只求明细，不求详情的。回溯法可以做的，都可以转DP
    //定义dp[i][j]为nums1前i个数和nums2前j个数所能连接的最大线条数目
    public int maxUncrossedLines2(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    if (i > 0 && j > 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    if (i > 0 && j > 0) {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i][j]);
                    } else {
                        if (i > 0) {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                        }
                        if (j > 0) {
                            dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[nums1.length - 1][nums2.length - 1];
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
