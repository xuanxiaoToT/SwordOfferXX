package com.xx.中大机试;

import com.xx.Answer;
import com.xx.algorithm.dynamicProgram.JumpGameII;
import com.xx.algorithm.dynamicProgram.MinimumNumberOfCoins;
import com.xx.algorithm.dynamicProgram.简单DP.ClimbStairsProblem;

import java.util.*;

/**
 * 1001 青蛙回家
 * <p>
 * 描述
 * 小贝是一只青蛙，它在一条长河上。长河可以看作一条无限长的数轴，在数轴的整点（..., -2, -1, 0, 1, 2, ...）上有荷叶可供小贝停留。
 * 小贝目前在位置 0 的荷叶上，它想跳回它在位置 1000000001 的家。
 * 小贝每次跳跃可以选择短跳跃或长跳跃。短跳跃跳跃的距离为 2，也就是说如果小贝当前位置为 x，
 * 那么一次短跳跃可以让它跳到 x-2 或者 x+2 的位置；长跳跃跳跃的距离为 k，也就是说如果小贝当前位置为 x，那么一次长跳跃可以让它跳到 x-k 或者 x+k 的位置。
 * 另外，虽然每个整点位置上都有荷叶，但是有些河段上的荷叶太小了，并不适合小贝停留，小贝任何一次跳跃都不能跳到这些荷叶上。
 * 进行长跳跃非常消耗体力，而相比而言，短跳跃几乎不费体力，所以小贝希望知道它至少需要经过多少次长跳跃可以到达它家所在的位置。
 * <p>
 * 输入
 * 输入有 T（T <= 200）组数据。每组数据有两行，第一行有两个整数 k 和 N（3 <= k <= 1000000000, 1 <= N <= 25），
 * 分别表示长跳跃的距离和不能被青蛙停留的河段的数量。第二行有 2N 个整数 x1, x2, ..., x2N，
 * 每一对整数 x [2i-1] 和 x [2i]（1 <= i <= N）分别表示一段不适合停留的河段的起点和终点，注意起点和终点也同样不能被停留。
 * 输入保证 1 <= x1 < x2 < ... < x2N <= 1000000000。
 * <p>
 * <p>
 * 输出
 * 对于每组数据，输出一行，包含一个整数，代表青蛙小贝回家的最少长跳跃次数。如果小贝无法回家，则输出-1.
 * <p>
 * 样例输入
 * <p>
 * 3
 * 3 1
 * 1 2
 * 4 1
 * 1 2
 * 19 4
 * 2 17 21 36 40 55 59 74
 * <p>
 * 样例输出
 * 1
 * -1
 * 5
 * <p>
 * 提示
 * 在第一个样例中，一种最优的跳法：0 => 3 -> 5 -> 7 -> ... -> 999999999 -> 1000000001，其中=>表示长跳跃，->表示短跳跃.
 * <p>
 * 类似题目：
 * 动态规划类
 * {@link JumpGameII}
 * {@link ClimbStairsProblem}
 * {@link MinimumNumberOfCoins}
 * <p>
 * bfs类：
 *
 */
public class FrogGoesHome implements Answer {

    public static void main(String[] args) {
        new FrogGoesHome().answer();
    }


    @Override
    public void answer() {
        // int k = 3;
        // int n = 1;
        // int[][] notAllowedStop = {{1, 2}};

        // int k = 4;
        // int n = 1;
        // int[][] notAllowedStop = {{1, 2}};

        int k = 19;
        int n = 4;
        int[][] notAllowedStop = {{2, 17}, {21, 36}, {40, 55}, {59, 74}};
        // System.out.println(computeResult(k, n, notAllowedStop));
        System.out.println(computeResult2(k, n, Arrays.stream(notAllowedStop).toList()));
    }

    /**
     * dp法。
     * 缺点：maxRight仍然会很大，其最大值为10^9
     * 此时遍历仍旧很耗时
     */
    public int computeResult(int k, int n, int[][] notAllowedStop) {
        int mod = 998_244_353;
        int result = 0;
        if (k % 2 == 0) {
            return -1;
        }
        int maxRight = notAllowedStop[notAllowedStop.length - 1][1];
        Set<Integer> noAllowedSet = new HashSet<>();
        for (int[] point : notAllowedStop) {
            int left = point[0];
            int right = point[1];
            if (right - left >= k) {
                return -1;
            }
            for (int i = left; i <= right; i++) {
                noAllowedSet.add(i);
            }
        }
        // 优化 改到maxRight
        Long[] dp = new Long[maxRight + 2 * k];
        for (int i = k; i < dp.length; i++) {
            if ((i - k) % 2 == 0 && !noAllowedSet.contains(i)) {
                dp[i] = 0L;
            } else {
                dp[i] = null;
            }
            if ((i - k) > maxRight) {
                break;
            }
        }
        for (int i = -1 * k; i < 0; i++) {
            if (i % 2 == 0) {
                dp[i + k] = 0L;
            } else {
                dp[i + k] = null;
            }
        }

        for (int i = k; i < dp.length; i++) {
            int realIndex = i - k;
            if (noAllowedSet.contains(realIndex)) {
                dp[i] = null;
            } else {
                if (dp[i - 2] == null && dp[i - k] == null) {
                    dp[i] = null;
                    continue;
                }
                if (dp[i - k] == null) {
                    dp[i] = dp[i - 2];
                } else {
                    if (dp[i - 2] == null) {
                        dp[i] = dp[i - k] + 1;
                    } else {
                        dp[i] = Math.min(dp[i - 2], dp[i - k] + 1);
                    }
                }
            }
            if (realIndex > maxRight && realIndex % 2 == 1) {
                return Math.toIntExact(dp[i]) % mod;
            }
        }
        return -1;
    }

    /**
     * bfs
     * 本质就是图的最短路径
     */
    public int computeResult2(int k, int n, List<int[]> notAllowedStop) {

        int mod = 998_244_353;
        if (k % 2 == 0) {
            return -1;
        }

        // 将notAllowedStop转换为allowed
        List<long[]> allowedIntervals = generateAllowedIntervals(notAllowedStop, k);
        if (allowedIntervals == null) {
            return -1;
        }

        long result = bfsSolve(k, allowedIntervals);
        return Math.toIntExact(result % mod);
    }

    /**
     * 生成允许区间列表（禁止区间之外的连续区域）
     */
    private List<long[]> generateAllowedIntervals(List<int[]> notAllowedStop, int k) {
        List<long[]> allowed = new ArrayList<>();

        // 第一个允许区间：负无穷 到 第一个禁止区间左边界-1
        long firstRight = notAllowedStop.get(0)[0] - 1;
        allowed.add(new long[]{Long.MIN_VALUE, firstRight});

        // 中间允许区间：两个禁止区间之间的区域（过滤空区间）
        for (int i = 0; i < notAllowedStop.size() - 1; i++) {
            if (notAllowedStop.get(i)[1] - notAllowedStop.get(i)[0] >= k) {
                return null;
            }
            long left = notAllowedStop.get(i)[1] + 1;
            long right = notAllowedStop.get(i + 1)[0] - 1;
            if (left <= right) {
                allowed.add(new long[]{left, right});
            }
        }

        // 最后一个允许区间：最后一个禁止区间右边界+1 到 正无穷
        long lastLeft = notAllowedStop.get(notAllowedStop.size() - 1)[1] + 1;
        allowed.add(new long[]{lastLeft, Long.MAX_VALUE});

        return allowed;
    }

    /**
     * 纯BFS求解核心逻辑
     *
     * @param k       长跳距离
     * @param allowed 允许区间列表
     * @return 最少长跳次数，无法到达返回-1
     */
    private long bfsSolve(int k, List<long[]> allowed) {
        int pointSize = allowed.size();  // 允许区间的数量
        // dist[i][p]：到达第i个区间、奇偶性p的最少长跳次数，初始为-1（未访问）
        long[][] dist = new long[pointSize][2];
        for (int i = 0; i < pointSize; i++) {
            Arrays.fill(dist[i], -1);
        }

        // BFS队列：存储状态（区间索引+奇偶性）
        Queue<State> queue = new LinkedList<>();
        // 起点状态：第0个区间（最左侧）、奇偶性0（偶数，因为起点是0）
        State startState = new State(0, 0);
        dist[0][0] = 0;  // 起点步数为0

        queue.offer(startState);

        // 目标：最后一个区间（最右侧）、奇偶性1（奇数，因为终点是1e9+1）
        int targetInterval = pointSize - 1;
        int targetParity = 1;

        // BFS主循环
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int currIdx = curr.intervalIdx;
            int currP = curr.parity;
            long currStep = dist[currIdx][currP];

            // 提前终止：首次到达目标状态，直接返回（BFS保证首次是最优解）
            if (currIdx == targetInterval && currP == targetParity) {
                return currStep;
            }

            // 遍历所有可能的目标区间，尝试转移
            for (int nextIdx = 0; nextIdx < pointSize; nextIdx++) {
                // if (nextIdx == currIdx) {
                //     continue;
                // }
                int nextP = currP ^ 1;  // 长跳翻转奇偶性
                // 检查：当前区间 -> 目标区间 是否可通过一次长跳到达
                if (canJump(allowed.get(currIdx), allowed.get(nextIdx), k, currP, nextP)) {
                    // 未访问过的状态，更新步数并加入队列
                    if (dist[nextIdx][nextP] == -1) {
                        dist[nextIdx][nextP] = currStep + 1;
                        queue.offer(new State(nextIdx, nextP));
                    }
                }
            }
        }

        // 遍历完所有状态仍未到达目标，返回-1
        return -1;
    }

    /**
     * 找到区间[L, R]中第一个奇偶性为p的数
     * 如果不存在，返回null
     */
    private Long findFirstWithParity(long L, long R, int p) {
        if (L > R)
            return L;
        for (long i = L; i <= R; i++) {
            if (Math.abs(i) % 2 == p) {
                return i;
            }
        }
        return null;
    }

    /**
     * 检查两个区间 [L1, R1] 和 [L2, R2] 是否相交
     */
    private boolean intervalsOverlap(long L1, long R1, long L2, long R2) {
        return !(R1 < L2 || R2 < L1);
    }

    /**
     * 找到区间[L, R]中最后一个奇偶性为p的数
     * 如果不存在，返回L-1
     */
    private Long findLastWithParity(long L, long R, int p) {
        if (L > R)
            return R;
        for (long i = R; i >= L; i--) {
            if (Math.abs(i) % 2 == p) {
                return i;
            }
        }
        return null;
    }

    /**
     * 判断从区间curr、奇偶性p，能否通过一次长跳到达区间next，且奇偶性颠倒
     *
     * @param curr 当前所在区间
     * @param next 目标区间
     * @param k    长跳距离
     * @param p    当前奇偶性
     *
     */
    private boolean canJump(long[] curr, long[] next, long k, int p, int nextP) {
        Long firstWithParity = findFirstWithParity(next[0], next[1], nextP);
        if (firstWithParity == null) {
            return false;
        }

        // 步骤1：找到区间A中奇偶性为p的点的范围 [currentLeft, currentRight]
        Long currentLeft = findFirstWithParity(curr[0], curr[1], p);
        if (currentLeft == null) {
            // 区间A中没有奇偶性为p的点
            return false;
        }
        long currentRight = findLastWithParity(curr[0], curr[1], p);

        // 情况1：向右跳 +k，检查落点范围 [currentLeft + k, currentRight + k] 是否与B相交
        long jumpRight_L = currentLeft + k;
        long jumpRight_R = currentRight + k;
        if (intervalsOverlap(jumpRight_L, jumpRight_R, next[0], next[1])) {
            return true;
        }

        // 情况2：向左跳 -k，检查落点范围 [currentLeft - k, currentRight - k] 是否与B相交
        long jumpLeft_L = currentLeft - k;
        long jumpLeft_R = currentRight - k;
        if (intervalsOverlap(jumpLeft_L, jumpLeft_R, next[0], next[1])) {
            return true;
        }

        return false;
    }


    @Override
    public Object initData() {
        return null;
    }

    static class State {
        int intervalIdx;  // 允许区间的索引
        int parity;        // 0=偶数，1=奇数

        public State(int intervalIdx, int parity) {
            this.intervalIdx = intervalIdx;
            this.parity = parity;
        }
    }
}
