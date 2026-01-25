package com.xx.util;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/26
 */
public class MathUtil {

    /**
     * 求三个以上数字的最大值
     */
    public static Integer intMaxByNull(Integer data1, Integer... data2) {
        Integer max = data1;
        for (Integer i : data2) {
            if (data1 == null && i != null) {
                max = i;
            }
            if (i != null && i > max) {
                max = i;
            }
        }
        return max;
    }

    // 非空求值
    public static Integer nvl(Integer num, Integer value) {
        if (num == null) {
            return value;
        }
        return num;
    }

    /**
     * 计算阶乘
     */
    public static int unRecursion(int num) {
        int result = 1;
        if (num == 0) {
            // 0的阶乘为1
            return 1;
        }
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * C(n, k) = n! / (k! * (n - k)!)
     */
    public static int computeCombineCount(int n, int k) {
        if (k == 0) {
            return 1;
        }
        return unRecursion(n) / (unRecursion(k) * unRecursion(n - k));
    }
}
