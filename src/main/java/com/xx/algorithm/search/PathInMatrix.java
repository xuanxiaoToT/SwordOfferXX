package com.xx.algorithm.search;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/17
 * 矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，
 * 每一步可以在矩阵中向左、右、上、下移动一格。
 * <p>
 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（
 * 路径中的字母用加粗标出）。
 * <p>
 * [["a","b","c","e"], ["s","f","c","s"], ["a","d","e","e"]]
 * <p>
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 */
public class PathInMatrix {

    private int maxRow;
    private int maxCol;

    public static void main(String[] args) {
        PathInMatrix pathInMatrix = new PathInMatrix();
        pathInMatrix.answerOne();
    }

    private void answerOne() {
        String target = "bcced";
        String[][] dataArray = initTestData();
        for (int i = 0; i < dataArray.length - 1; i++) {
            for (int j = 0; j < dataArray[0].length - 1; j++) {
                // 标记，已经遍历过的
                int[][] last = initLastPath(this.maxRow, this.maxCol);
                if (findPath(last, i, j, dataArray, target)) {
                    System.out.println(i + " " + j);
                    break;
                }
            }
        }
    }

    private String[][] initTestData() {
        String[][] result = {{"a", "b", "c", "e"}, {"s", "f", "c", "s"}, {"a", "d", "e", "e"}};
        this.maxRow = result.length;
        this.maxCol = result[0].length;
        return result;
    }

    private int[][] initLastPath(int rowCount, int colCount) {
        return new int[rowCount][colCount];
    }

    /**
     * 递归
     */
    private boolean findPath(int[][] lastPath, int rowId, int colId, String[][] matrix, String target) {
        //越界
        if (rowId >= this.maxRow || colId >= this.maxCol || rowId < 0 || colId < 0) {
            return false;
        }
        //之前此处已被遍历
        if (lastPath[rowId][colId] == 1) {
            return false;
        }
        lastPath[rowId][colId] = 1;
        if (target.length() == 1) {
            return matrix[rowId][colId].equals(target);
        } else {
            String first = target.substring(0, 1);
            String otherTarget = target.substring(1, target.length());
            if (matrix[rowId][colId].equals(first)) {
                boolean left = findPath(lastPath, rowId, colId - 1, matrix, otherTarget);
                boolean right = findPath(lastPath, rowId, colId + 1, matrix, otherTarget);
                boolean up = findPath(lastPath, rowId + 1, colId, matrix, otherTarget);
                boolean down = findPath(lastPath, rowId - 1, colId, matrix, otherTarget);
                return left || right || up || down;
            } else {
                return false;
            }
        }
    }
}