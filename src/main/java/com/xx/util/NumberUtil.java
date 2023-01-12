package com.xx.util;

/**
 * @author 玄霄
 * @CreateDate 2022/8/2
 */
public class NumberUtil {

    /**
     * 将value的第n位置为1；
     * 原理：将1左移N-1位，然后与目标值进行或运算
     */
    public static int setBit(int value, int n) {
        int result = (1 << (n - 1)) | value;
        return result;
    }
}