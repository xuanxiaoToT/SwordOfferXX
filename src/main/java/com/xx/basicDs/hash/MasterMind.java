package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.Arrays;


/**
 * 珠玑妙算
 * LeetCode Easy
 * <p>
 * 珠玑妙算游戏（the game of master mind）的玩法如下。
 * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。
 * 例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。
 * 要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
 * <p>
 * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
 * <p>
 * 示例：
 * 输入： solution="RGBY",guess="GGRR"
 * 输出： [1,1]
 * 解释： 猜中1次，伪猜中1次。
 * <p>
 * 提示：
 * len(solution) = len(guess) = 4
 * solution和guess仅包含"R","G","B","Y"这4种字符
 * <p>
 * Tag: HashMap比较 字符串遍历
 */
public class MasterMind implements Answer {
    public static void main(String[] args) {
        new MasterMind().answer();
    }

    @Override
    public void answer() {
        String solution = "RGBY", guess = "GGRR";
        System.out.println(Arrays.toString(masterMind(solution, guess)));
    }

    public int[] masterMind(String solution, String guess) {
        int[] result = new int[2];
        int[] solMap = new int[4];
        int[] guessMap = new int[4];
        for (int i = 0; i < solution.length(); i++) {
            char solChar = solution.charAt(i);
            char guessChar = guess.charAt(i);
            if (solChar == guessChar) {
                result[0]++;
            }
            mapSet(solMap, solChar);
            mapSet(guessMap, guessChar);
        }
        int falseGuess = 0;
        for (int i = 0; i < 4; i++) {
            falseGuess += Math.min(solMap[i], guessMap[i]);
        }
        result[1] = Math.max(falseGuess - result[0], 0);
        return result;
    }

    private void mapSet(int[] map, char c) {
        if (c == 'R') {
            map[0]++;
        }
        if (c == 'G') {
            map[1]++;
        }
        if (c == 'B') {
            map[2]++;
        }
        if (c == 'Y') {
            map[3]++;
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}
