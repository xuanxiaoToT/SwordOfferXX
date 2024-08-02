package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/2
 * <p>
 * 直角三角形
 * LeetCode 3128.  Medium
 * <p>
 * 给你一个二维 boolean 矩阵 grid 。
 * 请你返回使用 grid 中的 3 个元素可以构建的 直角三角形 数目，且满足 3 个元素值 都 为 1 。
 * <p>
 * 注意：
 * 如果 grid 中 3 个元素满足：一个元素与另一个元素在 同一行，同时与第三个元素在 同一列 ，那么这 3 个元素称为一个 直角三角形 。这 3 个元素互相之间不需要相邻。
 * <p>
 * 示例 1：
 * 输入：grid = [[0,1,0],[0,1,1],[0,1,0]]
 * 输出：2
 * <p>
 * 解释：
 * 有 2 个直角三角形。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,0,0,0],[0,1,0,1],[1,0,0,0]]
 * 输出：0
 * <p>
 * 解释：
 * 没有直角三角形。
 * <p>
 * 示例 3：
 * 输入：grid = [[1,0,1],[1,0,0],[1,0,0]]
 * <p>
 * 输出：2
 * 解释：
 * <p>
 * 有两个直角三角形。
 * <p>
 * 提示：
 * 1 <= grid.length <= 1000
 * 1 <= grid[i].length <= 1000
 * 0 <= grid[i][j] <= 1
 * <p>
 * Tag
 */
public class RightTriangle implements Answer {

    public static void main(String[] args) {
        new RightTriangle().answerOne();
    }

    @Override
    public void answerOne() {
        // int[][] grid = {{0, 1, 0}, {0, 1, 1}, {0, 1, 0}};
        // int[][] grid = {{1, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}};
        int[][] grid = {{1, 0, 1}, {1, 0, 0}, {1, 0, 0}};
        System.out.println(numberOfRightTriangles(grid));
    }

    /**
     * 改进：不要用map，效率很低
     * <p>
     * 考虑枚举两条直角边的交点，然后将「该点所在行的其他点」与「该点所在列的其他点」一一匹配，即可得到所有以该点为直角边交点的所有方案。
     * 设 row 为交点所在行 1 的个数，col 为交点所在列 1 的个数，那么它的贡献是 (row−1)×(col−1)，将所有交点的贡献加起来就是答案。
     */
    public long numberOfRightTriangles(int[][] grid) {
        //Map<Integer, Integer> hangMap = new HashMap<>();
        //Map<Integer, Integer> lieMap = new HashMap<>();
        int m = grid.length;
        int n = grid[0].length;
        int[] rowSum = new int[m];
        int[] colSum = new int[n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rowSum[i] += grid[i][j];
                    colSum[j] += grid[i][j];
                }
            }
        }
        long result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int hangCount = rowSum[i];
                    int lieCount = colSum[j];
                    result = (hangCount - 1) * (lieCount - 1) + result;
                }
            }
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
