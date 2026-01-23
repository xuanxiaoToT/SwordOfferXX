package com.xx.basicDs.graph.disjointSet;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2023/1/3
 * <p>
 * 相似的字符串
 * <p>
 * 如果交换字符串X中的两个字符就能得到字符串Y，那么
 * 两个字符串X和Y相似。
 * <p>
 * 例如，字符串"tars"和"rats"相似（交换下
 * 标为0和2的两个字符）、字符串"rats"和"arts"相似（交换下标为0
 * 和1的字符），但字符串"star"和"tars"不相似。
 * 输入一个字符串数组，根据字符串的相似性分组，请问能把输
 * 入数组分成几组？如果一个字符串至少和一组字符串中的一个相
 * 似，那么它就可以放到该组中。假设输入数组中的所有字符串的长
 * 度相同并且两两互为变位词
 * <p>
 * 例如，输入数组为
 * ["tars"，"rats"，"arts"，"star"]，可以分成两组，一组为
 * {"tars"，"rats"，"arts"}，另一组为{"star"}。
 */
public class SimilarStrings implements Answer {

    public static void main(String[] args) {
        new SimilarStrings().answer();
    }

    /**
     * 解:思路与{@link CircleOfFriends}类似
     */
    @Override
    public void answer() {
        String[] data = initData();
        int[] father = new int[data.length];
        Arrays.parallelSetAll(father, i -> i);
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (isSimilar(data, i, j)) {
                    unionGraph(father, i, j);
                }
            }
        }
        System.out.println(Arrays.toString(father));
    }

    // 尝试用遍历法去做试试
    private void answerTwo() {

    }

    //将i，j放到一个graph中
    private void unionGraph(int[] father, int i, int j) {
        int rootI = findRoot(father, i);
        int rootJ = findRoot(father, j);
        //加到一个图里
        if (rootI != rootJ) {
            father[rootJ] = rootI;
        }
    }

    private int findRoot(int[] father, int i) {
        if (father[i] != i) {
            return findRoot(father, father[i]);
        }
        return father[i];
    }

    // 是否是相似字符串
    private boolean isSimilar(String[] data, int i, int j) {
        String strI = data[i];
        String strJ = data[j];
        if (strI.length() != strJ.length()) {
            return false;
        }

        int inequality = 0;
        for (int k = 0; k < strI.length(); k++) {
            if (strI.charAt(k) != strJ.charAt(k)) {
                inequality++;
            }
        }
        return inequality == 2;
    }

    /**
     * 输入数据
     */
    @Override
    public String[] initData() {
        return new String[]{"tars", "rats", "arts", "star"};
    }
}
