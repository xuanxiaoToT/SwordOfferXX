package com.xx.basicDs.number.位运算;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/2
 * <p>
 * 只出现一次的数字II
 * LeetCode 137  Medium
 * <p>
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 * <p>
 * Tag: 哈希表  位运算
 * <p>
 */
public class NumbersThatOnlyAppearOnceII implements Answer {

    public static void main(String[] args) {
        new NumbersThatOnlyAppearOnceII().answerOne();
    }

    /**
     * 1.最简单的，用set来接
     * 2.重点：任何一个数字异或它自己的结果都是0,相同的3个数字异或的结果是数字本身
     */
    @Override
    public void answerOne() {
        int[] nums = initData();
        System.out.println(singleNumber(nums));
    }

    /**
     * 一个整数是由32个0或1组成的。我们可以将数组中所有数字的同
     * 一位置的数位相加。如果将出现3次的数字单独拿出来，那么这些出现
     * 了3次的数字的任意第i个数位之和都能被3整除。因此，如果数组中所
     * 有数字的第i个数位相加之和能被3整除，那么只出现一次的数字的第i
     * 个数位一定是0；如果数组中所有数字的第i个数位相加之和被3除余
     * 1，那么只出现一次的数字的第i个数位一定是1。这样只出现一次的任
     * 意第i个数位可以由数组中所有数字的第i个数位之和推算出来。当我
     * 们知道一个整数任意一位是0还是1之后，就可以知道它的数值。
     */
    public int singleNumber(int[] nums) {
        int[] bitSums = new int[32];
        for (int num : nums) {
            for (int i = 0; i < bitSums.length; i++) {
                //代码“（num>>（31-i））&1”用来得到整数num的二进制形式中从左数起第i个数位。
                bitSums[i] += (num >> (31 - i)) & 1;
            }
        }
        int result = 0;
        for (int bitSum : bitSums) {
            result = (result << 1) + bitSum % 3;
        }
        return result;
    }

    /**
     * 最笨的，非常数级空间
     * 用一个Map来计数
     */
    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1) {
                ans = num;
                break;
            }
        }
        return ans;
    }


    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{0, 1, 0, 1, 0, 1, 100};
    }
}
