package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/28
 * <p>
 * 组合总和III
 * LeetCode 216. Medium
 * <p>
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 * <p>
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 * <p>
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了。
 * <p>
 * 示例 3:
 * 输入: k = 4, n = 1
 * 输出: []
 * 解释: 不存在有效的组合。
 * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 * <p>
 * 提示:
 * 2 <= k <= 9
 * 1 <= n <= 60
 * <p>
 * Tag：回溯  剪枝
 */
public class SumOfCombinationIII implements Answer {
    public static void main(String[] args) {
        new SumOfCombinationIII().answerOne();
    }

    @Override
    public void answerOne() {
        System.out.println(computeResult(9, 45));
    }

    public List<List<Integer>> computeResult(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k >= n) {
            return result;
        }
        myDiGui(k, n, 1, 0, new ArrayList<>(), result);
        return result;
    }

    public void myDiGui(int k, int n, int index, int sum, List<Integer> temp, List<List<Integer>> result) {
        //注意两个if的顺序。因为是在下一层进行sum + index 所以不能先判断index越界，而是先判断sum。
        if (temp.size() == k && sum == n) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (temp.size() > k || sum > n || index > 9) {
            return;
        }
        // 选这个数
        temp.add(index);
        myDiGui(k, n, index + 1, sum + index, temp, result);
        //不选这个数
        temp.remove(temp.size() - 1);
        myDiGui(k, n, index + 1, sum, temp, result);
    }

    @Override
    public Object initData() {
        return null;
    }
}