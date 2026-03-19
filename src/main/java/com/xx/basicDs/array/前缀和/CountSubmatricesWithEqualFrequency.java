package com.xx.basicDs.array.前缀和;

import com.xx.Answer;

/**
 * 统计X和Y频数相等的子矩阵数量
 * LeetCode 3212. Medium
 * <p>
 * 给你一个二维字符矩阵 grid，其中 grid[i][j] 可能是 'X'、'Y' 或 '.'，返回满足以下条件的子矩阵数量：
 * <p>
 * 包含 grid[0][0]
 * 'X' 和 'Y' 的频数相等。
 * 至少包含一个 'X'。
 * <p>
 * 示例 1：
 * 输入： grid = [["X","Y","."],["Y",".","."]]
 * 输出： 3
 * 解释：
 * <p>
 * 示例 2：
 * 输入： grid = [["X","X"],["X","Y"]]
 * 输出： 0
 * 解释：
 * 不存在满足 'X' 和 'Y' 频数相等的子矩阵。
 * <p>
 * 示例 3：
 * 输入： grid = [[".","."],[".","."]]
 * 输出： 0
 * 解释：
 * 不存在满足至少包含一个 'X' 的子矩阵。
 * <p>
 * 提示：
 * 1 <= grid.length, grid[i].length <= 1000
 * grid[i][j] 可能是 'X'、'Y' 或 '.'.
 * <p>
 * Tag：二维前缀和
 */
public class CountSubmatricesWithEqualFrequency implements Answer {
    public static void main(String[] args) {
        new CountSubmatricesWithEqualFrequency().answer();
    }

    @Override
    public void answer() {
        char[][] grid = {{'X', 'Y', '.'}, {'Y', '.', '.'}};
        System.out.println(numberOfSubmatrices(grid));
    }

    /**
     * 依旧二维矩阵前缀和
     * 同{@link TopLeftElementAndSumLessThank}
     */
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Node[][] preSum = new Node[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i][j] = new Node();
                int xCount = 0;
                int yCount = 0;
                if (grid[i][j] == 'Y') {
                    yCount++;
                }
                if (grid[i][j] == 'X') {
                    xCount++;
                }
                if (i > 0 && j > 0) {
                    preSum[i][j].yCount = yCount + preSum[i][j - 1].yCount + preSum[i - 1][j].yCount - preSum[i - 1][j - 1].yCount;
                    preSum[i][j].xCount = xCount + preSum[i][j - 1].xCount + preSum[i - 1][j].xCount - preSum[i - 1][j - 1].xCount;
                    continue;
                }
                if (i > 0) {
                    preSum[i][j].yCount = yCount + preSum[i - 1][j].yCount;
                    preSum[i][j].xCount = xCount + preSum[i - 1][j].xCount;
                    continue;
                }
                if (j > 0) {
                    preSum[i][j].yCount = yCount + preSum[i][j - 1].yCount;
                    preSum[i][j].xCount = xCount + preSum[i][j - 1].xCount;
                    continue;
                }
                if (i == 0 && j == 0) {
                    preSum[i][j].yCount = yCount;
                    preSum[i][j].xCount = xCount;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Node node = preSum[i][j];
                if (node.xCount == node.yCount && node.xCount > 0) {
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public Object initData() {
        return null;
    }

    public class Node {
        public int xCount = 0;
        public int yCount = 0;
    }
}
