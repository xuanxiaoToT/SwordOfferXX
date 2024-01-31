package com.xx.basicDs.queue;

import com.xx.Answer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/31
 * <p>
 * Dota2参议院
 * LeetCode 649. Medium
 * <p>
 * Dota2 的世界里有两个阵营：Radiant（天辉）和 Dire（夜魇）
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。
 * 他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的 一 项：
 * <p>
 * 禁止一名参议员的权利：参议员可以让另一位参议员在这一轮和随后的几轮中丧失 所有的权利 。
 * 宣布胜利：如果参议员发现有权利投票的参议员都是 同一个阵营的 ，他可以宣布胜利并决定在游戏中的有关变化。
 * 给你一个字符串 senate 代表每个参议员的阵营。字母 'R' 和 'D'分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
 * <p>
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。
 * 这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。
 * 输出应该是 "Radiant" 或 "Dire" 。
 * <p>
 * 示例 1：
 * 输入：senate = "RD"
 * 输出："Radiant"
 * 解释：
 * 第 1 轮时，第一个参议员来自 Radiant 阵营，他可以使用第一项权利让第二个参议员失去所有权利。
 * 这一轮中，第二个参议员将会被跳过，因为他的权利被禁止了。
 * 第 2 轮时，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人。
 * <p>
 * 示例 2：
 * 输入：senate = "RDD"
 * 输出："Dire"
 * 解释：
 * 第 1 轮时，第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利。
 * 这一轮中，第二个来自 Dire 阵营的参议员会将被跳过，因为他的权利被禁止了。
 * 这一轮中，第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利。
 * 因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
 * <p>
 * 提示：
 * n == senate.length
 * 1 <= n <= 10^4
 * senate[i] 为 'R' 或 'D'
 * <p>
 * Tag:贪心算法  队列
 */
public class DotaSenate implements Answer {

    public static void main(String[] args) {
        new DotaSenate().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String senate = "RDD";
        System.out.println(predictPartyVictory(senate));
    }

    /**
     * 贪心算法，采用队列的数据结构。
     * <p>
     * 创建两个队列R和D，存入他们在字符串中的下标，在比较下标大小（出队）来决定谁先执行权力（废除），
     * 执行权力后入队，被废除的就不用入队了，注意入队时的下标赋值多少呢？要排到所有第一轮成员的后面，
     * 因为可能在第二轮投票之前被第一轮的反派废除。
     */
    public String predictPartyVictory(String senate) {
        Queue<Integer> queueD = new LinkedList<>();
        Queue<Integer> queueR = new LinkedList<>();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'D') {
                queueD.add(i);
            } else {
                queueR.add(i);
            }
        }
        while (!queueD.isEmpty() & !queueR.isEmpty()) {
            Integer pollD = queueD.poll();
            Integer pollR = queueR.poll();
            if (pollD > pollR) {
                //放到队尾，不影响本轮循环
                queueR.add(pollR + senate.length());
            } else {
                queueD.add(pollD + senate.length());
            }
        }
        return queueR.isEmpty() ? "Dire" : "Radiant";
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
