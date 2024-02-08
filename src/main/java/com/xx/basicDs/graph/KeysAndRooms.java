package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/8
 * <p>
 * 钥匙和房间
 * LeetCode 841. Medium
 * <p>
 * 有 n 个房间，房间按从 0 到 n - 1 编号。最初，除 0 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。
 * 当你进入一个房间，你可能会在里面找到一套不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。
 * 给你一个数组 rooms 其中 rooms[i] 是你进入 i 号房间可以获得的钥匙集合。如果能进入 所有 房间返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入：rooms = [[1],[2],[3],[]]
 * 输出：true
 * 解释：
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * <p>
 * 示例 2：
 * 输入：rooms = [[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * <p>
 * 提示：
 * n == rooms.length
 * 2 <= n <= 1000
 * 0 <= rooms[i].length <= 1000
 * 1 <= sum(rooms[i].length) <= 3000
 * 0 <= rooms[i][j] < n
 * 所有 rooms[i] 的值 互不相同
 * <p>
 * Tag：广度优先遍历
 */
public class KeysAndRooms implements Answer {
    public static void main(String[] args) {
        new KeysAndRooms().answerOne();
    }

    @Override
    public void answerOne() {
        // int[][] rooms = {{1}, {2}, {3}, {}};
        int[][] rooms = {{1, 3}, {3, 0, 1}, {2}, {0}};
        //System.out.println(whetherOpen(rooms));
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Map<Integer, List<Integer>> graph = initGraph(rooms);
        int[] flag = new int[rooms.size()];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> ints = graph.get(0);
        flag[0] = 1;
        for (int anInt : ints) {
            queue.add(anInt);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer nextRoom = queue.poll();
                if (flag[nextRoom] == 0) {
                    flag[nextRoom] = 1;
                    List<Integer> nexts = graph.get(nextRoom);
                    for (int next : nexts) {
                        if (flag[next] == 0) {
                            queue.add(next);
                        }
                    }
                }
            }
        }
        for (int value : flag) {
            if (value == 0) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, List<Integer>> initGraph(List<List<Integer>> rooms) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < rooms.size(); i++) {
            map.put(i, rooms.get(i));
        }
        return map;
    }

    @Override
    public Object initData() {
        return null;
    }
}