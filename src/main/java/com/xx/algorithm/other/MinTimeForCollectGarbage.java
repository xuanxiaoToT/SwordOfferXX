package com.xx.algorithm.other;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/11
 * <p>
 * 收集垃圾的最少总时间
 * LeetCode 2391. Medium
 * <p>
 * 给你一个下标从 0 开始的字符串数组 garbage ，其中 garbage[i] 表示第 i 个房子的垃圾集合。
 * garbage[i] 只包含字符 'M' ，'P' 和 'G' ，但可能包含多个相同字符，每个字符分别表示一单位的金属、纸和玻璃。
 * 垃圾车收拾 一 单位的任何一种垃圾都需要花费 1 分钟。
 * <p>
 * 同时给你一个下标从 0 开始的整数数组 travel ，其中 travel[i] 是垃圾车从房子 i 行驶到房子 i + 1 需要的分钟数。
 * 城市里总共有三辆垃圾车，分别收拾三种垃圾。每辆垃圾车都从房子 0 出发，按顺序 到达每一栋房子。但它们 不是必须 到达所有的房子。
 * 任何时刻只有 一辆 垃圾车处在使用状态。当一辆垃圾车在行驶或者收拾垃圾的时候，另外两辆车 不能 做任何事情。
 * 请你返回收拾完所有垃圾需要花费的 最少 总分钟数。
 * <p>
 * 示例 1：
 * 输入：garbage = ["G","P","GP","GG"], travel = [2,4,3]
 * 输出：21
 * 解释：
 * 收拾纸的垃圾车：
 * 1. 从房子 0 行驶到房子 1
 * 2. 收拾房子 1 的纸垃圾
 * 3. 从房子 1 行驶到房子 2
 * 4. 收拾房子 2 的纸垃圾
 * 收拾纸的垃圾车总共花费 8 分钟收拾完所有的纸垃圾。
 * 收拾玻璃的垃圾车：
 * 1. 收拾房子 0 的玻璃垃圾
 * 2. 从房子 0 行驶到房子 1
 * 3. 从房子 1 行驶到房子 2
 * 4. 收拾房子 2 的玻璃垃圾
 * 5. 从房子 2 行驶到房子 3
 * 6. 收拾房子 3 的玻璃垃圾
 * 收拾玻璃的垃圾车总共花费 13 分钟收拾完所有的玻璃垃圾。
 * 由于没有金属垃圾，收拾金属的垃圾车不需要花费任何时间。
 * 所以总共花费 8 + 13 = 21 分钟收拾完所有垃圾。
 * <p>
 * 示例 2：
 * 输入：garbage = ["MMM","PGM","GP"], travel = [3,10]
 * 输出：37
 * 解释：
 * 收拾金属的垃圾车花费 7 分钟收拾完所有的金属垃圾。
 * 收拾纸的垃圾车花费 15 分钟收拾完所有的纸垃圾。
 * 收拾玻璃的垃圾车花费 15 分钟收拾完所有的玻璃垃圾。
 * 总共花费 7 + 15 + 15 = 37 分钟收拾完所有的垃圾。
 * <p>
 * 提示：
 * 2 <= garbage.length <= 10^5
 * garbage[i] 只包含字母 'M' ，'P' 和 'G' 。
 * 1 <= garbage[i].length <= 10
 * travel.length == garbage.length - 1
 * 1 <= travel[i] <= 100
 * <p>
 * Tag:理解题意后，跟着来就行.
 */
public class MinTimeForCollectGarbage implements Answer {

    public static void main(String[] args) {
        new MinTimeForCollectGarbage().answerOne();
    }

    /**
     * 解:
     * 优化点1：字符遍历可以省？可以，因为总是要加
     * 优化点2：travel每次都要遍历三遍，能不能省？ travel的值取决于结束后，三个last的最大值
     * 知识点：String.indexOf:存在返回第一个字符索引位置,不存才返回-1，效率要比contains高
     */
    @Override
    public void answerOne() {
        //String[] garbage = {"G", "P", "GP", "GG"};
        //int[] travel = {2, 4, 3};

        String[] garbage = {"MMM", "PGM", "GP"};
        int[] travel = {3, 10};
        System.out.println(garbageCollection(garbage, travel));
    }

    /**
     *
     */
    public int garbageCollection(String[] garbage, int[] travel) {
        int result = 0;
        int lastM = -1;
        int lastP = -1;
        int lastG = -1;

        //M P G
        for (int i = 0; i < garbage.length; i++) {
            String gar = garbage[i];
            result += gar.length();
            if (gar.indexOf("M")>-1) {
                lastM = i;
            }
            if (gar.indexOf("P")>-1) {
                lastP = i;
            }
            if (gar.indexOf("G")>-1) {
                lastG = i;
            }
        }
        result = result + getResult(travel, lastM, lastP, lastG);
        return result;
    }

    private int getResult(int[] travel, int lastM, int lastP, int lastG) {
        int result = 0;
        for (int i = 0; i < travel.length; i++) {
            if (lastM > -1 && i < lastM) {
                result += travel[i];
            }
            if (lastP > -1 && i < lastP) {
                result += travel[i];
            }
            if (lastG > -1 && i < lastG) {
                result += travel[i];
            }
        }
        return result;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
