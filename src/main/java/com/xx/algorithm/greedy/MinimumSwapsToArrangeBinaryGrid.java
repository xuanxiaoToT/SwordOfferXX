package com.xx.algorithm.greedy;

import com.xx.Answer;

/**
 *
 * 排布二进制网格的最少交换次数
 * LeetCode  1536. Medium+
 * <p>
 * 给你一个 n x n 的二进制网格 grid，每一次操作中，你可以选择网格的 相邻两行 进行交换。
 * 一个符合要求的网格需要满足主对角线以上的格子全部都是 0 。
 * 请你返回使网格满足要求的最少操作次数，如果无法使网格符合要求，请你返回 -1 。
 * 主对角线指的是从 (1, 1) 到 (n, n) 的这些格子。
 * <p>
 * 示例 1：
 * 输入：grid = [[0,0,1],[1,1,0],[1,0,0]]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 * 输出：-1
 * 解释：所有行都是一样的，交换相邻行无法使网格符合要求。
 * <p>
 * 示例 3：
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,1]]
 * 输出：0
 * <p>
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 200
 * grid[i][j] 要么是 0 要么是 1 。
 * <p>
 * Tag：贪心算法
 */
public class MinimumSwapsToArrangeBinaryGrid implements Answer {
    public static void main(String[] args) {
        new MinimumSwapsToArrangeBinaryGrid().answer();
    }

    @Override
    public void answer() {
        // int[][] grid = {{0, 0, 1}, {1, 1, 0}, {1, 0, 0}};
        int[][] grid = {{1, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        System.out.println(minSwaps(grid));
    }

    public int minSwaps(int[][] grid) {
        int n = grid.length;
        // 1. 预处理每行右侧连续0的个数
        int[] zeros = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0)
                    count++;
                else
                    break;
            }
            zeros[i] = count;
        }

        // 2. 贪心选行，生成原行号数组
        boolean[] used = new boolean[n];
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int need = n - 1 - i;
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (!used[j] && zeros[j] >= need) {
                    used[j] = true;
                    arr[i] = j;
                    found = true;
                    break;
                }
            }
            if (!found)
                return -1;
        }

        // 3. 计算逆序对，得到最少交换次数
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j])
                    res++;
            }
        }
        return res;
    }

    @Override
    public Object initData() {
        return null;
    }
}
