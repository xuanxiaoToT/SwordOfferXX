package com.xx.basicDs.hash;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/27
 */
public class SumOfTheMinimumOfSubarray implements Answer {

    public static void main(String[] args) {
        new SumOfTheMinimumOfSubarray().answerOne();
    }

    @Override
    public void answerOne() {
        int[] arr = new int[]{11, 81, 94, 43, 3};
        System.out.println(sumSubarrayMinsMy(arr));
    }

    /**
     * n题目中最大为3∗10^4,n^2已接近10^9
     * 且有MOD运算，因此必然会超时。
     */
    public int sumSubarrayMinsMy(int[] arr) {
        int yu = 1000000007;
        long result = 0;
        for (int i = 0; i < arr.length; i++) {
            int minTemp = arr[i];
            result += minTemp;
            for (int j = i + 1; j < arr.length; j++) {
                minTemp = Math.min(arr[j], minTemp);
                result += minTemp;
            }
        }
        return (int) (result % yu);
    }

    /**
     * 下面考虑一个事实：
     * 最小值是在一段连续数字中被筛选出来的，也就是说每个最小值都有一定的辐射范围。
     * 假设给定数组A=[3,1,2,4,1]，在一段连续数字3、1、2、4、1中，只要其中一段数字包含1，那么这段数字的最小值肯定为1，
     * 例如[3,1,2,4,1]、[3,1,2,4]、[3,1,2]、[1,2]等最小值都为1，我们把这叫做元素1的辐射范围。
     * <p>
     * 每个元素E=A[i]的辐射范围都是一个连续数组，这个辐射范围内产生的所有子数组最小值都为E，
     * 因此E在每个子数组中对答案的贡献值都为E。如果这个辐射范围内的子数组有n个，那么总贡献值为n*E。
     */
    public int sumSubarrayMinsLeet(int[] arr) {
        return 0;
    }


    @Override
    public Object initData() {
        return null;
    }
}
