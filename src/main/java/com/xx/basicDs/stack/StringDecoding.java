package com.xx.basicDs.stack;

import com.xx.Answer;
import com.xx.util.StringUtil;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/9
 * <p>
 * 字符串解码
 * LeetCode 394
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 注意 k 保证为正整数。你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，
 * 且输入的方括号总是符合格式要求的。此外，你可以认为原始数据不包含数字，
 * 所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * <p>
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * <p>
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * <p>
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 */
public class StringDecoding implements Answer {

    //@AllArgsConstructor
    //@Data
    public static class StringDecodingTempData {
        private String str;
        private int num;

        public StringDecodingTempData(String str, int num) {
            this.str = str;
            this.num = num;
        }

        public String getStr() {
            return str;
        }

        public int getNum() {
            return num;
        }
    }

    public static void main(String[] args) {
        new StringDecoding().answer();
    }

    /**
     * 解1：利用栈
     * 思考的时候：先想清楚要存储什么信息，因为要存这些信息所以才选择了栈。
     * 思考：每次遇到'['时，都需要先把一个str和int存进去
     */
    @Override
    public void answer() {
        String data = initData();
        Stack<StringDecodingTempData> stack = new Stack<>();
        StringBuilder sbTemp = new StringBuilder();
        int num = 0;
        for (int i = 0; i < data.length(); i++) {
            Character c = data.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + Integer.parseInt(String.valueOf(c));
            } else if ('[' == c) {
                stack.add(new StringDecodingTempData(sbTemp.toString(), num));
                sbTemp = new StringBuilder();
                num = 0;
            } else if (']' == c) {
                StringDecodingTempData pop = stack.pop();
                int numPop = pop.getNum();
                String repeatStr = StringUtil.repeatStr(sbTemp.toString(), Math.max(0, numPop));
                sbTemp = new StringBuilder();
                sbTemp.append(pop.getStr()).append(repeatStr);
                num = 0;
            } else {
                sbTemp.append(c);
            }
        }
        System.out.println(sbTemp.toString());
    }


    /**
     * 输出数据
     */
    @Override
    public String initData() {
        //return "3[a2[c]]";
        //return "3[a]2[bc]";
        return "2[abc]3[cd]ef";
    }
}
