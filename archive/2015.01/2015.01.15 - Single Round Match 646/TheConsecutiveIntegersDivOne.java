package net.spak;

import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class TheConsecutiveIntegersDivOne {
    public int find(int[] numbers, int k) {
        Arrays.sort(numbers);
        int n = numbers.length;
        int ans = -1;
        for (int start = 0; start + k <= n; ++start) {
            int[] a = Arrays.copyOfRange(numbers, start, start + k);
            int cand = solve(a);
            if (ans == -1 || cand < ans) {
                ans = cand;
            }
        }
        return ans;
    }

    private int solve(int[] a) {
        int[] x = ArrayUtils.createArray(a.length, 0);
        for (int i = 0; i < x.length; ++i) {
            x[i] = a[i] - i;
        }
        int res = -1;
        for (int xx : x) {
            int cand = 0;
            for (int i = 0; i < x.length; ++i) {
                cand += Math.abs(xx + i - a[i]);
            }
            if (res == -1 || cand < res) {
                res = cand;
            }
        }
        return res;
    }
}
