package com.xx.algorithm.backTracking;

import com.xx.Answer;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/7
 * <p>
 * N 皇后
 * LeetCode 51.
 * <p>
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，
 * 该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 */
public class SolveNQueens implements Answer {

    public static void main(String[] args) {
        new SolveNQueens().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        Integer n = initData();
        List<List<String>> result = new ArrayList<>();
        List<String> tempResult = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String temp = ".".repeat(n);
            tempResult.add(temp);
        }

        nQueens(tempResult, 0, new HashSet<>(n), result, n, 0);
        System.out.println(result);
    }

    private void nQueens(List<String> tempResult, int row, HashSet<Point> notAllowedPoints, List<List<String>> result, int n, int queenNum) {
        if (queenNum == n) {
            result.add(new ArrayList<>(tempResult));
            return;
        }

        if (row < 0 || row >= n) {
            return;
        }

        // 选本行作为皇后，对于每一列有
        for (int column = 0; column < n; column++) {
            if (whetherPlaceTrue(row, column, notAllowedPoints)) {
                String record = tempResult.get(row);
                String tempCol = tempResult.get(row);
                StringBuilder sbTemp = new StringBuilder(tempCol);
                sbTemp.setCharAt(column, 'Q');
                tempResult.set(row, sbTemp.toString());
                notAllowedPoints.add(new Point(row, column));

                // 本行已选，下一行看看
                nQueens(tempResult, row + 1, notAllowedPoints, result, n, queenNum + 1);

                // 复原
                notAllowedPoints.remove(new Point(row, column));
                tempResult.set(row, record);
            }
        }

    }

    /**
     * 是否符合条件
     */
    private boolean whetherPlaceTrue(int row, int column, HashSet<Point> notAllowedPoints) {
        for (Point notAllowedPoint : notAllowedPoints) {
            if (row == notAllowedPoint.row || column == notAllowedPoint.column) {
                return false;
            }
            //当两个数组元素的行坐标和列坐标，各自进行相减或相加所得的结果一样时，则它们在同一对角线上面
            if (notAllowedPoint.row - notAllowedPoint.column == row - column || notAllowedPoint.row + notAllowedPoint.column == row + column) {
                return false;
            }
        }
        return true;
    }

    /**
     * 产生斜对角的坐标集合。
     */
    private void generateDiagonalCoordinates(int row, int column) {

    }

    @AllArgsConstructor
    public static class Point {
        public int row;

        public int column;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row && column == point.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }

    /**
     * 输出数据
     */
    @Override
    public Integer initData() {
        return 4;
    }
}
