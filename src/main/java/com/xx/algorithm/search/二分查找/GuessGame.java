package com.xx.algorithm.search.二分查找;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/22
 * <p>
 * 猜数字大小
 * LeetCode 374. Easy
 * <p>
 * 猜数字游戏的规则如下：
 * <p>
 * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * <p>
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 * <p>
 * 示例 1：
 * 输入：n = 10, pick = 6
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：n = 1, pick = 1
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：n = 2, pick = 1
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：n = 2, pick = 2
 * 输出：2
 * <p>
 * 提示：
 * 1 <= n <= 2^31 - 1
 * 1 <= pick <= n
 *
 * Tag: 二分查找
 */
public class GuessGame implements Answer {
    public static void main(String[] args) {
        new GuessGame().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        int n = 2126753390;
        System.out.println(guessNumber(n));
    }

    int pick = 1702766719 ;

    public int guessNumber(int n) {
        long start = 1;
        long end = n;
        // 此处是小于等于
        while (start <= end) {
            //  int mid = start + (end - start) / 2;
            int mid = (int) ((start + end) / 2);
            int guess = guess(mid);
            if (guess == 0) {
                return mid;
            }
            if (guess == -1) {
                end = mid - 1;
            }
            if (guess == 1) {
                start = mid + 1;
            }
        }
        return -1;
    }

    int guess(int num) {
        if (num == pick) {
            return 0;
        }
        if (num > pick) {
            return -1;
        }
        return 1;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
