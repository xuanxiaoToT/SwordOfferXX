package com.xx.temp;

import com.xx.Answer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 你能穿过矩阵的最后一天
 * LeetCode 1970. Hard
 * <p>
 * 给你一个下标从 1 开始的二进制矩阵，其中 0 表示陆地，1 表示水域。同时给你 row 和 col 分别表示矩阵中行和列的数目。
 * 一开始在第 0 天，整个 矩阵都是 陆地 。但每一天都会有一块新陆地被 水 淹没变成水域。给你一个下标从 1 开始的二维数组 cells ，
 * 其中 cells[i] = [ri, ci] 表示在第 i 天，第 ri 行 ci 列（下标都是从 1 开始）的陆地会变成 水域 （也就是 0 变成 1 ）。
 * 你想知道从矩阵最 上面 一行走到最 下面 一行，且只经过陆地格子的 最后一天 是哪一天。你可以从最上面一行的 任意 格子出发，
 * 到达最下面一行的 任意 格子。你只能沿着 四个 基本方向移动（也就是上下左右）。
 * <p>
 * 请返回只经过陆地格子能从最 上面 一行走到最 下面 一行的 最后一天 。
 * <p>
 * 示例 1：
 * 输入：row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
 * 输出：2
 * 解释：上图描述了矩阵从第 0 天开始是如何变化的。
 * 可以从最上面一行到最下面一行的最后一天是第 2 天。
 * <p>
 * 示例 2：
 * 输入：row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
 * 输出：1
 * 解释：上图描述了矩阵从第 0 天开始是如何变化的。
 * 可以从最上面一行到最下面一行的最后一天是第 1 天。
 * <p>
 * 示例 3：
 * 输入：row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
 * 输出：3
 * 解释：上图描述了矩阵从第 0 天开始是如何变化的。
 * 可以从最上面一行到最下面一行的最后一天是第 3 天。
 * <p>
 * 提示：
 * 2 <= row, col <= 2 * 10^4
 * 4 <= row * col <= 2 * 10^4
 * cells.length == row * col
 * 1 <= ri <= row
 * 1 <= ci <= col
 * cells 中的所有格子坐标都是 唯一 的。
 * <p>
 * Tag：并查集？ todo
 */
public class LastDayWhereYouCanStillCross implements Answer {
    public static void main(String[] args) {
        new LastDayWhereYouCanStillCross().answerOne();
    }

    @Override
    public void answerOne() {
        // int row = 2;
        // int col = 2;
        // int[][] cells = new int[][]{{1, 1}, {2, 1}, {1, 2}, {2, 2}};

        int row = 3;
        int col = 3;
        int[][] cells = new int[][]{{1, 2}, {2, 1}, {3, 3}, {2, 2}, {1, 1}, {1, 3}, {2, 3}, {3, 2}, {3, 1}};
        System.out.println(latestDayToCross(row, col, cells));
    }

    /**
     * 笨方法，每天都判断连通性
     * 超时~！
     * <p>
     * 改进：
     * 1.可以修改为二分查找。
     * 2.不要用point
     */
    public int latestDayToCross(int row, int col, int[][] cells) {
        // 优化：这里可以用二分查找，但是waterLand应该如何构建
        int left = 0;
        int right = cells.length - 1;
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            HashSet<Point> waterLand = new HashSet<>();
            for (int i = 0; i < mid; i++) {
                waterLand.add(new Point(cells[i][0], cells[i][1]));
            }
            if (whetherIsSuccess(waterLand, row, col)) {
                left = mid + 1;
                result = Math.max(result, mid);
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private boolean whetherIsSuccess(HashSet<Point> waterLand, int row, int col) {
        Queue<Point> queue = new LinkedList<>();
        for (int i = 1; i <= col; i++) {
            queueAddPoint(new Point(1, i), queue, waterLand);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point poll = queue.poll();
                if (poll.hang == row) {
                    return true;
                }
                // 同时作为 已经 遍历过的 记录set
                waterLand.add(poll);
                if (poll.hang > 1) {
                    queueAddPoint(new Point(poll.hang - 1, poll.lie), queue, waterLand);
                }
                if (poll.hang < row) {
                    queueAddPoint(new Point(poll.hang + 1, poll.lie), queue, waterLand);
                }
                if (poll.lie > 1) {
                    queueAddPoint(new Point(poll.hang, poll.lie - 1), queue, waterLand);
                }
                if (poll.lie < col) {
                    queueAddPoint(new Point(poll.hang, poll.lie + 1), queue, waterLand);
                }

            }
        }
        return false;
    }

    private void queueAddPoint(Point point, Queue<Point> queue, HashSet<Point> hashSet) {
        if (!hashSet.contains(point)) {
            queue.add(point);
            return;
        }
        return;
    }

    @Override
    public Object initData() {
        return null;
    }

    public static class Point {
        public int hang;

        public int lie;

        public Point(int hang, int lie) {
            this.hang = hang;
            this.lie = lie;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point point))
                return false;
            return hang == point.hang && lie == point.lie;
        }

        @Override
        public int hashCode() {
            return Objects.hash(hang, lie);
        }
    }
}
