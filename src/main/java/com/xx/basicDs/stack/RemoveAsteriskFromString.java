package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/30
 * <p>
 * 从字符串中移除星号
 * LeetCode 2390.  Medium
 * <p>
 * 给你一个包含若干星号 * 的字符串 s 。
 * 在一步操作中，你可以：
 * 选中 s 中的一个星号。
 * 移除星号 左侧 最近的那个 非星号 字符，并移除该星号自身。
 * 返回移除 所有 星号之后的字符串。
 * <p>
 * 注意：
 * 生成的输入保证总是可以执行题面中描述的操作。
 * 可以证明结果字符串是唯一的。
 * <p>
 * 示例 1：
 * 输入：s = "leet**cod*e"
 * 输出："lecoe"
 * 解释：从左到右执行移除操作：
 * - 距离第 1 个星号最近的字符是 "leet**cod*e" 中的 't' ，s 变为 "lee*cod*e" 。
 * - 距离第 2 个星号最近的字符是 "lee*cod*e" 中的 'e' ，s 变为 "lecod*e" 。
 * - 距离第 3 个星号最近的字符是 "lecod*e" 中的 'd' ，s 变为 "lecoe" 。
 * 不存在其他星号，返回 "lecoe" 。
 * <p>
 * 示例 2：
 * 输入：s = "erase*****"
 * 输出：""
 * 解释：整个字符串都会被移除，所以返回空字符串。
 * <p>
 * 提示：
 * 1 <= s.length <= 105
 * s 由小写英文字母和星号 * 组成
 * s 可以执行上述操作
 * <p>
 * Tag:快慢指针  栈
 */
public class RemoveAsteriskFromString implements Answer {
    public static void main(String[] args) {
        new RemoveAsteriskFromString().answer();
    }

    @Override
    public void answer() {
        String str = "erase****";
        System.out.println(removeAsterisk(str));
    }

    public String removeAsterisk(String str) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == '*') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        if (stack.isEmpty()) {
            return "";
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    /**
     * LeetCode 最快解法
     * 快慢指针法
     */
    public String removeStars(String s) {
        char[] array = s.toCharArray();
        int slow = 0, fast = 0;
        while (fast < array.length) {
            if (array[fast] == '*') {
                slow--;
                slow = Math.max(0, slow);
            } else {
                array[slow++] = array[fast];
            }
            fast++;
        }
        return new String(array, 0, slow);
    }

    @Override
    public Object initData() {
        return null;
    }
}
