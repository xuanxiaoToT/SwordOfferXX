package com.xx.basicDs.heap;

import com.xx.Answer;
import com.xx.basicDs.queue.FrontMiddleBackQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/1
 * <p>
 * 数据流的中位数
 * LeetCode 295. Hard
 * <p>
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * <p>
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * MedianFinder() 初始化 MedianFinder 对象。
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 * <p>
 * 示例 1：
 * 输入
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * 输出
 * [null, null, null, 1.5, null, 2.0]
 * <p>
 * 解释
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * 提示:
 * <p>
 * -10^5 <= num <= 10^5
 * 在调用 findMedian 之前，数据结构中至少有一个元素
 * 最多 5 * 10^4 次调用 addNum 和 findMedian
 * <p>
 * Tag: 堆  大顶堆   小顶堆
 */
public class MedianInDataFlow implements Answer {

    private List<Integer> list = new ArrayList<>();

    // PriorityQueue，优先队列
    // 优先队列的作用是能保证每次取出的元素都是队列中权值最小的（ Java 的优先队列每次取最小元素，C++的优先队列每次取最大元素）
    // 由于 Java 的优先队列每次取最小元素，即默认函数是实现小顶堆
    // 大顶堆的概念：每个节点的值大于等于左右孩子节点的值，堆顶为最大值
    // 因此，大顶堆的初始化需要额外处理
    // maxHeap 存储数据流中较小一半的值
    PriorityQueue<Integer> maxHeap;

    // 小顶堆的概念：每个节点的值小于等于左右孩子节点的值，堆顶为最小值
    // minHeap 来存储数据流中较大一半的值
    PriorityQueue<Integer> minHeap;

    /**
     * 思路类似
     * {@link FrontMiddleBackQueue}
     */
    public static void main(String[] args) {
        MedianInDataFlow medianInDataFlow = new MedianInDataFlow();
        medianInDataFlow.answer();
    }

    @Override
    public void answer() {
        // 缺点，插入的复杂度不是O1，而是On。效率很低。
        List<Integer> initData = initData();
        initData.forEach(num -> {
            addNum(num);
            System.out.println(findMedian());
        });
        System.out.println(list);
    }

    private void addNum(Integer data) {
        for (int i = 0; i < list.size(); i++) {
            if (data < list.get(i)) {
                if (i > 1) {
                    list.add(i, data);
                    return;
                } else {
                    list.add(0, data);
                    return;
                }
            }
        }
        list.add(list.size(), data);
    }

    private double findMedian() {
        int length = list.size();
        int mid = length / 2;
        if (length % 2 == 0) {
            return (list.get(mid) + list.get(mid - 1)) / 2.0;
        } else {
            return list.get(mid);
        }
    }

    // 采用堆的方式
    public void answerTwo() {
        // 初始化操作
        maxHeap = new PriorityQueue<Integer>((x, y) -> (y - x));
        minHeap = new PriorityQueue<Integer>();
    }

    private void addNum2(Integer num) {
        // 数据流的长度有奇数和偶数两种情况，并且是在动态变化的

        // 1、【大顶堆】与【小顶堆】的长度不相等，由于两者的长度至多相差 1，那么数据流的总长度就是奇数
        // 假设 minHeap 的长度为 n，则 maxHeap 的长度为 n - 1
        // 那么 maxHeap 是应该需要加入一个【新的元素】的，这样就能使得 minHeap 和 maxHeap 的长度均为 n
        // 那么加入新元素之后，中位数就是 （ minHeap 的堆顶 + maxHeap 的堆顶） / 2
        // 但如果直接把 num 加入到 maxHeap 中，如果 num 是一个很大的值
        // 由于 maxHeap 是存储数据流中较小一半的值，这样就会破坏我们维护的属性
        // 因此，我们可以先把 num 加入到 minHeap 中，然后从 minHeap 挤出一个最小值来，重新加入到 maxHeap
        // 一来一回，minHeap 的长度依旧为 n，maxHeap 的长度变成了 n
        if (maxHeap.size() != minHeap.size()) {

            // 先将元素添加到小顶堆 minHeap 中
            // 由于 minHeap 添加了新的元素，PriorityQueue 会自动的将 minHeap 之前的元素和 num 进行操作
            // 使得 minHeap 的每个节点的值小于等于左右孩子节点的值，堆顶为最小值
            // 这个时候，minHeap 的长度变成了 n + 1
            minHeap.add(num);

            // 由于 minHeap 来存储数据流中较大一半的值，而新添加的元素 num 有可能是一个很小的值
            // 理论上应该要加入到 maxHeap 才对
            // 所以，先去获取此时 minHeap 的堆顶元素（不一定值是 num），即最小值，把它抛出后加入到 maxHeap 中
            maxHeap.add(minHeap.poll());

            // 2、【大顶堆】与【小顶堆】的长度相等，那么数据流的总长度就是偶数
            // 假设 minHeap 的长度为 n，则 maxHeap 的长度为 n
            // 我们把新的元素加入到 minHeap 中，使得 minHeap 的长度变成了 n + 1
            // 那么中位数就是 minHeap 的堆顶元素了
            // 但如果直接把 num 加入到 minHeap 中，如果 num 是一个很小的值
            // 由于 minHeap 是存储数据流中较大一半的值，这样就会破坏我们维护的属性
            // 因此，我们可以先把 num 加入到 maxHeap 中，然后从 maxHeap 挤出一个最大值来，重新加入到 minHeap
            // 一来一回，maxHeap 的长度依旧为 n，mminHeap 的长度变成了 n + 1
        } else {

            // 先将元素添加到大顶堆 maxHeap 中
            // 由于 maxHeap 添加了新的元素，PriorityQueue 会自动的将 maxHeap 之前的元素和 num 进行操作
            // 使得 maxHeap 的每个节点的值大于等于左右孩子节点的值，堆顶为最大值
            // 这个时候，maxHeap 的长度变成了 n + 1
            maxHeap.add(num);

            // 由于 maxHeap 来存储数据流中较小一半的值，而新添加的元素 num 有可能是一个很大的值
            // 理论上应该要加入到 minHeap 才对
            // 所以，先去获取此时 maxHeap 的堆顶元素（不一定值是 num），即最大值，把它抛出后加入到 minHeap 中
            minHeap.add(maxHeap.poll());
        }
    }

    private double findMedian2() {
        if (maxHeap.size() != minHeap.size()) {
            return minHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) * 1.0 / 2;
        }
    }


    @Override
    public List<Integer> initData() {
        return Arrays.asList(3, 2, 5, 9, 1, 4);
    }
}
