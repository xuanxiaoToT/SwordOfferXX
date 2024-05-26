package com.xx.basicDs.number.位运算;

import com.xx.Answer;

import java.util.PriorityQueue;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/26
 * <p>
 * 找出第K大的异或坐标值
 * LeetCode 1738 Medium
 * <p>
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）
 * 执行异或运算得到。
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * <p>
 * 示例 1：
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * <p>
 * 示例 2：
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * <p>
 * 示例 3：
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * <p>
 * 示例 4：
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 10^6
 * 1 <= k <= m * n
 * <p>
 * Tag:前缀和  临时表  异或的性质  最小堆
 */
public class FindKthLargestXorCoordinateValue implements Answer {

    public static void main(String[] args) {
        new FindKthLargestXorCoordinateValue().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 4;
        System.out.println(kthLargestValue(matrix, k));
    }

    /**
     * 预存临时结果，到二维表result中。假设其为result
     * 可得result[i][j] = result[i - 1][j] ^ result[i - 1][j - 1] ^ result[i][j - 1] ^ matrix[i][j];
     * 这里用了一个异或的自反性质：即 a = b ^ a ^ b
     * 可知result[i - 1][j] ^ result[i - 1][j - 1] ^ result[i][j - 1]所代表的左上角共同其余，被计算了三次，本质上2次抵消。
     * 只有1次参与计算。
     */
    public int kthLargestValue(int[][] matrix, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        int[][] result = new int[matrix.length][matrix[0].length];
        int sum = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            result[0][i] = sum ^ matrix[0][i];
            sum = result[0][i];
            addHeap(minHeap, result[0][i], k);
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0) {
                    result[i][j] = result[i - 1][j] ^ matrix[i][j];
                } else {
                    result[i][j] = result[i - 1][j] ^ result[i - 1][j - 1] ^ result[i][j - 1] ^ matrix[i][j];
                }
                addHeap(minHeap, result[i][j], k);
            }
        }
        return minHeap.peek();
    }

    private void addHeap(PriorityQueue<Integer> minHeap, int val, int k) {
        if (minHeap.size() < k) {
            minHeap.add(val);
        } else {
            Integer poll = minHeap.poll();
            minHeap.add(Math.max(poll, val));
        }
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
