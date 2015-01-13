package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] w = ArrayUtils.createArray(n, 0);
        for (int i = 0; i < n; ++i) {
            w[i] = in.readInt();
        }
        int[] b = ArrayUtils.createArray(m, 0);
        for (int i = 0; i < m; ++i) {
            b[i] = in.readInt() - 1;
        }

        int ans = 0;
        for (int bId = 0; bId < n; ++bId) {
            if (contains(b, bId)) {
                boolean[] used = ArrayUtils.createArray(n, false);
                int add = 0;
                int j = 0;
                while (j < m && b[j] != bId) {
                    if (!used[b[j]]) {
                        used[b[j]] = true;
                        add += w[b[j]];
                    }
                    ++j;
                }
                ans += add;
                ++j;
                while (j < m) {
                    add = 0;
                    Arrays.fill(used, false);
                    while (j < m && b[j] != bId) {
                        if (!used[b[j]]) {
                            used[b[j]] = true;
                            add += w[b[j]];
                        }
                        ++j;
                    }
                    if (j < m)
                        ans += add;
                    ++j;
                }
            }
        }
        out.printLine(ans);
    }

    private boolean contains(int[] b, int bId) {
        for (int i = 0; i < b.length; ++i) {
            if (bId == b[i]) return true;
        }
        return false;
    }
}
