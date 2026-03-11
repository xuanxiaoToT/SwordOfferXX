package com.xx.中大机试;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collision {

    int m;
    Map<Integer, List<String>> map;

    public Collision(int m) {
        this.m = m;
        map = new HashMap<>(m);
    }

    public static void main(String[] args) {
        Collision collision = new Collision(10);
        System.out.println(collision.insertStr("a"));
        System.out.println(collision.insertStr("k"));
        System.out.println(collision.insertStr("u"));
        System.out.println(collision.insertStr("k"));
        System.out.println(collision.selectStr("a"));
        System.out.println(collision.selectStr("k"));
        System.out.println(collision.selectStr("u"));
        System.out.println(collision.selectStr("b"));
    }

    public static int elfHash(String str) {
        int h = 0;
        int g;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 对应 C 代码中的 h = (h << 4) + *str++
            h = (h << 4) + c;
            // 计算 g = h & 0xf0000000
            if ((g = h & 0xf0000000) != 0) {
                // 对应 h ^= g >> 24，Java 中用无符号右移 >>> 避免符号扩展
                h ^= (g >>> 24);
            }
            // 对应 h &= ~g，清除高4位
            h &= ~g;
        }
        return h;
    }

    public String insertStr(String str) {
        int hash = elfHash(str);
        int index = hash % m;
        if (map.containsKey(index)) {
            List<String> list = map.get(index);
            if (list.contains(str)) {
                return "existed";
            }
            // 冲突次数+1
            list.add(str);
        } else {
            List<String> set = new ArrayList<>();
            set.add(str);
            map.put(index, set);
        }
        return "insert ok";
    }

    public String selectStr(String str) {
        int hash = elfHash(str);
        int index = hash % m;
        if (map.containsKey(index)) {
            List<String> list = map.get(index);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(str)) {
                    return String.valueOf(i);
                }
            }
        }
        return "-1";
    }
}
