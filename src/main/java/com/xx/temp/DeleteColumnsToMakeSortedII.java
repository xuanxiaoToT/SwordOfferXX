package com.xx.temp;

import com.xx.Answer;

import java.util.Arrays;

/**
 * 删列造序II
 * LeetCode 955. Medium
 * <p>
 * 给定由 n 个字符串组成的数组 strs，其中每个字符串长度相等。
 * 选取一个删除索引序列，对于 strs 中的每个字符串，删除对应每个索引处的字符。
 * 比如，有 strs = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 strs 为["bef", "vyz"]。
 * 假设，我们选择了一组删除索引 answer，那么在执行删除操作之后，
 * 最终得到的数组的元素是按 字典序（strs[0] <= strs[1] <= strs[2] ... <= strs[n - 1]）排列的，
 * 然后请你返回 answer.length 的最小可能值。
 * <p>
 * 示例 1：
 * 输入：strs = ["ca","bb","ac"]
 * 输出：1
 * 解释：
 * 删除第一列后，strs = ["a", "b", "c"]。
 * 现在 strs 中元素是按字典排列的 (即，strs[0] <= strs[1] <= strs[2])。
 * 我们至少需要进行 1 次删除，因为最初 strs 不是按字典序排列的，所以答案是 1。
 * <p>
 * 示例 2：
 * 输入：strs = ["xc","yb","za"]
 * 输出：0
 * 解释：
 * strs 的列已经是按字典序排列了，所以我们不需要删除任何东西。
 * 注意 strs 的行不需要按字典序排列。
 * 也就是说，strs[0][0] <= strs[0][1] <= ... 不一定成立。
 * <p>
 * 示例 3：
 * 输入：strs = ["zyx","wvu","tsr"]
 * 输出：3
 * 解释：
 * 我们必须删掉每一列。
 * <p>
 * 提示：
 * n == strs.length
 * 1 <= n <= 100
 * 1 <= strs[i].length <= 100
 * strs[i] 由小写英文字母组成
 */
public class DeleteColumnsToMakeSortedII implements Answer {

    public static void main(String[] args) {
        DeleteColumnsToMakeSortedII ans = new DeleteColumnsToMakeSortedII();
        System.out.println(ans.minDeletionSize(ans.initData()));
    }

    /**
     * 在方法1中，会发现我们只需要记录上一列中相等的列就行，而不需要全部记录再比较。
     * <p>
     * 核心思路是记录每一列的”割“信息。在第一个例子中，A = ["axx","ayy","baa","bbb","bcc"]（R 也是相同的定义），
     * 第一列将条件 R[0] <= R[1] <= R[2] <= R[3] <= R[4] 切成了 R[0] <= R[1] 和 R[2] <= R[3] <= R[4]。
     * 也就是说，"a" == column[1] != column[2] == "b" ”切割“了 R 中的一个条件。
     * <p>
     * 从更高层面上说，我们的算法只需要考虑新加进的列是否保证有序。通过维护”割“的信息，只需要比较新列的字符。
     *
     */
    public int minDeletionSize(String[] strs) {
        int N = strs.length;
        int W = strs[0].length();
        // cuts[j] is true : we don't need to check any new A[i][j] <= A[i][j+1]，
        // 需要再次比较的行号
        boolean[] cuts = new boolean[N - 1];

        int ans = 0;
        search:
        // 列
        for (int j = 0; j < W; ++j) {
            // Evaluate whether we can keep this column
            for (int i = 0; i < N - 1; ++i)
                if (!cuts[i] && strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    // Can't keep the column - delete and continue
                    ans++;
                    continue search;
                }

            // Update 'cuts' information，i是行，标记为true表明本列的下一行是大于我的，不是等于的，说明前一列已经满足了，不需要比较了
            for (int i = 0; i < N - 1; ++i) {
                if (strs[i].charAt(j) < strs[i + 1].charAt(j)) {
                    cuts[i] = true;
                }
            }
        }

        return ans;
    }

    /**
     * 初版贪心
     * 最前面的保证有序就可以了，因此靠前面的有序就直接保留，无序的直接删除。
     * 但是会存在相等的情况。因此我们先把之前的有序的列给保存下来，最后再加上第二列进行比较，如果第二列也有序，但是存在相等的，则继续比较第三列。
     * 如果第一列加上第二列是无序的，则删除第二列。
     * 以此类推
     * 复杂度：O(NW^2)，很差
     */
    public int minDeletionSize2(String[] A) {
        int N = A.length;
        int W = A[0].length();
        int ans = 0;

        // cur : all rows we have written
        // For example, with A = ["abc","def","ghi"] we might have
        // cur = ["ab", "de", "gh"].
        String[] cur = new String[N];
        for (int j = 0; j < W; ++j) {
            // cur2 : What we potentially can write, including the
            //        newest column col = [A[i][j] for i]
            // Eg. if cur = ["ab","de","gh"] and col = ("c","f","i"),
            // then cur2 = ["abc","def","ghi"].
            String[] cur2 = Arrays.copyOf(cur, N);
            for (int i = 0; i < N; ++i) {
                cur2[i] += A[i].charAt(j);
            }
            if (isSorted(cur2)) {
                cur = cur2;
            } else {
                ans++;
            }
        }

        return ans;
    }

    public boolean isSorted(String[] A) {
        for (int i = 0; i < A.length - 1; ++i) {
            if (A[i].compareTo(A[i + 1]) > 0) {
                return false;
            }
        }

        return true;
    }


    @Override
    public void answer() {

    }

    @Override
    public String[] initData() {
        // return new String[]{"ca", "bb", "ac"};
        // return new String[]{"xc","yb","za"};
        // return new String[]{"zyx", "wvu", "tsr"};
        // return new String[]{"xga", "xfb", "yfa"};
        return new String[]{"vdy", "vei", "zvc", "zld"};
    }
}
