package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/28
 * <p>
 * 蛇梯棋
 * LeetCode 909  Hard
 * 时间花了一个小时~！！还是不熟练
 * <p>
 * 给你一个大小为 n x n 的整数矩阵 board ，方格按从 1 到 n2 编号，编号遵循 转行交替方式 ，
 * 从左下角开始 （即，从 board[n - 1][0] 开始）每一行交替方向。
 * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
 * 每一回合，玩家需要从当前方格 curr 开始出发，按下述要求前进：
 * 选定目标方格 next ，目标方格的编号符合范围 [curr + 1, min(curr + 6, n2)] 。
 * 该选择模拟了掷 六面体骰子 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。
 * 传送玩家：如果目标方格 next 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 next 。
 * 当玩家到达编号 n2 的方格时，游戏结束。
 * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
 * 编号为 1 和 n2 的方格上没有蛇或梯子。
 * <p>
 * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也 不能 继续移动。
 * <p>
 * 举个例子，假设棋盘是 [[-1,4],[-1,3]] ，第一次移动，玩家的目标方格是 2 。那么这个玩家将会顺着梯子到达方格 3 ，但 不能 顺着方格 3 上的梯子前往方格 4 。
 * 返回达到编号为 n2 的方格所需的最少移动次数，如果不可能，则返回 -1。
 * <p>
 * 示例 1：
 * 输入：board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
 * 输出：4
 * 解释：
 * 首先，从方格 1 [第 5 行，第 0 列] 开始。
 * 先决定移动到方格 2 ，并必须爬过梯子移动到到方格 15 。
 * 然后决定移动到方格 17 [第 3 行，第 4 列]，必须爬过蛇到方格 13 。
 * 接着决定移动到方格 14 ，且必须通过梯子移动到方格 35 。
 * 最后决定移动到方格 36 , 游戏结束。
 * 可以证明需要至少 4 次移动才能到达最后一个方格，所以答案是 4 。
 * <p>
 * 示例 2：
 * 输入：board = [[-1,-1],[-1,3]]
 * 输出：1
 * <p>
 * 提示：
 * n == board.length == board[i].length
 * 2 <= n <= 20
 * grid[i][j] 的值是 -1 或在范围 [1, n2] 内
 * 编号为 1 和 n2 的方格上没有蛇或梯子
 */
public class SnakesAndLadders implements Answer {

    public static void main(String[] args) {
        new SnakesAndLadders().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        int[][] board = initData();
        System.out.println(snakesAndLadders(board));
        //System.out.println(Arrays.toString(computeNextBySnakesAndLadders(19, board)));
    }

    /**
     * 广度优先遍历，每一个节点，其下一步可能到达的节点，全部加到queue中。
     */
    public int snakesAndLadders(int[][] board) {
        int stepCount = 0;
        int target = (board.length) * (board.length);
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> hasVisited = new HashSet<>();
        queue.add(1);
        hasVisited.add(1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                if (poll == target) {
                    return stepCount;
                }
                List<Integer> nextNodeList = findNextNodeList(poll, board, hasVisited);
                for (Integer nextInt : nextNodeList) {
                    queue.add(nextInt);
                }
            }
            stepCount++;
        }
        return -1;
    }

    /**
     * 犯错点：连环跳的时候没考虑，导致出错。
     * poll本身是能继续跳的，这是一点。
     * 第二点，是hasVisited判断时，不应该放开始，而是先确定跳的位置，再确定是否add。
     */
    private List<Integer> findNextNodeList(int poll, int[][] board, HashSet<Integer> hasVisited) {
        List<Integer> result = new ArrayList<>();
        int maxSteps = Math.min(poll + 6, board.length * board.length);
        for (int i = poll + 1; i <= maxSteps; i++) {
            int[] nextNode = computeNextBySnakesAndLadders(i, board);
            int nextValByLadder = board[nextNode[0]][nextNode[1]];
            if (nextValByLadder != -1) {
                if (!hasVisited.contains(nextValByLadder)) {
                    result.add(nextValByLadder);
                    hasVisited.add(nextValByLadder);
                }
            } else {
                if (!hasVisited.contains(i)) {
                    result.add(i);
                    hasVisited.add(i);
                }
            }
        }
        return result;
    }

    /**
     * 给定确定值，求出其在board中的位置。
     * 这个方法写的有点慢~！
     */
    private int[] computeNextBySnakesAndLadders(int val, int[][] board) {
        int n = board.length;
        int[] result = new int[2];
        int lastLine = (int) Math.ceil(val * 1.0 / n);
        int row = val % n;
        if (lastLine % 2 == 0) {
            // 逆着。右往左
            result[0] = n - lastLine;
            result[1] = row == 0 ? 0 : n - row;
        } else {
            // 顺着。左往右
            result[0] = n - lastLine;
            result[1] = row == 0 ? n - 1 : row - 1;
        }
        return result;
    }

    /**
     * 官解：先减1，在计算。
     */
    private int[] computeNextBySnakesAndLadders2(int val, int[][] board) {
        int n = board.length;
        int r = (val - 1) / n, c = (val - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }

    /**
     * 输出数据
     */
    @Override
    public int[][] initData() {
        //[[1,1,-1],[1,1,1],[-1,1,1]]
        //return new int[][]{{-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1},
        //        {-1, 35, -1, -1, 13, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 15, -1, -1, -1, -1}};
        //return new int[][]{{1, 1, -1}, {1, 1, 1}, {-1, 1, 1}};
        //return new int[][]{{-1, -1, 2, -1}, {14, 2, 12, 3}, {4, 9, 1, 11}, {-1, 2, 1, 16}};
        return new int[][]{{-1, -1, -1, 46, 47, -1, -1, -1}, {51, -1, -1, 63, -1, 31, 21, -1}, {-1, -1, 26, -1, -1, 38, -1, -1}, {-1, -1, 11, -1, 14, 23, 56, 57}, {11, -1, -1, -1, 49, 36, -1, 48},
                {-1, -1, -1, 33, 56, -1, 57, 21}, {-1, -1, -1, -1, -1, -1, 2, -1}, {-1, -1, -1, 8, 3, -1, 6, 56}};
    }


}
