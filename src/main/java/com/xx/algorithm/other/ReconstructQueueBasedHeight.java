package com.xx.algorithm.other;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/10
 * <p>
 * 根据身高重建队列
 * LeetCode 406
 * <p>
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面正好有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * <p>
 * 示例 1：
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 * <p>
 * 示例 2：
 * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 */
public class ReconstructQueueBasedHeight implements Answer {

    public static void main(String[] args) {
        new ReconstructQueueBasedHeight().answerOne();
    }

    /**
     * 解1：最简单的O(N2)，即先找出0号位置的放入列表，然后依次判断剩下的该插在哪
     */
    @Override
    public void answerOne() {
        Integer[][] data = initData();
        int length = data.length;
        Set<Integer> hasHandled = new HashSet<>(length);
        List<Integer[]> result = new ArrayList<>(length);
        //先初始化，找为0的
        for (int i = 0; i < length; i++) {
            Integer[] datum = data[i];
            if (datum[1] == 0) {
                result.add(datum);
                hasHandled.add(i);
            }
        }
        result.sort(Comparator.comparingInt(dto -> dto[0]));

        while (hasHandled.size() != length) {
            for (int i = 0; i < length; i++) {
                if (!hasHandled.contains(i)) {
                    Integer[] datum = data[i];
                    // 寻找插入位置
                    if (checkData(datum, result)) {
                        hasHandled.add(i);
                    }
                }
            }
        }

        System.out.println(result);
    }

    /**
     * 矮个子排在哪都对高个子没有影响，但是高个子排在矮个子前面就会造成影响。
     * 所以，矮个子要主动选择位置。
     */
    private boolean checkData(Integer[] datum, List<Integer[]> result) {
        Integer heigh = datum[0];
        Integer rank = datum[1];
        int count = 0;
        int flag = 0;
        for (int i = 0; i < result.size(); i++) {
            Integer[] data = result.get(i);
            if (heigh <= data[0]) {
                count++;
            }
            if (count == rank) {
                flag = i;
            }
        }
        if (flag != 0) {
            result.add(flag + 1, datum);
        }
        return flag != 0;
    }

    /**
     * 方法二：从低到高考虑
     * <p>
     * 当每个人的身高都不相同时，如果我们将他们按照身高从小到大进行排序，那么就可以很方便地还原出原始的队列了。
     * <p>
     * https://leetcode.cn/problems/queue-reconstruction-by-height/solutions/486066/gen-ju-shen-gao-zhong-jian-dui-lie-by-leetcode-sol/
     */
    public void answerTwo() {
        Integer[][] people = initData();
        int length = people.length;
        Arrays.sort(people, new Comparator<Integer[]>() {
            public int compare(Integer[] person1, Integer[] person2) {
                if (!Objects.equals(person1[0], person2[0])) {
                    return person1[0] - person2[0];
                } else {
                    return person2[1] - person1[1];
                }
            }
        });
        // 找插入位置，与方法一类似。
        Integer[][] ans = new Integer[length][];
        for (Integer[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < length; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    /**
     * 输出数据
     */
    @Override
    public Integer[][] initData() {
        //return new Integer[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        return new Integer[][]{{6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}};
    }
}
