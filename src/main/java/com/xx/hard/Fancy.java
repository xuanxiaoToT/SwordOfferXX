package com.xx.hard;

import java.util.ArrayList;
import java.util.List;


/**
 * 奇妙序列
 * LeetCode 1622. Hard-
 * <p>
 * 请你实现三个 API append，addAll 和 multAll 来实现奇妙序列。
 * 请实现 Fancy 类 ：
 * Fancy() 初始化一个空序列对象。
 * void append(val) 将整数 val 添加在序列末尾。
 * void addAll(inc) 将所有序列中的现有数值都增加 inc 。
 * void multAll(m) 将序列中的所有现有数值都乘以整数 m 。
 * int getIndex(idx) 得到下标为 idx 处的数值（下标从 0 开始），并将结果对 109 + 7 取余。如果下标大于等于序列的长度，请返回 -1 。
 * <p>
 * 示例：
 * 输入：
 * ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
 * [[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
 * 输出：
 * [null, null, null, null, null, 10, null, null, null, 26, 34, 20]
 * <p>
 * 解释：
 * Fancy fancy = new Fancy();
 * fancy.append(2);   // 奇妙序列：[2]
 * fancy.addAll(3);   // 奇妙序列：[2+3] -> [5]
 * fancy.append(7);   // 奇妙序列：[5, 7]
 * fancy.multAll(2);  // 奇妙序列：[5*2, 7*2] -> [10, 14]
 * fancy.getIndex(0); // 返回 10
 * fancy.addAll(3);   // 奇妙序列：[10+3, 14+3] -> [13, 17]
 * fancy.append(10);  // 奇妙序列：[13, 17, 10]
 * fancy.multAll(2);  // 奇妙序列：[13*2, 17*2, 10*2] -> [26, 34, 20]
 * fancy.getIndex(0); // 返回 26
 * fancy.getIndex(1); // 返回 34
 * fancy.getIndex(2); // 返回 20
 * <p>
 * 提示：
 * 1 <= val, inc, m <= 100
 * 0 <= idx <= 10^5
 * 总共最多会有 10^5 次对 append，addAll，multAll 和 getIndex 的调用。
 */
public class Fancy {

    private int mod = 1000_000_007;
    private List<Node> operList;
    private List<Integer> numList;


    public Fancy() {
        this.operList = new ArrayList<Node>();
        this.numList = new ArrayList<>();

    }

    public static void main(String[] args) {
        Fancy fancy = new Fancy();
        fancy.append(2);
        fancy.addAll(3);
        fancy.append(7);
        fancy.multAll(2);
        fancy.getIndex(0);
        fancy.addAll(3);
        fancy.append(10);
        fancy.multAll(2);
        fancy.getIndex(0);
        fancy.getIndex(1);
        fancy.getIndex(2);
    }

    public void append(int val) {
        numList.add(val);
    }

    public void addAll(int inc) {
        Node node = new Node(this.numList.size() - 1, 0, inc);
        operList.add(node);
    }

    public void multAll(int m) {
        Node node = new Node(this.numList.size() - 1, 1, m);
        operList.add(node);
    }

    public int getIndex(int idx) {
        if (idx < 0 || idx >= numList.size()) {
            return -1;
        }
        long res = numList.get(idx);
        int left = findMaxIndexLessThanTarget(this.operList, idx) + 1;

        for (int i = left; i < this.operList.size(); i++) {
            Node node = this.operList.get(i);
            if (idx <= node.index) {
                if (node.oper == 0) {
                    res = (res + node.val) % mod;
                } else {
                    res = (res * node.val) % mod;
                }

            } else {
                break;
            }
        }


        System.out.println(Math.toIntExact(res % mod));
        return Math.toIntExact(res % mod);
    }

    private int findMaxIndexLessThanTarget(List<Node> operList, int target) {
        // 处理边界：数组为空
        if (operList == null || operList.isEmpty()) {
            return -1;
        }

        int left = 0;
        int right = operList.size() - 1;
        int result = -1; // 记录符合条件的最大下标

        while (left <= right) {
            // 计算mid，避免(left+right)溢出
            int mid = left + (right - left) / 2;

            if (operList.get(mid).index < target) {
                // 当前mid位置的元素符合条件，更新结果
                result = mid;
                // 向右找更大的下标（因为要找最大下标）
                left = mid + 1;
            } else {
                // 元素≥target，向左缩小范围
                right = mid - 1;
            }
        }

        return result;
    }

    public static class Node {
        public int index;
        /**
         * 0代表加
         * 1代表乘
         */
        public int oper;
        public int val;

        public Node(int val) {
        }

        public Node(int index, int oper, int val) {
            this.index = index;
            this.oper = oper;
            this.val = val;
        }
    }
}
