package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/23
 * <p>
 * 查找和最小的K对数字
 * LeetCode 373. Medium
 * <p>
 * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * <p>
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * <p>
 * 示例 2:
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * <p>
 * 示例 3:
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * <p>
 * 提示:
 * 1 <= nums1.length, nums2.length <= 10^5
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1 和 nums2 均为升序排列
 * 1 <= k <= 10^4
 * <p>
 * Tag: 数组  最小堆
 */
public class FindSumMinimumKPairNumbers implements Answer {

    public static void main(String[] args) {
        new FindSumMinimumKPairNumbers().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int[] nums1 = new int[]{1, 7};
        int[] nums2 = new int[]{2, 4, 6};
        int k = 3;
        System.out.println(kSmallestPairs(nums1, nums2, 10));
    }

    /**
     * 第一时间想到堆
     * 如何利用递增条件？
     * 实际求解时可以不用求出所有的数对，只需从所有符合待选的数对中选出最小的即可，假设当前已选的前 n 小数对的索引分别为 (a1,b1),(a2,b2),(a3,b3),…,(an,bn)，
     * 由于两个数组都是按照升序进行排序的，则可以推出第 n+1 小的数对的索引选择范围为 (a1+1,b1),(a1,b1+1),(a2+1,b2),(a2,b2+1),(a3+1,b3),(a3,b3+1),…,(an+1,bn),(an,bn+1)，
     * 假设我们利用堆的特性可以求出待选范围中最小数对的索引为 (ai,bi)，同时将新的待选的数对 (ai+1,bi),(ai,bi+1)加入到堆中，直到我们选出 k 个数对即可。
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        //最小堆
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });

        int m = nums1.length;
        int n = nums2.length;

        // m n过大时，超出内存限制
        int[][] flag = new int[m][n];
        HashSet<int[]> set = new HashSet<>();
        minHeap.add(new int[]{0, 0});
        flag[0][0] = 1;

        while (!minHeap.isEmpty() && result.size() < Math.min(k, m * n)) {
            //一定是最小的
            int[] poll = minHeap.poll();
            int x = poll[0];
            int y = poll[1];
            result.add(Arrays.asList(nums1[x], nums2[y]));
            // fixme：如何解决重复问题?
            if (x + 1 < m && flag[x + 1][y] == 0) {
                minHeap.add(new int[]{x + 1, y});
                flag[x + 1][y] = 1;
            }
            if (y + 1 < n && flag[x][y + 1] == 0) {
                minHeap.add(new int[]{x, y + 1});
                flag[x][y + 1] = 1;
            }
        }
        return result;
    }

    /**
     * 如果我们每次都将已选的数对 (ai,bi)的待选索引 (ai+1,bi),(ai,bi+1)加入到堆中则可能出现重复的问题，
     * 一般需要设置标记位解决去重的问题。我们可以将 nums1的前 k 个索引数对 (0,0),(1,0),…,(k−1,0)加入到队列中，
     * 每次从队列中取出元素 (x,y) 时，我们只需要将 nums2的索引增加即可，这样避免了重复加入元素的问题。
     */
    public List<List<Integer>> kSmallestPairsByLeet(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            minHeap.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] idxPair = minHeap.poll();
            int x = idxPair[0];
            int y = idxPair[1];
            List<Integer> list = Arrays.asList(nums1[x], nums2[y]);
            ans.add(list);
            if (y + 1 < n) {
                minHeap.offer(new int[]{x, y + 1});
            }
        }
        return ans;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
