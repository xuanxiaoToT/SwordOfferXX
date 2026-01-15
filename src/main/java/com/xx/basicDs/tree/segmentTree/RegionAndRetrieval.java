package com.xx.basicDs.tree.segmentTree;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/13
 * <p>
 * 区域和检索 - 数组可修改
 * LeetCode 307.  Medium
 * <p>
 * 给你一个数组 nums ，请你完成两类查询。
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * -NumArray(int[] nums) 用整数数组 nums 初始化对象
 * -void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * -int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 * <p>
 * <p>
 * 示例 1：
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 * <p>
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 10^4
 *
 * Tag:分块  分段   线段树
 */
public class RegionAndRetrieval {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        RegionAndRetrieval regionAndRetrieval = new RegionAndRetrieval(nums);
        // regionAndRetrieval.update(12, 50);
        System.out.println(regionAndRetrieval.sumRange(0, 2));
    }

    private int[] nums = null;
    private int[] sumRangeK = null;
    private int k = 10;

    /**
     * 10个点为一个段，来求解
     * fix: 当K去根号N时，其效果最好
     */
    public RegionAndRetrieval(int[] nums) {
        this.nums = nums;
        this.sumRangeK = new int[nums.length / k + 1];
        for (int i = 0; i < nums.length; i++) {
            int KIndex = i / k;
            sumRangeK[KIndex] += nums[i];
        }
    }

    public void update(int index, int val) {
        int cha = val - this.nums[index];
        int KIndex = index / this.k;
        this.nums[index] = val;
        this.sumRangeK[KIndex] = this.sumRangeK[KIndex] + cha;
    }

    public int sumRange(int left, int right) {
        int leftKIndex = left / this.k;
        int rightKIndex = right / this.k;
        if (leftKIndex == rightKIndex || rightKIndex - leftKIndex == 1) {
            int sumResult = 0;
            for (int i = left; i <= right && i < nums.length; i++) {
                sumResult += nums[i];
            }
            return sumResult;
        }
        int sumResult = 0;
        for (int i = leftKIndex + 1; i < rightKIndex; i++) {
            sumResult += this.sumRangeK[i];
        }
        for (int i = left; i < (leftKIndex + 1) * this.k; i++) {
            sumResult += nums[i];
        }
        for (int i = rightKIndex * this.k; i <= right; i++) {
            sumResult += nums[i];
        }
        return sumResult;
    }

}