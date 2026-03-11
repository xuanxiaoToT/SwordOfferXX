package com.xx.中大机试;

import com.xx.Answer;

import java.util.Arrays;
import java.util.List;

public class SequenceCounting implements Answer {
   public int name;
    public static void main(String[] args) {

        SequenceCounting counting = new SequenceCounting();
        SequenceCounting counting2 = new SequenceCounting();
        counting2.name = counting.name;
        counting.answer();
    }

    @Override
    public void answer() {
        // List<Integer> listA = Arrays.asList(1, 3, 1, 4);
        // List<Integer> listB = Arrays.asList(6, 5, 4, 6);

        // List<Integer> listA = Arrays.asList(2,1);
        // List<Integer> listB = Arrays.asList(3,2);

        List<Integer> listA = Arrays.asList(1, 1, 1);
        List<Integer> listB = Arrays.asList(10000, 10000, 10000);
        System.out.println(computeResult(listA, listB));
    }

    /**
     * 最大循环次数：
     * 300*10000*5000
     */
    public int computeResult(List<Integer> listA, List<Integer> listB) {
        int mod = 998_244_353;
        long result = 0;
        Long[][] dp = new Long[listA.size()][10001];
        for (int i = 0; i < dp.length; i++) {
            Integer aNum = listA.get(i);
            Integer bNum = listB.get(i);
            if (bNum < aNum) {
                return 0;
            }
            for (int j = aNum; j <= bNum; j++) {
                if (i == 0) {
                    dp[i][j] = 1L;
                    continue;
                }
                for (int k = 0; k < j; k++) {
                    if (dp[i - 1][k] != null) {
                        dp[i][j] = dp[i - 1][k] + (dp[i][j] == null ? 0 : dp[i][j]);
                    }
                }
                if (i == dp.length - 1 && dp[i][j] != null) {
                    result = result + dp[i][j];
                }
            }
        }
        return Math.toIntExact(result % mod);
    }

    public int computeResult2(List<Integer> listA, List<Integer> listB) {
        int n = listA.size();
        if (n == 0) {
            return 0;
        }


        int MAX_VALUE = 10000;
        int mod = 998_244_353;
        // 滚动数组：prevDp[i]表示当前以i结尾的合理方案数
        long[] prevDp = new long[MAX_VALUE + 1];

        // 初始化第一个元素的合法方案数
        int firstA = listA.get(0);
        int firstB = listB.get(0);
        if (firstB < firstA) {
            return 0;
        }
        for (int j = firstA; j <= firstB; j++) {
            prevDp[j] = 1L;
        }

        // 处理第2到第n个元素
        for (int i = 1; i < n; i++) {
            int currA = listA.get(i);
            int currB = listB.get(i);
            if (currB < currA) {
                return 0;
            }
            // 计算上一层的前缀和数组，O(V)时间
            long[] preSum = new long[MAX_VALUE + 1];
            preSum[0] = prevDp[0];
            for (int j = 1; j <= MAX_VALUE; j++) {
                preSum[j] = (preSum[j - 1] + prevDp[j]) % mod;
            }

            // 计算当前层的dp，O(V)
            long[] currDp = new long[MAX_VALUE + 1];
            for (int j = currA; j <= currB; j++) {
                currDp[j] = preSum[j - 1] % mod;
            }

            // 滚动更新，当前层变为下一次的上一层
            prevDp = currDp;
        }

        // 统计最后一层所有合法值的方案总和
        long result = 0;
        int lastA = listA.get(n - 1);
        int lastB = listB.get(n - 1);
        for (int j = lastA; j <= lastB; j++) {
            result = (result + prevDp[j]) % mod;
        }

        return (int) result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
