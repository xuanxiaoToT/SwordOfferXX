package com.xx.basicDs.array;

import com.xx.Answer;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/30
 * <p>
 * 最小的k个数
 * <p>
 * 输入整数数组 arr ，找出其中最小的 k 个数。
 * 例如，输入 4、5、1、6、2、7、3、8 这 8 个数字，则最小的 4 个数字是 1、2、3、4 。
 */
public class MinimumNumberOfK implements Answer {

    public static void main(String[] args) {
        MinimumNumberOfK minimumNumberOfK = new MinimumNumberOfK();
        minimumNumberOfK.answerThree();
    }

    /**
     * 最简单的：循环K遍
     * 时间复杂度：k*O(N)
     */
    @Override
    public void answerOne() {
        int k = 4;
        List<Integer> list = initData();

        Map<Integer, Integer> map = new HashMap<>(k);
        IntStream.range(0, k).forEach(num -> {
            Integer min = null;
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                if (map.containsKey(i)) {
                    continue;
                }
                if (min == null) {
                    min = list.get(i);
                    index = i;
                } else {
                    if (list.get(i) < min) {
                        min = list.get(i);
                        index = i;
                    }
                }
            }
            map.put(index, min);
        });
        System.out.println(map);
    }

    /**
     * 方法二，采用快排序，当左侧个数小于等于k个时即可停止。
     */
    private int[] answerTwo(int[] arr, int k) {

        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 执行快速排序操作，定位找到下标为 k - 1 的那个元素
        return quickSort(arr, 0, arr.length - 1, k - 1);
    }


    /**
     * 方法三：使用最大堆
     */
    private void answerThree() {
        List<Integer> data = initData();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        data.forEach(num -> {
            heapAdd(maxHeap, num, 4);
        });
        System.out.println(maxHeap);
    }

    /**
     * 函数传入待排序数组 nums
     * 排序区间的左端点 left
     * 排序区间的右端点 right
     */
    private int[] quickSort(int[] nums, int left, int right, int index) {
        // 调用函数 partition，将 left 和 right 之间的元素划分为左右两部分
        int mid = partition(nums, left, right);
        // 如果 mid 下标恰巧为 index，那么找到了最小的 k 个数
        if (mid == index) {
            // 直接返回
            return Arrays.copyOf(nums, mid + 1);
            // 如果 mid 下标大于 index，那么说明需要在左侧元素中去切分
        } else if (mid > index) {
            // 对 mid 左侧的元素进行快速排序
            return quickSort(nums, left, mid - 1, index);
        } else {
            // 对 mid 右侧的元素进行快速排序
            return quickSort(nums, mid + 1, right, index);
        }
    }

    private int partition(int[] nums, int left, int right) {
        // 经典快速排序的写法
        // 设置当前区间的第一个元素为基准元素
        int pivot = nums[left];
        // left 向右移动，right 向左移动，直到 left 和 right 指向同一元素为止
        while (left < right) {
            // 只有当遇到小于 pivot 的元素时，right 才停止移动
            // 此时，right 指向了一个小于 pivot 的元素，这个元素不在它该在的位置上
            while (left < right && nums[right] >= pivot) {
                // 如果 right 指向的元素是大于 pivot 的，那么
                // right 不断的向左移动
                right--;
            }
            // 将此时的 nums[left] 赋值为 nums[right]
            // 执行完这个操作，比 pivot 小的这个元素被移动到了左侧
            nums[left] = nums[right];

            // 只有当遇到大于 pivot left 才停止移动
            // 此时，left 指向了一个大于 pivot 的元素，这个元素不在它该在的位置上
            while (left < right && nums[left] <= pivot) {
                // 如果 left 指向的元素是小于 pivot 的，那么
                // left 不断的向右移动
                left++;
            }
            // 将此时的 nums[right] 赋值为 nums[left]
            // 执行完这个操作，比 pivot 大的这个元素被移动到了右侧
            nums[right] = nums[left];
        }
        // 此时，left 和 right 相遇，那么需要将此时的元素设置为 pivot
        // 这个时候，pivot 的左侧元素都小于它，右侧元素都大于它
        nums[left] = pivot;
        // 返回 left
        return left;
    }

    private void heapAdd(PriorityQueue<Integer> heap, Integer value, int maxSize) {
        if (heap.size() < maxSize) {
            heap.add(value);
        } else {
            if (value < heap.peek()) {
                heap.poll();
                heap.add(value);
            }
        }
    }

    @Override
    public List<Integer> initData() {
        return Arrays.asList(4, 5, 1, 6, 2, 7, 3, 8);
    }
}