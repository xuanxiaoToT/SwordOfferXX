package com.xx.basicDs.number;

import com.xx.Answer;

import java.util.HashSet;
import java.util.Set;

/**
 * 找出不同的二进制字符串
 * LeetCode 1980. Medium
 * <p>
 * 给你一个字符串数组 nums ，该数组由 n 个 互不相同 的二进制字符串组成，且每个字符串长度都是 n 。
 * 请你找出并返回一个长度为 n 且 没有出现 在 nums 中的二进制字符串。如果存在多种答案，只需返回 任意一个 即可。
 * <p>
 * 示例 1：
 * 输入：nums = ["01","10"]
 * 输出："11"
 * 解释："11" 没有出现在 nums 中。"00" 也是正确答案。
 * <p>
 * 示例 2：
 * 输入：nums = ["00","01"]
 * 输出："11"
 * 解释："11" 没有出现在 nums 中。"10" 也是正确答案。
 * <p>
 * 示例 3：
 * 输入：nums = ["111","011","001"]
 * 输出："101"
 * 解释："101" 没有出现在 nums 中。"000"、"010"、"100"、"110" 也是正确答案。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 16
 * nums[i].length == n
 * nums[i] 为 '0' 或 '1'
 * nums 中的所有字符串 互不相同
 * <p>
 * Tag：二进制  字符串遍历
 */
public class FindUniqueBinaryString implements Answer {
    public String result;

    public static void main(String[] args) {
        new FindUniqueBinaryString().answer();
    }

    @Override
    public void answer() {
        // String[] nums = {"111", "011", "001"};
        String[] nums = {"00", "01"};
        System.out.println(findDifferentBinaryString(nums));
    }

    /**
     * 将其转换为整数
     * 方法2：直接用dfs穷举，然后判断是否在set中，类似全排列
     */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        // 预处理对应整数的哈希集合
        Set<Integer> vals = new HashSet<>();
        for (String num : nums) {
            vals.add(Integer.parseInt(num, 2));
        }
        // 寻找第一个不在哈希集合中的整数
        int val = 0;
        while (vals.contains(val)) {
            ++val;
        }
        // 将整数转化为二进制字符串返回
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(val));
        // 补齐前导0
        while (sb.length() < n) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    public String findDifferentBinaryString(String[] nums, int asd) {

        return "";
    }


    @Override
    public Object initData() {
        return null;
    }


}
