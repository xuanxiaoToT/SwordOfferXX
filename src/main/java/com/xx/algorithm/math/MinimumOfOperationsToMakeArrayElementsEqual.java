package com.xx.algorithm.math;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/1
 * <p>
 * 最小操作次数使数组元素相等
 * LeetCode 453. Medium
 * <p>
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,1]
 * 输出：0
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 答案保证符合 32-bit 整数
 * <p>
 * 提示：正着需要操作n-1个加1，反着只需要减一个1。
 * <p>
 * Tag: 数学  数组   脑筋急转弯
 */
public class MinimumOfOperationsToMakeArrayElementsEqual implements Answer {

    public static void main(String[] args) {
        new MinimumOfOperationsToMakeArrayElementsEqual().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(minMoves(nums));
    }

    /**
     * 为了方便，令原数组 num 的总和为 sum，最小值为 min，最大值为 max，长度为 n，真实最小操作次数为 ans,最终数组会变为每个数为t
     * 则易得：ans = (t*n-sum)/(n-1)
     * <p>
     * 要取得最小的 ans，其实等价于取得最小的 t，但仅靠 t⩾max关系，我们无法直接求得 ans。
     * 事实上，我们可以通过值变化来进行分析，凭直觉我们会觉得：在配平整个数组的过程中，当前数组中的最小值会参与到自增过程中。
     * <p>
     * 至此，我们可以得到 t 和 min 的关系式：
     * t=min+ans
     * 易得：ans=sum−min∗n
     */
    public int minMoves(int[] nums) {
        int n = nums.length;
        long min = nums[0], sum = 0;
        for (int i : nums) {
            min = Math.min(min, i);
            sum += i;
        }
        return (int) (sum - min * n);
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
