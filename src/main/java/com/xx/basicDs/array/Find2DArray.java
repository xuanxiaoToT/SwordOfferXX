package com.xx.basicDs.array;

/**
 * @author 玄霄
 * @CreateDate 2022/6/14
 * 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Find2DArray {

    public static void main(String[] args) {
        Find2DArray find2DArray = new Find2DArray();
        //目标值
        int targetData = 9;
        find2DArray.answerTwo(targetData);
    }

    // 如果 左下角元素 大于了目标值，则目标值一定在该行的上方， 左下角元素 所在行可以消去。
    // 如果 左下角元素 小于了目标值，则目标值一定在该列的右方， 左下角元素 所在列可以消去。
    private void answerTwo(int targetData) {
        int[][] dataArray = initTestDate();
        int rowId = dataArray.length - 1;
        int colId = 0;
        while (colId < dataArray[0].length && rowId >= 0) {
            if (dataArray[rowId][colId] == targetData) {
                System.out.println(rowId + " " + colId);
                break;
            }
            if (dataArray[rowId][colId] > targetData) {
                rowId--;
            }
            if (dataArray[rowId][colId] < targetData) {
                colId++;
            }
        }
        System.out.println("AAA");
    }

    /**
     * 分别对行列进行二分查找
     */
    private void answerOne(int targetData) {
        int[][] dataArray = initTestDate();
        int left = 0;
        int right = dataArray[0].length - 1;
        int rowId = 0;
        int colId = 0;
        if (dataArray[0][right] < targetData) {
            colId = dataArray[0].length - 1;
        } else {
            while (left < right - 1) {
                int rowPoint = (left + right) / 2;
                if (dataArray[0][rowPoint] == targetData) {
                    System.out.println(0 + " " + rowPoint);
                }
                if (dataArray[0][rowPoint] > targetData) {
                    right = rowPoint;
                }
                if (dataArray[0][rowPoint] < targetData) {
                    left = rowPoint;
                }
            }
            colId = left;
        }

        int up = 0;
        int down = dataArray.length - 1;
        while (up <= down) {
            int rowPoint = (up + down) / 2;
            if (dataArray[rowPoint][colId] > targetData) {
                down = rowPoint - 1;
            }
            if (dataArray[rowPoint][colId] < targetData) {
                up = rowPoint + 1;
            }
            if (dataArray[rowPoint][colId] == targetData) {
                rowId = rowPoint;
                break;
            }
        }
        System.out.println(rowId + " " + colId);
    }

    private int[][] initTestDate() {
        //    [
        //   [1,   4,  7, 11, 15],
        //   [2,   5,  8, 12, 19],
        //   [3,   6,  9, 16, 22],
        //   [10, 13, 14, 17, 24],
        //   [18, 21, 23, 26, 30]
        // ]
        // 审题！
        int[][] testDate = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};

        System.out.println(testDate.toString());
        return testDate;
    }
}