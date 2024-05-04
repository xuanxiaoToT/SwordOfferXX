package com.xx.algorithm.dynamicProgram;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/4
 * <p>
 * 规划兼职工作
 * LeetCode 1235. Hard
 * <p>
 * 你打算利用空闲时间来做兼职工作赚些零花钱。
 * 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
 * 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，
 * 请你计算并返回可以获得的最大报酬。
 * 注意，时间上出现重叠的 2 份工作不能同时进行。
 * 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
 * <p>
 * 示例 1：
 * 输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * 输出：120
 * 解释：
 * 我们选出第 1 份和第 4 份工作，
 * 时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
 * <p>
 * 示例 2：
 * 输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * 输出：150
 * 解释：
 * 我们选择第 1，4，5 份工作。
 * 共获得报酬 150 = 20 + 70 + 60。
 * <p>
 * 示例 3：
 * 输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * 输出：6
 * <p>
 * 提示：
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 * <p>
 * Tag：注意值的范围   注意dp是对什么dp
 */
public class PlanningPartTimeJobs implements Answer {
    public static void main(String[] args) {
        new PlanningPartTimeJobs().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        //int[] startTime = {1, 2, 3, 4, 6};
        //int[] endTime = {3, 5, 10, 6, 9};
        //int[] profit = {20, 20, 100, 70, 60};
        int[] startTime = {6, 15, 7, 11, 1, 3, 16, 2};
        int[] endTime = {19, 18, 19, 16, 10, 8, 19, 8};
        int[] profit = {2, 9, 1, 19, 5, 7, 3, 19};
        System.out.println(jobScheduling(startTime, endTime, profit));
    }

    /**
     * 超出内存限制！
     * 注意：读题时就应该注意到endTime的最大值！
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] list = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            list[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(list, Comparator.comparing(dto -> dto[1]));
        int max = list[endTime.length - 1][1];
        int[] dp = new int[max + 1];
        for (int i = 0; i < list.length; i++) {
            int[] data = list[i];
            int right = data[1];
            int left = data[0];
            int gain = data[2];
            dp[right] = Math.max(dp[left] + gain, dp[right]);
            int next = dp.length - 1;
            if (i + 1 < list.length) {
                next = list[i + 1][1];
            }
            for (int j = right + 1; j <= next; j++) {
                dp[j] = Math.max(dp[right], dp[j]);
            }
        }
        return dp[max];
    }


    /**
     * 动态规划 + 二分查找
     * dp[i]表示第i个工作时，可以取得的最大利润。
     */
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 上一个endTime小于本left的工作的最大值。由于endTime有序
            int k = binarySearch(jobs, i - 1, jobs[i - 1][0]);
            // 这份工作不选，上一个endTime小于本left的工作的最大值+这份工作的利润。
            dp[i] = Math.max(dp[i - 1], dp[k] + jobs[i - 1][2]);
        }
        return dp[n];
    }

    // 返回 endTime <= upper 的最大下标
    public int binarySearch(int[][] jobs, int right, int upper) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (jobs[mid][1] > upper) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
