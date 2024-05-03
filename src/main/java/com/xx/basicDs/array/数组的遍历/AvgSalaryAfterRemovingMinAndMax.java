package com.xx.basicDs.array.数组的遍历;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/3
 * <p>
 * 去掉最低工资和最高工资后的工资平均值
 * LeetCode 1491. Easy
 * <p>
 * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，
 * 其中 salary[i] 是第 i 个员工的工资。
 * 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
 * <p>
 * 示例 1：
 * 输入：salary = [4000,3000,1000,2000]
 * 输出：2500.00000
 * 解释：最低工资和最高工资分别是 1000 和 4000 。
 * 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
 * <p>
 * 示例 2：
 * 输入：salary = [1000,2000,3000]
 * 输出：2000.00000
 * 解释：最低工资和最高工资分别是 1000 和 3000 。
 * 去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000
 * <p>
 * 示例 3：
 * 输入：salary = [6000,5000,4000,3000,2000,1000]
 * 输出：3500.00000
 * <p>
 * 示例 4：
 * 输入：salary = [8000,9000,2000,3000,6000,1000]
 * 输出：4750.00000
 * <p>
 * 提示：
 * 3 <= salary.length <= 100
 * 10^3 <= salary[i] <= 10^6
 * salary[i] 是唯一的。
 * 与真实值误差在 10^-5 以内的结果都将视为正确答案。
 * <p>
 * Tag:数组的简单遍历
 */
public class AvgSalaryAfterRemovingMinAndMax implements Answer {

    public static void main(String[] args) {
        new AvgSalaryAfterRemovingMinAndMax().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {

    }

    public double average(int[] salary) {
        int max = 0;
        int min = salary[0];
        int sum = 0;
        for (int j : salary) {
            sum += j;
            max = Math.max(max, j);
            min = Math.min(min, j);
        }
        sum = sum - max - min;
        return sum * 1.0 / (salary.length - 2);
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
