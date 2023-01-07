package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Stack;

/**
 * @author 玄霄
 * @CreateDate 2022/9/6
 * 小行星碰撞
 * 输入一个表示小行星的数组，数组中每个数字的绝对值
 * 表示小行星的大小，数字的正负号表示小行星运动的方向，正号表
 * 示向右飞行，负号表示向左飞行。如果两颗小行星相撞，那么体积
 * 较小的小行星将会爆炸最终消失，体积较大的小行星不受影响。如
 * 果相撞的两颗小行星大小相同，那么它们都会爆炸消失。飞行方向
 * 相同的小行星永远不会相撞。求最终剩下的小行星。例如，有6颗小
 * 行星[4，5，-6，4，8，-5]，如图6.2所示（箭头表示飞行的方
 * 向），它们相撞之后最终剩下3颗小行星[-6，4，8]。
 */
public class AsteroidCollision implements Answer {
    public static void main(String[] args) {
        new AsteroidCollision().answerOne();
    }

    /**
     * 这符合“后入先出”的顺序，所以可以考虑用栈实现这个数据容器。
     */
    @Override
    public void answerOne() {
        Stack<Integer> stack = new Stack<>();
        int[] dateList = initData();
        for (int date : dateList) {
            if (stack.isEmpty()) {
                stack.push(date);
            } else {
                // 向左
                if (date < 0) {
                    boolean flag = false;
                    while (!stack.isEmpty()) {
                        Integer peek = stack.peek();
                        if (peek < 0) {
                            flag = true;
                            break;
                        } else {
                            if (Math.abs(peek) < Math.abs(date)) {
                                stack.pop();
                                flag = true;
                            } else {
                                break;
                            }
                        }
                    }
                    if (flag) {
                        stack.push(date);
                    }
                } else {
                    stack.push(date);
                }
            }
        }
        for (Integer integer : stack) {
            System.out.println(integer);
        }
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{4, 5, -6, 4, 8, -5};
    }
}