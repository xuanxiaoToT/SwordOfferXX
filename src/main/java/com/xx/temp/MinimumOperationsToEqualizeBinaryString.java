package com.xx.temp;

import com.xx.Answer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumOperationsToEqualizeBinaryString implements Answer {
    public static void main(String[] args) {
        new MinimumOperationsToEqualizeBinaryString().answer();
    }

    @Override
    public void answer() {
        String s = "0101";
        int k = 3;
        System.out.println(minOperations(s, k));
    }

    /**
     * 用0的个数表示状态数，然后进行状态转换就是图的便利即可
     * 关键是建模
     */
    public int minOperations(String s, int k) {
        int length = s.length();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i <= length; i++) {
            if (i == 0) {
                map.put(i, null);
            } else {
                // i代表0的个数
                Set<Integer> set = new HashSet<>();
                // 滑动窗口
                for (int left = 0; left + k - 1 < length && left <= i; left++) {
                    int index = i - 1;
                    if (left > index) {
                        set.add(k + i);
                    } else {
                        // left左边的0
                        int temp = left + (k - i + left);
                        set.add(temp);
                    }
                }
                map.put(i, set);
            }
        }
        return 0;
    }

    @Override
    public Object initData() {
        return null;
    }
}
