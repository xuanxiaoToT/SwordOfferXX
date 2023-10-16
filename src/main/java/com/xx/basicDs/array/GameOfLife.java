package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/10
 * <p>
 * 生命游戏
 * LeetCode 289. Medium
 * <p>
 * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 1.如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 2.如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 3.如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 4.如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * 给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 * <p>
 * 示例 1：
 * 输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * 输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * <p>
 * 示例 2：
 * 输入：board = [[1,1],[1,0]]
 * 输出：[[1,1],[1,1]]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] 为 0 或 1
 * <p>
 * 进阶：
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */
public class GameOfLife implements Answer {

    public static void main(String[] args) {
        new GameOfLife().answerTwo();
    }

    @Override
    public void answerOne() {
        //
        int[][] data = initData();
        //采用辅助数组，解法，略
        int[][] dp = new int[data.length][data[0].length];
    }


    /**
     * 如何原地改变呢
     * 原来如果为1，变为了0，就给其改为-1
     * 原来是0，变为了1，就给其改为-2
     * 遍历的第二遍，再刷回去。
     */
    public void answerTwo() {
        int[][] data = initData();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                //计算周围活细胞数
                int count = computeLiveCount(data, i, j);
                if (data[i][j] == 1 && (count < 2 || count > 3)) {
                    data[i][j] = -1;
                }
                if (data[i][j] == 0 && count == 3) {
                    data[i][j] = -2;
                }
            }
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == -1) {
                    data[i][j] = 0;
                }
                if (data[i][j] == -2) {
                    data[i][j] = 1;
                }
            }
        }
        System.out.println(Arrays.deepToString(data));
    }

    private int computeLiveCount(int[][] data, int oriCol, int oriRow) {
        int count = 0;
        for (int i = oriCol - 1; i <= oriCol + 1 && i < data.length; i++) {
            if (i < 0) {
                continue;
            }
            for (int j = oriRow - 1; j < data[0].length && j <= oriRow + 1; j++) {
                if (j < 0) {
                    continue;
                }
                if (!(i == oriCol && j == oriRow) && (data[i][j] == 1 || data[i][j] == -1)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int[][] initData() {
        return new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
    }
}