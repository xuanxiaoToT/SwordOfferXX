package com.xx.basicDs.hash;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/5
 * 最小时间差
 * <p>
 * 给定一组范围在00：00至23：59的时间，求任意两个时
 * 间之间的最小时间差。
 * <p>
 * 例如，输入时间数组["23：50"，"23：59"，"00：00"]，
 * "23：59"和"00：00"之间只有1分钟的间隔，是最小的时间差。
 */
public class MinimumTimeDifference implements Answer {
    public static void main(String[] args) {
        new MinimumTimeDifference().answerOne();
    }

    /**
     * 1.最笨的方法，直接两两比较即可。O(N2)
     * 2.转换到一个数组上，全天共1440分钟，则创建一个1440的数组，然后遍历此数组，找出离得最近的即可。
     */
    @Override
    public void answerOne() {
        String[] strings = initData();
        String[] dict = new String[1441];
        for (String time : strings) {
            String[] split = time.split(":");
            int minSum = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            if (dict[minSum] != null) {
                System.out.println(0);
                return;
            }
            if (minSum == 0) {
                dict[1440] = time;
            }
            dict[minSum] = time;
        }

        int pre = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != null) {
                if (pre < 0) {
                    pre = i;
                } else {
                    min = Math.min(i - pre, min);
                    pre = i;
                }
            }
        }
        System.out.println(min);
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"23:50", "23:59", "00:00"};
    }
}