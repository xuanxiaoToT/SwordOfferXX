package com.xx.basicDs.number.位运算;

import com.xx.Answer;

/**
 * 配对交换
 * LeetCode Easy
 * <p>
 * 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位 0 与位 1 交换，位 2 与位 3 交换，以此类推）。
 * <p>
 * 示例 1：
 * 输入：num = 2（或者 0b10）
 * 输出：1 (或者 0b01)
 * <p>
 * 示例 2：
 * 输入：num = 3
 * 输出：3
 * <p>
 * 提示:
 * num 的范围在[0, 230 - 1]之间，不会发生整数溢出。
 * <p>
 * Tag：位运算  位移
 */
public class Exchange implements Answer {
    public static void main(String[] args) {
        new Exchange().answerOne();
    }

    @Override
    public void answerOne() {
        // int num = 3;
        // int num = 2;
        // int num = 1;
        int num = 571603718;
        System.out.println(exchangeBits(num));
    }

    /**
     * 注意是12交换 34交换
     * 所以分别提出奇数部分和偶数部分，然后再组合就可以了
     * <p>
     * 例如：
     * 1234 分解为：
     * 奇数：0204
     * 偶数：1030
     * 最后需要的结果是：2143
     * 所以奇数左移，偶数右移(右侧必然有个0)
     */
    public int exchangeBits(int num) {
        int result = 0;
        int jiShu = 0;
        int ouShu = 0;
        int i = 0;
        boolean flag = true;
        while (num != 0) {
            int temp = num & 1;
            temp = temp << i;
            if (flag == true) {
                jiShu = jiShu | temp;
            } else {
                ouShu = ouShu | temp;
            }
            flag = !flag;
            i++;
            num = num >> 1;
        }
        jiShu = jiShu << 1;
        ouShu = ouShu >> 1;
        return ouShu | jiShu;
    }

    @Override
    public Object initData() {
        return null;
    }
}
