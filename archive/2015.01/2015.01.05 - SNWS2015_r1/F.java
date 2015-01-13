package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class F {
    private static final int UNDEFINED = -1;
    private static final int N = 100005;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = ArrayUtils.createArray(n, 0);
        for (int i = 0; i < n; ++i) {
            a[i] = in.readInt();
        }
        int[] f = ArrayUtils.createArray(n, UNDEFINED);
        int[] g = ArrayUtils.createArray(n, UNDEFINED);
        int[] id = ArrayUtils.createArray(N, UNDEFINED);
        for (int i = 0; i < n; ++i) {
            int m = a[i];
            for (int d = 1; d * d <= m; ++d) {
                if (m % d > 0) continue;
                int k = d;
                if (id[k] != UNDEFINED && g[id[k]] == UNDEFINED) {
                    g[id[k]] = i;
                }
                if (d * d == m) continue;
                k = m / d;
                if (id[k] != UNDEFINED && g[id[k]] == UNDEFINED) {
                    g[id[k]] = i;
                }
            }
            id[m] = i;
        }
        for (int i = 0; i < n; ++i) {
            if (g[i] == UNDEFINED) g[i] = i;
        }
        id = ArrayUtils.createArray(N, UNDEFINED);
        for (int i = n - 1; i >= 0; --i) {
            int m = a[i];
            for (int d = 1; d * d <= m; ++d) {
                if (m % d > 0) continue;
                int k = d;
                if (id[k] != UNDEFINED && f[id[k]] == UNDEFINED) {
                    f[id[k]] = i;
                }
                if (d * d == m) continue;
                k = m / d;
                if (id[k] != UNDEFINED && f[id[k]] == UNDEFINED) {
                    f[id[k]] = i;
                }
            }
            id[m] = i;
        }
        for (int i = 0; i < n; ++i) {
            if (f[i] == UNDEFINED) f[i] = i;
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += a[f[i]] * (long) a[g[i]];
        }
        out.printLine(ans);
    }
}
