package net.spak;

import net.egork.misc.ArrayUtils;

public class BinaryCode {
    public String[] decode(String message) {
        String[] ans = new String[2];
        int n = message.length();
        int[] a = ArrayUtils.createArray(n, 0);
        for (int i = 0; i < n; ++i) {
            a[i] = message.charAt(i) - '0';
        }
        for (int i = 0; i < 2; ++i) {
            boolean ok = true;
            int[] q = ArrayUtils.createArray(n, 0);
            q[0] = i;
            for (int j = 1; j < n; ++j) {
                q[j] = a[j - 1] - q[j - 1];
                if (j > 1) q[j] -= q[j - 2];
                if (q[j] > 1 || q[j] < 0) {
                    ok = false;
                    break;
                }
            }
            int s = q[n - 1];
            if (n > 1) s += q[n - 2];
            if (s != a[n - 1]) {
                ok = false;
            }
            if (ok) {
                StringBuilder b = new StringBuilder();
                for (int j = 0; j < n; ++j) {
                    b.append(q[j]);
                }
                ans[i] = b.toString();
            } else {
                ans[i] = "NONE";
            }
        }
        return ans;
    }
}
