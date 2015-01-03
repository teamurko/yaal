package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class EvenOddQuery {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int a[] = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = in.readInt();
        }
        int numQueries = in.readInt();
        for (int queryIndex = 0; queryIndex < numQueries; ++queryIndex) {
            int x = in.readInt();
            int y = in.readInt();
            --x;
            --y;
            out.printLine(solve(a, x, y));
        }
    }

    private String solve(int[] a, int x, int y) {
        if (x == y) {
            if (a[x] % 2 == 0) {
                return "Even";
            }
            return "Odd";
        }
        if (a[x] == 0) {
            return "Even";
        }
        if (a[x] % 2 == 0 && a[x + 1] != 0) {
            return "Even";
        }
        return "Odd";
    }
}
