package com.xx.basicDs.string;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/28
 * <p>
 * N 字形变换
 * LeetCode 06. 中等
 * <p>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * <p>
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * <p>
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 */
public class N_ShapedTransformation implements Answer {

    public static void main(String[] args) {
        new N_ShapedTransformation().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String data = initData();
        System.out.println(convert(data, 3));
        System.out.println(convert(data, 4));
    }


    /**
     * 字符串 s 是以 ZZZ 字形为顺序存储的字符串，目标是按行打印。
     * 则容易发现：按顺序遍历字符串 s 时，每个字符 c 在 N 字形中对应的 行索引 先从 s1 增大至 sn，
     * 再从 sn减小至 s1 …… 如此反复。
     * 因此解决方案为：模拟这个行索引的变化，在遍历 s 中把每个字符填到正确的行 res[i] 。
     * <p>
     * 算法流程：
     * 按顺序遍历字符串 s ：
     * 1. res[i] += c： 把每个字符 c 填入对应行 si
     * 2. i += flag： 更新当前字符 c 对应的行索引；
     * 3. flag = - flag： 在达到 Z 字形转折点时，执行反向。
     */
    public String convert(String s, int numRows) {
        if (numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) flag = -flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) res.append(row);
        return res.toString();
    }


    /**
     * 输出数据
     */
    @Override
    public String initData() {
        return "PAYPALISHIRING";
    }
}
