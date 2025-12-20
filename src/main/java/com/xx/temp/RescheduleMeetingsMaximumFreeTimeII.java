package com.xx.temp;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2025/7/10
 */
public class RescheduleMeetingsMaximumFreeTimeII implements Answer {

    public static void main(String[] args) {
        RescheduleMeetingsMaximumFreeTimeII rescheduleMeetingsMaximumFreeTimeII = new RescheduleMeetingsMaximumFreeTimeII();
        rescheduleMeetingsMaximumFreeTimeII.answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        //int eventTime = 5;
        //int[] startTime = new int[]{1, 3};
        //int[] endTime = new int[]{2, 5};

        //int eventTime = 10;
        //int[] startTime = new int[]{0, 7, 9};
        //int[] endTime = new int[]{1, 8, 10};

        //int eventTime = 10;
        //int[] startTime = new int[]{0,3,7,9};
        //int[] endTime = new int[]{1,4,8,10};

        int eventTime = 5;
        int[] startTime = new int[]{0, 1, 2, 3, 4};
        int[] endTime = new int[]{1, 2, 3, 4, 5};

        System.out.println(maxFreeTime(eventTime, startTime, endTime));
    }

    /**
     * 只能重排一个
     */
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int result = 0;

        List<int[]> temp = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            temp.add(new int[]{startTime[i], endTime[i]});
        }
        temp.sort(Comparator.comparing(dto -> dto[0]));
        int last = 0;

        for (int[] ints : temp) {
            result = result + (ints[0] - last);
            last = ints[1];
        }
        result += eventTime - temp.get(temp.size() - 1)[1];
        return result;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
