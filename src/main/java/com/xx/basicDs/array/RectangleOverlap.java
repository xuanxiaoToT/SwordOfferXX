package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/8
 * <p>
 * 矩形重叠
 * LeetCode 836
 * <p>
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 * 矩形的上下边平行于 x 轴，左右边平行于 y 轴。
 * 如果相交的面积为 正 ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 * 给出两个矩形 rec1 和 rec2 。如果它们重叠，返回 true；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：rec1 = [0,0,1,1], rec2 = [2,2,3,3]
 * 输出：false
 */
public class RectangleOverlap implements Answer {

    public static void main(String[] args) {
        new RectangleOverlap().answer();
    }

    /**
     * 解1：略
     */
    @Override
    public void answer() {
        int[] rec1 = new int[]{0, 0, 2, 2}, rec2 = new int[]{1, 1, 3, 3};
        System.out.println(isRectangleOverlap(rec1, rec2));
    }

    private boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
