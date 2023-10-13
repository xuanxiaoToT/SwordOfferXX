package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/13
 * <p>
 * 汇总区间
 * LeetCode 228. 简单
 * <p>
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，
 * 并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * 示例 1：
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * <p>
 * 示例 2：
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * <p>
 * 提示：
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 */
public class SummaryInterval implements Answer {
    public static void main(String[] args) {
        new SummaryInterval().answerOne();
    }

    @Override
    public void answerOne() {
        int[] data = initData();
        List<String> result = new ArrayList<>();
        String temp = "";
        int tempCount = 0;
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                temp = temp + data[i];
                tempCount = 1;
            } else {
                if (data[i] == data[i - 1] + 1) {
                    tempCount++;
                }
                if (data[i] > data[i - 1] + 1) {
                    if (tempCount > 1) {
                        temp = temp + "->" + data[i - 1];
                        result.add(temp);
                    } else {
                        result.add(temp);
                    }
                    temp = "" + data[i];
                    tempCount = 1;
                }
                if (i == data.length - 1) {
                    if (tempCount > 1) {
                        temp = temp + "->" + data[i];
                        result.add(temp);
                        temp = "";
                    } else {
                        result.add(temp);
                        temp = "";
                    }
                }
            }
        }
        if (temp.length() > 0) {
            result.add(temp);
        }
        System.out.println(result);
    }

    @Override
    public int[] initData() {
        //return new int[]{0, 1, 2, 4, 5, 7};
        //return new int[]{0, 2, 3, 4, 6, 8, 9};
        return new int[]{-1};
    }
}