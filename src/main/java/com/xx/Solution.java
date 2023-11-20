package com.xx;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/5
 */
public class Solution {

    public static void main(String[] args) {
        new Solution().answer();
    }

    public void answer() {


        //System.out.println(maximumXorProduct(1, 6, 3));
    }

    public int maximumXorProduct(long a, long b, int n) {
        int yu = 1000000000;
        String aBin = Long.toBinaryString(a);
        String bBin = Long.toBinaryString(b);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int aIndex = aBin.length() - n + i;
            char ai = aIndex >= 0 ? aBin.charAt(aIndex) : '0';
            int bIndex = bBin.length() - n + i;
            char bi = bIndex >= 0 ? bBin.charAt(bIndex) : '0';
            if (ai == '0' && bi == '0') {
                sb.append('1');
            }
            if (ai == '1' && bi == '1') {
                sb.append('0');
            }
            if (ai == '0' && bi == '1' || ai == '1' && bi == '0') {
                sb.append('1');
            }
        }
        long x = Long.parseLong(sb.toString(), 2);
        return (int) (((a ^ x) * (b ^ x)) % yu);
    }




}
