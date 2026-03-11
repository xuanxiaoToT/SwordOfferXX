package com.xx.中大机试;

import java.util.*;

public class FrogHomeOriginal {
    // 检查位置是否在禁止停留的河段内
    private static boolean isForbidden(int pos, List<int[]> forbidden) {
        for (int[] seg : forbidden) {
            if (pos >= seg[0] && pos <= seg[1]) {
                return true;
            }
        }
        return false;
    }

    // BFS 求解最少长跳跃次数
    private static int minLongJumps(int k, List<int[]> forbidden, int target) {
        // 队列：[当前位置, 长跳跃次数]
        Queue<int[]> queue = new LinkedList<>();
        // 记录访问过的位置
        Set<Integer> visited = new HashSet<>();

        queue.offer(new int[]{0, 0});
        visited.add(0);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int longJumps = current[1];

            // 到达目标位置，返回结果
            if (pos == target) {
                return longJumps;
            }

            // 尝试短跳跃 +2，不增加长跳次数
            int nextPos = pos + 2;
            if (nextPos <= target && !isForbidden(nextPos, forbidden) && !visited.contains(nextPos)) {
                visited.add(nextPos);
                queue.offer(new int[]{nextPos, longJumps});
            }

            // 尝试短跳跃 -2，不增加长跳次数
            nextPos = pos - 2;
            if (nextPos >= 0 && !isForbidden(nextPos, forbidden) && !visited.contains(nextPos)) {
                visited.add(nextPos);
                queue.offer(new int[]{nextPos, longJumps});
            }

            // 尝试长跳跃 +k，长跳次数+1
            nextPos = pos + k;
            if (nextPos <= target && !isForbidden(nextPos, forbidden) && !visited.contains(nextPos)) {
                visited.add(nextPos);
                queue.offer(new int[]{nextPos, longJumps + 1});
            }

            // 尝试长跳跃 -k，长跳次数+1
            nextPos = pos - k;
            if (nextPos >= 0 && !isForbidden(nextPos, forbidden) && !visited.contains(nextPos)) {
                visited.add(nextPos);
                queue.offer(new int[]{nextPos, longJumps + 1});
            }
        }

        // 无法到达终点
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        while (T-- > 0) {
            int k = scanner.nextInt();
            int N = scanner.nextInt();
            List<int[]> forbidden = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                int x1 = scanner.nextInt();
                int x2 = scanner.nextInt();
                forbidden.add(new int[]{x1, x2});
            }

            int target = 1000000001;
            int result = minLongJumps(k, forbidden, target);
            System.out.println(result);
        }
        scanner.close();
    }
}