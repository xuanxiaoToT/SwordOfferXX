package com.xx.algorithm.recursion;

import com.xx.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 颜色填充
 * LeetCode Easy
 * <p>
 * 编写函数，实现许多图片编辑软件都支持的「颜色填充」功能。
 * 待填充的图像用二维数组 image 表示，元素为初始颜色值。初始坐标点的行坐标为 sr 列坐标为 sc。需要填充的新颜色为 newColor 。
 * 「周围区域」是指颜色相同且在上、下、左、右四个方向上存在相连情况的若干元素。
 * 请用新颜色填充初始坐标点的周围区域，并返回填充后的图像。
 * <p>
 * 示例：
 * 输入：
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出：[[2,2,2],[2,2,0],[2,0,1]]
 * 解释:
 * 初始坐标点位于图像的正中间，坐标 (sr,sc)=(1,1) 。
 * 初始坐标点周围区域上所有符合条件的像素点的颜色都被更改成 2 。
 * 注意，右下角的像素没有更改为 2 ，因为它不属于初始坐标点的周围区域。
 * <p>
 * 提示：
 * image 和 image[0] 的长度均在范围 [1, 50] 内。
 * 初始坐标点 (sr,sc) 满足 0 <= sr < image.length 和 0 <= sc < image[0].length 。
 * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535] 内。
 * <p>
 * Tag：深度优先遍历 广度优先遍历
 */
public class ColorFill implements Answer {
    public static void main(String[] args) {
        new ColorFill().answer();
    }

    @Override
    public void answer() {
        int[][] image = new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int sr = 1;
        int sc = 1;
        int newColor = 2;

        // int[][] image = new int[][]{{0, 0, 0}, {0, 1, 1}};
        // int sr = 1;
        // int sc = 1;
        // int newColor = 1;
        System.out.println(Arrays.deepToString(floodFill2(image, sr, sc, newColor)));
    }

    /**
     * 深度优先DFS
     */
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void dfs(int[][] image, int row, int col, int oldColor, int newColor) {
        if (row >= image.length || col >= image[0].length || row < 0 || col < 0) {
            return;
        }
        if (image[row][col] == newColor || image[row][col] != oldColor) {
            return;
        }
        image[row][col] = newColor;
        dfs(image, row - 1, col, oldColor, newColor);
        dfs(image, row + 1, col, oldColor, newColor);
        dfs(image, row, col - 1, oldColor, newColor);
        dfs(image, row, col + 1, oldColor, newColor);
    }

    /**
     * 广度优先遍历
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int hang = cur[0];
            int lie = cur[1];
            image[hang][lie] = newColor;
            if (hang >= 1 && image[hang - 1][lie] == oldColor) {
                queue.add(new int[]{hang - 1, lie});
            }
            if (hang < image.length - 1 && image[hang + 1][lie] == oldColor) {
                queue.add(new int[]{hang + 1, lie});
            }
            if (lie >= 1 && image[hang][lie - 1] == oldColor) {
                queue.add(new int[]{hang, lie - 1});
            }
            if (lie < image[0].length - 1 && image[hang][lie + 1] == oldColor) {
                queue.add(new int[]{hang, lie + 1});
            }
        }
        return image;
    }

    @Override
    public Object initData() {
        return null;
    }
}
