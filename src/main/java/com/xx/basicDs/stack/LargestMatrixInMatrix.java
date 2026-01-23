package com.xx.basicDs.stack;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/13
 * <p>
 * 矩阵中的最大矩形
 * 最大矩形
 * LeetCode 85 Hard
 * <p>
 * 请在一个由0、1组成的矩阵中找出最大的只包含1的矩形
 * 并输出它的面积。
 * <p>
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * <p>
 * 示例 2：
 * 输入：matrix = [["0"]]
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：matrix = [["1"]]
 * 输出：1
 * <p>
 * 提示：
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= rows, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class LargestMatrixInMatrix implements Answer {
    public static void main(String[] args) {
        new LargestMatrixInMatrix().answer();
    }

    /**
     * 最简单的方式，挨个遍历，以每个1的值为左上角，计算围成矩形的面积，然后求最大即可。
     * 注意，每个左上角都要算横向和纵向两次
     * 复杂度O(N2)
     * ！！似乎不太可行！！
     */
    @Override
    public void answer() {
        // char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        char[][] matrix = new char[][]{{'1', '1', '0', '1'}, {'1', '1', '0', '1'}, {'1', '1', '1', '1'}};
        System.out.println(maximalRectangle(matrix));
    }

    /**
     * 记录下每列的前缀长，然后按行遍历，最长的行，乘以最短的列，就是当前的最大。
     */
    public int maximalRectangle(char[][] matrix) {
        // 表示i，j点处的上面列的高度
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = i >= 1 ? dp[i - 1][j] + 1 : 1;
                }
            }
        }
        int result = 0;
        // 这里可以优化成单调栈，参考{@link HistogramMaxRectangularArea} 直方图最大矩形面积
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // 计算i,j为最左下角时，其最大矩形是多少
                if (matrix[i][j] == '1') {
                    int hangSum = 1;
                    int min = dp[i][j];
                    result = Math.max(result, hangSum * min);
                    for (int k = j + 1; k < matrix[0].length && matrix[i][k] == '1'; k++) {
                        hangSum++;
                        min = Math.min(min, dp[i][k]);
                        result = Math.max(result, hangSum * min);
                    }
                }
            }
        }
        return result;
    }

    /**
     * something
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 0, 1, 0, 0}, {0, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};
    }
}
