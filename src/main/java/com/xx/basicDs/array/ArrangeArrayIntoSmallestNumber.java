package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/6
 * <p>
 * 把数组排成最小的数
 * 剑指 Offer 45
 * <p>
 * 输入一个非负整数数组nums，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 0 < nums.length <= 100
 * <p>
 * 示例：
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 */
public class ArrangeArrayIntoSmallestNumber implements Answer {
    public static void main(String[] args) {
        ArrangeArrayIntoSmallestNumber arrangeArrayIntoSmallestNumber = new ArrangeArrayIntoSmallestNumber();
        arrangeArrayIntoSmallestNumber.answer();
    }

    @Override
    public void answer() {
        int[] nums = initData();
        // 先将 nums 转换为字符串数组的形式
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        // 通过快速排序的方式，将字符串数组的每个字符按照约定的顺序进行排序
        quickSort(strs, 0, strs.length - 1);
        // 再把字符串数组转字符串的形式
        StringBuilder ans = new StringBuilder();
        for (String s : strs) {
            ans.append(s);
        }
        System.out.println(ans.toString());
    }

    // 函数传入待排序数组 nums
    // 排序区间的左端点 left
    // 排序区间的右端点 right
    private void quickSort(String[] strs, int left, int right) {
        // 如果 left 大于等于 right，说明该区间只有 1 个或者没有元素
        if (left >= right) {
            // 无需再递归划分后再排序，直接返回
            return;
        }

        // 调用函数 partition，将 left 和 right 之间的元素划分为左右两部分
        int mid = partition(strs, left, right);

        // 划分之后，再对 mid 左侧的元素进行快速排序
        quickSort(strs, left, mid - 1);

        // 划分之后，再对 mid 右侧的元素进行快速排序
        quickSort(strs, mid + 1, right);
    }

    // 直接套之前的快速排序的代码进行修改
    // 原先的小于的含义指的是数值上的小于，比如 1  < 10
    // 但现在的小于含义为：a + b 拼凑的字符串小于 b + a 拼凑的字符串
    // 比如 a = 1 ，b = 10
    // 那么 a + b = “110”，b + a = “101”
    // 显然，b + a < a + b
    // 也就是说 a 应该放到 b 的后面来拼凑字符串
    private int partition(String[] strs, int left, int right) {
        // 经典快速排序的写法
        // 设置当前区间的第一个元素为基准元素
        String pivot = strs[left];
        // left 向右移动，right 向左移动，直到 left 和 right 指向同一元素为止
        while (left < right) {
            // 当 pivot + strs[right] 的字符串小于 strs[right] + pivot 的字符串时
            // 说明 strs[right] 在正确的位置上，right 向左移动
            while (left < right && (pivot + strs[right]).compareTo(strs[right] + pivot) <= 0) {
                // right 不断的向左移动
                right--;
            }
            // 此时，跳出了上面这个 while 循环，说明 pivot + strs[right] 的字符串大于 strs[right] + pivot 的字符串了
            // 说明 strs[right] 不在正确的位置上
            // 将此时的 strs[left] 赋值为 strs[right]
            // 执行完这个操作，比 pivot 小的这个元素被移动到了左侧
            strs[left] = strs[right];
            // 当 strs[left] + pivot 的字符串小于 pivot + strs[left] 的字符串时
            // 说明 strs[left] 在正确的位置上，left 向右移动
            while (left < right && (strs[left] + pivot).compareTo(pivot + strs[left]) <= 0) {
                // left 不断的向右移动
                left++;
            }
            // 此时，跳出了上面这个 while 循环，说明 strs[left] + pivot 的字符串大于 pivot + strs[left] 的字符串了
            // 说明 strs[left] 不在正确的位置上
            // 将此时的 strs[right] 赋值为 strs[left]
            // 执行完这个操作，比 pivot 大的这个元素被移动到了右侧
            strs[right] = strs[left];
        }
        // 此时，left 和 right 相遇，那么需要将此时的元素设置为 pivot
        // 这个时候，pivot 的左侧元素都小于它，右侧元素都大于它
        strs[left] = pivot;
        // 返回 left
        return left;
    }

    @Override
    public int[] initData() {
        return new int[]{3, 30, 34, 5, 9};
    }
}