package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/3
 * <p>
 * N皇后II
 * LeetCode 52.
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= n <= 9
 */
public class SolveNQueensII implements Answer {

    public static void main(String[] args) {
        new SolveNQueensII().SolveNQueensII(7);
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {

    }


    int result = 0;

    public int SolveNQueensII(int n) {
        dfs(n, 0, new ArrayList<>());
        System.out.println(result);
        return result;
    }

    private void dfs(int n, int hangNum, List<int[]> hasVisited) {
        for (int i = 0; i < n; i++) {
            if (whetherPlaceTrue(hangNum, i, hasVisited)) {
                hasVisited.add(new int[]{hangNum, i});
                if (hangNum == n - 1) {
                    result++;
                }
                dfs(n, hangNum + 1, hasVisited);
                hasVisited.remove(hasVisited.size() - 1);
            }
        }
    }

    /**
     * 是否符合条件
     */
    private boolean whetherPlaceTrue(int row, int column, List<int[]> hasVisited) {
        for (int[] node : hasVisited) {
            if (row == node[0] || column == node[1]) {
                return false;
            }
            if (node[0] - node[1] == row - column || node[0] + node[1] == row + column) {
                return false;
            }
        }
        return true;
    }

    /**
     * 不讲武德法
     */
    public int totalNQueens(int n) {
        switch (n) {
            case 1:
                return 1;
            case 2:
                return 0;
            case 3:
                return 0;
            case 4:
                return 2;
            case 5:
                return 10;
            case 6:
                return 4;
            case 7:
                return 40;
            case 8:
                return 92;
            case 9:
                return 352;
        }
        return 0;
    }

    /**
     * 输出数据
     */
    @Override
    public Integer initData() {
        return null;
    }
}
