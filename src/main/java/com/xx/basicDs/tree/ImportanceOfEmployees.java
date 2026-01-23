package com.xx.basicDs.tree;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/26
 * <p>
 * 员工的重要性
 * LeetCode 690 Medium
 * <p>
 * 你有一个保存员工信息的数据结构，它包含了员工唯一的 id ，重要度和直系下属的 id 。
 * 给定一个员工数组 employees，其中：
 * employees[i].id 是第 i 个员工的 ID。
 * employees[i].importance 是第 i 个员工的重要度。
 * employees[i].subordinates 是第 i 名员工的直接下属的 ID 列表。
 * 给定一个整数 id 表示一个员工的 ID，返回这个员工和他所有下属的重要度的 总和。
 * <p>
 * 示例 1：
 * 输入：employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
 * 输出：11
 * 解释：
 * 员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。因此员工 1 的总重要度是 5 + 3 + 3 = 11 。
 * <p>
 * 示例 2：
 * 输入：employees = [[1,2,[5]],[5,-3,[]]], id = 5
 * 输出：-3
 * 解释：员工 5 的重要度为 -3 并且没有直接下属。
 * 因此，员工 5 的总重要度为 -3。
 * <p>
 * 提示：
 * 1 <= employees.length <= 2000
 * 1 <= employees[i].id <= 2000
 * 所有的 employees[i].id 互不相同。
 * -100 <= employees[i].importance <= 100
 * 一名员工最多有一名直接领导，并可能有多名下属。
 * employees[i].subordinates 中的 ID 都有效。
 *
 * Tag:行序遍历  BFS
 */
public class ImportanceOfEmployees implements Answer {

    public static void main(String[] args) {
        new ImportanceOfEmployees().answer();
    }

    @Override
    public void answer() {
        Employee employee1 = new Employee(1, 5);
        Employee employee2 = new Employee(2, 3);
        Employee employee3 = new Employee(3, 3);
        List<Integer> subordinates1 = Arrays.asList(employee2.id, employee3.id);
        employee1.subordinates = subordinates1;
        List<Employee> employeeList = Arrays.asList(employee1, employee2, employee3);
        System.out.println(getImportance(employeeList, 1));
    }

    /**
     * BFS简单做
     */
    public int getImportance(List<Employee> employees, int id) {
        int result = 0;
        Map<Integer, List<Integer>> treeMap = new HashMap<>(employees.size());
        Map<Integer, Employee> selfMap = new HashMap<>(employees.size());
        Employee start = null;
        for (Employee employee : employees) {
            treeMap.put(employee.id, employee.subordinates);
            selfMap.put(employee.id, employee);
        }
        if (selfMap.get(id) == null) {
            return 0;
        }
        if (treeMap.get(id) == null) {
            return selfMap.get(id).importance;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                result += selfMap.get(poll).importance;
                List<Integer> employeeList = treeMap.get(poll);
                if (employeeList != null) {
                    queue.addAll(employeeList);
                }
            }
        }
        return result;
    }

    public static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance) {
            this.id = id;
            this.importance = importance;
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}