package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/25
 * <p>
 * 被围绕的区域
 * LeetCode 130 Medium
 * <p>
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，
 * 并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，
 * 或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 */
public class SurroundedArea implements Answer {

    public static void main(String[] args) {
        new SurroundedArea().answerOne();
    }

    /**
     * 解1：深度优先遍历，每一个O都是入口进行搜索，并标记。
     * 符合的条件为是否会遍历到边界。
     * 如果符合，则进行置换为X
     */
    @Override
    public void answerOne() {
        char[][] board = initData();
        int[][] flag = new int[board.length][board[0].length];
        int flagValue = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ('O' == board[i][j] && flag[i][j] == 0) {
                    boolean result = findSurroundedArea(board, flag, flagValue, i, j);
                    if (!result) {
                        modifyBoard(board, flag, flagValue);
                    }
                    flagValue++;
                }
            }
        }
        System.out.println(Arrays.deepToString(board));
    }

    /**
     * 解法1有冗余
     * <p>
     * 注意到题目解释中提到：任何边界上的 O 都不会被填充为 X。 我们可以想到，所有的不被包围的 O 都直接或间接与边界上的 O 相连。
     * 我们可以利用这个性质判断 O 是否在边界上，具体地说：
     * <p>
     * 1.对于每一个边界上的 O，我们以它为起点，标记所有与它直接或间接相连的字母 O；
     * 2.最后我们遍历这个矩阵，对于每一个字母：
     * --如果该字母被标记过，则该字母为没有被字母 X 包围的字母 O，我们将其还原为字母 O；
     * --如果该字母没有被标记过，则该字母为被字母 X 包围的字母 O，我们将其修改为字母 X。
     */
    public void answerTwo() {
        //解法略
    }

    private boolean findSurroundedArea(char[][] board, int[][] flag, int flagValue, int row, int col) {
        if (whetherCrossBorder(row, col, board, flag)) {
            if (row == 0 || row == board.length - 1 || col == 0 || col == board[0].length - 1) {
                return true;
            }
            flag[row][col] = flagValue;
            // 往左
            boolean left = findSurroundedArea(board, flag, flagValue, row, col - 1);
            // 往右
            boolean right = findSurroundedArea(board, flag, flagValue, row, col + 1);
            // 往下
            boolean down = findSurroundedArea(board, flag, flagValue, row + 1, col);
            // 往上
            boolean up = findSurroundedArea(board, flag, flagValue, row - 1, col);
            return left || right || down || up;
        }
        return false;
    }

    private boolean whetherCrossBorder(int i, int j, char[][] data, int[][] flag) {
        return i >= 0 && i < data.length && j >= 0 && j < data[0].length && data[i][j] == 'O' && flag[i][j] == 0;
    }

    private void modifyBoard(char[][] board, int[][] flag, int flagValue) {
        for (int i = 0; i < flag.length; i++) {
            for (int j = 0; j < flag[0].length; j++) {
                if (flag[i][j] == flagValue) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * 输出数据
     */
    @Override
    public char[][] initData() {
        return new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
    }
}
