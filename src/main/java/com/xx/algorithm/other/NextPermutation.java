package com.xx.algorithm.other;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/14
 * <p>
 * 下一个排列
 * LeetCode 31.
 * <p>
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
 * 更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
 * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
 * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 */
public class NextPermutation implements Answer {

    public static void main(String[] args) {
        new NextPermutation().answer();
    }

    /**
     * 解1:
     * 可以将该问题形式化地描述为：给定若干个数字，将其组合为一个整数。如何将这些数字重新排列，以得到下一个更大的整数。
     * 如 123 下一个更大的数为 132。如果没有更大的整数，则输出最小的整数。
     * <p>
     * 我们希望下一个数 比当前数大，这样才满足 “下一个排列” 的定义。因此只需要 将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。比如 123456，将 5 和 6 交换就能得到一个更大的数 123465。
     * 我们还希望下一个数 增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为了满足这个要求，我们需要：
     * 在 尽可能靠右的低位 进行交换，需要 从后向前 查找
     * 将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
     * 将「大数」换到前面后，需要将「大数」后面的所有数 重置为升序，升序排列就是最小的排列。以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。
     * 显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
     * 以上就是求 “下一个排列” 的分析过程。
     */
    @Override
    public void answer() {
        int[] nums = initData();
        //寻找最后两个逆序的
        int start = -1;
        int end = -1;
        int maxi = -1;
        int minj = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    start = i;
                    end = j;
                }
            }
            if (start > maxi) {
                maxi = start;
                minj = end;
            }
        }
        if (maxi == -1 || minj == -1) {
            //这里可能用时太多，还可以继续优化。
            Arrays.sort(nums);
        } else {
            swap(nums, maxi, minj);
            sort(nums, maxi + 1);
        }
        System.out.println(Arrays.toString(nums));
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void sort(int[] nums, int start) {
        int min;
        int index;
        for (int i = start; i < nums.length; i++) {
            index = -1;
            min = Integer.MAX_VALUE;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    index = j;
                }
            }
            if (index != -1)
                swap(nums, i, index);
        }
    }

    /**
     * 输出数据
     */
    @Override
    public int[] initData() {
        return new int[]{1, 2, 3, 5};
    }
}
