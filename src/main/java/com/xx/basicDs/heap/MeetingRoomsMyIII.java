package com.xx.basicDs.heap;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 会议室 III
 * LeetCode 2402. Hard
 * <p>
 * 给你一个整数 n ，共有编号从 0 到 n - 1 的 n 个会议室。
 * 给你一个二维整数数组 meetings ，其中 meetings[i] = [starti, endi] 表示一场会议将会在 半闭 时间区间 [starti, endi) 举办。
 * 所有 starti 的值 互不相同 。
 * <p>
 * 会议将会按以下方式分配给会议室：
 * 每场会议都会在未占用且编号 最小 的会议室举办。
 * 如果没有可用的会议室，会议将会延期，直到存在空闲的会议室。延期会议的持续时间和原会议持续时间 相同 。
 * 当会议室处于未占用状态时，将会优先提供给原 开始 时间更早的会议。
 * 返回举办最多次会议的房间 编号 。如果存在多个房间满足此条件，则返回编号 最小 的房间。
 * <p>
 * 半闭区间 [a, b) 是 a 和 b 之间的区间，包括 a 但 不包括 b 。
 * <p>
 * 示例 1：
 * 输入：n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
 * 输出：0
 * 解释：
 * - 在时间 0 ，两个会议室都未占用，第一场会议在会议室 0 举办。
 * - 在时间 1 ，只有会议室 1 未占用，第二场会议在会议室 1 举办。
 * - 在时间 2 ，两个会议室都被占用，第三场会议延期举办。
 * - 在时间 3 ，两个会议室都被占用，第四场会议延期举办。
 * - 在时间 5 ，会议室 1 的会议结束。第三场会议在会议室 1 举办，时间周期为 [5,10) 。
 * - 在时间 10 ，两个会议室的会议都结束。第四场会议在会议室 0 举办，时间周期为 [10,11) 。
 * 会议室 0 和会议室 1 都举办了 2 场会议，所以返回 0 。
 * <p>
 * 示例 2：
 * 输入：n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 * 输出：1
 * 解释：
 * - 在时间 1 ，所有三个会议室都未占用，第一场会议在会议室 0 举办。
 * - 在时间 2 ，会议室 1 和 2 未占用，第二场会议在会议室 1 举办。
 * - 在时间 3 ，只有会议室 2 未占用，第三场会议在会议室 2 举办。
 * - 在时间 4 ，所有三个会议室都被占用，第四场会议延期举办。
 * - 在时间 5 ，会议室 2 的会议结束。第四场会议在会议室 2 举办，时间周期为 [5,10) 。
 * - 在时间 6 ，所有三个会议室都被占用，第五场会议延期举办。
 * - 在时间 10 ，会议室 1 和 2 的会议结束。第五场会议在会议室 1 举办，时间周期为 [10,12) 。
 * 会议室 1 和会议室 2 都举办了 2 场会议，所以返回 1 。
 * <p>
 * 提示：
 * 1 <= n <= 100
 * 1 <= meetings.length <= 105
 * meetings[i].length == 2
 * 0 <= starti < endi <= 5 * 105
 * starti 的所有值 互不相同
 * <p>
 * 反复提交多次的问题：
 * 第一次提交：题目没读清楚，会议不是一开始就非要把N个全占满的，开始时间需要比较。所以size != n的逻辑需要重写。
 * 第二次提交：不够细心，人家都说了是开区间，我还是用的<而不是<=.
 * 第三次提交：count求最大值的逻辑重构，提了一个全局变量，这个在写之前就应该考虑清楚。
 * 第四次提交：数据整理了半天，才反应过来不能用int，count得用long型，应该在看到那些数据的一瞬间就反应过来才对。
 * <p>
 * 注意的事项：读题读清楚了再开始；条件限制一定要想明白。
 * <p>
 * Tag：区间排序
 */
public class MeetingRoomsMyIII implements Answer {


    int maxVal = 1;
    int maxIndex = 0;

    public static void main(String[] args) {
        new MeetingRoomsMyIII().answer();
    }

    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparing(dto -> dto[0]));
        // 可以换成数组
        TreeMap<Integer, countDto> treeMap = new TreeMap<>();

        for (int[] meeting : meetings) {
            int startTime = meeting[0];
            int endTime = meeting[1];
            int size = treeMap.size();

            if (size != n) {
                int indexNotFull = computeIndexNotFull(treeMap, startTime);
                if (treeMap.containsKey(indexNotFull)) {
                    countDto countDto = treeMap.get(indexNotFull);
                    countDto.count++;
                    countDto.maxRight = (long) endTime;
                    computeMax(countDto.count, indexNotFull);
                } else {
                    treeMap.put(indexNotFull, new countDto(1, (long) endTime));
                }
            } else {
                // 此会议需要延期
                int index = computeMinTime(treeMap, startTime);
                countDto countDto = treeMap.get(index);
                countDto.count++;
                if (startTime >= countDto.maxRight) {
                    countDto.maxRight = (long) endTime;
                } else {
                    countDto.maxRight = countDto.maxRight - startTime + endTime;
                }
                computeMax(countDto.count, index);
            }
        }
        return maxIndex;
    }

    private void computeMax(int count, int index) {
        if (count > maxVal) {
            maxVal = count;
            maxIndex = index;
        }
        if (count == maxVal) {
            maxIndex = Math.min(maxIndex, index);
        }
    }

    private int computeIndexNotFull(TreeMap<Integer, countDto> treeMap, int startTime) {
        for (Map.Entry<Integer, countDto> entry : treeMap.entrySet()) {
            if (startTime >= entry.getValue().maxRight) {
                return entry.getKey();
            }
        }
        return treeMap.size();
    }

    /**
     * 获取最小编号：可以使用小根堆？也就是maxRight最小的那个
     */
    private int computeMinTime(TreeMap<Integer, countDto> treeMap, int startTime) {
        Long min = Long.MAX_VALUE;
        int result = 0;
        for (Map.Entry<Integer, countDto> entry : treeMap.entrySet()) {
            if (startTime >= entry.getValue().maxRight) {
                return entry.getKey();
            }
            if (entry.getValue().maxRight < min) {
                result = entry.getKey();
                min = entry.getValue().maxRight;
            }
        }
        return result;
    }

    @Override
    public void answer() {
        // int n = 2;
        // int[][] meetings = new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}};

        // int n = 3;
        // int[][] meetings = new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}};

        // int n = 4;
        // int[][] meetings = new int[][]{{18, 19}, {3, 12}, {17, 19}, {2, 13}, {7, 10}};

        // int n = 4;
        // int[][] meetings = new int[][]{{19, 20}, {14, 15}, {13, 14}, {11, 20}};

        int n = 10;
        int[][] meetings = new int[][]{{1, 300001}, {2, 300002}, {3, 300003}, {4, 300004}, {5, 300005}, {6, 300006}, {7, 300007}, {8, 300008}, {9, 300009}, {10, 300010}, {11, 300011}, {12, 300012}, {13, 300013}, {14, 300014}, {15, 300015}, {16, 300016}, {17, 300017}, {18, 300018}, {19, 300019}, {20, 300020}, {21, 300021}, {22, 300022}, {23, 300023}, {24, 300024}, {25, 300025}, {26, 300026}, {27, 300027}, {28, 300028}, {29, 300029}, {30, 300030}, {31, 300031}, {32, 300032}, {33, 300033}, {34, 300034}, {35, 300035}, {36, 300036}, {37, 300037}, {38, 300038}, {39, 300039}, {40, 300040}, {41, 300041}, {42, 300042}, {43, 300043}, {44, 300044}, {45, 300045}, {46, 300046}, {47, 300047}, {48, 300048}, {49, 300049}, {50, 300050}, {51, 300051}, {52, 300052}, {53, 300053}, {54, 300054}, {55, 300055}, {56, 300056}, {57, 300057}, {58, 300058}, {59, 300059}, {60, 300060}, {61, 300061}, {62, 300062}, {63, 300063}, {64, 300064}, {65, 300065}, {66, 300066}, {67, 300067}, {68, 300068}, {69, 300069}, {70, 300070}, {71, 300071}, {72, 300072}, {73, 300073}, {74, 300074}, {75, 300075}, {76, 300076}, {77, 300077}, {78, 300078}, {79, 300079}, {80, 300080}, {81, 300081}, {82, 300082}, {83, 300083}, {84, 300084}, {85, 300085}, {86, 300086}, {87, 300087}, {88, 300088}, {89, 300089}, {90, 300090}, {91, 300091}, {92, 300092}, {93, 300093}, {94, 300094}, {95, 300095}, {96, 300096}, {97, 300097}, {98, 300098}, {99, 300099}, {100, 300100}, {101, 300101}, {102, 300102}};

        System.out.println(mostBooked(n, meetings));
    }

    @Override
    public Object initData() {
        return null;
    }

    private static class countDto {
        /**
         * 当前会议室使用次数
         */
        public int count;
        /**
         * 会议室结束的最大时间
         */
        public Long maxRight;

        public countDto(int count, Long maxRight) {
            this.count = count;
            this.maxRight = maxRight;
        }
    }
}
