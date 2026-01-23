package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/15
 * 最大的岛屿
 * leetCode 695  岛屿的最大面积
 * 海洋岛屿地图可以用由0、1组成的二维数组表示，水平
 * 或竖直方向相连的一组1表示一个岛屿，请计算最大的岛屿的面积
 * （即岛屿中1的数目）。
 */
public class LargestIsland implements Answer {

    public static void main(String[] args) {
        new LargestIsland().answer();
    }

    /**
     * 解：类似图的遍历。采用深度优先或者广度优先都可以。
     */
    @Override
    public void answer() {
        int[][] data = initData();
        int[][] flag = new int[data.length][data[0].length];
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1 && flag[i][j] == 0) {
                    // int temp = diGui(data, flag, i, j);
                    int temp = breadthSearch(data, flag, i, j);
                    max = Math.max(temp, max);
                }
            }
        }
        System.out.println(Arrays.deepToString(flag));
        System.out.println(max);
    }

    // 利用递归实现的深度优先遍历。这个也可以用栈来实现，请仔细思考树的遍历
    private int diGui(int[][] data, int[][] flag, int i, int j) {
        int sum = 0;
        // 越界
        if (i < 0 || i >= data.length || j < 0 || j >= data[0].length) {
            return sum;
        }
        // 已经访问过了
        if (flag[i][j] == 1 || data[i][j] == 0) {
            return sum;
        }
        //遍历当前点
        sum += 1;
        flag[i][j] = 1;
        // 往左
        int left = diGui(data, flag, i, j - 1);
        // 往右
        int right = diGui(data, flag, i, j + 1);
        // 往下
        int down = diGui(data, flag, i + 1, j);
        // 往上
        int up = diGui(data, flag, i - 1, j);
        return sum + left + right + down + up;
    }

    // 利用队列实现的广度优先遍历
    private int breadthSearch(int[][] data, int[][] flag, int i, int j) {
        int sum = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int tempI = temp[0];
            int tempJ = temp[1];
            if (flag[tempI][tempJ] == 0) {
                sum += 1;
                flag[tempI][tempJ] = 1;
            }
            // 上
            if (whether(tempI - 1, tempJ, data, flag)) {
                queue.add(new int[]{tempI - 1, tempJ});
            }
            // 下
            if (whether(tempI + 1, tempJ, data, flag)) {
                queue.add(new int[]{tempI + 1, tempJ});
            }
            // 左
            if (whether(tempI, tempJ - 1, data, flag)) {
                queue.add(new int[]{tempI, tempJ - 1});
            }
            // 右
            if (whether(tempI, tempJ + 1, data, flag)) {
                queue.add(new int[]{tempI, tempJ + 1});
            }
        }

        return sum;
    }

    private boolean whether(int i, int j, int[][] data, int[][] flag) {
        return i >= 0 && i < data.length && j >= 0 && j < data[0].length && data[i][j] == 1 && flag[i][j] == 0;
    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        return new int[][]{{1, 1, 0, 0, 1}, {1, 0, 0, 1, 0}, {1, 1, 0, 1, 0}, {0, 0, 1, 0, 0}};
    }
}