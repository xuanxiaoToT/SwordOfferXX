package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/1/19
 * <p>
 * 压缩字符串
 * LeetCode 443.  Medium
 * <p>
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 * <p>
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 * <p>
 * 示例 1：
 * 输入：chars = ["a","a","b","b","c","c","c"]
 * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 * 解释："aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 * <p>
 * 示例 2：
 * 输入：chars = ["a"]
 * 输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
 * 解释：唯一的组是“a”，它保持未压缩，因为它是一个字符。
 * <p>
 * 示例 3：
 * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
 * 解释：由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
 * <p>
 * 提示：
 * 1 <= chars.length <= 2000
 * chars[i] 可以是小写英文字母、大写英文字母、数字或符号
 * <p>
 * Tag: 字符串遍历
 */
public class StringCompression implements Answer {
    public static void main(String[] args) {
        new StringCompression().answerOne();
    }

    @Override
    public void answerOne() {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(returnLengthStringCompression(chars));
    }

    public int returnLengthStringCompression(char[] chars) {
        int result = 0;
        int tempLength = 0;
        Character last = null;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (last == null) {
                last = c;
                tempLength = 1;
                result++;
            } else {
                //重复时计数
                if (last.equals(c)) {
                    tempLength++;
                } else {
                    //不重复时，先把前面的数字加进去
                    if (tempLength > 1) {
                        String tempStr = String.valueOf(tempLength);
                        for (int j = 0; j < tempStr.length(); j++) {
                            chars[result] = tempStr.charAt(j);
                            result++;
                        }
                    }
                    //再把不重复的第一个字母，给加进去
                    tempLength = 1;
                    chars[result] = c;
                    result++;
                    last = c;
                }
            }
            if (i == chars.length - 1 && tempLength > 1) {
                String tempStr = String.valueOf(tempLength);
                for (int j = 0; j < tempStr.length(); j++) {
                    chars[result] = tempStr.charAt(j);
                    result++;
                }
            }
        }
        System.out.println(chars);
        return result;
    }

    /**
     * 官解:双指针法
     */
    public int compress(char[] chars) {
        //wi最终结果的指针，i遍历指针
        int wi = 0, i = 0;
        while (i < chars.length) {
            char c = chars[i];
            int num = 0;
            while (i < chars.length && c == chars[i]) {
                num++;
                i++;
            }
            chars[wi++] = c;

            // 将数字转化为字符串，采用短除法将子串长度倒序写入原字符串
            if (num > 1) {
                int ws = wi;
                while (num > 0) {
                    chars[wi++] = (char) (num % 10 + '0');
                    num = num / 10;
                }
                reverse(chars, ws, wi - 1);
            }
        }
        return wi;
    }

    public void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }


    @Override
    public Object initData() {
        return null;
    }
}