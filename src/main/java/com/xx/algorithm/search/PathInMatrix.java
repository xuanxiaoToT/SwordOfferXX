package com.xx.algorithm.search;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/17
 * <p>
 * 矩阵中的路径
 * LeetCode 79 单词搜索 Medium
 * <p>
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，
 * 每一步可以在矩阵中向左、右、上、下移动一格。
 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * <p>
 * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * [["a","b","c","e"], ["s","f","c","s"], ["a","d","e","e"]]
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 * <p>
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 * <p>
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * <p>
 * <p>
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 * 可以用前缀树来进行startWith判断，快速减枝
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
     * 优化：lastPath可以重复利用，使得只需要初始化一次即可
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
                lastPath[rowId][colId] = 0;
                return left || right || up || down;
            } else {
                //优化：lastPath可以重复利用，使得只需要初始化一次即可
                lastPath[rowId][colId] = 0;
                return false;
            }
        }
    }
}