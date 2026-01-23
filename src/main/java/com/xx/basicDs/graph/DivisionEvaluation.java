package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/4
 * <p>
 * 除法求值
 * LeetCode 399  Medium
 * <p>
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
 * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，
 * 请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * 如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * 示例 1：
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * <p>
 * 示例 2：
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * <p>
 * 示例 3：
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * 思路：
 * 这个问题是关于两个变量之间的除法，因此可以将变量看作图中的节点。
 * 如果存在两个变量的除法等式，那么这两个变量对应的节点之间有一条边相连。一个除法等式除了被除
 * 数和除数，还有商。被除数和除数都对应图中的节点，商是两个变量的除法的结果，表达的是变量之间的关系，
 * 因此商应该是边的属性。可以给图中的每条边定义一个权重，为两个变量的除法的商。由于a/b一般不等于b/a，
 * 因此从节点a到节点b的边和从节点b到节点a的边的权重不同，即这个图是有向图，
 * 节点a和节点b之间有两条不同方向的有向边。
 */
public class DivisionEvaluation implements Answer {

    public static void main(String[] args) {
        new DivisionEvaluation().answer();
    }

    /**
     * 解1：采用深度优先遍历
     */
    @Override
    public void answer() {
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"),
                Arrays.asList("a", "a"), Arrays.asList("x", "x"));
        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 构造图
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String up = query.get(0);
            String down = query.get(1);
            if (graph.containsKey(up) && graph.containsKey(down)) {
                if (up.equals(down)) {
                    result[i] = 1.0;
                } else {
                    Double dfsValue = myDfs(graph, up, down, new HashSet<>(), 1.0);
                    result[i] = dfsValue;
                }
            } else {
                result[i] = -1.0;
            }
        }
        return result;
    }

    private Double myDfs(Map<String, Map<String, Double>> graph, String up, String down, HashSet<String> visited, Double temp) {
        if (visited.contains(up)) {
            return -1.0;
        }
        visited.add(up);
        Map<String, Double> stringDoubleMap = graph.get(up);
        for (Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {
            String next = entry.getKey();
            Double value = entry.getValue();
            if (down.equals(next)) {
                return temp * value;
            } else {
                Double aDouble = myDfs(graph, next, down, visited, temp * value);
                if (aDouble > 0) {
                    return aDouble;
                }
            }
        }
        visited.remove(up);
        return -1.0;
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String start = equation.get(0);
            String end = equation.get(1);
            if (!map.containsKey(start)) {
                Map<String, Double> temp = new HashMap<>();
                temp.put(end, values[i]);
                map.put(start, temp);
            } else {
                Map<String, Double> temp = map.get(start);
                temp.put(end, values[i]);
                map.put(start, temp);
            }
            if (!map.containsKey(end)) {
                Map<String, Double> temp = new HashMap<>();
                temp.put(start, 1 / values[i]);
                map.put(end, temp);
            } else {
                Map<String, Double> temp = map.get(end);
                temp.put(start, 1 / values[i]);
                map.put(end, temp);
            }
        }
        return map;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }


}
