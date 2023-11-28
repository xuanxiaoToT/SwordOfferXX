package com.xx.util;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/26
 */
public class MathUtil {

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
            //0的阶乘为1
            return 1;
        }
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
