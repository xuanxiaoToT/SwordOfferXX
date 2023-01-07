package com.xx.basicDs.hash;

import java.util.TreeMap;

/**
 * @author 玄霄
 * @CreateDate 2022/10/8
 * 日程表
 * 请实现一个类型MyCalendar用来记录自己的日程安排，
 * 该类型用方法book（int start，int end）在日程表中添加一个时
 * 间区域为[start，end）的事项（这是一个半开半闭区间）。如果
 * [start，end）中之前没有安排其他事项，则成功添加该事项并返回
 * true；否则，不能添加该事项，并返回false。
 */
public class MyCalendar {

    public static void main(String[] args) {

    }

    public MyCalendar() {

    }

    /**
     * 无脑遍历，用一个set存储。再用一个dto类来放置开始结束。
     * 遍历比较即可。
     */
    public boolean book(int start, int end) {
        // 方法略
        return false;
    }

    //用treeMap来实现
    private TreeMap<Integer, Integer> map;

    /**
     * 如果待添加的事项占用的时间区间是[m，n），就需要找出开始时
     * 间小于m的所有事项中开始最晚的一个，以及开始时间大于m的所有事
     * 项中开始最早的一个。如果待添加的事项和这两个事项都没有重叠，
     * 那么该事项可以添加在日程表中。
     */
    public boolean book1(int start, int end) {
        if (map.containsKey(start)) {
            return false;
        }

        Integer beforeEnd = map.floorEntry(start).getValue();
        Integer beforeStart = map.ceilingEntry(end).getValue();
        return start >= beforeEnd && end < beforeStart;
    }


}