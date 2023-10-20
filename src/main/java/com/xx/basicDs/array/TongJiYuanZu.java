package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/20
 * <p>
 * 同积元组
 * LeetCode 1726. Medium
 * <p>
 * 给你一个由 不同 正整数组成的数组 nums ，
 * 请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。
 * 其中 a、b、c 和 d 都是 nums 中的元素，且 a != b != c != d 。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,4,6]
 * 输出：8
 * 解释：存在 8 个满足题意的元组：
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,4,5,10]
 * 输出：16
 * 解释：存在 16 个满足题意的元组：
 * (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
 * (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
 * (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
 * (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^4
 * nums 中的所有元素 互不相同
 */
public class TongJiYuanZu implements Answer {

    public static void main(String[] args) {
        new TongJiYuanZu().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        if (nums.length < 4) {
            return;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int temp = nums[i] * nums[j];
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
        }
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value >= 2) {
                // C(M,N) = A(M,N)/A(N,N)
                int temp = value * (value - 1) * 8 / 2;
                result += temp;
            }
        }
        System.out.println(result);
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{2, 3, 4, 6};
    }
}
