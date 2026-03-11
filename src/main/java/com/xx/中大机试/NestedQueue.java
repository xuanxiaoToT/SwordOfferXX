package com.xx.中大机试;

import java.util.Arrays;
import java.util.Comparator;

public class NestedQueue {
    public static void main(String[] args) {
        NestedQueue nestedQueue = new NestedQueue();
        int[] nums = {1, 2, 2, 1, 3, 3};
        // int[] nums = {1, 2, 1, 2};
        // int[] nums = {1, 1, 1, 1};
        System.out.println(nestedQueue.whetherNestedQueue(nums));
    }

    public String whetherNestedQueue(int[] nums) {
        if (nums.length % 2 != 0) {
            return "NO";
        }
        int n = nums.length / 2;
        Integer[][] record = new Integer[n][2];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int recordIndex = num - 1;
            if (record[recordIndex][0] == null) {
                record[recordIndex][0] = i;
                continue;
            }
            if (record[recordIndex][1] == null) {
                record[recordIndex][1] = i;
                continue;
            }
            if (record[recordIndex][1] != null && record[recordIndex][0] != null) {
                return "NO";
            }
        }
        Arrays.sort(record, Comparator.comparing(dto -> dto[0]));
        for (int i = 0; i < record.length; i++) {
            Integer[] point = record[i];
            int left = point[0];
            int right = point[1];
            for (int j = i + 1; j < record.length; j++) {
                Integer[] next = record[j];
                if (next[0] > right) {
                    break;
                }
                if (next[0] < right && next[1] > right) {
                    return "NO";
                }
            }
        }
        return "YES";
    }

}
