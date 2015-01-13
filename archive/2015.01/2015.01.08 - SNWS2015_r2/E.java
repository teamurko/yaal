package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class E {
    private static final long INF = (long) 1e9;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] m = ArrayUtils.createArray(n, 0);
        for (int i = 0; i < n; ++i) {
            m[i] = in.readInt();
        }
        long[] s = ArrayUtils.partialSums(m);
        long[] d = ArrayUtils.createArray(n + 1, INF);
        d[n] = 0;
        for (int k = n - 1; k >= 0; --k) {
            long add = 0;
            for (int i = k + 1; i <= n; ++i) {
                long cand = d[i];
                add += s[i - 1] - s[k];
                cand += s[n] - s[i] + add;
                d[k] = Math.min(d[k], cand);
            }
        }
        out.printLine(d[0]);
    }
}
