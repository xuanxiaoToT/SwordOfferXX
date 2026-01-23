package com.xx.algorithm.recursion;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * 汉诺塔问题
 * LeetCode Medium
 * <p>
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 * <p>
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 * <p>
 * 你需要原地修改栈。
 * <p>
 * 示例 1：
 * 输入：A = [2, 1, 0], B = [], C = []
 * 输出：C = [2, 1, 0]
 * <p>
 * 示例 2：
 * 输入：A = [1, 0], B = [], C = []
 * 输出：C = [1, 0]
 * <p>
 * 提示：
 * A 中盘子的数目不大于 14 个。
 * <p>
 * Tag：递归  深度优先遍历
 */
public class HanotaProblem implements Answer {
    public static void main(String[] args) {
        new HanotaProblem().answer();
    }

    @Override
    public void answer() {
        List<Integer> A = new ArrayList<>();
        A.add(2);
        A.add(1);
        A.add(0);
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        hanota(A, B, C);
        System.out.println(C);
    }

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanotaDiGui(A.size(), A, B, C);
    }

    /**
     * n:表示当前要移动的盘子数
     */
    private void hanotaDiGui(int n, List<Integer> from, List<Integer> aux, List<Integer> to) {
        // 递归终止条件：只有1个盘子时，直接移动（列表最后一个元素=栈顶小盘，对应单个盘子）
        if (n == 1) {
            // 取出from的最后一个元素（当前要移动的单个盘子），添加到to中
            Integer disk = from.remove(from.size() - 1);
            to.add(disk);
            // System.out.printf("将盘子%d从[%s]移动到[%s]%n", disk, from, to);
            return;
        }

        // 步骤1：将前n-1个盘子从from借助to，移动到aux
        hanotaDiGui(n - 1, from, to, aux);

        // 步骤2：将第n个盘子（from的最后一个元素=当前最大盘）从from移动到to
        Integer maxDisk = from.remove(from.size() - 1);
        to.add(maxDisk);
        // System.out.printf("将盘子%d从[%s]移动到[%s]%n", maxDisk, from, to);

        // 步骤3：将n-1个盘子从aux借助from，移动到to
        hanotaDiGui(n - 1, aux, from, to);
    }

    /**
     * 核心参数 n 不可缺失
     * <p>
     * 一、为什么无法用 from.isEmpty() 替代 n 的判断？
     * 1. 核心缺陷：无法实现 “n-1 个盘子” 的分层拆分（递归的核心逻辑）
     * 汉诺塔递归的灵魂是 “将 n 个盘子拆分为‘n-1 个小盘’和‘1 个大盘’”，
     * n 是精准标识 “当前要移动的盘子数量” 的核心参数，它能指导我们明确 “需要先移动多少个盘子到辅助柱”。
     * 而 from.isEmpty() 仅仅是一个 “状态判断”（判断起始柱是否有盘子），
     * 它无法区分 “当前要移动 3 个盘子”“要移动 2 个盘子” 还是 “要移动 1 个盘子”。没有 n 的约束，
     * 代码根本不知道 “该先移动多少个小盘到辅助柱”，无法完成递归的分层拆解，最终导致移动逻辑彻底混乱。
     */
    public void hanotaError(List<Integer> A, List<Integer> B, List<Integer> C) {

        if (A.isEmpty()) {
            return;
        }
        Integer popElement = popElement(A);
        // C的全部挪到B，
        hanota(C, A, B);
        // A的一个挪到C
        C.add(popElement);
        // 再把B全部挪到C
        hanota(B, A, C);
    }

    public Integer popElement(List<Integer> A) {
        Integer Apop = A.get(A.size() - 1);
        A.remove(A.size() - 1);
        return Apop;
    }

    @Override
    public Object initData() {
        return null;
    }
}
