package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/10
 * <p>
 * 验证回文串
 * LeetCode 125.  简单
 * <p>
 * 给定一个字符串，请判断它是不是回文。假设只需要考
 * 虑字母和数字字符，并忽略大小写。例如，"Was it a cat I
 * saw？"是一个回文字符串，而"race a car"不是回文字符串。
 * <p>
 * 举一反三：给定一个字符串，请判断如果最多从字符串中删除一个
 * 字符能不能得到一个回文字符串。例如，如果输入字符串"abca"，
 * 由于删除字符'b'或'c'就能得到一个回文字符串，因此输出为
 * true。
 * 找到不一致处的点，然后跳过left点或跳过right点，判断剩下的点是否是回文即可。
 * 也是用双指针
 * <p>
 * 示例 1：
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 * <p>
 * 示例 2：
 * 输入：s = "race a car"
 * 输出：false
 * 解释："raceacar" 不是回文串。
 * <p>
 * 示例 3：
 * 输入：s = " "
 * 输出：true
 * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
 * 由于空字符串正着反着读都一样，所以是回文串。
 */
public class ValidPalindrome implements Answer {

    public static void main(String[] args) {
        new ValidPalindrome().answerOne();
    }

    /**
     * something
     */
    @Override
    public void answerOne() {
        String oriStr = initData();
        if (oriStr.length() == 0) {
            System.out.println(true);
            return;
        }
        int left = 0;
        int right = oriStr.length() - 1;
        while (left < right) {
            while (left < oriStr.length() && !Character.isLetterOrDigit(oriStr.charAt(left))) {
                left++;
            }
            while (right >= 0 && !Character.isLetterOrDigit(oriStr.charAt(right))) {
                right--;
            }
            if (left >= oriStr.length() || right < 0) {
                System.out.println(false);
                return;
            }
            if (Character.toLowerCase(oriStr.charAt(left)) == Character.toLowerCase(oriStr.charAt(right))) {
                left++;
                right--;
            } else {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    /**
     * something
     */
    @Override
    public String initData() {
        //return "Was it a cat I saw？";
        // return "A man，a plan,a canal: panama";
        //return "race a car";
        return "++";
    }
}
