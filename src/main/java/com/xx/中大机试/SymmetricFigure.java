package com.xx.中大机试;

import com.xx.Answer;
import com.xx.algorithm.other.RotateImage;
import com.xx.basicDs.array.MatrixSetZeroes;
import com.xx.basicDs.array.SpiralMatrix;


/**
 * 对称图形
 * <p>
 * 小贝有一个矩形板，矩形板由N行M列的单位方块组成。每个单位方块要么是白色的要么是黑色的。小贝想知道矩形板是否是水平对称或者竖直对称的，你能帮助她吗？
 * 所谓水平对称，是指如果将矩形板沿着其水平中轴线翻转，得到的矩形板和原来一样。
 * 具体地说，如果N是奇数，水平中轴线是指第(N+1)/2行方块的中线；
 * 如果N是偶数，水平中轴线是指在第N/2行和第N/2+1行方块之间的直线。竖直对称的定义类似。
 * <p>
 * 输入:
 * 输入的第一行是一个整数T（T <= 100），代表输入数据的组数。每组数据的第一行是两个整数N和M（1 <= N, M <= 50），
 * 接下来的N行，每行有M个字符，每个字符是`.`或者`*`，分别表示对应方块是白色或者黑色。
 * <p>
 * 对于每组数据，输出独占一行，
 * 如果矩形板既是水平对称又是竖直对称，输出 `Both`；
 * 如果矩形板只是水平对称，输出 `Horizontally symmetric`；
 * 如果矩形板只是竖直对称，输出 `Vertically symmetric`；
 * 如果矩形板不对称，输出 `Neither`。
 * <p>
 * 矩阵类题目：
 * 旋转矩阵: {@link RotateImage}
 * 螺旋矩阵: {@link SpiralMatrix}
 * 矩阵置零：{@link MatrixSetZeroes}
 *
 */
public class SymmetricFigure implements Answer {

    public static void main(String[] args) {
        new SymmetricFigure().answer();
    }

    @Override
    public void answer() {
        // int m = 1;
        // int n = 1;
        // char[][] input = {{'*'}};

        // int m = 2;
        // int n = 3;
        // char[][] input = {{'.', '*', '.'}, {'*', '*', '*'}};

        // int m = 3;
        // int n = 3;
        // char[][] input = {{'*', '.', '.'}, {'.', '*', '.'}, {'.', '.', '*'}};

        int m = 2;
        int n = 2;
        char[][] input = {{'*', '.'}, {'*', '.'}};
        System.out.println(computeSymmetricFigure(m, n, input));
    }

    String computeSymmetricFigure(int m, int n, char[][] input) {
        // 判断他是否 水平对称  布尔值
        boolean horizontally = computeHorizontally(input);
        // 判断他是否 垂直对称
        boolean vertically = computeVertically(input);
        if (horizontally && vertically) {
            return "Both";
        }
        if (!horizontally && !vertically) {
            return "Neither";
        }
        if (horizontally) {
            return "Horizontally symmetric";
        }
        return "Vertically symmetric";
    }

    // 水平对称
    private boolean computeHorizontally(char[][] input) {
        int top = 0;
        int bottom = input.length - 1;
        if (top == bottom) {
            return true;
        }
        while (top < bottom) {
            // 按列遍历
            for (int i = 0; i < input[0].length; i++) {
                if (input[top][i] != input[bottom][i]) {
                    return false;
                }
            }
            top++;
            bottom--;
        }
        return true;
    }

    // 垂直对称
    private boolean computeVertically(char[][] input) {
        int left = 0;
        int right = input[0].length - 1;
        if (left == right) {
            return true;
        }

        while (left < right) {
            for (int i = 0; i < input.length; i++) {
                if (input[i][left] != input[i][right]) {
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }

    @Override
    public Object initData() {
        return null;
    }
}
