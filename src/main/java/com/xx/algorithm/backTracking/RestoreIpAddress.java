package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 玄霄
 * @CreateDate 2022/11/28
 * <p>
 * 恢复IP地址
 * <p>
 * 输入一个只包含数字的字符串，请列出所有可能恢复出来的IP地址。
 * <p>
 * 例如，输入字符串"10203040"，可能恢复出3个IP地
 * 址，分别为"10.20.30.40"、"102.0.30.40"和"10.203.0.40"。
 * <p>
 * ps:ip地址的范围为0-255
 */
public class RestoreIpAddress implements Answer {
    public static void main(String[] args) {
        new RestoreIpAddress().answerOne();
    }

    private List<List<String>> result = new ArrayList<>();

    /**
     * something
     */
    @Override
    public void answerOne() {
        String dataStr = initData();
        diGui(new ArrayList<>(), dataStr, 0);
        System.out.println(result);
    }

    private void diGui(List<String> temp, String dataStr, int index) {
        if (temp.size() == 4 && index >= dataStr.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (temp.size() > 4 || index >= dataStr.length()) {
            return;
        }
        for (int i = index; i < dataStr.length(); i++) {
            if (whetherIp(dataStr.substring(index, i + 1))) {
                temp.add(dataStr.substring(index, i + 1));
                diGui(temp, dataStr, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean whetherIp(String s) {
        if (s.startsWith("0") && s.length() > 1) {
            return false;
        }
        int num = Integer.parseInt(s);
        return num >= 0 && num <= 255;
    }

    /**
     * something
     */
    @Override
    public String initData() {
        return "10203040";
    }
}
