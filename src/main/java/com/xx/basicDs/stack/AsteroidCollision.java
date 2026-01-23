package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/6
 * <p>
 * 小行星碰撞
 * LeetCode 735 Medium
 * <p>
 * 输入一个表示小行星的数组，数组中每个数字的绝对值
 * 表示小行星的大小，数字的正负号表示小行星运动的方向，正号表示向右飞行，
 * 负号表示向左飞行。如果两颗小行星相撞，那么体积较小的小行星将会爆炸最终消失，
 * 体积较大的小行星不受影响。如果相撞的两颗小行星大小相同，那么它们都会爆炸消失。
 * 飞行方向相同的小行星永远不会相撞。求最终剩下的小行星。
 * <p>
 * 示例 1：
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * <p>
 * 示例 2：
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * <p>
 * 示例 3：
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * <p>
 * 提示：
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 * <p>
 * Tag：先进后出  栈
 */
public class AsteroidCollision implements Answer {

    public static void main(String[] args) {
        new AsteroidCollision().answer();
    }

    /**
     * 这符合“后入先出”的顺序，所以可以考虑用栈实现这个数据容器。
     */
    @Override
    public void answer() {
        int[] dateList = initData();
        System.out.println(Arrays.toString(asteroidCollision(dateList)));
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int date : asteroids) {
            if (stack.isEmpty()) {
                stack.push(date);
            } else {
                // 向左
                if (date < 0) {
                    boolean addThisFlag = false;
                    while (!stack.isEmpty()) {
                        Integer peek = stack.peek();
                        if (peek < 0) {
                            addThisFlag = true;
                            break;
                        } else {
                            if (Math.abs(peek) == Math.abs(date)) {
                                stack.pop();
                                addThisFlag = false;
                                break;
                            }
                            if (Math.abs(peek) < Math.abs(date)) {
                                stack.pop();
                                addThisFlag = true;
                            } else {
                                addThisFlag = false;
                                break;
                            }
                        }
                    }
                    if (addThisFlag) {
                        stack.push(date);
                    }
                } else {
                    stack.push(date);
                }
            }
        }
        int[] result = new int[stack.size()];
        int i = 0;
        // stack的for循环是正向遍历，如果挨个pop，则是反向。
        for (Integer integer : stack) {
            result[i++] = integer;
        }
        return result;
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        //return new int[]{4, 5, -6, 4, 8, -5};
        return new int[]{10, 2, -5};
    }
}