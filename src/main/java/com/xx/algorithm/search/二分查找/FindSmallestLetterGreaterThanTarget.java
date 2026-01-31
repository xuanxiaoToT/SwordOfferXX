package com.xx.algorithm.search.二分查找;

import com.xx.Answer;

/**
 * 寻找比目标字母大的最小字母
 * LeetCode 744. Easy
 * <p>
 * 给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。
 * 返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。
 * <p>
 * 示例 1：
 * 输入: letters = ['c', 'f', 'j']，target = 'a'
 * 输出: 'c'
 * 解释：letters 中字典上比 'a' 大的最小字符是 'c'。
 * <p>
 * 示例 2:
 * 输入: letters = ['c','f','j'], target = 'c'
 * 输出: 'f'
 * 解释：letters 中字典顺序上大于 'c' 的最小字符是 'f'。
 * <p>
 * 示例 3:
 * 输入: letters = ['x','x','y','y'], target = 'z'
 * 输出: 'x'
 * 解释：letters 中没有一个字符在字典上大于 'z'，所以我们返回 letters[0]。
 * <p>
 * 提示：
 * 2 <= letters.length <= 10^4
 * letters[i] 是一个小写字母
 * letters 按非递减顺序排序
 * letters 最少包含两个不同的字母
 * target 是一个小写字母
 * <p>
 * Tag:二分查找
 *
 */
public class FindSmallestLetterGreaterThanTarget implements Answer {
    public static void main(String[] args) {
        new FindSmallestLetterGreaterThanTarget().answer();
    }

    @Override
    public void answer() {
        // char[] letters = {'c', 'f', 'j'};
        // char target = 'a';

        // char[] letters = {'c', 'f', 'j'};
        // char target = 'c';

        // char[] letters = {'a', 'a', 'b'};
        // char target = 'a';

        char[] letters = {'a', 'a', 'a'};
        char target = 'b';
        System.out.println(nextGreatestLetter(letters, target));
    }

    /**
     * 采用不+1的二分查找
     * 因为+1后有可能把那个刚好大于目标值的点给跳过
     * <p>
     * 因为不+1后，需要手动break，并再次验证
     */
    public char nextGreatestLetter(char[] letters, char target) {
        char res = letters[0];

        int left = 0;
        int right = letters.length - 1;
        while (left < right) {
            if (left + 1 == right) {
                break;
            }
            int mid = (right + left) / 2;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        for (int i = left; i <= right; i++) {
            if (letters[i] > target) {
                return letters[i];
            }
        }
        return res;
    }

    @Override
    public Object initData() {
        return null;
    }
}
