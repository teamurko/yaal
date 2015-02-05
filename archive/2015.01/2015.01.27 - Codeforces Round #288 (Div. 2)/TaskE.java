package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    private static final int UNDEFINED = -1;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] l = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; ++i) {
            l[i] = in.readInt();
            r[i] = in.readInt();
        }
        boolean[][] d = new boolean[n + 1][];
        for (int i = 0; i <= n; ++i) {
            d[i] = ArrayUtils.createArray(n + 1, true);
            if (i < n) {
                d[i][i] = l[i] <= 1 && r[i] >= 1;
            }
        }
        int[][] next = new int[n + 1][];
        for (int i = 0; i <= n; ++i) {
            next[i] = ArrayUtils.createArray(n + 1, UNDEFINED);
        }
        for (int len = 2; len <= n; ++len) {
            for (int start = 0; start + len <= n; ++start) {
                d[start][start + len - 1] = false;
                for (int i = 0; i < len; ++i) {
                    if (l[start] <= 1 + 2 * i && 1 + 2 * i <= r[start]) {
                        d[start][start + len - 1] |= d[start + 1][start + i] && d[start + i + 1][start + len - 1];
                        if (d[start][start + len - 1]) {
                            next[start][start + len - 1] = i;
                            break;
                        }
                    }
                }
            }
        }
        if (!d[0][n - 1]) {
            out.printLine("IMPOSSIBLE");
            return;
        }
        print(out, d, next, 0, n - 1);
    }

    private void print(OutputWriter out, boolean[][] d, int[][] next, int l, int r) {
        if (l > r) return;
        if (l == r) {
            out.print("()");
            return;
        }
        assert(next[l][r] != UNDEFINED);
        int i = next[l][r];
        out.print("(");
        print(out, d, next, l + 1, l + i);
        out.print(")");
        print(out, d, next, l + i + 1, r);
    }
}
